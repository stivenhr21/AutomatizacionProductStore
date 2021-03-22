package utilities.exceldata;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * Ingresa los datos obtenidos del archivo de Excel al archivo feature del cual se está llamando
 *
 * @author Mateo Ortiz Bedoya
 * @since 01 /11/2019
 */
public class DatosFeature {

    private DatosFeature() {
    }

    /**
     * Ingresa los datos obtenidos de un excel al archivo .feature del cual se está llamando, hace que
     * se genere la tabla en el escenario Outline como Data Table
     *
     * @param featureFile Nombre del archivo .feature el cual se modificará, debe tener la ruta del
     *                    archivo y la hoja ser usada
     * @return
     * @author Mateo Ortiz Bedoya
     * @since 01 /11/2019
     */
    private static List<String> setExcelDataToFeature(File featureFile)
            throws InvalidFormatException, IOException {
        List<String> fileData = new ArrayList<>();
        try (BufferedReader buffReader =
                     new BufferedReader(
                             new InputStreamReader(
                                     new BufferedInputStream(new FileInputStream(featureFile)),
                                     StandardCharsets.UTF_8))) {
            String data;
            String idCaso = null;
            List<Map<String, String>> excelData = null;
            boolean foundHashTag = false;
            boolean featureData = false;
            while ((data = buffReader.readLine()) != null) {
                String sheetName = null;
                String excelFilePath = null;

                if (data.trim().contains("@TestCase")) {
                    idCaso = data.trim();
                    idCaso = idCaso.substring(9);
                }

                if (data.trim().contains("##@externaldata")) {
                    excelFilePath =
                            data.substring(StringUtils.ordinalIndexOf(data, "@", 2) + 1, data.lastIndexOf('@'));
                    sheetName = data.substring(data.lastIndexOf('@') + 1);
                    foundHashTag = true;
                    fileData.add(data);
                }
                if (foundHashTag) {
                    excelData = new LectorExcel().getData(excelFilePath, sheetName, idCaso);
                    for (int rowNumber = 0; rowNumber < excelData.size() - 1; rowNumber++) {
                        StringBuilder cellDataBuilder = new StringBuilder("\t");
                        for (Entry<String, String> mapData : excelData.get(rowNumber).entrySet()) {
                            cellDataBuilder.append("|").append(mapData.getValue());
                        }
                        String cellData = cellDataBuilder.toString();
                        cellData += "|";
                        fileData.add(cellData);
                    }
                    foundHashTag = false;
                    featureData = true;
                    continue;
                }
                if (data.startsWith("|") || data.endsWith("|")) {
                    if (featureData) {
                        continue;
                    } else {
                        fileData.add(data);
                        continue;
                    }
                } else {
                    featureData = false;
                }
                fileData.add(data);
            }
        }
        return fileData;
    }

    /**
     * Lista de todos los features con sus respectivos archivo de excel que se usarán en la prueba
     *
     * @param folder Carpeta donde estarán los archivo .feature
     * @return lista de Features
     * @author Mateo Ortiz Bedoya
     * @since 01/11/2019
     */
    private static List<File> listOfFeatureFiles(File folder) {
        List<File> featureFiles = new ArrayList<>();
        for (File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                featureFiles.addAll(listOfFeatureFiles(fileEntry));
            } else {
                if (fileEntry.isFile() && fileEntry.getName().endsWith(".feature")) {
                    featureFiles.add(fileEntry);
                }
            }
        }
        return featureFiles;
    }

    /**
     * Hace una lista con todos los features dependiendo de la ruta asignada
     *
     * @param featuresDirectoryPath Ruta donde se encuentran los features que tendrán las tablas
     * @throws IOException            the io exception
     * @throws InvalidFormatException the invalid format exception
     * @author Mateo Ortiz Bedoya
     * @since 01/11/2019
     */
    public static void overrideFeatureFiles(String featuresDirectoryPath)
    // public void overrideFeatureFiles(String featuresDirectoryPath)
            throws IOException, InvalidFormatException {
        List<File> listOfFeatureFiles = listOfFeatureFiles(new File(featuresDirectoryPath));
        for (File featureFile : listOfFeatureFiles) {
            List<String> featureWithExcelData = setExcelDataToFeature(featureFile);
            try (BufferedWriter writer =
                         new BufferedWriter(
                                 new OutputStreamWriter(new FileOutputStream(featureFile), StandardCharsets.UTF_8))) {
                for (String string : featureWithExcelData) {
                    writer.write(string);
                    writer.write("\n");
                }
            }
        }
    }
}

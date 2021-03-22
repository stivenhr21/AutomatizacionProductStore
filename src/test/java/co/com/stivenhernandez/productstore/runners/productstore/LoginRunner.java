package co.com.stivenhernandez.productstore.runners.productstore;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.runner.RunWith;
import utilities.exceldata.BeforeRunner;
import utilities.exceldata.DatosFeature;
import utilities.runner.ProductStoreRunner;

import java.io.IOException;

@CucumberOptions(
    features = "src/test/resources/productstore/features/productstore/productstore.feature",
    snippets = SnippetType.CAMELCASE,
    glue = {"co.com.stivenhernandez.productstore.stepdefinitions.productstore"},
    plugin = {"json:target/cucumber_json/cucumber.json"})

@RunWith(ProductStoreRunner.class)

public class LoginRunner {
    @BeforeRunner
    public static void test() throws InvalidFormatException, IOException {
        DatosFeature.overrideFeatureFiles(
                "./src/test/resources/productstore/features/productstore");
    }
}

package utilities.runner;

import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import utilities.exceldata.BeforeRunner;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * personalizacion_del_runner_con el cual puede ir a buscar un archivo de excel
 * y modificar los ejemplos del feature.
 *
 * los datos ingresados en el documento de excel sera modificados en el archivo feature seleccionado
 *  @actor Mateo ortiz bedoya
 *  @sice 01/11/2019
 * */
public class ProductStoreRunner extends Runner {
    private static final Logger LOGGER = LogManager.getLogger(ProductStoreRunner.class.getName());
    private Class<CucumberWithSerenity> classValue;
    private CucumberWithSerenity cucumberWithSerenity;

    public ProductStoreRunner(Class<CucumberWithSerenity> classValue)
            throws IOException, InitializationError {
        this.classValue = classValue;
        cucumberWithSerenity = new CucumberWithSerenity(classValue);
    }

    @Override
    public Description getDescription() {
        return cucumberWithSerenity.getDescription();
    }

    private void runAnnotatedMethods(Class<?> annotation)
            throws IllegalAccessException, InvocationTargetException {
        if (!annotation.isAnnotation()) {
            return;
        }
        Method[] methods = this.classValue.getMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation item : annotations) {
                if (item.annotationType().equals(annotation)) {
                    method.invoke(null);
                    break;
                }
            }
        }
    }

    @Override
    public void run(RunNotifier notifier) {
        try {
            runAnnotatedMethods(BeforeRunner.class);
            cucumberWithSerenity = new CucumberWithSerenity(classValue);
        } catch (IllegalAccessException
                | InvocationTargetException
                | IOException
                | InitializationError e) {
            LOGGER.error(e.getMessage(), e);
        }
        cucumberWithSerenity.run(notifier);
    }
}

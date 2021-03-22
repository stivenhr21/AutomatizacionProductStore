package co.com.stivenhernandez.productstore.task.opensite;

import co.com.stivenhernandez.productstore.userinterface.productstore.ProductStorePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import utilities.properties.PropertiesProject;

import java.io.IOException;

import static net.serenitybdd.core.pages.PageObject.withParameters;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenSiteProductStoreTask implements Task {
    private ProductStorePage page = new ProductStorePage();

    @Override
    public <T extends Actor> void performAs(T actor) {
        String strUrl = "";
        try {
            strUrl = PropertiesProject.getUrl();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!strUrl.isEmpty() && !strUrl.equals("")) {
            page.open("open.ProductStore", withParameters(strUrl));
        }
    }

    public static OpenSiteProductStoreTask openSiteProductStoreTask() {
        return instrumented(OpenSiteProductStoreTask.class);
    }
}

package co.com.stivenhernandez.productstore.userinterface.productstore;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.NamedUrl;
import net.thucydides.core.annotations.NamedUrls;

@NamedUrls(
        {
                @NamedUrl(name = "open.ProductStore", url = "http://{1}"),
        })

public class ProductStorePage extends PageObject {

    public static final Target BTN_INICIAR_SESION_HEAD =
            Target.the("Boton de cabecera para mostrar inputs de credenciales").locatedBy("//a[@id='login2']");

    public static final Target LABEL_USUARIO =
            Target.the("Label para copiar el usuario").locatedBy("//input[@id='loginusername']");

    public static final Target LABEL_CLAVE =
            Target.the("Label para copiar la clave").locatedBy("//input[@id='loginpassword']");

    public static final Target BTN_LOGIN =
            Target.the("Boton para iniciar sesion").locatedBy("//button[contains(text(),'Log in')]");
}

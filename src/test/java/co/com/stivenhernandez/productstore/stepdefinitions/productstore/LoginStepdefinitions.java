package co.com.stivenhernandez.productstore.stepdefinitions.productstore;

import co.com.stivenhernandez.productstore.models.builder.DatosUsuarioBuilder;
import co.com.stivenhernandez.productstore.task.opensite.OpenSiteProductStoreTask;
import cucumber.api.java.Before;
import cucumber.api.java.es.Dado;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.List;

import static co.com.stivenhernandez.productstore.task.productstore.LoginTask.login;
import static net.serenitybdd.screenplay.actors.OnStage.*;

public class LoginStepdefinitions {
    @Before
    public void initialConfiguration() {
        setTheStage(new OnlineCast());
    }

    @Dado("^que nos encontramos en la pagina principal de ProductStore$")
    public void queNosEncontramosEnLaPaginaPrincipalDeProductStore() throws Exception {
        theActorCalled("Usuario").attemptsTo(OpenSiteProductStoreTask.openSiteProductStoreTask());
    }


    @Dado("^nos autentificamos con nuestras credenciales$")
    public void nosAutentificamosConNuestrasCredenciales(List<String> arg1){
        theActorInTheSpotlight().attemptsTo(login(DatosUsuarioBuilder.con(arg1)));
    }
}

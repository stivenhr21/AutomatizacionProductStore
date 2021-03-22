package co.com.stivenhernandez.productstore.task.productstore;

import co.com.stivenhernandez.productstore.interactions.comunes.Esperar;
import co.com.stivenhernandez.productstore.models.builder.DatosUsuarioBuilder;
import co.com.stivenhernandez.productstore.models.productstore.DatosUsuario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import static co.com.stivenhernandez.productstore.userinterface.productstore.ProductStorePage.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class LoginTask implements Task {
  private DatosUsuario ingresardatos;

  public LoginTask(DatosUsuario dta) {
    ingresardatos = dta;
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        Click.on(BTN_INICIAR_SESION_HEAD),
        Enter.theValue(ingresardatos.getUsuario()).into(LABEL_USUARIO),
        Enter.theValue(ingresardatos.getClave()).into(LABEL_CLAVE),
        Click.on(BTN_LOGIN),
        Esperar.esperarEnSegundos(4000));
  }

  public static LoginTask login(DatosUsuarioBuilder datos) {
    return instrumented(LoginTask.class, datos.build());
  }
}

# language: es

@E2E_QaPrueba2
Característica: Inicio de sesion exitoso en el sitio web ProductStore
  El usuario debe tener la habilidad de iniciar sesion

  @TestCase1
  Esquema del escenario: Inicio de sesion exitoso en ProductStore

    Dado que nos encontramos en la pagina principal de ProductStore

    Y nos autentificamos con nuestras credenciales
      | <usuario> | <clave> |

    Ejemplos:
      | usuario | clave |
    ##@externaldata@.\src\test\resources\productstore\datadriver\productstore\ProductStore.xlsx@Datos
	|stivenhrstiven@gmail.com|STIVENc1qyay492121|

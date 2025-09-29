Feature: LogOut

  Background: Precodiciones de LogOut
    Given El usuario se logueara dentro de la pagina


  @regressiones
  Scenario: LogOut
    When navega a la home y luego procede a precionar el boton Logout
    Then navega a la pagina de login nuevamente
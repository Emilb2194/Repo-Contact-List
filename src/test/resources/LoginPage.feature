Feature: Login

  Background: Precodiciones de login
    Given El usuario navegara a la pagina de login


  @regressiones
  Scenario: login
    When completar formulario de login y hacer click en el boton Login
    Then navega a la home page y el usuario valida los botones Logout y Delete account
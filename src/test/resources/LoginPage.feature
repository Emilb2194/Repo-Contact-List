Feature: Login

  Background: Precodiciones de login
    Given El usuario navegara a la pagina de login


  @regressiones
  Scenario: Login
    When completar formulario de login y hacer click en el boton Login
    Then navega a la home page validando cada elemento dentro de la misma


  @regressiones
  Scenario: Login Error
    When completar formulario de login con mail invalido y hacer click en el boton Login
    Then devuelve un mensaje de error





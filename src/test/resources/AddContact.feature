Feature: AddContact

  Background: Precodiciones de addcontact
    Given Luego de averse logueado el usuario aniade contactos haciendo click en el boton Add a New Contact


  @regressiones
  Scenario: Adherir nuevo contacto
    When completa los datos para agregar un nuevo contacto y preciona el boton submit
    Then validar la existencia de los registros creados dentro de la tabla
Feature: ModifyContact

  Background: Precodiciones de Modificar usuario
    Given Luego de realizar doble click sobre un contacto agregado nos redirecciona a detalle del mismo


  @regressiones
  Scenario: Modificar un contacto
    When cuando presionamos el boton editar navegamos a la pantalla de edicion modificamos y enviamos
    Then validar que los datos han sido modificados

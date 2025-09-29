Feature: DeleteContact

  Background: Precodiciones de Modificar usuario
    Given Luego de realizar doble click sobre un contacto agregado nos redirecciona a su detalle


  @regressiones
  Scenario: Eliminar todos los contactos
    When cuando presionamos el boton Delete Contact redirige pantalla home realizamos el proceso para eliminar todos los contactos
    Then validar que los datos han sido eliminados


Feature: A description

  Scenario: La letra en la misma posicion es correcta
    Given la palabra catde
    And mi palabra de intento es datds
    When tomo la posicion 1
    Then las letras coinciden

  Scenario: la letra en la misma posicion es incorrecta pero se encuentra en la palabra
    Given la palabra catde
    And mi palabra de intento es datds
    When tomo la posicion 0
    Then la letra no coinciden pero se encuentra

  Scenario: la letra no se encuentra
    Given la palabra catde
    And mi palabra de intento es datds
    When tomo la posicion 4
    Then la letra no se encuentra


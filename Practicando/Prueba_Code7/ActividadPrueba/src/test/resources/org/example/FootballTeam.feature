Feature: FootballTeam
  Scenario: Un equipo gana 2 partidos de las rondas
    Given Un equipo de futbol Dortmund
    When Este equipo gana 3 partidos de la segunda fecha
    Then El equipo debe tener 3 partidos ganados

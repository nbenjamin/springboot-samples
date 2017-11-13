Feature: embedded kafka test

  Scenario: embedded kafka class rule test
    When application is up and running
    Then publish message
    And consumer the same message
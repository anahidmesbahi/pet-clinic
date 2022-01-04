Feature: PetService
  Background: We have some owners and some pets
    Given There is owner "Ali" "Shahverdi"
    And There is owner "Anahid" "Mesbahid"
    And There is owner "Ahmad" "Zoghi"
    And There is petType "Khar"
    And There is petType "Sag"
    And There is pet "GooGooli" with owner "Ali"
    And There is pet "MaaGooli" with owner "Anahid"

  Scenario: Getting Owner 1
    When Find owner "Ali" with petService
    Then Returned owner equals "Ali"

  Scenario: Getting Owner 2
    When Find owner "Anahid" with petService
    Then Returned owner not equals "Ali"

  Scenario: Getting Owner 3
    When Find owner "Ahmad" with petService
    Then Returned owner not equals "Ali"
    Then Returned owner not equals "Anahid"

  Scenario: Getting Pet 1
    When Find pet "GooGooli" with petService
    Then Returned pet equals "GooGooli"

  Scenario: Getting Pet 2
    When Find pet "GooGooli" with petService
    Then Returned pet not equals "MaaGooli"
    Then Returned pet not equals "Ahmad"
    

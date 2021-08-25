
@db
Feature: FrontEnd BackEnd Testing



Scenario Outline: Names are added frontEnd

 Given I am logged into HRMS
 And I navigated to Add Employee Page
 
  When I add "<FirstName>", "<MiddleName>" and "<LastName>"
  And I click Save
  Then I see Employee with "<FirstName>", "<MiddleName>" and "<LastName>" is displayed
  
  Examples:
 | FirstName  | MiddleName | LastName |
 | Ramazan    |    Çok     | Mubarek  |


  Scenario: Names validated from DataBase
   
    When I retrive data table from DataBase
    And I retrive data table from my scenario
    | emp_firstname  | emp_lastname | emp_middle_name |
    | Ramazan    |    Çok     | Mubarek  |
 
    
    Then I verify both tables are equals

  

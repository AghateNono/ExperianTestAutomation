Feature: Loan consolidation
  As a user on Experian.co.uk
  I want to access the website
  So that apply for a Debts consolidation loan

Scenario: Loan application submission with repayment year more than 30
  Given user navigates to Experian website
  When user enters the amount to borrow "15000"
  And user enters the loan repayment term "31"
  And user clicks on Continue
  Then an error message is displayed for more than 30

  Scenario: Loan application submission with amount less than £500
  Given user navigates to Experian website
  When user enters an invalid amount to borrow "150"
  And user enters the loan repayment term "3"
  And user clicks on Continue
  Then an error message is displayed for less than 500

  Scenario: Loan application submission with amount more than £50000
    Given user navigates to Experian website
    When user enters an invalid amount to borrow "55000"
    And user enters the loan repayment term "3"
    And user clicks on Continue
    Then an error message is displayed for more than 50000




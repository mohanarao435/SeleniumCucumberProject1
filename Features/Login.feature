Feature: Login

@sanity
Scenario: Successful Login with Valid Credentials
     Given User Launch Chrome Browser
     When User opens URL "https://admin-demo.nopcommerce.com/login" 
     And User enters Email as "admin@yourstore.com" and Password as "admin"
     And Click on login
     Then Page Title should be "Dashboard / nopCommerce administration"
     When User click on logout link
     Then Page Title should be "Your store. Login"
     And close browser

@regression
Scenario Outline: Login Data Driven
     Given User Launch Chrome Browser
     When User opens URL "https://admin-demo.nopcommerce.com/login" 
     And User enters Email as "<email>" and Password as "<password>"
     And Click on login
     Then Page Title should be "Dashboard / nopCommerce administration"
     When User click on logout link
     Then Page Title should be "Your store. Login"
     And close browser
     
     Examples:
        | email | password |
        | admin@yourstore.com | admin |
        | admin@yourstore.com | admin123 |
        | admin123@yourstore.com | admin |
        | admin123@yourstore.com | admin123 |
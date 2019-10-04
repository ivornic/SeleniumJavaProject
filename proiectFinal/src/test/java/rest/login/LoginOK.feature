Feature: login with credentials
  Background:

  Scenario: login with correct credeintials

Given url 'http://the-internet.herokuapp.com/authenticate'
  And header Connection = 'keep-alive'
  #And header Content-Length = '49'
  #And header Content-Type = 'application/x-www-form-urlencoded'
  And form field username = 'tomsmith'
  And form field password = 'SuperSecretPassword!'
When method post
Then status 200
    And  header Location = 'http://the-internet.herokuapp.com/secure'

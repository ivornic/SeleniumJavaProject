Feature: login with credentials
  Background:

  Scenario: login with incorrect credeintials

Given url 'http://the-internet.herokuapp.com/authenticate'
  And header Connection = 'keep-alive'
  #And header Content-Length = '49'
  #And header Content-Type = 'application/x-www-form-urlencoded'
  And form field username = 'bkhih'
  And form field password = 'khjkhjhj!'
When method post
Then status 200
    And  header Location = 'http://the-internet.herokuapp.com/login'
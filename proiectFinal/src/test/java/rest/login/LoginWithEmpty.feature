Feature: login with credentials
Background:


Scenario: login with empty credentials

  Given url 'http://the-internet.herokuapp.com/Login'
  And header Connection = 'keep-alive'
  #And header Content-Length = '49'
  #And header Content-Type = 'application/x-www-form-urlencoded'
  And form field username = ''
  And form field password = ''
  When method post
  Then status 404
  And  header Location = 'http://the-internet.herokuapp.com/login'
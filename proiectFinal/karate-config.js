function() {
  var env = karate.env; // get system property 'karate.env'
  var config = {
    google : 'http://the-internet.herokuapp.com'
  };

  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);
  return config;
}
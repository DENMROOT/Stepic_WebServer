Stepic.org - Java web server tutorial project.

Deploy project by:
1. Change output directory of server.jar in pom.xml
2. run assembly:single maven plugin
Start project by: running ServerMain class

CURL POST request usage:
1. curl -d "login=denm&password=123" http://localhost:8080/signup
2. curl -d "login=denm&password=123" http://localhost:8080/signin
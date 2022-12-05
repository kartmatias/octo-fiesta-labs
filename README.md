# octo-fiesta-labs
A spring boot project

## Requirements

>I have used sdkman to install java
 
`curl -s "https://get.sdkman.io" | bash`

>Using java 17 based on spring framework [recomendations] (https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-Versions#jdk-version-range)

`sdk install java 17.0.5-zulu`

`sdk use java 17.0.5-zulu`

>Base project generated using spring [initializr] (https://start.spring.io/)

`mvn package && java -jar target/alticci-0.0.1.jar`

>creating container docker

`docker build -t matias/alticci .`
`docker run -p 8080:8080 matias/alticci`

>run in your browser: http://localhost:8080/v1/alticci/{NUMBER}

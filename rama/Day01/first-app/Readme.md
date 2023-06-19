## Create maven project

    mvn -B archetype:generate -DgroupId=com.mycompany.app -DartifactId=first-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4

## Build application

    mvn clean install

## Clean  target folder

    mvn clean

## Run Test application

    mvn test
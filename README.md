# A project of russian license plate detection with java, opencv and Adaboost algorithm
This project is an implementation of licence plate detection with opencv and Adaboost


### Usage:
    mvn clean with skipped tests
    mvn package
    mvn test
    cd target 
    java -Djava.library.path=lib/ -jar car-number-1.0-SNAPSHOT.jar
for test in the test class 2 vars have to be changes with path(path, cascadePath)
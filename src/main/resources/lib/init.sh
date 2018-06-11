#!/usr/bin/env bash

mvn install:install-file -Dfile=opencv-3.4.1.jar -DgroupId=pirate.nojpg -DartifactId=opencv -Dversion=3.4.1 -Dpackaging=jar

mvn install:install-file -Dfile=opencvjar-native-3.4.1.jar -DgroupId=pirate.nojpg -DartifactId=opencv-native -Dversion=3.4.1 -Dpackaging=jar
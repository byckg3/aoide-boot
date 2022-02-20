# 
###
- Running unit tests with Gradle => ./gradlew clean test
- ./gradlew bootRun --args="--spring.profiles.active=dev"
- docker run --rm -v $(pwd):/usr/src/aoide-boot -p 8081:8081 -it aoide-boot /bin/sh
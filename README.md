# Test case about spring boot issue #40429

Reference: https://github.com/spring-projects/spring-boot/issues/40429

## Hot to run the case

### Build
* Build / install **my-service-parent** repo with *mvn  -Pnative clean install* command
* Build / install **my-tech-library** repo with *mvn  -Pnative clean install* command
* Build native image **my-service** repo with *mvn -e -Pnative spring-boot:build-image* command

### Run

Running the docker image will end up in the application suddenly exit right after startup without logging anything:
```
docker --debug run --rm -p 8080 docker.io/library/myservice:0.0.2-SNAPSHOT

DEBU[0000] [hijack] End of stdout
```

Running the jar archive directly will let it work:
```
java -Dspring.aot.enabled=true -jar myservice-0.0.2-SNAPSHOT.jar

2024-04-19T15:53:51.453+02:00  INFO 469840 --- [my-service] [           main] com.myservice.myservice.Application      : Starting AOT-processed Application v0.0.2-SNAPSHOT using Java 21.0.2 with PID 469840 (/home/daniele/dev/github/spring-boot-native-environment-post-processor-issue/my-service/target/myservice-0.0.2-SNAPSHOT.jar started by daniele in /home/daniele/dev/github/spring-boot-native-environment-post-processor-issue/my-service/target)
[...]
2024-04-19T15:53:52.677+02:00  INFO 469840 --- [my-service] [           main] com.myservice.myservice.Application      : Started Application in 2.272 seconds (process running for 2.795)
```

If you try do delete _SpringConfigImportEnvironmentPostProcessor_ class and _spring.factories_ file the example will startup correctly from docker:
```
docker --debug run --rm -p 8080 docker.io/library/myservice:0.0.2-SNAPSHOT

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.4)

2024-04-19T14:19:22.447Z  INFO 1 --- [my-service] [           main] com.myservice.myservice.Application      : Starting AOT-processed Application using Java 21.0.2 with PID 1 (/workspace/com.myservice.myservice.Application started by cnb in /workspace)
[...]
2024-04-19T14:19:22.573Z  INFO 1 --- [my-service] [           main] com.myservice.myservice.Application      : Started Application in 0.143 seconds (process running for 0.149)
```
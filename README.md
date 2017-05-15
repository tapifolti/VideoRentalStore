# VideoRentalStore

How to maintain the API
---

1. Project was generated with: `mvn archetype:generate -DarchetypeGroupId=io.dropwizard.archetypes -DarchetypeArtifactId=java-simple -DarchetypeVersion=1.1.0`
1. Update/maintain `swagger.yaml` in project root using the swaggerEditor: `http://editor.swagger.io/#`
1. Download and replace it in the project folder
1. TODO: automate with Maven build
    1. Download swagger-codegen-cli-<version>.jar from maven repo
    1. Run swagger code-gen as: `java -jar swagger-codegen-cli-<version>.jar generate -i file:///<full_path_to>/VideoRentalStore/swagger.yaml -l jaxrs -o <swagger_out_location>`
    1. Copy swagger generated `src/gen` folder to `VideoRentalStore/src/gen`
    1. High level API change may require manual update of: `src/main/java/io/swagger/api/factories` from swagger generated `src/main/java/io/swagger/api/factories`
1. After API change always update *Impl.java classes in dropwizzard's `resources` folder
1. Re-build project

How to start the VideoRentalStore application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/VideoRentalStore-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`


Sample requests
---

1. TODO with curl

How to generate client code for the REST API
---

1. Use the `swagger.yaml` file in the project root folder
1. Use swaggerEditor: `http://editor.swagger.io/#` and generate client
1. Or use command line tool: `https://github.com/swagger-api/swagger-codegen#to-generate-a-sample-client-library`

Open tasks - To Do List
---

1. Concurrent renting !
1. Configure price and bonus points
1. Show `swagger.yaml` via http
1. Add unit test
1. Add authentication and authorization support
1. Implement real database access layer and create database schema
1. Administration
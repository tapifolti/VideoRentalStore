# VideoRentalStore

Approach
---

1. Used swagger (`http://editor.swagger.io/#`) to create REST API description in `swagger.yaml`
1. Used swagger server code-gen to generate REST entry points and model objects
1. Embedded swagger generated code into dropwizard project
1. Advantages:
    1. There is a REST API definition which is easy to overview and modify
    1. **Client can be generated**
1. Disadvantages:
    1. Dropwizard's advised project layout was changed
    1. Lost dropwizard provided input validation at REST entry point level, as it is swagger generated
    1. Cannot use constructor parameters on resource classes as they are swagger generated
    1. Swagger do not generate Java8 compatible code
    1. Finally needed to modify swagger generated classes as HK2 dependency injection worked only on REST API classes (FilmApi, CustomerApi, RentApi, ReturnApi)

Focus points and missing parts
---

1. Focused on REST API layout/design
1. Targeted to have a working solution
1. Implemented an in memory data store instead of using real database, so concurrency issues was addressed at this level, it can be a permanent solution for small data sets with additional persistence management
1. All features can work concurrently
1. Had no time for to add
    1. appropriate logging
    1. health check
    1. required amount of unit and integration test cases
    1. javadoc comments
    1. test and address all error cases
    1. input validation (dropwizard provided one do not work with swagger)

How to maintain the API
---

1. Project was generated with: `mvn archetype:generate -DarchetypeGroupId=io.dropwizard.archetypes -DarchetypeArtifactId=java-simple -DarchetypeVersion=1.1.0`
1. Update/maintain `swagger.yaml` in project root using the swaggerEditor: `http://editor.swagger.io/#`
1. Download and replace it in the project folder
1. TODO: automate with Maven build
    1. Download swagger-codegen-cli-<version>.jar from maven repo
    1. Run swagger code-gen as: `java -jar swagger-codegen-cli-<version>.jar generate -i file:///<full_path_to>/VideoRentalStore/swagger.yaml -l jaxrs -o <swagger_out_location>`
    1. Copy swagger generated `src/gen` folder to `VideoRentalStore/src/gen` (FilmApi, CustomerApi, RentApi, ReturnApi classes modified manually as DI was added)
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

1. Configure price and bonus points
1. Add unit test
1. Sample requests
1. Show `swagger.yaml` via http
1. Add authentication and authorization support
1. Implement real database access layer and create database schema
1. Administration
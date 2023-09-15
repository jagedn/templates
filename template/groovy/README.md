## Template: groovy

The Groovy template uses gradle as a build system.

Gradle version: 7.6

### Structure

There are three projects which make up a single gradle build:

- model - (Library) classes for parsing request/response
- function - (Library) your function code as a developer, you will only ever see this folder
- entrypoint - (App) HTTP server for re-using the JVM between requests

### Handler

The handler is written in the `./src/groovy/main/Handler.java` folder

Tests are supported with junit via files in `./src/test`

### External dependencies

External dependencies can be specified in ./build.gradle in the normal way using mavenCentral, a local JAR or some other remote repository.


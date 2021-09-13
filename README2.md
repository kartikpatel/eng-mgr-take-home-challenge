## Non-Functional Testing
To run all unit, integration, and component tests: `make gradle.check`

To run unit tests: `make gradle.test`

To run integration tests: `make gradle.integration-test`

To run component tests: `make gradle.component-test`

## Functional Testing

To run end-to-end tests: `make gradle.e2e-test` 
* note: The api service should be run prior to running this task 

## Important Docs and Links

Swagger UI: http://localhost:3000/swagger-ui/

Architectural Decisions Records can be found in the `doc/architecture/decisions` folder
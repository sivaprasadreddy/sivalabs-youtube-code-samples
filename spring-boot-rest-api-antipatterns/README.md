# Spring Boot REST API Anti Patterns

This is a sample repository to demonstrate some of the common anti-patterns in 
Spring Boot REST API development.

* Not following REST API conventions
  * No proper URL structure
  * Not returning proper HTTP status code
* Using Field Injection
* Not using pagination for collection type resources
* Not using request-specific payload structures (Using entity types as payloads)
* Using JPA DDL auto generation
* Misusing Spring Data JPA derived-query method names
* Loading entire entities and using only a small subset of fields
* Using JPA that results in N+1 Select problems
* Not implementing proper Exception Handling
* Using @SpringBootTest for testing slices (controller, repository)
* Testing with in-memory database
* Not using proper package structure
    * package-by-layer
    * package-by-feature
    * package-by-component
    * Hexagonal
    * Ports & Adapters
    * Clean Code
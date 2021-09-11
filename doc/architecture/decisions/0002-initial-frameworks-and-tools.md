# 2. initial-frameworks-and-tools

Date: 2021-09-11

## Status

Accepted

## Context

A decision needs to be made on the programming langauge, framework, and initial tools to be used for this project to start writing the API.  

## Decision

Out of the possible programming language choices (Python, Kotlin, Swift, GoLang) Kotlin has been choice due to familiarity and speed of development.

Java 11 will be used as it is the current LTS version. Other options considered were Java 16 and the current build of the unreleased Java 17.

Spring Boot version 2.5.4 will be used due to familiarity and speed of development.

Gradle will be used due to simplicity, familiarity, and speed of development

JUnit 5 will be used for unit, integration, and component testing due to familiarity and availability of extensive documentation.

## Consequences

While development will be easier, there are consequences in runtime performance that might need to mitigated at a later time.
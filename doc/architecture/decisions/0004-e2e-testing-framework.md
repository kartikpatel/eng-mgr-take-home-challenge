# 4. e2e testing framework

Date: 2021-09-12

## Status

Accepted

## Context

It's beneficial to have end-to-end tests to verify the api is working in a fully running state. Karate was chosen for it's ability to integrate with existing tools like Gradle and it's flexibility. 

## Decision

Usage of [Karate](https://github.com/intuit/karate) for end-to-end testing 

## Consequences

We gain the ability to end-to-end test using existing tools but other end-to-end testing tools might be more familiar to others.

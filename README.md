# java-coding-assessment-db
>  This microservice is responsible for processing trading signals

## Contents

- [Description](#description)
- [Setup](#setup)
- [Dependencies](#dependencies)
- [Endpoints](#endpoints)
- [Unit Tests](#unit-tests)

## Description

The implementation includes the use of the Command Pattern which makes it is easy to separate 
the signals into individual classes. This supports the single responsibility principle that
makes the code easier to understand, fix and maintain. Additionally classes are less coupled and 
more resilient to change. This software design also enhances the testability of the code.

In a nutshell, the signal classes get a specific name that will be loaded of a map based on the
request payload. The payload includes the `signalId` which is the base of the service name e.g.
`signalId=2` will result into service name `SIGNAL-2` that is loaded from a map 
called `Map<String, SignalHandler> signalHandlers`. For simplicity I used simple names, but this can be changed
as per the needs. The `SignalHandler` interface lays the contract
for the signals to execute their individual code. This way the service could handle several thousand
commands easily and nicely seperated in correctly named packages to make it better understandable.

## Setup

Go into the root directory and run the `startup.sh` file

```
cd java-coding-assessment-db
chmod +x startup.sh
./startup.sh
```

It will build the app with maven and start the application on port 8080

## Dependencies

- Java 11
- Maven 
- JUnit 5
- Spring Boot 2.7
- JUnit

## Endpoints

| Action           | Endpoint          | Type     | Example                                          | Payload                                                                                                                                                |
|------------------|-------------------|----------|--------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|
| Process a Signal | `/api/v1/signal/` | `POST`   | `/api/v1/signal/` | `{ "signalId": 1 }`                                                                                                                                    |


## Unit Tests

To execute Unit Tests run:

```
mvn test
```


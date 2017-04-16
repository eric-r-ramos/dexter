DEXTER : The ING ATM Search
==========================

## Overview
The Dexter project begun with some friends who wants to study CI/CD tools like
Vagrant, Jenkins, Chef, etc. The code was pretty small with just one functionality:
Consume a web service, make some logic on the results and create other web service
the delivery processed data

The original web service used is the ING ATM locator (www.ing.nl/api/locator/atms/)

Dexter application consume the ING original data and provide a city filter.

---

## Dependencies
The application was written in Java 8 and uses **Spring Boot**, **AngularJS** and **Bootstrap CSS**. The package manager chosen was **Maven** (back end) and **Bower** (front end)


### Maven
Pleaser check `pom.xml` for full code insertion.

* `spring-boot-starter-web`
* `spring-boot-starter-security`
* `spring-boot-starter-test`
* `json-path`
* `spring-test`
* `spring-security-test`
* `junit`
* `hamcrest-core`
* `hamcrest-library`
* `mockito-core`


### Bower
Pleaser check `bower.json` for full code insertion.

* `angular: v1.5.8`
* `angular-route v1.5.8`
* `bootstrap v3.3.7`

---

## Structure
Only folders :
```
.
├── src
│   └── main
│       ├── java
│       │   └── io
│       │       └── dexter
│       │           ├── config
│       │           │   └── security
│       │           ├── entities
│       │           │   └── atm
│       │           ├── rest
│       │           │   └── dto
│       │           └── services
│       │               ├── dispatcher
│       │               │   └── entities
│       │               └── impl
│       ├── resources
│       │   └── config
│       └── webapp
│           ├── app
│           │   └── js
│           │       ├── home
│           │       └── login
│           ├── bower_components
│           │   ├── angular
│           │   ├── angular-bootstrap
│           │   ├── angular-route
│           │   └── bootstrap
│           │       ├── dist
│           │       │   ├── css
│           │       │   ├── fonts
│           │       │   └── js
│           │       ├── fonts
│           │       ├── grunt
│           │       ├── js
│           │       ├── less
│           │       │   ├── mixins
│           │       ├── nuget
│           ├── content
│           │   ├── css
│           │   │   └── dexter.css
│           │   └── images
│           │       └── logo.gif
│           ├── favicon.ico
│           └── index.html
└── src.test.java
    └── io
        └── dexter
            ├── config
            └── services

```

## How it was made

### The server side
The Dexter application works as a proxy service. The service data is provided by a public RESTful service from ING Bank.

Although the project has _**server**_ and _**client**_ code, the Dexter application is ready to work as a service provider and let the presentation layer being implemented for any other application. This kind of application architecture ensure a scale-out capability of server side.

There are 3 main endpoints at Dexter web services, all of them is RESTful service and return JSON data. Those services use a Dispatcher design pattern.

---


* **Endpoint:** `/api/atm/`
* **Public Service**: *NO* (you must to be logged to use)
* **Return**: a full list of ATM from ING service.
* **Specific Logic**: This service has no specific logic, it just call the original service and map to the DTO object used by Dexter Project


---

* **Endpoint:** `/api/atm/{city}/`
* **Public Service**: *NO* (you must to be logged to use)
* **Return**: a filtered list of ATM from ING service, given a specific city as parameter.
* **Parameter** `city`: Name of the city to use as filter
* **Specific Logic**: This service invoke the original service (method used to return full list of ATM) and filter the list using the expression:

```
listAllAtms().stream().filter(atm -> atm.getCity().trim().equalsIgnoreCase(city.trim())).collect(Collectors.toList());
```

where: `listAllAtms()` is the method that return  an `ArrayList<Atm>` with all ATM address and `city` is the filter parameter.


---

* **Endpoint:** `/api/atm/listCities/`
* **Public Service**: *YES*
* **Return**: a list of cities which has at least one ATM machine.
* **Specific Logic**: This service invoke the original service *(method used to return full list of ATM)* and group the cities returned using the expression:

```
listAllAtm().stream().collect(Collectors.groupingBy(a -> a.getAddress().getCity())));
```

where: `listAllAtms()` is the method that return  an `ArrayList<Atm>` with all ATM address

### The client side
The client side is an web application and use the *single page application pattern*.

The "`/`" route load the `index.html` file which has a `<ng-view></ng-view>` space to load other pages.
Since the application features is not so extensive, the `home.html` is able to handle all the application functionalities

The home page has:

* instructions to proceed with login
* login form (shown only if the user is not logged)
* city combo box with all cities which has an ATM from ING (result of `/api/atm/listCities`)
* table of ATM list (shown after Search was executed)

The theme used is the Twitter Bootstrap CSS.

## Running Application

### Running as a packaged application
After user maven to pack the build you can load the application with `java -jar`
```
$ java -jar dexter-0.1.0.jar
```
### Using the Maven plugin
The Spring Boot Maven plugin includes a run goal which can be used to compile and run the application

```
$ mvn spring-boot:run
```

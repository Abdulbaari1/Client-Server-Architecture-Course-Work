# Client Server Architecture Course-Work

# API Design Overview: 

The SmartCampus project is a RESTful JAX-RS Service that manages sensors and rooms in a university setting.

The entry point for the API is in the **EntryPoint.java** file within the resources tab, with the application path being `("/Api/v1")`.

The following are the main endpoints for this coursework:

```
"/rooms" Manages the room data, such as creation or deletion.
"/sensors" Manages the sensors of the project, supports filtering by type
```
Additional features are accessed through path parameters(e.g.,"/rooms/{roomId}", /sensors/{sensorId}/readings").

All incoming and outgoing requests are handled by using a logging filter.

# How to build and launch the Project Server

- Open Project in NetBeans<br/>
- Go to services tab, expand servers and confirm Apache Tomcat 9 is installed<br/>
- Right click servers, add server and locate Tomcat installation location<br/>
- Right click the project, select Clean and Build<br/>
- Right click the project, select run<br/>

# Curl Commands 
Here are a few curl commands to demonstrate the API.
```
curl http://localhost:8080/CourseWork/api/v1
curl http://localhost:8080/CourseWork/api/v1/rooms
curl http://localhost:8080/CourseWork/api/v1/sensors
curl -X POST http://localhost:8080/CourseWork/api/v1/rooms -H "Content-Type: application/json" -d '{
"id": "LIB-301",
"name": "Library",
"capacity": 30,
"sensorIds":[]
}'
curl localhost:8080/CourseWork/api/v1/rooms/LIB-301
curl -X DELETE localhost:8080/CourseWork/api/v1/rooms/LIB-301
```

_____
# Questions 

### Question 1: In your report, explain the default lifecycle of a JAX-RS Resource class. Is a new instance instantiated for every incoming request, or does the runtime treat it as a singleton? Elaborate on how this architectural decision impacts the way you manage and synchronize your in-memory data structures (maps/lists) to prevent data loss or race conditions.

## Answer 1:

JAX-RS resource classes are handled per-request by default, meaning that with every new request, a new resource will be created. This is beneficial as each request receives its own object, which reduces any state issues and enhances overall safety. However, shared memory objects such as hashmaps can be accessed by multiple requests at once. When storing api data, synchronisation is imperative.
____

###  Question 2: Why is the provision of ”Hypermedia” (links and navigation within responses) considered a hallmark of advanced RESTful design (HATEOAS)? How does this approach benefit client developers compared to static documentation?

## Answer 2:

Hypermedia as the engine of application state (HATEOAS) allows a client to navigate through resources dynamically. This allows for greater flexibility, as the client does not need prior knowledge of the API's structure, unlike static documentation, which would require manual coordination between teams. 

_____

### Question 3: When returning a list of rooms, what are the implications of returning only IDs versus returning the full room objects? Consider network bandwidth and client side processing.

## Answer 3:

Transmitting only Id's as opposed to full room objects decreases bandwidth load as less data is being transmitted. However, in order for a client to receive full details of a room, they would have to input additional requests, making it more complex for the client. Therefore, transmitting IDs is more efficient while transmitting the full objects is simpler.

_____

### Question 4: Is the DELETE operation idempotent in your implementation? Provide a detailed justification by describing what happens if a client mistakenly sends the exact same DELETE request for a room multiple times.

## Answer 4:

The DELETE operation is idempotent within my implementation, meaning that sending the DELETE request multiple times will have the same effect as sending one request. After the first successful DELETE request, the system will return a response indicating that the requested room no longer exists.

_____
### Question 5: We explicitly use the @Consumes (MediaType.APPLICATION_JSON) annotation on the POST method. Explain the technical consequences if a client attempts to send data in a different format, such as text/plain or application/xml. How does JAX-RS handle this mismatch?

## Answer 5:

The @Consumes annotation tells the JAX-RS implementation how to dynamically parse and deserialise the request body into a more suitable type. If the @Consumes annotation is not used, JAX-RS will not attempt to convert the request body, instead sending an HTTP"415 Unsupported Media Type" error. 

_____
###  Question 6: You implemented this filtering using @QueryParam. Contrast this with an alternative design where the type is part of the URL path (e.g., GET /api/vl/sensors/type/CO2). Why is the query parameter approach generally considered superior for filtering and searching collections

## Answer 6:

@QueryParam is more suitable for filtering and searching collections as opposed to @PathParam, as it allows you to customise your results based on specific, pre-defined conditions. @PathParam is more suitable for fetching singular items or locating specific resources.

_____

### Question 7: Discuss the architectural benefits of the Sub-Resource Locator pattern. How does delegating logic to separate classes help manage complexity in large APIs compared to defining every nested path (e.g., sensors/{id}/readings/{rid}) in one massive controller class?

## Answer 7:

Sub resource locators allow separate classes and methods to handle different paths within a larger, controller class. This reduces the complexity of the code, making it much easier to read and debug if necessary. This makes the overall API design a lot cleaner and improves code scalability.

_____

### Question 8: Why is HTTP 422 often considered more semantically accurate than a standard 404 when the issue is a missing reference inside a valid JSON payload?

## Answer 8:

Status code 422 is more specific, giving information about the validity of the request itself. Instead of giving a generic response like status code 404, which is "Resource not found", status code 422 informs us that the request was syntactically correct, but was not able to be processed. 

_____

### Question 9: From a cybersecurity standpoint, explain the risks associated with exposing internal Java stack traces to external API consumers. What specific information could an attacker gather from such a trace?

## Answer 9:

Exposing internal Java stack traces to external API consumers could allow malicious users to find vulnerabilities and create targeted attacks on those weaknesses. It would be better for all parties if, instead of showing stack traces, it showed a generic error message.

_____

### Question 10: Why is it advantageous to use JAX-RS filters for cross-cutting concerns like logging, rather than manually inserting Logger.info() statements inside every single resource method?

## Answer 10:

Using JAX-RS filters for cross-cutting concerns like logging is beneficial in a multitude of ways. They keep the code organised and easy to maintain, as well as being reusable and flexible, allowing for filters to be used in multiple resources and allowing for full control over where the filters are to be applied.

_____






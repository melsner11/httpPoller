# Task
VIXcodereviewScenario

We host and maintain a wide range of public facing web services and we would like to gaina better understanding of how these services are performing. 
We are particularly interested in the availability, performance and quality of these services.
We've done a discovery, and have come up with a rough idea of what the service shoulddo. 

We would like you to take a look at these requirements below and make a start on thisproject. Read through all of the requirements before you start.

## Requirements: 

Feel free to build in any stack you are most proficient in, here are some examples:

- Java/Python/Node or any other backend service

- ANy front end framework such as React, Angular, Backbone, Marionette

- SQL/SQLite/NoSQL databaseBasicStart by polling vix.digital and storing any information that will help us understand how wellthis services are performing.


- Implement a HTTP poller to check the status of a service at regular intervals

- Persist the information to an database

- Log a warning to console when the service does not respond properly

After you are happy with the basic service. 
Use your remaining time to take a look at thebackend & frontend tasks listed below.

###Backend

- We want to be able to manage (add/remove/update) services via an API call.

- Protect the poller against cases where a service takes a very long time to respond

###Frontend

- Show the latest status of each service

- Allow users to manage the list of services


###Solutionsubmission

Once you are happy with your solution, 
- put your code on GitHub and send us a link. 

Please include a basic 

- readme of how to run your project and include any database schema's or
- dependencies so we can run your solution.
 java 8, mysql database,


Poller - Main Class: 
checks in intervalls list of services and checks for statist ok
log time and status in db
log console when error

##Further: 

### Backend
- extend poller main class to Protect the poller against cases where a service takes a very long time to respond
- add REST API to create update and delete


### Frontend:
- show latest status /status 
- show list of services with current status (create update delete)


https://stackoverflow.com/questions/13424975/apache-httpclient-multiple-polling-connections-to-server-strategy
https://stackoverflow.com/questions/24550906/polling-an-http-server-sending-http-get-requests-repeatedly-in-java
https://stackoverflow.com/questions/24550906/polling-an-http-server-sending-http-get-requests-repeatedly-in-java
https://stackoverflow.com/questions/974973/java-timestamp-how-can-i-create-a-timestamp-with-the-date-23-09-2007
https://www.baeldung.com/java-timer-and-timertask
https://developer.okta.com/blog/2018/07/19/simple-crud-react-and-spring-boot
https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
https://vladmihalcea.com/a-beginners-guide-to-jpa-and-hibernate-cascade-types/

#Database

endPoints
- id
- url

endPointStatus
- id
- endPointId
- date time
- status (1 ok, 0 error, 2 timeout  )
- reason


//create a new endpoint
curl -i -X POST -H "Content-Type:application/json" -d "{\"url\" : \"http://Hello-Koding\"}" http://localhost:8080/api/endpoints



##react snippets
 
 <td>{(endpoint.endPointStatusList != null) ? endpoint.endPointStatusList.map(status => {return <div key={status.id}>{status.status}</div>}) : "empty"}</td>


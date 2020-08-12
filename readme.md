### Dependencies so we can run your solution.
 
 - java 8 
 - mysql
 - nodejs 
 - maven 
 - react


### Create database 

What the application expects: 

    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_httppoller
    spring.datasource.username=springuser
    spring.datasource.password=ThePassword

execute from the mysql prompt:

    create database db_httppoller;
    create user 'springuser'@'%' identified by 'ThePassword';
    create user 'springuser'@'localhost' identified by 'ThePassword';
    grant all on db_httppoller.* to 'springuser'@'%';
    grant all on db_httppoller.* to 'springuser'@'localhost';

All Tables will be auto generated


    --
    -- Database: `db_httppoller`
    --
    
    -- --------------------------------------------------------
    
    --
    -- Table structure for table `endpoint`
    --
    
    CREATE TABLE IF NOT EXISTS "endpoint" (
      "id" int(11) NOT NULL,
      "url" varchar(255) DEFAULT NULL,
      "currentendpointstatus_id" int(11) DEFAULT NULL,
      PRIMARY KEY ("id"),
      KEY "FK9h6n2wtrr7qrogx35wkdgvx6c" ("currentendpointstatus_id")
    ) AUTO_INCREMENT=3 ;
    
    -- --------------------------------------------------------
    
    --
    -- Table structure for table `end_point_status`
    --
    
    CREATE TABLE IF NOT EXISTS "end_point_status" (
      "id" int(11) NOT NULL,
      "reason" varchar(255) DEFAULT NULL,
      "status" int(11) NOT NULL,
      "timestamp" datetime DEFAULT NULL,
      "endpoint_id" int(11) DEFAULT NULL,
      PRIMARY KEY ("id"),
      KEY "FK1rq65qcy3odqmfjcxiy6hi16n" ("endpoint_id")
    ) AUTO_INCREMENT=1 ;


### Run the Backend

    ./mvnw  clean spring-boot:run   - will start REST Backend and the Poller 
    or 
    mvn clean spring-boot:run - when you have maven installed and in your path           
           
#### REST Backend and Poller:            

Point your browser to:            
http://localhost:8080/api/endpoints   to see all endpoints 

### Run React Frontend: 

cd to project dir: httpPoller/app

    npm install - will install requirements in node_modules
    npm audit fix
    
    npm start - will start frontend server for react gui - localhost:3000

 



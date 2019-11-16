# spring-cloud-data-flow-test
spring cloud data flow - stream, task evaluation

Rich material is here,
https://spring.io/projects/spring-cloud-dataflow

1. Setup a local postgresql server, pgadmin with default username and password

2. Setup and run rabbit mq server in local with default username and password
    username: guest
    password: guest
    RabbitMQ management console: http://localhost:15672/#/
    
    This is required for streams.
    
3. Build the jars by running mvn command on all 4 projects
    mvn clean install

4. Run the below fellows,
    
    java -jar spring-cloud-skipper-server-2.1.2.RELEASE.jar
    
    Skipper is a tool that allows you to discover Spring Boot applications and manage their lifecycle on multiple Cloud Platforms. ... Skipper provides a means to orchestrate the upgrade/rollback procedure of applications between different versions, taking the minimal set of actions to bring the system to the desired state.
    
    java -jar spring-cloud-dataflow-server-2.2.1.RELEASE.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/postgres?currentSchema=scdf  --spring.datasource.driver-class-name=org.postgresql.Driver --spring.datasource.username=postgres --spring.rabbitmq.host=127.0.0.1  --spring.rabbitmq.port=5672   --spring.rabbitmq.username=guest --spring.rabbitmq.password=guest --maven.localRepository=mylocal --maven.remote-repositories.repo1.url="file:///C:/Users/tech prudent/.m2/repository/"
   
5. Access spring cloud data flow dashboard UI here, http://localhost:9393/dashboard/index.html

6. Apps > Register application > maven uri
   Basically maven resource that we built in step 3 is the input for this registration. Since we set a localRepo in spring cloud data flow server, it will create a folder called 'mylocal' where the scdf.jar resides.
   Maven uri samples: 
      maven://com.dataflow:task:1.0
      maven://com.dataflow:source:1.0
      maven://com.dataflow:processor:1.0
      maven://com.dataflow:sink:1.0
     
7. Once registered, they are available in the apps page. Goto streams > create stream, if we want to create a long running process. Goto tasks if they are going to be spring cloud starter tasks and short lived.

  Spring Cloud Task allows a user to develop and run short lived microservices using Spring Cloud and run them locally, in the cloud, even on Spring Cloud Data Flow.
  Spring Cloud Stream is a framework built on top of Spring Boot and Spring Integration that helps in creating event-driven or message-driven microservices.
  
  task > create task > select task > connect them using GUI > name it. Run it > view logs and status of the job ran
  streams > create stream > import source, processor, sink > connect the pipeline > deploy the stream > verify logs
  
  Spring cloud data flow server local is suggested to be used to development purpose and not for production. For production, better to use cloud foundry or kubernetes versions.
  
  Cheers!.
   
    

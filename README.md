1.What Is a Microservice?
  In simple words, microservice(s) are clusters of small applications that work together in coordination to provide a complete solution.
  When we say a lot of small applications running independently together, then they will all have their own URLs and ports. 
  So we have to maintain all these microservices to run in synchronization, and more importantly, with monitoring.
  To solve this issue, we need a tool that will monitor and maintain the registry of all the microservices in the ecosystem.

2.What Is Netflix Eureka?
  It is a system or tool that provides integration with external systems and use for load balancing. 

       1.Config Server :- central place to manage external properties for applications across all environment
       2.Eureka Server :- is responsible to maintain the registry of all the Micro services that have been deployed and removed.
       3.Eureka Clients :- are the independent microservices. We will see how to configure this in a microservice ecosystem.
                           When a client microservice registers with Eureka it provides metadata such as host, port, and health indicator thus allowing for other microservices                            to discover it. 
  The discovery server i.e Eureka Server expects a regular heartbeat message from each microservice instances. 
  If an instance begins to consistently fail to send a heartbeat, the discovery server will remove the instance from his registry.
 
3.Config Server
  When developing a cloud application, one issue is maintaining and distributing configuration to our services. We really don't want to spend time to configure each environment
  Spring Cloud Config is Spring's client/server approach for storing and serving distributed configurations across multiple applications and environments.
  The server is embeddable in a Spring Boot application, by using the @EnableConfigServer annotation.
 
4.Eureka Server
  @EnableEurekaServer will configure this server as a discovery server using Netflix Eureka. 
  Spring Boot will automatically detect the configuration dependency on the classpath and lookup the configuration from the config server.
 
       1.	adding spring-cloud-starter-netflix-eureka-server to the dependencies
       <dependency>
           <groupId>org.springframework.cloud</groupId>
           <artifactId>spring-cloud-starter-netflix-eureka-server  
           </artifactId>
       </dependency>
       
       2.	enable the Eureka Server in a @SpringBootApplication by annotating it with @EnableEurekaServer
       The Spring Boot application to enable this as a Eureka Server. To do so, we need to add  @EnableEurekaServer .

       @SpringBootApplication
       @EnableEurekaServer
       public class EurekaServerApplication {
           public static void main(String[] args) {
               SpringApplication.run(EurekaServerApplication.class, args);
           }
       }
       3.	configure some properties
       server.port = 8761
       eureka.client.registerWithEureka = false
       eureka.client.fetchRegistry = false
  Here we're configuring an application port – 8761 is the default one for Eureka servers.
  Now one question may pop up our mind: what is the Eureka server itself?
  The Eureka server is nothing but another microservice which treats itself as a Eureka client.
 
 
5.Discovery Client
  Now, let’s add a few microservices to the ecosystem and register them to the discovery server. For this, we also need to add the dependencies required in each service and       register them to the server. We will see how in the details below.
  Then we need to annotate a @Configuration with either @EnableDiscoveryClient or @EnableEurekaClient – note that this annotation is optional if we have the spring-cloud-         starter-netflix-eureka-client dependency on the classpath.
  We can let Spring Boot choose a random port for us because later we are accessing this service with its name.
      1. <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-starter-netflix-eureka-starter</artifactId>
      </dependency>

      2.The main application will be annotated with @EnableEurekaClient or @EnableDiscoveryClient  in each microservice.
      From spring boot 2.X onwards spring suggest to use @EnableDiscoveryClient   instead of @EnableEurekaClient.
      Whatever implementation you choose, you’ll soon see the eureka-client registered under whatever name you specify in the spring.application.name property.
      Boot up this application to run on port 8004 and it will automatically register itself to the discovery server. 
      
 HeartBeat : After registering is successful after every 30 seconds Eureka client sends heartbeat to  Eureka server to renew its leases. So if till 90 seconds if Eureka server   not getting any heartbeat from Eureka client it unregisters the Eureka client instance from Service registry and sends the updated registry to all peers and Eureka clients.

6.GateWay Server
  All Netflix traffic first go to a Zuul cluster which mainly responsible for dynamic routing and monitoring.
  A gateway server is an excellent application in microservice architecture as it allows all responses to originate from a single host. 
      <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-starter-zuul</artifactId>
      </dependency>

     Zuul is a JVM-based router and server side load balancer by Netflix.
     Zuul Components
     •	pre filters – are invoked before the request is routed.
     •	post filters – are invoked after the request has been routed.
     •	route filters – are used to route the request.
     •	error filters – are invoked when an error occurs while handling the request.
     •	Custom filters - 
 
Request Processing flow inside Zuul with different filters
This service sits on top of all other microservices.
We have to annotate the class as @EnableZuulProxy.

Now we have tell the zuul server about the services and map them
        zuul.routes.sample-service.path=/sample-api/**
        zuul.routes.sample-service.serviceId=SAMPLE-SERVICE 






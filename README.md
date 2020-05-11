==>build the spring boot project 
	mvn clean 
	mvn install

==> skip test case and build project 
    mvn -Dmaven.test.skip package
  

Discovery Server configuration 
  
1) Add @EnableEurekaServer in application main file   

2) application.properties file
	server.port=8010
	spring.application.name=discoveryservice 
	eureka.client.registerWithEureka=false
	eureka.client.fetchRegistry=false
	eureka.client.serviceUrl.defaultZone=http://localhost:8010/eureka 
	
To view DashBoard of Eureka Server go to link http://localhost:8010 



Users Web service configuration

1) add eureka client dependency in pom.xml
2) add @EnableDiscoveryClient annotaction in main class 
3) Following things in property file
   server.port=0
   spring.application.name=users-ws 
   eureka.client.serviceUrl.defaultZone=http://localhost:8010/eureka 
   spring.devtools.restart.enabled=true

Zuul API gateway comes with Ribbion load balancer 

==>Dynamic Instance-id in web-service 
  
instance-id dynamic  eureka.instance.instance-id=${spring.application.name}:${spring.application.instance_id:${random.value}}
set server port   ${PORT:0}  


User service URL :
http://localhost:8011/user-ws/users/check

User serice H2 console URL:
http://localhost:8011/user-ws/h2-console


## To work with MySQL use following code lines
spring.datasource.url=jdbc:mysql://localhost:3306/users?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create


To enable spring boot security 
 
  1) Add spring security starter dependeny in pom.xml 
  2) create class WebSecurity and add @Confiuration and @EnableWebSecurity annotation top of the class  
  3) extends WebSecurityConfigurerAdapter and override its method
  
  To allow http request or restirct URL use the below method 
  @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/users/**").permitAll();
	}


Spring login URL 
 http://localhost:8011/user-ws/login

Users Registration endpoint 
URL : http://172.20.10.2:8011/users-ws/users
method : POST
Payload : 
{
	"firstName":"iphone",
	"lastName":"Verma",
	"password":"12345678",
	"email":"iphone@gmail.com"
}


User login URL 
http://172.20.10.2:8011/users-ws/users/login
Method:POST
payload :
 {
	"email":"iphone@gmail.com",
	"password":"12345678"
 }   

We have added authencitaion in Zuul api gateway and allow only login and register apis ,if any other request came without header Authorization header id will access denied

User service status check
URL : http://localhost:8011/users-ws/users/status/check
Method : GET
Headers : 
Authorization : Bearer genrated token when login


rabbitmq-plugins enable rabbitmq_management run this cmnd to after install rabitMQ
RabitMQ 
localhost:15672 login url 
defulat username password
username : guest 
password : guest

my username password
username :guest 
password : root


localhost:8012/actuator/bus-refresh 
spring cloud Bus URL to update the changes in propety file without restarting microservices



Encryption Decryption password 

Java JCE (Java cryptography extension) 
for symetric encryption 



keytool -genkeypair -alias apiEncryptionKey -keyalg RSA\-dname "CN=ankit verma,OU=Api devepomentent ,O=appsdevelopment.com,L=Indore,S=ON,C=IN"\-keypass av@dacoder -keystore apiEncryptionKey.jks -storepass av@dacoder
  

clone this project 
https://github.com/simplyi/PhotoAppApiAlbums

get albums ent-point direct using port number 
http://172.20.10.2:52583/users/12345/albums

endpoint to load property files for apiconfig 
http://localhost:8012/users-ws/default

get users albums list using userid
http://localhost:8011/users-ws/users/8488e8df-a2ef-4005-aeef-7560565d9de1

Key :Authorization 
value :Bearer token  

user login endpoint 
http://172.20.10.2:8011/users-ws/users/login

POST {
	"email":"vicky@gmail.com",
	"password":"12345678"
}

user register 
http://172.20.10.2:8011/users-ws/users
POST :
{
	"firstName":"vicky",
	"lastName":"Verma",
	"password":"12345678",
	"email":"vicky@gmail.com"
}



	
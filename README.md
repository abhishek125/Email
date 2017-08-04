# Email
This is a simple email sending project. it has features such as sending and receiving multimedia/html emails, users can also edit their profiles, mark emails as read/unread. users can move emails from inbox/outbox to important, spam folder or delete them altogether. 

## Todo's
* peer to peer chat feature so users can chat with their contacts.

## Technologies
* Backend: Java 8 with Spring
* Frontend: jsp
* Database: MySQL
* ORM: Hibernate
* Security: Spring Security

## Requirements
* tomcat 7+,jdk 1.7+ ,mysql 5.6+,maven or eclipse javaee

## Setup
* Install system dependencies: latest versions (at the time of this writing) of Java,tomcat and MySQL.If you are not using eclipse
  then you also need to install maven.
* Update src/main/resources/hibernate.xml with your MySQL credentials. Default username is "root" and password is "123".
* Execute src/main/java/sql/newdb.sql to create the database
* Run "mvn package" from the root of application directory which creates a war file located under {projectname}/target/ ,
  copy & paste this war file to Tomcats "webapps" directory.
* if you are using eclipse then skip previous step instead just import this project into eclipse and right click project -> run as -> run     on server.
* Start tomcat and visit http://localhost:8080/Email/

## note
we would not store user uploaded files inside the webapp(why? http://bit.ly/2sLfXwf) instead we would store all the user uploaded files in "c:/uploads/" which can be changed by modifying server.xml inside "server" directory of your eclipse workspace or under "conf" directory inside your tomcat installation directory if you are not using eclipse workspace.you can also set environment variable UPLOAD_LOCATION to the value of your preferred location.

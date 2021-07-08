# BrightMindes-JavaAssignment
Solution for BrightMindes Java Assignment

{Run}

1-	download the project. 

2-	use the import service in Eclipse IDE.

3-	press Ctrl+shift+R then enter application.properties change the location of the MS access DB 
     spring.datasource.url=jdbc:ucanaccess://{path of the db}
     
4-	change the value of spring.jpa.hibernate.ddl-auto from none to update.

5-	open the DB and insert the user and admin in the user table and their encrypted password.  

     user: $2a$04$GeWJbAympUa5S.EnQ1rFHuq.lQWJMbh3ukKdWYtCVrfZKkJoZ6vGm
     
     admin: $2a$04$O8i5pQyCHdYkpzB8G3OCL.4IbEnpejUJ8bEQtmAI1UKYsyZdJhXhW
     
6-	insert in authority table ADMIN and USER.

7-	link in authorities_users table the users with their authorities.
8-	make maven clean update. 
9-	open the browser inter address http://localhost:8080/
10-	enter the user Authentication and then submit.

	 
	
	

{Test report}
1-	right click on the main folder if the project 
2-	go to run as and click maven test.
3-	go to the folder /javaTest/target/site/jacoco/index.html
4-	right click on the index.html and then copy qualifying name.
5-	open the browser and paste it in the address and enter.

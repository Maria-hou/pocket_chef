Make sure the gson and sql jdbc jar files are added to the classpath under Java Build Path under Libraries tab (they are both located in WEB-INF lib folder)

Make sure to run SQL script titled "finalproject.sql"

This project has been running on port 8080, so make sure that the port number is set as such in the server.xml file

When running, right click on index.jsp in the webapp folder and run it on the tomcat server

The SQL Database username and password I used is located in Constant.java (located in the "j" package). Password and Username is "root".

To import the project properly, make sure not to check the first box nor the servers box. Create a new tomcat server, the installation directory should be something like /usr/local/Cellar/tomcat@9/9.0.59/libexec for MAC and C:/Program Files/Apache Tomcat Foundation/Tomcat9.0 for Windows, then click finish.

If tomcat is having errors, try to create a new tomcat server by right clicking on the project, choosing file->new->other and choose Server/Server then next and choose Apache Tomcat 9 and finish.
Then in the package explorer, click on Servers folder, right click on the Tomcat v9.0 Server at localhost... folder and Show in->System Explorer. Unzip the original archive file and copy+paste the Tomcat v9.0 Server at localhost... folder into the workspace's Servers folder.
And make sure to delete the other server to prevent a crash.
If Tomcat is not working, check properties and make sure the Targeted Runtimes includes Apache Tomcat. Maybe also try to select "clean" on the server.
   

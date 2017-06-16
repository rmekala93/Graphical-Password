/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocess;

/**
 *
 * @author Administrator
 */
import java.sql.*; 
import java.io.*; 

class ImageProcess { 
public static void main(String[] args) throws SQLException { 

Connection connection = null; 

//String connectionURL = "jdbc:mysql://localhost:3306/mahendra"; 

ResultSet rs = null; 

PreparedStatement psmnt = null; 

FileInputStream fis; 
try { 

Class.forName("com.mysql.jdbc.Driver"); 


connection = DriverManager.getConnection("jdbc:mysql://localhost/image","root",""); 

File image = new File("E:\\Projects\\imageProcess\\src\\imageprocess\\flower6.jpg"); 

psmnt = connection.prepareStatement ("insert into pixel values(?,?,?)"); 
psmnt.setString(1,"mahendra"); 
psmnt.setString(2,"Delhi"); 

fis = new FileInputStream(image); 
psmnt.setBinaryStream(3, (InputStream)fis, (int)(image.length())); 

int s = psmnt.executeUpdate(); 
if(s>0) { 
System.out.println("Uploaded successfully !"); 
} 
else { 
System.out.println("unsucessfull to upload image."); 
} 
} 


catch (Exception ex) { 
System.out.println("Found some error : "+ex); 
} 
finally { 

connection.close(); 
psmnt.close(); 
} 
} 
} 

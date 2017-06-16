/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocess;

/**
 *
 * @author Administrator
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.sql.DriverManager;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Administrator
 */
public class CompareImage extends JFrame implements ActionListener
{
    
    JPanel panel=new JPanel();
    Container c;
   
    static JLabel picture=new JLabel();
    
    JLabel name=new JLabel("Name");
    JLabel password=new JLabel("Password");
    JLabel filename=new JLabel("FileName");
     
    JTextField nametext=new JTextField();
    JTextField passwordtext=new JTextField();
    JTextField filenametext=new JTextField();
    
    JButton upload=new JButton("Upload");
    JButton Compare=new JButton("Display");
    JButton Browse=new JButton("Browse");
    JButton viewport=new JButton("Viewport");
   
    
    static ImageIcon icon;
    
    File file;
    BufferedImage image;
    static Image pic;
    
     static byte[] bytes = null; 
    
    
    CompareImage() throws IOException
    {
       
        c=(JPanel)getContentPane();
        c.setLayout(null);
        c.setBackground(Color.WHITE);
        
        picture.setBounds(50,100,400,325);
        
        filename.setBounds(600,100,100,30);
        filenametext.setBounds(720,100,300,30);
        
        name.setBounds(600,150,100,30);
        password.setBounds(600,200,100,30);
        
        nametext.setBounds(720,150,100,30);
        passwordtext.setBounds(720,200,100,30);
        
       
        Browse.setBounds(530,400,100,20);
        upload.setBounds(650,400,100,20);
        Compare.setBounds(770,400,100,20);
        viewport.setBounds(770,450,100,20);
       
        
        
        c.add(picture);
        c.add(filename);
        c.add(filenametext);
        c.add(name);
        c.add(nametext);
        c.add(password);
        c.add(passwordtext);
        
        
        c.add(viewport);
        c.add(upload);
        c.add(Compare);
        c.add(Browse);
       
       
        viewport.addActionListener(this);
        upload.addActionListener(this);
        Compare.addActionListener(this);
        Browse.addActionListener(this);
        
        
      
         
        
    }
    
    
    
   
    
    
   public void actionPerformed(ActionEvent ae)
   {
       if(ae.getSource()==upload)
       {
          Connection connection = null; 

          PreparedStatement psmnt = null; 

          FileInputStream fis; 
          
          String filename=filenametext.getText();
          String name=nametext.getText();
          String password=passwordtext.getText();
          try 
          { 

           Class.forName("com.mysql.jdbc.Driver"); 


           connection = DriverManager.getConnection("jdbc:mysql://localhost/image","root",""); 

           File image = new File(filename); 

           psmnt = connection.prepareStatement ("insert into pixelvalue values(?,?,?)"); 
           psmnt.setString(1,name); 
           psmnt.setString(2,filename); 

           fis = new FileInputStream(image); 
           psmnt.setBinaryStream(3, (InputStream)fis, (int)(image.length()));
          

           int s = psmnt.executeUpdate();   
          }

         catch(Exception ee)
         {
    
         }
           
                
       }
       
       else if(ae.getSource()==Compare)
       {
           
           
            String filename=filenametext.getText();
           
           try
           {
               File file=new File(filename);
               BufferedImage image=ImageIO.read(file);
               
               ImageIcon icon=new ImageIcon(image);
               picture.setIcon(icon);
               
         
             
            
            }
            
           
            
           
        catch(Exception ee)
        {
            
        }
       }
       else if(ae.getSource()==Browse)
       {
            JFileChooser chooser = new JFileChooser();
			    			    
			    try {
			        
			        File f = new File(new File("filename.txt").getCanonicalPath());
			    
			        chooser.setSelectedFile(f);
			        }      
                            catch (IOException e1) 
                                {
			        }
		 int retval = chooser.showOpenDialog(Browse);
                  if (retval == JFileChooser.APPROVE_OPTION){
                  File field = chooser.getSelectedFile();
                  filenametext.setText(field.getAbsolutePath());	    		  
           
       }
                  else if(ae.getSource()==viewport)
                  {
                      System.out.println("aa");
                  }                  
                  
                  
       }
   }
   
    
    public static void main(String[] arg) throws IOException
    {
        
        
        JFrame pixel=new CompareImage(); 
        pixel.setSize(1000,700);
        pixel.setVisible(true);
        pixel.setLocationRelativeTo(null);
                
    }

    
   
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocess;

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
public class selectpixel extends JFrame implements ActionListener
{
    
    JPanel panel=new JPanel();
    Container c;
   
    static JLabel picture=new JLabel();
    
    JLabel name=new JLabel("User Name");
   // JLabel password=new JLabel("Password");
    JLabel filename=new JLabel("Image");
    
    JLabel xvalue=new JLabel("X Point");
    JLabel yvalue=new JLabel("Y Point");
    JLabel alphavalue=new JLabel("Alpha");
    JLabel redvalue=new JLabel("Red");
    JLabel greenvalue=new JLabel("Green");
    JLabel bluevalue=new JLabel("Blue");
     
    JTextField nametext=new JTextField();
    JTextField passwordtext=new JTextField();
    JTextField filenametext=new JTextField();
    
    
    JTextField x_value=new JTextField();
    JTextField y_value=new JTextField();
    JTextField a_value=new JTextField();
    JTextField r_value=new JTextField();
    JTextField g_value=new JTextField();
    JTextField b_value=new JTextField();
    
    JButton upload=new JButton("Create");
    JButton Compare=new JButton("Display");
    JButton Browse=new JButton("Browse");
    JButton viewport=new JButton("Viewport");
    JButton shuffle=new JButton("Shuffle");
    
    static ImageIcon icon;
    int i=1;
    
    String user_name=UserRegister.Username_Text.getText();
    String name_text=UserRegister.Name_Text.getText();
    String acc_text=UserRegister.AccNo_Text.getText();
    
    selectpixel()
     {
      
        c=(JPanel)getContentPane();
        c.setLayout(null);
        c.setBackground(Color.WHITE);
        
        picture.setBounds(70,195,400,325);
        
        filename.setBounds(70,150,100,25);
        filenametext.setBounds(190,150,300,25);
        
        name.setBounds(70,100,100,25);
       
        nametext.setBounds(190,100,200,25);
        
        xvalue.setBounds(550,220,100,25);
        yvalue.setBounds(550,265,100,25);
        alphavalue.setBounds(550,310,100,25);
        redvalue.setBounds(550,355,100,25);
        greenvalue.setBounds(550,400,100,25);
        bluevalue.setBounds(550,445,100,25);
        
        x_value.setBounds(620,220,100,25);
        y_value.setBounds(620,265,100,25);
        a_value.setBounds(620,310,100,25);
        r_value.setBounds(620,355,100,25);
        g_value.setBounds(620,400,100,25);
        b_value.setBounds(620,445,100,25);
        
       
        Browse.setBounds(510,150,100,20);
        Compare.setBounds(630,150,100,20);
        upload.setBounds(600,500,100,20);
        shuffle.setBounds(240,540,100,20);
        
        viewport.setBounds(120,540,100,20);
       
        
        c.add(xvalue);
        c.add(yvalue);
        c.add(alphavalue);
        c.add(redvalue);
        c.add(greenvalue);
        c.add(bluevalue);
       
        
        c.add(x_value);
        c.add(y_value);
        c.add(a_value);
        c.add(r_value);
        c.add(g_value);
        c.add(b_value);
        
        
        
        c.add(picture);
        c.add(filename);
        c.add(filenametext);
        c.add(name);
        c.add(nametext);
        
        
        
        c.add(viewport);
        c.add(upload);
        c.add(Compare);
        c.add(Browse);
        c.add(shuffle);
       
        viewport.addActionListener(this);
        upload.addActionListener(this);
        Compare.addActionListener(this);
        Browse.addActionListener(this);
        shuffle.addActionListener(this);
        
        picture.addMouseListener(ml);
        
        nametext.setText(user_name);
        
     }
    
    
    public void actionPerformed(ActionEvent ae)
    {
        
        
        if(ae.getSource()==viewport)
          {
              String filename=filenametext.getText();
           
                try
                   {
                      File file=new File(filename);
                      BufferedImage image=ImageIO.read(file);
               
                      ImageIcon icon=new ImageIcon(image);
                      picture.setIcon(icon);
               
                      Path2D.Float regionOfInterest = new Path2D.Float();
        
                      regionOfInterest.moveTo(100.0,100.0);
                      regionOfInterest.lineTo(200.0,100.0);
                      regionOfInterest.lineTo(200.0,150.0);
                      regionOfInterest.lineTo(100.0,150.0);
                      regionOfInterest.closePath();
        
                      Path2D.Float pathForWholeImage = new Path2D.Float();
                      pathForWholeImage.moveTo(0,0);
                      pathForWholeImage.lineTo(image.getWidth(),0);
                      pathForWholeImage.lineTo(image.getWidth(),image.getHeight());
                      pathForWholeImage.lineTo(0,image.getHeight());
                      pathForWholeImage.lineTo(0,0);
    
                      Area wholeImage = new Area(pathForWholeImage);
    
                      wholeImage.subtract(new Area(regionOfInterest));
    
                      Graphics2D g2d = (Graphics2D)image.getGraphics();
   
                      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                      g2d.setColor(new Color(255,255,255,100));
                      g2d.fill(wholeImage);
                      g2d.setStroke(new BasicStroke(5f));
                      g2d.setColor(new Color(255,0,0,200));
                      g2d.draw(regionOfInterest);
                  }
              catch(Exception ee)
               {
               
               }
        }
        
      
      if(ae.getSource()==upload)
         {
        
             Connection connection = null; 
             PreparedStatement psmnt = null; 
             FileInputStream fis; 
          
             String filename=filenametext.getText();
             String name=nametext.getText();
             //String password=passwordtext.getText();
             String xvalue=x_value.getText();
             String yvalue=y_value.getText();
             String avalue=a_value.getText();
             String rvalue=r_value.getText();
             String gvalue=g_value.getText();
             String bvalue=b_value.getText();
            try 
             { 

                 
                 Class.forName("com.mysql.jdbc.Driver");
                 
                 connection = DriverManager.getConnection("jdbc:mysql://localhost/image","root",""); 
                 
                 File image = new File(filename); 
                 
                 fis = new FileInputStream(image);
                
                 
                   
                 String query="UPDATE user_data SET Path=?,Image=? WHERE Username=?";
                
                 PreparedStatement pstmt=connection.prepareStatement(query);
                
                 pstmt.setString(1, filename);
                
                 pstmt.setBinaryStream(2,(InputStream)fis,(int)(image.length()));
                 
                 pstmt.setString(3, name);
                 
                 pstmt.executeUpdate();
               
                 
                 //Statement stmt=connection.createStatement();
                 //String query="update user_data set Path='"+filename+"' where Username='"+name+"'";
                 //System.out.println("update user_data set Path='"+filename+"' where Username='"+name+"'");
                 //stmt.executeQuery(query);
                 
                 psmnt = connection.prepareStatement ("insert into imagetable values(?,?,?,?,?,?,?,?,?,?,?)"); 
                 
                 psmnt.setString(1,name); 
                 
                 psmnt.setString(2,filename);
                 
                 psmnt.setBinaryStream(3, (InputStream)fis, (int)(image.length()));
                
                 psmnt.setString(4,xvalue);
                 
                 psmnt.setString(5,yvalue);
                 
                 psmnt.setString(6,avalue);
                 
                 psmnt.setString(7,rvalue);
                
                 psmnt.setString(8,gvalue);
                 
                 psmnt.setString(9,bvalue);
                
                 psmnt.setString(10,name_text);
               
                 psmnt.setString(11,acc_text);
               
          
                 int s = psmnt.executeUpdate(); 
                 
                 JOptionPane.showMessageDialog(null,"Password Created");
                 
                 this.hide();
                 new Login();
                // System.out.println("1");
                // PreparedStatement stmt;
                // System.out.println("2");
                // stmt=connection.prepareStatement("update user_data set Path=?,Image=? where Username=?");
               //  System.out.println("3");
                 
                // stmt.setString(1, filename);
                // System.out.println("4");
                // stmt.setBinaryStream(2,(InputStream)fis,(int)(image.length()));
                // System.out.println("5");
                // stmt.setString(3,name);
               //  System.out.println("6");
                 
                // stmt.executeUpdate();
               //  System.out.println("7");
                 
                 
                 
               
             }

           catch(Exception ee)
            {
    
            }
        }
         
      
      
      if(ae.getSource()==Compare)
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
          
           
      if(ae.getSource()==Browse)
          {
        
         
               JFileChooser chooser = new JFileChooser();
			    			    
	       try 
               {
	           File f = new File(new File("filename.txt").getCanonicalPath());
		   chooser.setSelectedFile(f);
			 
               }      
               catch (IOException e1) 
               {
	       }
	       
               int retval = chooser.showOpenDialog(Browse);
               
               if (retval == JFileChooser.APPROVE_OPTION)
                 {
                  
                 File field = chooser.getSelectedFile();
                 filenametext.setText(field.getAbsolutePath());	    		  
           
                 }
               else if(ae.getSource()==viewport)
                {
                      System.out.println("aa");
                }                  
                  
                  
       
        }
      
    
      
       if(ae.getSource()==shuffle)
        {
         
           
           
                
                
                
              
                
              String filename=filenametext.getText();
           
             try
               {
               
                    File file=new File(filename);
                    BufferedImage image=ImageIO.read(file);
               
                    ImageIcon icon=new ImageIcon(image);
                    picture.setIcon(icon);
               
                    Path2D.Float regionOfInterest = new Path2D.Float();
        
                    Random r=new Random();
                    int x=(Math.abs(r.nextInt())%20)*10;
                    int y=(Math.abs(r.nextInt())%20)*10;
                   
               
                   regionOfInterest.moveTo(x,y);
                   regionOfInterest.lineTo(x+100.0,y);
                   regionOfInterest.lineTo(x+100.0,y+50.0);
                   regionOfInterest.lineTo(x,y+50.0);
                   regionOfInterest.closePath();
                   
        
                   Path2D.Float pathForWholeImage = new Path2D.Float();
                   pathForWholeImage.moveTo(0,0);
                   pathForWholeImage.lineTo(image.getWidth(),0);
                   pathForWholeImage.lineTo(image.getWidth(),image.getHeight());
                   pathForWholeImage.lineTo(0,image.getHeight());
                   pathForWholeImage.lineTo(0,0);
    
                   Area wholeImage = new Area(pathForWholeImage);
                   wholeImage.subtract(new Area(regionOfInterest));
                   
                   Graphics2D g2d = (Graphics2D)image.getGraphics();
                   g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                   g2d.setColor(new Color(255,255,255,100));
                   g2d.fill(wholeImage);
                   g2d.setStroke(new BasicStroke(5f));
                   g2d.setColor(new Color(255,0,0,200));
                   g2d.draw(regionOfInterest);
                   
           }
           catch(Exception ee)
           {
               
           }
            
             
            
        }
   
    }
    
    public MouseListener ml=new MouseAdapter()
    {
             

      
        public void mouseClicked(MouseEvent e) 
        {
            String filename=filenametext.getText();
           
            
            try
            { 
            File file=new File(filename);
            BufferedImage image=ImageIO.read(file);
               
            ImageIcon icon=new ImageIcon(image);
            picture.setIcon(icon);
            
                int x=e.getX();
                int y=e.getY();
                 
                 
                 int rgb=image.getRGB(x, y);
        
                 int a = (rgb >> 24) & 0xff;
                 int r= (rgb >> 16) & 0xff;
                 int g = (rgb >> 8) & 0xff;
                 int b= (rgb) & 0xff;
                 
                  
                 x_value.setText(String.valueOf(x));
                 y_value.setText(String.valueOf(y));
                 a_value.setText(String.valueOf(a));
                 r_value.setText(String.valueOf(r));
                 g_value.setText(String.valueOf(g));
                 b_value.setText(String.valueOf(b));
         
       
            }
            catch(Exception ee)
            {
                
            }
            
        }

      
        public void mousePressed(MouseEvent e) {
           
        }

        
        public void mouseReleased(MouseEvent e) {
           
        }

        public void mouseEntered(MouseEvent e) {
            
        }

        
        public void mouseExited(MouseEvent e) {
            
        }
   
            
            
            
            
    };
    
    public static void main(String arg[])
    {
        JFrame pixel=new selectpixel(); 
        pixel.setSize(1000,700);
        pixel.setVisible(true);
        pixel.setLocationRelativeTo(null);
        pixel.setTitle("Secured Banking");
    }    
}

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
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
class RetrieveImage
{
    
      public static  JLabel lab1=new JLabel();
      public static  JLabel lab2=new JLabel("Name");
      public static  JTextField text1=new JTextField(20);
      public static  JLabel lab3=new JLabel("Image");
      public static  JTextField text2=new JTextField(20);
      
      
      public static JLabel xvalue=new JLabel("X-Point");
      public static JLabel yvalue=new JLabel("Y-Point");
      public static JLabel avalue=new JLabel("Alpha");
      public static JLabel rvalue=new JLabel("Red");
      public static JLabel gvalue=new JLabel("Green");
      public static JLabel bvalue=new JLabel("Blue");
      
      
      public static JTextField x_value=new JTextField();
      public static JTextField y_value=new JTextField();
      public static JTextField a_value=new JTextField();
      public static JTextField r_value=new JTextField();
      public static JTextField g_value=new JTextField();
      public static JTextField b_value=new JTextField();
      
      
      public static JButton getimage=new JButton("Get Image");
      public static JButton Checkvalid=new JButton("Check Validity");
      public static JButton Browse=new JButton("Browse");
      public static JButton display=new JButton("Display");
      
      public static Image image;
      
      public static String name=null,file_name=null;
      public static JFrame f=new JFrame();
      
      public static void main(String[] args)throws SQLException
       {
           
        
           f.setLayout(null);
        
        
           getimage.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent ae)
                 {
                   try
                     {
            
                       String getname=text1.getText();
         
                       Class.forName("com.mysql.jdbc.Driver");
                       Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/image","root","");
                       Statement st=conn.createStatement();
                       ResultSet rs=st.executeQuery("select * from imagetable where Username='"+getname+"'");
                       
                       byte[] bytes = null;
                       if(rs.next())
                       {
                          
                           name=rs.getString(1);
                           file_name=rs.getString(2);
            
                           bytes = rs.getBytes(3);
                           image = f.getToolkit().createImage(bytes);
                           ImageIcon icon=new ImageIcon(image);
                           lab1.setIcon(icon);
                           text2.setText(file_name);
                       }
                    }
                  catch(Exception e)
                    {
            
                    }
               }
         }
        );
        
        lab1.addMouseMotionListener(new MouseMotionListener()
            {

                 public void mouseMoved(MouseEvent e)
                    {
                
                  }

            
             public void mousePressed(MouseEvent e) {
                 
                
               
              }

         
             public void mouseReleased(MouseEvent e) {
               
              }

           
             public void mouseEntered(MouseEvent e) {
               
              }

            
             public void mouseExited(MouseEvent e) {
               
              }

            @Override
            public void mouseDragged(MouseEvent e) {
                try
                         {
                             
                             String filename=text2.getText();
                            File file=new File(filename);
                            BufferedImage image=ImageIO.read(file);
                
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

            
            
            
                
           }       
      );
        
        
        Browse.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
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
                 text2.setText(field.getAbsolutePath());	    		  
           
                 }
                              
                  
            }
        }
                
                
                
                );
        
        
        display.addActionListener(new ActionListener()
        {
                public void actionPerformed(ActionEvent ae)
                {
                    
                
                String filename=text2.getText();
           
           try
           {
               File file=new File(filename);
               BufferedImage image=ImageIO.read(file);
               
               ImageIcon icon=new ImageIcon(image);
               lab1.setIcon(icon);
           }
            
          catch(Exception ee)
           {
            
           }
                
                
                
                
                
                
                
                
                
                }
                
        }       
                );
        
        Checkvalid.addActionListener(new ActionListener()
        {
                public void actionPerformed(ActionEvent ae)
                {
                   try
                   {
                       String name=text1.getText();
                       String path=text2.getText();
                       String x=x_value.getText();
                       String y=y_value.getText();
                       String a=a_value.getText();
                       String r=r_value.getText();
                       String g=g_value.getText();
                       String b=b_value.getText();
                       
                     Class.forName("com.mysql.jdbc.Driver");
                     Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/image","root","");
                     Statement st=conn.createStatement();
                     ResultSet rs=st.executeQuery("select * from imagetable where Username='"+name+"'");  
                      while(rs.next())
                      {
                       String username=rs.getString(1);
                       String xvalue=rs.getString(4);
                       String yvalue=rs.getString(5);
                       String avalue=rs.getString(6);
                       String rvalue=rs.getString(7);
                       String gvalue=rs.getString(8);
                       String bvalue=rs.getString(9);
                       
                       if((username.equals(name))&&(xvalue.equals(x))&&(yvalue.equals(y))&&(avalue.equals(a))&&(rvalue.equals(r))&&(gvalue.equals(g))&&(bvalue.equals(b)))
                       {
                           JOptionPane.showMessageDialog(null,"Matches");
                       }
                       
                      }
                   
                   
                   
                   }
                   catch(Exception ee)
                   {
                       
                   }
                }
                
        }       
        );
        
        
        
                
                
        lab1.setBounds(50,100,400,325);
        lab2.setBounds(470,100,100,20);
        text1.setBounds(590,100,100,20);
       

        lab3.setBounds(470,140,100,20);
        text2.setBounds(590,140,330,20);
        
        xvalue.setBounds(470,180,100,20);
        yvalue.setBounds(470,220,100,20);
        avalue.setBounds(470,260,100,20);
        rvalue.setBounds(470,300,100,20);
        gvalue.setBounds(470,340,100,20);
        bvalue.setBounds(470,380,100,20);
        
        x_value.setBounds(590,180,150,20);
        y_value.setBounds(590,220,150,20);
        a_value.setBounds(590,260,150,20);
        r_value.setBounds(590,300,150,20);
        g_value.setBounds(590,340,150,20);
        b_value.setBounds(590,380,150,20);
        
        getimage.setBounds(500,430,100,20);
        Browse.setBounds(940, 140, 100,20);
        display.setBounds(940,180,100,20);
        Checkvalid.setBounds(620,430,150,20);
        
        f.add(lab1);
        f.add(lab2);
        f.add(text1);
        f.add(lab3);
        f.add(text2);
        f.add(getimage);
        
        f.add(xvalue);
        f.add(yvalue);
        f.add(avalue);
        f.add(rvalue);
        f.add(gvalue);
        f.add(bvalue);
        
        f.add(x_value);
        f.add(y_value);
        f.add(a_value);
        f.add(r_value);
        f.add(g_value);
        f.add(b_value);
        f.add(Checkvalid);
        f.add(Browse);
        f.add(display);
        
        f.setVisible(true);
        f.setSize(1000,500);
    }
}
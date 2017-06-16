/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocess;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Administrator
 */
public class datecheck 
{
    public JLabel Name=new JLabel("Name");
    public JLabel Time=new JLabel("Time");
    
    public JTextField Name_Text=new JTextField();
    public JTextField Time_Text=new JTextField();
    
    public JButton insert=new JButton("Insert");
    
    JFrame frame=new JFrame();
    Container c;
    
    public static final String DATE_FORMAT_NOW = "dd-MM-yyyy HH:mm:ss";
    
    
   java.util.Date dt = new java.util.Date();

   java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

   String currentTime = sdf.format(dt);


   // Calendar currentDate = Calendar.getInstance();
    //    SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
    //    String dateNow = formatter.format(currentDate.getTime());
    
    datecheck()
    {
       c=frame.getContentPane();
       c.setLayout(null);
       c.setBackground(Color.LIGHT_GRAY);
       
       frame.setSize(500,300);
       frame.setVisible(true);
       
       
       
       Name.setBounds(150,100,100,30);
       Name_Text.setBounds(270,100,100,30);
       
       Time.setBounds(150, 150,100,30);
       Time_Text.setBounds(270, 150,100,30);
       
       insert.setBounds(170,190,100,30);
       
       c.add(Name);
       c.add(Name_Text);
       c.add(Time);
       c.add(Time_Text);
       c.add(insert);
       
       Time_Text.setText(currentTime);
       
       insert.addActionListener(new ActionListener()
       {
        public void actionPerformed(ActionEvent ae)
        {
            
            String name=Name_Text.getText();
            System.out.println(name);
            String time=Time_Text.getText();
            System.out.println(time);
            try
            {
              System.out.println("1");
              Class.forName("com.mysql.jdbc.Driver");
              System.out.println("2");
              Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/image","root","");
              System.out.println("3");
              
              PreparedStatement pstmt=conn.prepareStatement("insert into date_data values(?,?)");
              System.out.println("4");
              
              pstmt.setString(1,name);
              System.out.println("5");
              
              //Calendar cal = Calendar.getInstance();

              pstmt.setString(2, time);
              System.out.println("6");
              
              pstmt.executeUpdate();
              System.out.println("7");
               
               
               
               
            }
            catch(Exception e)
            {
                
            }
               
               
        }      
       }       
               );
    }
    
    public static void main(String[] arg)
    {
        new datecheck();
    }
}

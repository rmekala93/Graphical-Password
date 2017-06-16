/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocess;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Administrator
 */
public class UserRegister extends JFrame implements ActionListener
{
    public JLabel Username=new JLabel("UserName");
    public JLabel Name=new JLabel("Name");
    public JLabel Guardianname=new JLabel("Guardian Name");
    public JLabel Address=new JLabel("Address");
    public JLabel MobileNo=new JLabel("Mobile Number");
    public JLabel AccNo=new JLabel("Account Number");
    public JLabel Balance=new JLabel("Minimum Balance");
    public JLabel icon=new JLabel();
    
    
    public static JTextField Username_Text=new JTextField();
    public static JTextField Name_Text=new JTextField();
    public JTextField Guardian_Text=new JTextField();
    public JTextField Address_Text=new JTextField();
    public JTextField Mobile_Text=new JTextField();
    public static JTextField AccNo_Text=new JTextField();
    public JTextField Balance_Text=new JTextField();
    
   // public JTextField time=new JTextField();
    
    public JButton register=new JButton("Register");
    public JButton password=new JButton("Create Password");
    
    JFrame frame=new JFrame();
    Container c;
    
    ImageIcon image=new ImageIcon(this.getClass().getResource("bank.jpg"));
    
    java.util.Date dt = new java.util.Date();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentTime = sdf.format(dt);
   
    UserRegister()
    {
       c=frame.getContentPane();
       c.setLayout(null);
       c.setBackground(Color.LIGHT_GRAY);
       frame.setSize(800,850);
       frame.setVisible(true);
       frame.setTitle("Secured Banking");
       //frame.setLocationRelativeTo(null);
       
       Username.setBounds(250,335,100,25);
       Name.setBounds(250,380,100,25);
       Guardianname.setBounds(250,425,100,25);
       Address.setBounds(250,470,100,25);
       MobileNo.setBounds(250,515,100,25);
       AccNo.setBounds(250,560,100,25);
       Balance.setBounds(250,605,150,25);
       
       Username_Text.setBounds(390,335,130,25);
       Name_Text.setBounds(390,380,130,25);
       Guardian_Text.setBounds(390,425,130,25);
       Address_Text.setBounds(390,470,130,25);
       Mobile_Text.setBounds(390,515,130,25);
       AccNo_Text.setBounds(390,560,130,25);
       Balance_Text.setBounds(390,605,130,25);
       //time.setBounds(530,650,130,25);
       
       register.setBounds(270,650,100,25);
       password.setBounds(390,650,150,25);
       
       icon.setBounds(150,40,900,300);
       icon.setIcon(image);
       
       c.add(icon);
       c.add(Username);
       c.add(Name);
       c.add(Guardianname);
       c.add(Address);
       c.add(MobileNo);
       c.add(AccNo);
       c.add(Balance);
       
       c.add(Username_Text);
       c.add(Name_Text);
       c.add(Guardian_Text);
       c.add(Address_Text);
       c.add(Mobile_Text);
       c.add(AccNo_Text);
       c.add(Balance_Text);
       //c.add(time);
       
       c.add(register);
       c.add(password);
       
       //time.setText(currentTime);
       
       password.addActionListener(this);
       register.addActionListener(this);
      
       
    } 
      
    
    public static void main(String[] arg)
    {
        new UserRegister();
    }

 
    public void actionPerformed(ActionEvent e) 
    
    {
      if(e.getSource()==register)
      {
          
          String name=Name_Text.getText();
          String username=Username_Text.getText();
          String guardian=Guardian_Text.getText();
          String address=Address_Text.getText();
          String mobile=Mobile_Text.getText();
          String accno=AccNo_Text.getText();
          String balance=Balance_Text.getText();
          
          int ibalance=Integer.parseInt(balance);
         // String curtime=time.getText();
          try
          {
              
              Class.forName("com.mysql.jdbc.Driver");
         
              Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/image","root","");
             
              Statement st=conn.createStatement();
           
              String check="select * from imagetable";
          
              ResultSet rs=st.executeQuery(check);
            
              while(rs.next())
              {
                 
                  String user_name=rs.getString(1);
                  
                 
                 if(user_name.equals(username))
                  {
                    JOptionPane.showMessageDialog(this, "Username Already Exists", "Try Different Name",JOptionPane.INFORMATION_MESSAGE);
                  
                    Username_Text.setText(" ");
                  
                    
                            
                    break;
                  }
                      
                  
                 else if(ibalance<1000)
                     
                 {
                     JOptionPane.showMessageDialog(this,"Minimum Balance should be 1000");
                 }
                 else
                {
                    
                    File image = new File("Prepare\\GraphicalPassword\\Code\\imageProcess\\imageProcess\\src\\imageprocess\\build\\classes\\imageprocess\\flower6.jpg");
                    
                    FileInputStream fis = new FileInputStream(image);
                   
                 
            
                    PreparedStatement pstmt=conn.prepareStatement("insert into user_data values(?,?,?,?,?,?,?,?,?,?)");
                  
             
        
                    pstmt.setString(1,name);
                    pstmt.setString(2,guardian);
                    pstmt.setString(3,username);
                    pstmt.setString(4,"aa");
                    pstmt.setBinaryStream(5,(InputStream)fis,(int)(image.length()));
                    pstmt.setString(6,accno);
                    pstmt.setString(7,balance);
                    pstmt.setString(8,address);
                    pstmt.setString(9,mobile);
                    pstmt.setString(10,currentTime);
                    
            
                    pstmt.executeUpdate();
                    
           
              
              
                   PreparedStatement pstmt1=conn.prepareStatement("insert into transaction values(?,?,?,?,?,?)");
                  
           
                   pstmt1.setString(1, username);
                   pstmt1.setString(2, accno);
                   pstmt1.setString(3, balance);
                   pstmt1.setString(4, currentTime);
                   pstmt1.setString(5, balance);
                   pstmt1.setString(6, " ");
                   
              
              
                   pstmt1.executeUpdate();
                   System.out.println("33");              
              
              JOptionPane.showMessageDialog(null,"Data Registered");
              break;
              
                  }
          }
          }
          catch(Exception ee)
          {
              
          }
      }
    
      else if(e.getSource()==password)
      {
          this.hide();
          selectpixel select=new selectpixel();
          select.setSize(1000,800);
          select.show();
         
      }
    
 }
}

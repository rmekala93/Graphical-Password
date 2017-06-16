/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocess;

/**
 *
 * @author Administrator
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
public class withdrawform implements ActionListener,FocusListener {
    
    
    public JLabel Name=new JLabel("Name");
    public JLabel AccNo=new JLabel("Account No");
    public JLabel Date=new JLabel("Date");
    public JLabel Withdraw=new JLabel("Amount");
    public JLabel balance=new JLabel("Current Balance");
    public JLabel icon=new JLabel();
    
    public JTextField Name_Text=new JTextField();
    public JTextField AccNo_Text=new JTextField();
    public JTextField Date_Text=new JTextField();
    public JTextField Withdraw_Text=new JTextField();
    public JTextField Balance_Text=new JTextField();
    
    public JButton withdraw=new JButton("Withdraw");
    
    JFrame frame=new JFrame();
    Container c;
    
    ImageIcon image=new ImageIcon(this.getClass().getResource("bank.jpg"));
    
    java.util.Date dt = new java.util.Date();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentTime = sdf.format(dt);
    
    withdrawform(String username)
    {
       
       c=frame.getContentPane();
       c.setLayout(null);
       c.setBackground(Color.LIGHT_GRAY);
       frame.setSize(800,700);
       frame.setVisible(true);
       frame.setLocationRelativeTo(null);
       frame.setTitle("Secured Banking");
      
       Name.setBounds(250,335,100,25);
       AccNo.setBounds(250,380,100,25);
       Date.setBounds(250,425,100,25);
       Withdraw.setBounds(250,470,100,25);
       balance.setBounds(250,515,100,25);
       
       
       Name_Text.setBounds(390,335,130,25);
       AccNo_Text.setBounds(390,380,130,25);
       Date_Text.setBounds(390,425,130,25);
       Withdraw_Text.setBounds(390,470,130,25);
       Balance_Text.setBounds(390,515,130,25);
       
       
       
       withdraw.setBounds(310,575,100,25);
       
       
       icon.setBounds(150,40,900,300);
       icon.setIcon(image);
       
       c.add(icon);
       c.add(Name);
       c.add(AccNo);
       c.add(Date);
       c.add(Withdraw);
       c.add(balance);
     
       
       c.add(Name_Text);
       c.add(AccNo_Text);
       c.add(Date_Text);
       c.add(Withdraw_Text);
       c.add(Balance_Text);
       
       c.add(withdraw);
       
       
       
       
       
       
       
       
       Name_Text.setText(username);
       
       try
       {
           
           String name=Name_Text.getText();
           Class.forName("com.mysql.jdbc.Driver");
           Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/image","root","");
           
           Statement stmt=conn.createStatement();
           ResultSet rs=stmt.executeQuery("select AccountNo from user_data where Username='"+name+"'");
           
           while(rs.next())
           {
               String accnum=rs.getString(1);
               AccNo_Text.setText(accnum);
               
           }
       }
       catch(Exception ee)
       {
           
       }
       
       
       
       
       
       
       
       
       
       
       
       
       Date_Text.setText(currentTime);
       withdraw.addActionListener(this);
       Balance_Text.addFocusListener(this);
    }
    
    public static void main(String args[])
    {
      new withdrawform("Sai");  
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==withdraw)
        {
            
            String name=Name_Text.getText();
            String accno=AccNo_Text.getText();
            String date=Date_Text.getText();
            String withdraw=Withdraw_Text.getText();
            String balance=Balance_Text.getText();
            String amt=null;
            
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost/image","root","");
                
                Statement st=con.createStatement();
                String dep_amt="select Balance from user_data where Username='"+name+"'";
                ResultSet rs=st.executeQuery(dep_amt);
              
              while(rs.next())
              {
                   amt=rs.getString(1);
              }
              
              int iamt=Integer.parseInt(amt);
              int iwithdraw=Integer.parseInt(withdraw);
              
              int ibalance=iamt-iwithdraw;
              String dbalance=Integer.toString(ibalance);
              
              if(ibalance<=500)
              {
                  JOptionPane.showMessageDialog(null,"Sorry you can't withdraw amount");
              }
              
              else
              {
                
              PreparedStatement pstmt=con.prepareStatement("insert into withdraw values(?,?,?,?,?,?)");
                
                pstmt.setString(1,"aa");
                pstmt.setString(2,name);
                pstmt.setString(3,accno);
                pstmt.setString(4,date);
                pstmt.setString(5,withdraw);
                pstmt.setString(6,dbalance);
                
                pstmt.executeUpdate();
                
                
                PreparedStatement pstmt1=con.prepareStatement("update user_data set Balance=? where Username=?");
                
                pstmt1.setString(1,dbalance);
                pstmt1.setString(2,name);
                
                pstmt1.executeUpdate();
                
                
                PreparedStatement pstmt2=con.prepareStatement("insert into transaction values(?,?,?,?,?,?)");
                
                pstmt2.setString(1,name);
                pstmt2.setString(2, accno);
                pstmt2.setString(3," ");
                pstmt2.setString(4,date);
                pstmt2.setString(5," ");
                pstmt2.setString(6,withdraw);
                
                pstmt2.executeUpdate();
                
                
                
                JOptionPane.showMessageDialog(null,"Amount Withdrawn");
                
            }
            }
            catch(Exception ee)
            {
                
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        
        String amt=null;
        String name=Name_Text.getText();
        String withdraw=Withdraw_Text.getText();
        
        try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost/image","root","");
                
                Statement st=con.createStatement();
                String dep_amt="select Balance from user_data where Username='"+name+"'";
                ResultSet rs=st.executeQuery(dep_amt);
              
              while(rs.next())
              {
                   amt=rs.getString(1);
              }
              
              int iamt=Integer.parseInt(amt);
              int iwithdraw=Integer.parseInt(withdraw);
              
              int ibalance=iamt-iwithdraw;
              String dbalance=Integer.toString(ibalance);
              
              Balance_Text.setText(dbalance);
            }
        catch(Exception ee)
        {
            
        }
        
    }

    @Override
    public void focusLost(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

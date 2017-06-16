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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class transhistory implements ActionListener 
{
     
    public JFrame jf=new JFrame("Transaction History");
    Container c;
       
     JLabel labels[] = new JLabel[20];
     JLabel label1[] = new JLabel[20];
     JLabel label2[] = new JLabel[20];
     JLabel label3[] = new JLabel[20];
     
    
     JButton jb=new JButton("Transaction Details");
     
     public JLabel title = new JLabel("Transaction History");
     public JLabel userid=new JLabel("Username");
     public static JTextField useridtext=new JTextField();
        
     public JLabel date=new JLabel("Date");
     public JLabel credit=new JLabel("Credit");
     public JLabel debit=new JLabel("Debit");
     
     
     public JLabel name=new JLabel("Name");
     public JLabel accno=new JLabel("Account Number");
     public JLabel balance=new JLabel("Current Balance");
     
     public JLabel name_text=new JLabel("aa");
     public JLabel accno_text=new JLabel("aa");
     public JLabel balance_text=new JLabel("aa");
        
     Connection con;
     Statement stmt;     
     ResultSet rs,rs1;
     
     int cnt;
    
    public transhistory(String username)
    {
        
        
        c=jf.getContentPane();
        jf.setSize(1000,800);
        c.setLayout(null);
        jf.setVisible(true);
        jf.setTitle("Transaction History");
        c.setBackground(Color.BLACK);
        
        userid.setBounds(450,120,100,30);
                
        userid.setForeground(Color.WHITE);
                
        useridtext.setBounds(570,120,200,30);
                
        jb.setBounds(790,120,170,30);   
        
        name.setBounds(150,120,100,30);
        name.setForeground(Color.white);
        
        accno.setBounds(150,170,100,30);
        accno.setForeground(Color.white);
        
        balance.setBounds(150,220,100,30);
        balance.setForeground(Color.white);
                
         
        date.setBounds(360,270,150,30);
        date.setForeground(Color.WHITE);
         
        credit.setBounds(540,270,150,30);
        credit.setForeground(Color.WHITE);
         
        debit.setBounds(720,270,150,30);
        debit.setForeground(Color.WHITE);
        
        name_text.setBounds(270,120,100,30);
        name_text.setForeground(Color.white);
        
        accno_text.setBounds(270,170,100,30);
        accno_text.setForeground(Color.white);
        
        balance_text.setBounds(270,220,100,30);
        balance_text.setForeground(Color.white);
         
         
		
        int v1=320; 
        for (int i =  0; i < 10; i++) 
        {
            
          label1[i] = new JLabel(" ");
          label1[i].setBounds(360,v1,150,30);
          label1[i].setForeground(Color.WHITE);
          c.add(label1[i]);
          v1=v1+50;
        }
        
        int v2=320; 
        for (int i =  0; i < 10; i++) 
        {
             
          label2[i] = new JLabel(" ");
          label2[i].setBounds(540,v2,150,30);
          label2[i].setForeground(Color.WHITE);
          c.add(label2[i]);
          v2=v2+50;
        }
        
        
        int v3=320; 
        for (int i =  0; i < 10; i++) 
        {
           
         label3[i] = new JLabel(" ");
         label3[i].setBounds(720,v3,150,30);
         label3[i].setForeground(Color.WHITE);
         c.add(label3[i]);
         v3=v3+50;
        }
        
        
        
                c.add(date); 
                c.add(credit);   
                c.add(debit);   
                   
                c.add(title);
                c.add(userid);
                c.add(useridtext);
                c.add(jb);
                
                c.add(name);
                c.add(accno);
                c.add(balance);
                
                c.add(name_text);
                c.add(accno_text);
                c.add(balance_text);
                
                date.setVisible(false);
                credit.setVisible(false);
                debit.setVisible(false);
                name.setVisible(false);
                accno.setVisible(false);
                balance.setVisible(false);
                name_text.setVisible(false);
                accno_text.setVisible(false);
                balance_text.setVisible(false);
                
                useridtext.setText(username);
        
        jb.addActionListener(this);
        
    }
    public static void main(String[] args) 
    {
        
       new transhistory("Sai");
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        
        if(ae.getSource()==jb)
         {
             
            String name1=useridtext.getText();
            String date1=null,credit1=null,debit1=null;
            
            date.setVisible(true);
            credit.setVisible(true);
            debit.setVisible(true);
            
            name.setVisible(true);
            accno.setVisible(true);
            balance.setVisible(true);
            
            name_text.setVisible(true);
            accno_text.setVisible(true);
            balance_text.setVisible(true);
            
            
           
          try
           {
               
               Class.forName("com.mysql.jdbc.Driver");
              
               con=DriverManager.getConnection("jdbc:mysql://localhost/image","root","");
               stmt=con.createStatement();
               
               ResultSet rs2=stmt.executeQuery("select Name,AccountNo,Balance from user_data where Username='"+name1+"'");
               
               while(rs2.next())
               {
                   String uname=rs2.getString(1);
                   String accno=rs2.getString(2);
                   String balance=rs2.getString(3);
                   
                   name_text.setText(uname);
                   accno_text.setText(accno);
                   balance_text.setText(balance);
                   
                   
               }
             
               rs1=stmt.executeQuery("select count(Name) from transaction where Name='"+name1+"'");
            
            //  rs1=stmt.executeQuery("select count(Itemname) from itemdetail where TO_CHAR(Pdate,'YYYY/MM/DD')='5/11/2012'");
              
                  while(rs1.next())
                  {
                   cnt=rs1.getInt(1);
                  }
                  
               rs=stmt.executeQuery("select Date,Deposit,Withdraw from transaction where Name='"+name1+"'");
              
            //   rs=stmt.executeQuery("select Itemname,Nofitems,Price,Amount,TO_CHAR(Pdate,'YYYY/MM/DD') from itemdetail TO_CHAR(Pdate,'MM/DD/YYYY')='5/11/2012'");
               int i=0;
               
               while(rs.next()&& i<10)
                   {
                      
                       date1=rs.getString(1);
                       credit1=rs.getString(2);
                       debit1=rs.getString(3);
             
                       label1[i].setText(date1);
                       label2[i].setText(credit1);
                       label3[i].setText(debit1);
                        
                    i=i+1;
                     
                    }
                    
            }
           
           catch(Exception e)
           {
               
           }
          }
       }
    }



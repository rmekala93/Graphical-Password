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
import java.awt.Font;
import java.awt.event.*;
import java.lang.String.*;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;


public class process extends JFrame implements ActionListener
{
    
    
   
      JButton withdraw_amount;
      JButton deposit_amount;
      JButton trans_history;
      
      
      public JLabel title=new JLabel("Banking System");
        
      public Font l = new Font("Colonna MT" , Font.BOLD , 32);
    
      String username=test.text1.getText();
      
  public process()
    {
     JPanel panel = (JPanel) getContentPane();//to add all components to the panel
     panel.setLayout(null);//to set the setBounds value
     panel.setBackground(Color.BLACK);

    
    deposit_amount= new JButton("Deposit Amount");
    withdraw_amount= new JButton("Withdraw Amount");
    trans_history= new JButton("Transaction History");
    //view_locations= new JButton("View Locations");
    //view_staffs= new JButton("View Staffs");
    
    deposit_amount.setBounds(150,120,200,40);
    withdraw_amount.setBounds(150,170,200,40);
    trans_history.setBounds(150,220,200,40);
    //view_locations.setBounds(150,270,200,40);
    //view_staffs.setBounds(150,320,200,40);
    
    
    title.setBounds(100,50,300,30);
    title.setFont(l);
    title.setForeground(Color.WHITE);
       
    panel.add(deposit_amount);
    panel.add(withdraw_amount);
    panel.add(trans_history);
    //panel.add(view_locations);
    //panel.add(view_staffs);
    panel.add(title);
   
    deposit_amount.addActionListener(this);
    withdraw_amount.addActionListener(this);
    trans_history.addActionListener(this);
    //view_locations.addActionListener(this);
    //view_staffs.addActionListener(this);
    
    
    }
    

    public void actionPerformed(ActionEvent ae)
    {
       
        if(ae.getSource()==deposit_amount)
        {
            this.hide();
            new depositform(username);
            
        }
  
 
       else if(ae.getSource()==withdraw_amount)
       {
          this.hide();  
          new withdrawform(username);
       }
       
        else if(ae.getSource()==trans_history)
        {

            this.hide();
            //new transhistory("Sai");

            
        }
       
       
            
    }

  public static void main(String[] args) throws Exception
    {
    JFrame frame = new process();
   
    frame.setTitle("Secured Banking");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(500,500);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}


    

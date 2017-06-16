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
import javax.swing.*;

public class Login extends JFrame implements ActionListener 
{
    
    Container c;
    JFrame jf;
    JLabel image=new JLabel();
    JLabel title=new JLabel("Secured Banking");
    JButton admin=new JButton("New User");
    JButton user=new JButton("Registered User");
    public Font l = new Font("Colonna MT" , Font.BOLD , 32);
    
    Login()
    {
        jf=new JFrame();
        c=jf.getContentPane();
        c.setLayout(null);
        jf.setSize(500,500);
        jf.setVisible(true);
        jf.setTitle("Secured Banking");
        c.setBackground(Color.BLACK);
        
        
        
      ImageIcon v0 = new ImageIcon(this.getClass().getResource("login.jpg"));
      image.setIcon(v0);
      image.setBounds(110,70,530,260);
      
      title.setBounds(100,50,400,30);
      title.setForeground(Color.white);
      title.setFont(l);
      
      admin.setBounds(130,350,100,30);
      user.setBounds(260,350,150,30);
      
      c.add(admin);
      c.add(user);
      c.add(image);
      c.add(title);
      
      admin.addActionListener(this);
      user.addActionListener(this);
        
    }
public void actionPerformed(ActionEvent ae)
{
    if(ae.getSource()==admin)
    {
        this.hide();
        //selectpixel select=new selectpixel();
        //select.setSize(1000,800);
        //select.show();
        
        new UserRegister();
        
    }
    else if(ae.getSource()==user)
    { 
       // this.hide();
       new test();
       
        
        
    }

}
public static void main(String arg[])
{
    Login login=new Login();
    login.setLocationRelativeTo(null);
    
}
    
}
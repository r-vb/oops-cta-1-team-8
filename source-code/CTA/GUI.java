package CTA;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

 class GUI {
    JFrame frame;
    JPanel loginPanel;
    JComboBox WhoAreYou;
    String Users[] = {"--Select--","Clerk","Teacher","Student"};
    JButton Login;

    GUI(){
        frame = new JFrame("CTA-1 Student Teacher Clerk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,500);
        frame.setLayout(null);


        JLabel NFheadingLabel = new JLabel("Student Database");
        NFheadingLabel.setBounds(300, 10, 500, 60);
        NFheadingLabel.setFont(new Font("Serif", Font.BOLD, 35));
        NFheadingLabel.setForeground(Color.BLACK);



        //login panel for identifying user
        loginPanel = new JPanel();
        loginPanel.setBounds(10, 60, 865, 390);
        loginPanel.setLayout(null);
        loginPanel.setBackground(Color.gray);

        JLabel NFWhoAreYou = new JLabel("Who Are You?");
        NFWhoAreYou.setBounds(180, 100, 200, 50);
        NFWhoAreYou.setFont(new Font("Serif", Font.BOLD, 30));
        NFWhoAreYou.setForeground(Color.BLACK);


        WhoAreYou = new JComboBox(Users);
        WhoAreYou.setFont(new Font("Serif", Font.BOLD, 20));
        WhoAreYou.setBounds(160, 100, 200, 40);
        WhoAreYou.setForeground(Color.BLACK);

        WhoAreYou.setBounds(500, 108, 200, 40);

        Login = new JButton("Login");
        Login.setFont(new Font("Serif", Font.BOLD, 20));
        Login.setBounds(370, 200, 100, 40);
        Login.setForeground(Color.BLACK);

        Login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (WhoAreYou.getSelectedItem().equals("--Select--")) {
                    JOptionPane.showMessageDialog(frame, "Please Select one of the choices ", "Error", JOptionPane.ERROR_MESSAGE);                    
                } 

                if (WhoAreYou.getSelectedItem().equals("Clerk")) {
                    new Clerk();
                    frame.dispose();
                } 

                if (WhoAreYou.getSelectedItem().equals("Teacher")) {
                    new Teacher();
                    frame.dispose();
                } 

                if (WhoAreYou.getSelectedItem().equals("Student")) {
                    new Student();
                    frame.dispose();
                } 
            }
            });
             

        loginPanel.add(Login);
        loginPanel.add(WhoAreYou);
        loginPanel.add(NFWhoAreYou);

        
        
    
        frame.add(NFheadingLabel);  //this stays throughout 
        frame.add(loginPanel);
        frame.setVisible(true);
    }





    public static void main(String[] args) {
        new GUI();
    }





    
}






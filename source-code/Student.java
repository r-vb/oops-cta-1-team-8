package CTA;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Student {
    JFrame StudentFrame;
    JPanel StudentPanel;
    JButton checkMarks,GoHome;
    
    Student(){
        StudentFrame = new JFrame("Student Window ");
        StudentFrame.setSize(900,500);
        StudentFrame.setVisible(true);
        StudentFrame.setLayout(null);

        JLabel NFheadingLabel = new JLabel("Student Database");
        NFheadingLabel.setBounds(300, 10, 500, 60);
        NFheadingLabel.setFont(new Font("Serif", Font.BOLD, 35));
        NFheadingLabel.setForeground(Color.BLACK);

        StudentPanel = new JPanel();
        StudentPanel.setBounds(10, 60, 865, 390);
        StudentPanel.setLayout(null);
        StudentPanel.setBackground(Color.gray);
        



        checkMarks = new JButton("Check Marks" );
        checkMarks.setBounds(250,100,300,50);
        checkMarks.setFont(new Font("Serif", Font.BOLD, 20));
        checkMarks.setForeground(Color.BLACK);
        

        GoHome = new JButton("Go Back to HOME" );
        GoHome.setBounds(250,210,300,50);
        GoHome.setFont(new Font("Serif", Font.BOLD, 20));
        GoHome.setForeground(Color.BLACK);



        StudentPanel.add(GoHome);
        StudentPanel.add(checkMarks);



        StudentFrame.add(NFheadingLabel);
        StudentFrame.add(StudentPanel);
        


        checkMarks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                File file = new File("student.txt");

                String rollnumber = JOptionPane.showInputDialog(StudentFrame,"Enter your Roll Number");
                String fields[] = null;;
                Boolean flag=false;
                
                try {
                    Scanner reader = new Scanner(file);
                    while( reader.hasNextLine() ) {
                        String line = reader.nextLine();
                        fields = line.split(",");
                        if( fields[0].equals(rollnumber)){
                            flag = true;
                            break;
                        }
                    }
                    reader.close();
                } 
                catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                
                if( flag == true ){
                    JOptionPane.showMessageDialog(StudentFrame, "Roll number : " + fields[0] +"\nName : " + fields[1] +"\nIA1 : " + fields[2] +"\nIA2 : " + fields[3] + "\nIA3 : " + fields[4] + "\nCTA : " + fields[5] + "\nCIE: " + fields[6] , "Marks", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(StudentFrame, "Student not found", "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });





        GoHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                StudentFrame.dispose();
                new GUI();
            }
        });


    }

}

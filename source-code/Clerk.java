


package CTA;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Clerk {
    JFrame ClerkFrame;
    JButton AddStudent, DeleteStudent, GoHome;
    JPanel ClerkPanel;
    Clerk(){
        ClerkFrame = new JFrame("Clerk Window ");
        ClerkFrame.setSize(900,500);
        ClerkFrame.setVisible(true);

        JLabel NFheadingLabel = new JLabel("Student Database");
        NFheadingLabel.setBounds(300, 10, 500, 60);
        NFheadingLabel.setFont(new Font("Serif", Font.BOLD, 35));
        NFheadingLabel.setForeground(Color.BLACK);


        ClerkPanel = new JPanel();
        ClerkPanel.setBounds(10, 60, 865, 390);
        ClerkPanel.setLayout(null);
        ClerkPanel.setBackground(Color.gray);


        AddStudent = new JButton("Add Student to Database" );
        AddStudent.setBounds(250,70,300,50);
        AddStudent.setFont(new Font("Serif", Font.BOLD, 20));
        AddStudent.setForeground(Color.BLACK);

        DeleteStudent = new JButton("Delete Student from Database" );
        DeleteStudent.setBounds(250,160,300,50);
        DeleteStudent.setFont(new Font("Serif", Font.BOLD, 20));
        DeleteStudent.setForeground(Color.BLACK);

        GoHome = new JButton("Go Back to HOME" );
        GoHome.setBounds(250,250,300,50);
        GoHome.setFont(new Font("Serif", Font.BOLD, 20));
        GoHome.setForeground(Color.BLACK);

        ClerkPanel.add(AddStudent);
        ClerkPanel.add(DeleteStudent);
        ClerkPanel.add(GoHome);

        ClerkFrame.add(ClerkPanel);
        ClerkFrame.add(NFheadingLabel);
        ClerkFrame.setLayout(null);


        AddStudent.addActionListener(new ActionListener()  {
            public void actionPerformed(ActionEvent e) {
               File MyFile = new File("student.txt");
               int rollNumber = Integer.parseInt(JOptionPane.showInputDialog(ClerkFrame,"Enter student roll number: "));
               String name = JOptionPane.showInputDialog(ClerkFrame,"Enter student name: ");

               try {
                    Scanner Filereader = new Scanner(MyFile);
                    Boolean flag = false;
                    while( Filereader.hasNextLine()) {
                        String line = Filereader.nextLine();
                        String Fileds[] = line.split(",");
                        //if roll number is repeated
                        if( rollNumber ==  Integer.parseInt(Fileds[0]) ) {
                            flag = true;
                            JOptionPane.showMessageDialog(ClerkFrame, "Student already registered", "Warning", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                    Filereader.close();


                    if( flag == false) {
                        try {
                            FileWriter MyWriter = new FileWriter(MyFile,  true);
                            MyWriter.write(rollNumber + "," + name + ",0,0,0,0,0\n");
                            MyWriter.close();

                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        
                    }

               }
               catch (FileNotFoundException e1) {
                    e1.printStackTrace();
               }
            }
        });




        DeleteStudent.addActionListener(new ActionListener()  {
            public void actionPerformed(ActionEvent e) {
               File MyFile = new File("student.txt");
               String rollNumber = JOptionPane.showInputDialog(ClerkFrame,"Enter student roll number: ");
               
               boolean flag = false;
               int i=0;

               try {
                //extreme jugaad technique to delete a line from a file
                    Scanner Filereader = new Scanner(MyFile);
                    while( Filereader.hasNextLine()) {
                        String line = Filereader.nextLine();
                        String field[] = line.split(",");
                        if( field[0].equals(rollNumber)){
                            flag = true;
                        }
                        i++;
                    }
                    Filereader.close(); //closing to get back to first line



                    int j=0;
                    //copy file contents to a string array
                    Scanner copyreader = new Scanner(MyFile);
                    String copyoffile[] = new String[i];
                    while(copyreader.hasNextLine()){
                        copyoffile[j] = copyreader.nextLine();
                        j++;
                    }


                    
            
                    if( flag == true ){
                        FileWriter blankWriter = new FileWriter(MyFile,false);
                        blankWriter.write("");
                        blankWriter.close();


                        FileWriter writer = new FileWriter(MyFile, true);
                        int k =0;
                        //now write back to file except the student to be deleted
                        while( k < copyoffile.length ){
                            
                            String fields[] = copyoffile[k].split(",");
                            
                            
                            if(fields[0].equals(rollNumber) )  
                            {
                                JOptionPane.showMessageDialog(ClerkFrame, "Student records deleted\nRoll number : " + fields[0] + "\nName: "+ fields[1] , "Successful",JOptionPane.INFORMATION_MESSAGE);
                                k++;
                                continue;
                            }
                            else{
                                writer.write(copyoffile[k] + "\n");
                                k++;
                            }
                        }
                        writer.close();
                    }
                    else{
                        JOptionPane.showMessageDialog(ClerkFrame, "Student not found\nRoll Number: " + rollNumber , "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }   

               }
               catch (FileNotFoundException e1) {
                    e1.printStackTrace();
               }
               catch (IOException e1) {
                    e1.printStackTrace();
               }
            }
        });



        

        GoHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ClerkFrame.dispose();
                new GUI();
            }
        });
    }
}

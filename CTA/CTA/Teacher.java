package CTA;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JTable;

public class Teacher {
    JFrame TeacherFrame,displayFrame2,displayFrame3,displayFrame4,displayFrame5,displayFrame6;
    JPanel TeacherPanel; 
    JButton updateMarks,checkMarks,topPerformers,GoHome;


    Teacher(){
        TeacherFrame = new JFrame("Teacher Window ");
        TeacherFrame.setSize(900,500);
        TeacherFrame.setVisible(true);

        JLabel NFheadingLabel = new JLabel("Student Database");
        NFheadingLabel.setBounds(300, 10, 500, 50);
        NFheadingLabel.setFont(new Font("Serif", Font.BOLD, 30));
        NFheadingLabel.setForeground(Color.BLACK);


        TeacherPanel = new JPanel();
        TeacherPanel.setBounds(10, 60, 865, 390);
        TeacherPanel.setLayout(null);
        TeacherPanel.setBackground(Color.gray);



        updateMarks = new JButton("Update Student Marks" );
        updateMarks.setBounds(250,50,300,40);
        updateMarks.setFont(new Font("Serif", Font.BOLD, 20));
        updateMarks.setForeground(Color.BLACK);

        checkMarks = new JButton("Check Student Marks" );
        checkMarks.setBounds(250,130,300,40);
        checkMarks.setFont(new Font("Serif", Font.BOLD, 20));
        checkMarks.setForeground(Color.BLACK);


        topPerformers = new JButton("Display Top Performers" );
        topPerformers.setBounds(250,210,300,40);
        topPerformers.setFont(new Font("Serif", Font.BOLD, 20));
        topPerformers.setForeground(Color.BLACK);

        GoHome = new JButton("Go Back to HOME" );
        GoHome.setBounds(250,290,300,40);
        GoHome.setFont(new Font("Serif", Font.BOLD, 20));
        GoHome.setForeground(Color.BLACK);

        TeacherPanel.add(checkMarks);
        TeacherPanel.add(topPerformers);
        TeacherPanel.add(updateMarks);
        TeacherPanel.add(GoHome);


        TeacherFrame.add(TeacherPanel);
        TeacherFrame.add(NFheadingLabel);
        TeacherFrame.setLayout(null);


        updateMarks.addActionListener(new ActionListener()  {
            public void actionPerformed(ActionEvent e) {

                String rollNumber = JOptionPane.showInputDialog(TeacherFrame,"Enter Student Roll number ");
                File myfile =  new File("student.txt");


                //load the data in an array
                int i=0,index=0;
                Scanner Filereader;

                try {
                    int count=0;
                    Scanner counter = new Scanner(myfile);
                    while( counter.hasNextLine()) {
                        counter.nextLine();
                        count++;
                    }
                    counter.close();





                String copyoffile[] = new String[count];
                String fields[] = new String[7];
                boolean flag = false;
                
                    Filereader = new Scanner(myfile);
                     while( Filereader.hasNextLine()) {
                        copyoffile[i] = Filereader.nextLine();
                        String tempfields[] = copyoffile[i].split(",");
                        if( rollNumber.equals(tempfields[0])){
                            flag = true;
                            int r=0;
                            while (r <  tempfields.length ){
                                fields[r] = tempfields[r];
                                r++;
                            }
                            index = i;
                        }
                        i++;
                    }
                    Filereader.close();


                    System.out.println(Arrays.toString(fields));
                    if( flag == true ){
                        
                        String choice =  JOptionPane.showInputDialog(TeacherFrame,"Select choice\n1 for IA1 \n2 for IA2 \n3 for IA3 \n4 for CTA ");
                        
                        if( Integer.parseInt(choice) <1  ||  Integer.parseInt(choice) >4){
                            JOptionPane.showMessageDialog(TeacherFrame, "Select valid choice", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }


                        
                        String marks =  JOptionPane.showInputDialog(TeacherFrame,"Enter marks ");

                        if( Integer.parseInt(marks) < 0 || Integer.parseInt(marks) >20 ){
                            JOptionPane.showMessageDialog(TeacherFrame, "Marks out of range( 0 - 20 )", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        if( choice.equals("1") ){
                            fields[2] = marks;
                        }
                        if( choice.equals("2") ){
                            fields[3] = marks;
                        }
                        if( choice.equals("3") ){
                            fields[4] = marks;
                        }
                        if( choice.equals("4") ){
                            fields[5] = marks;
                        }

                        //CIE logic
                        int IA1 = Integer.parseInt(fields[2]);
                        int IA2 = Integer.parseInt(fields[3]);
                        int IA3 = Integer.parseInt(fields[4]);

                        int sum = IA1 + IA2 + IA3 ;
                        int small = IA1;
                        if ( small > IA2 )
                            small = IA2;
                        if ( small > IA3 )
                            small = IA3;
                        int cie = IA1 + IA2 + IA3 - small + Integer.parseInt(fields[5]);


                        //write back to file
                        copyoffile[index] = fields[0]+ ","+ fields[1]+ "," + fields[2]+ "," + fields[3]+ "," + fields[4]+ "," + fields[5] + ","  +cie   ;


                        //first clear the old file
                        FileWriter clearer = new FileWriter(myfile,false);
                        clearer.write("");
                        clearer.close();

                        FileWriter updater = new FileWriter(myfile, true);

                        int v=0;
                        while( v <  copyoffile.length){
                            updater.write(copyoffile[v] + "\n");
                            v++;
                        }

                        updater.close();



                    }

                    else{
                        JOptionPane.showMessageDialog(TeacherFrame, "Student not found", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }



                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                catch( IOException e1){
                    e1.printStackTrace();
                }
                
            }


           

        });


        checkMarks.addActionListener(new ActionListener()  {
            public void actionPerformed(ActionEvent e) { 
                String rollNumber = JOptionPane.showInputDialog(TeacherFrame,"Enter student roll number");
                    

                boolean flag = false;
                File myfile = new File("student.txt");
                Scanner Filereader;
                try {
                    
                    String fields[] = new String[7];
                    Filereader = new Scanner(myfile);
                    while( Filereader.hasNextLine()) {
                       String lines = Filereader.nextLine();
                       fields = lines.split(",");
                       System.out.println(fields[1]);
                       if( rollNumber.equals(fields[0])){
                           flag = true;
                           break;
                       }
                   }

                   Filereader.close();

                   if( flag == true ){
                        String choice = JOptionPane.showInputDialog(TeacherFrame,"Select choice\n  1 for IA1 \n 2 for IA2 \n 3 for IA3 \n 4 for CTA \n 5 for CIE ");
                        if( choice.equals("1") ){
                            JOptionPane.showMessageDialog(TeacherFrame, "IA1 marks " + fields[2] , "Info" , JOptionPane.INFORMATION_MESSAGE);
                        }
                        if( choice.equals("2") ){
                            JOptionPane.showMessageDialog(TeacherFrame, "IA2 marks " + fields[3] , "Info" , JOptionPane.INFORMATION_MESSAGE);

                        }
                        if( choice.equals("3") ){
                            JOptionPane.showMessageDialog(TeacherFrame, "IA3 marks " + fields[4] , "Info" , JOptionPane.INFORMATION_MESSAGE);

                        }
                        if( choice.equals("4") ){
                            JOptionPane.showMessageDialog(TeacherFrame, "CTA marks " + fields[5] , "Info" , JOptionPane.INFORMATION_MESSAGE);
                        
                        }
                        if( choice.equals("5") ){
                            JOptionPane.showMessageDialog(TeacherFrame, "CIE marks " + fields[6] , "Info" , JOptionPane.INFORMATION_MESSAGE);
                        
                        }
                   
                    }
                    else{
                        JOptionPane.showMessageDialog(TeacherFrame, "Student not found", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        

        topPerformers.addActionListener(new ActionListener()  {
            public void actionPerformed(ActionEvent e) { 
                String choice  =  JOptionPane.showInputDialog(TeacherFrame,"Select Top Performers List Based On:\n 1 for IA-1\n 2 for IA-2\n 3 for IA-3\n 4 for CTA\n 5 for CIE");
                String limit  =  JOptionPane.showInputDialog(TeacherFrame,"Enter number of Toppers to View");
                String real[][] = null;
                int count=0;

                File myfile = new File("student.txt");
                Scanner filereader;
                try {
                    filereader = new Scanner(myfile);

                    //get no of students in file

                    while( filereader.hasNextLine()) {
                        count++;
                        filereader.nextLine();
                    }

                    filereader.close();
                    
                    real = new String[count][7];

                                
                    filereader = new Scanner(myfile);

                    int i=0;
                    while( filereader.hasNextLine() ){
                        String line = filereader.nextLine();
                        String fields[] = line.split(",");
                        for ( int j=0; j<fields.length ; j++ ){
                            real[i][j] = fields[j];
                        }
                        i++;
                    }
                    filereader.close();                                
                } 
                catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }


                if( Integer.parseInt(limit) > count ){
                    JOptionPane.showMessageDialog(TeacherFrame, "There are only " + count +" students", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                //print Tooppers
                if (choice.equals("1") ){
                    //IA1 toppers
                    String copyofreal[][] = new String[real.length][];

                    for( int i=0; i<real.length; i++ ){
                        copyofreal[i] = real[i].clone();
                    }
                    
                    //sort
                    for( int i =0;  i< copyofreal.length -1  ; i++ ){
                        for( int j=0;  j<copyofreal.length -i-1 ; j++){
                            if( Integer.parseInt(copyofreal[j+1][2]) > Integer.parseInt(copyofreal[j][2]) )  {
                                String temp[];
                                temp = copyofreal[j];
                                copyofreal[j] = copyofreal[j+1];
                                copyofreal[j+1] = temp;
                            }
                        }
                    }
                    
                    displayFrame2 = new JFrame("IA1 toppers");
                    displayFrame2.setVisible(true);
                    displayFrame2.setSize(400, 400);
                    displayFrame2.setLayout(null);


                    String data[][] = new String[Integer.parseInt(limit) + 1][3];

                    data[0][0] = "Roll No.";
                    data[0][1] = "Name";
                    data[0][2] = "Marks";


                    for( int i=0; i<Integer.parseInt(limit); i++){
                        data[i+1][0] = copyofreal[i][0];
                        data[i+1][1] = copyofreal[i][1];
                        data[i+1][2] = copyofreal[i][2];
                    }


                    String column[] = {"roll","name","marks"};
                    JTable displayTable = new JTable(data, column);
                    displayTable.setBounds(0, 0, 400, 400);
                        
   
                    displayFrame2.add(displayTable);
                    
                        //System.out.printf("%-12s | %5s | %5s\n",copyofreal[i][0],copyofreal[i][1],copyofreal[i][2]);
            
                }




                if (choice.equals("2") ){
                    //IA2 toppers
                    String copyofreal[][] = new String[real.length][];

                    for( int i=0; i<real.length; i++ ){
                        copyofreal[i] = real[i].clone();
                    }
                    
                    //sort
                    for( int i =0;  i< copyofreal.length -1  ; i++ ){
                        for( int j=0;  j<copyofreal.length -i-1 ; j++){
                            if( Integer.parseInt(copyofreal[j+1][3]) > Integer.parseInt(copyofreal[j][3]) )  {
                                String temp[];
                                temp = copyofreal[j];
                                copyofreal[j] = copyofreal[j+1];
                                copyofreal[j+1] = temp;
                            }
                        }
                    }
                    
                    displayFrame3 = new JFrame("IA2 toppers");
                    displayFrame3.setVisible(true);
                    displayFrame3.setSize(400, 400);
                    displayFrame3.setLayout(null);


                    String data[][] = new String[Integer.parseInt(limit) + 1][3];

                    data[0][0] = "Roll No.";
                    data[0][1] = "Name";
                    data[0][2] = "Marks";


                    for( int i=0; i<Integer.parseInt(limit); i++){
                        data[i+1][0] = copyofreal[i][0];
                        data[i+1][1] = copyofreal[i][1];
                        data[i+1][2] = copyofreal[i][3];
                    }


                    String column[] = {"roll","name","marks"};
                    JTable displayTable = new JTable(data, column);
                    displayTable.setBounds(0, 0, 400, 400);
                        
   
                    displayFrame3.add(displayTable);
                    
                        //System.out.printf("%-12s | %5s | %5s\n",copyofreal[i][0],copyofreal[i][1],copyofreal[i][3]);
            
                }



                if (choice.equals("3") ){
                    //IA3 toppers
                    String copyofreal[][] = new String[real.length][];

                    for( int i=0; i<real.length; i++ ){
                        copyofreal[i] = real[i].clone();
                    }
                    
                    //sort
                    for( int i =0;  i< copyofreal.length -1  ; i++ ){
                        for( int j=0;  j<copyofreal.length -i-1 ; j++){
                            if( Integer.parseInt(copyofreal[j+1][4]) > Integer.parseInt(copyofreal[j][4]) )  {
                                String temp[];
                                temp = copyofreal[j];
                                copyofreal[j] = copyofreal[j+1];
                                copyofreal[j+1] = temp;
                            }
                        }
                    }
                    
                    displayFrame4 = new JFrame("IA3 toppers");
                    displayFrame4.setVisible(true);
                    displayFrame4.setSize(400, 400);
                    displayFrame4.setLayout(null);


                    String data[][] = new String[Integer.parseInt(limit) + 1][3];

                    data[0][0] = "Roll No.";
                    data[0][1] = "Name";
                    data[0][2] = "Marks";


                    for( int i=0; i<Integer.parseInt(limit); i++){
                        data[i+1][0] = copyofreal[i][0];
                        data[i+1][1] = copyofreal[i][1];
                        data[i+1][2] = copyofreal[i][4];
                    }


                    String column[] = {"roll","name","marks"};
                    JTable displayTable = new JTable(data, column);
                    displayTable.setBounds(0, 0, 400, 400);
                        
   
                    displayFrame4.add(displayTable);
                    
                        //System.out.printf("%-12s | %5s | %5s\n",copyofreal[i][0],copyofreal[i][1],copyofreal[i][4]);
            
                }



                if (choice.equals("4") ){
                    //CTA toppers
                    String copyofreal[][] = new String[real.length][];

                    for( int i=0; i<real.length; i++ ){
                        copyofreal[i] = real[i].clone();
                    }
                    
                    //sort
                    for( int i =0;  i< copyofreal.length -1  ; i++ ){
                        for( int j=0;  j<copyofreal.length -i-1 ; j++){
                            if( Integer.parseInt(copyofreal[j+1][5]) > Integer.parseInt(copyofreal[j][5]) )  {
                                String temp[];
                                temp = copyofreal[j];
                                copyofreal[j] = copyofreal[j+1];
                                copyofreal[j+1] = temp;
                            }
                        }
                    }
                    
                    displayFrame5 = new JFrame("CTA toppers");
                    displayFrame5.setVisible(true);
                    displayFrame5.setSize(400, 400);
                    displayFrame5.setLayout(null);


                    String data[][] = new String[Integer.parseInt(limit) + 1][3];

                    data[0][0] = "Roll No.";
                    data[0][1] = "Name";
                    data[0][2] = "Marks";


                    for( int i=0; i<Integer.parseInt(limit); i++){
                        data[i+1][0] = copyofreal[i][0];
                        data[i+1][1] = copyofreal[i][1];
                        data[i+1][2] = copyofreal[i][5];
                    }


                    String column[] = {"roll","name","marks"};
                    JTable displayTable = new JTable(data, column);
                    displayTable.setBounds(0, 0, 400, 400);
                        
   
                    displayFrame5.add(displayTable);
                    
                        //System.out.printf("%-12s | %5s | %5s\n",copyofreal[i][0],copyofreal[i][1],copyofreal[i][5]);
                }



                if (choice.equals("5") ){
                    //CIE toppers
                    String copyofreal[][] = new String[real.length][];

                    for( int i=0; i<real.length; i++ ){
                        copyofreal[i] = real[i].clone();
                    }
                    
                    //sort
                    for( int i =0;  i< copyofreal.length -1  ; i++ ){
                        for( int j=0;  j<copyofreal.length -i-1 ; j++){
                            if( Integer.parseInt(copyofreal[j+1][6]) > Integer.parseInt(copyofreal[j][6]) )  {
                                String temp[];
                                temp = copyofreal[j];
                                copyofreal[j] = copyofreal[j+1];
                                copyofreal[j+1] = temp;
                            }
                        }
                    }


                    displayFrame6 = new JFrame("CIE toppers");
                    displayFrame6.setVisible(true);
                    displayFrame6.setSize(400, 400);
                    displayFrame6.setLayout(null);


                    String data[][] = new String[Integer.parseInt(limit) + 1][3];

                    data[0][0] = "Roll No.";
                    data[0][1] = "Name";
                    data[0][2] = "Marks";


                    for( int i=0; i<Integer.parseInt(limit); i++){
                        data[i+1][0] = copyofreal[i][0];
                        data[i+1][1] = copyofreal[i][1];
                        data[i+1][2] = copyofreal[i][6];
                    }


                    String column[] = {"roll","name","marks"};
                    JTable displayTable = new JTable(data, column);
                    displayTable.setBounds(0, 0, 400, 400);
                        
   
                    displayFrame6.add(displayTable);
                    
                        //System.out.printf("%-12s | %5s | %5s\n",copyofreal[i][0],copyofreal[i][1],copyofreal[i][6]);
                    
            
                }

            }
        });



        GoHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                TeacherFrame.dispose();
                
            try{
                displayFrame2.dispose();
                displayFrame3.dispose();
                displayFrame4.dispose();
                displayFrame5.dispose();
                displayFrame6.dispose();
            } catch(NullPointerException e1) {
                try{
                    displayFrame3.dispose();
                    displayFrame4.dispose();
                    displayFrame5.dispose();
                    displayFrame6.dispose();
                } catch(NullPointerException e2){
                        try{
                            displayFrame4.dispose();
                            displayFrame5.dispose();
                            displayFrame6.dispose();
                        } catch(NullPointerException e3){
                                try{
                                    displayFrame5.dispose();
                                    displayFrame6.dispose();
                                } catch(NullPointerException e4){
                                        try{
                                            displayFrame6.dispose();
                                        } catch(NullPointerException e5){
                                            new GUI();
                                        }
                                }
                        }
                }
            }
            }
        });



    }

}

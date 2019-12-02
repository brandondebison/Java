import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ArrayPerson extends JFrame implements ActionListener {

// Buttons
private JButton update = new JButton("Update");
private JButton prePerson = new JButton("Previous");
private JButton nextPerson = new JButton("Next");
private JButton prePosition = new JButton("Previous");
private JButton nextPosition = new JButton("Next");
private JButton upload = new JButton("Upload");

//Labels
private JLabel FirstName = new JLabel("First Name: ");
private JLabel FirstNameL = new JLabel("");

private JLabel LastName = new JLabel("Last Name: ");
private JLabel LastNameL = new JLabel("");

private JLabel Age = new JLabel("Age: ");
private JLabel AgeL = new JLabel("");

private JLabel Grade = new JLabel("Grade: ");
private JLabel GradeL = new JLabel("");

private JLabel Program = new JLabel("Program: ");
private JLabel ProgramL = new JLabel("");

private JLabel InfoLabel = new JLabel("Update Section");
private JLabel information = new JLabel("output");
private JLabel Person = new JLabel("Person");
private JLabel Position = new JLabel("Position");
private JLabel Input = new JLabel("Input: ");

//Text Fields
private JTextField tb_update1 = new JTextField(20);
private JTextField tb_update2 = new JTextField(20);
private JTextField tb_update3 = new JTextField(20);
private JTextField tb_update4 = new JTextField(20);
private JTextField tb_update5 = new JTextField(20);


//Panels
private JPanel blankPanel1 = new JPanel();
private JPanel blankPanel2 = new JPanel();
private JPanel blankPanel3 = new JPanel();
private JPanel blankPanel4 = new JPanel();
private JPanel blankPanel5 = new JPanel();
private JPanel blankPanel6 = new JPanel();
private JPanel blankPanel7 = new JPanel();
private JPanel blankPanel8 = new JPanel();
private JPanel blankPanel9 = new JPanel();
private JPanel blankPanel10 = new JPanel();
private JPanel blankPanel11 = new JPanel();
private JPanel blankPanel12 = new JPanel();
private JPanel blankPanel13 = new JPanel();
private JPanel blankPanel14 = new JPanel();
private JPanel blankPanel15 = new JPanel();
private JPanel blankPanel16 = new JPanel();
private JPanel blankPanel17 = new JPanel();
private JPanel blankPanel18 = new JPanel();
private JPanel blankPanel19 = new JPanel();


    
static int counter = 0;
static int N = 5;

static Person[] arrayOfFive = new Person[N];


//Container
private Container content = getContentPane();

    

    public ArrayPerson(){
        int start = 0;

        for ( start = 0; start<N; start++ ) {
            arrayOfFive[start] = new Person();
        };
        
        
        
        //Person[] arrayOfFive = new Person[4];

        
        




        /*
        
        FirstNameL.setText(arrayOfFive[i].getPersonFName());
        LastNameL.setText(arrayOfFive[i].getPersonLName());
        AgeL.setText(Integer.toString(arrayOfFive[i].getPersonAge()));
        GradeL.setText(Double.toString(arrayOfFive[i].getPersonGrade()));
        ProgramL.setText(arrayOfFive[i].getPersonProgram());
        */

        

        
        setSize(750,800);
        content.setBackground(Color.white);
        
        
        content.setLayout(new GridLayout(8,5));
        FirstName.setHorizontalAlignment(SwingConstants.CENTER);
        LastName.setHorizontalAlignment(SwingConstants.CENTER);
        Age.setHorizontalAlignment(SwingConstants.CENTER);
        Grade.setHorizontalAlignment(SwingConstants.CENTER);
        Program.setHorizontalAlignment(SwingConstants.CENTER);
        InfoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        Input.setHorizontalAlignment(SwingConstants.RIGHT);
        Person.setHorizontalAlignment(SwingConstants.CENTER);
        Position.setHorizontalAlignment(SwingConstants.CENTER);
        information.setHorizontalAlignment(SwingConstants.CENTER);






        content.add(FirstName);
        content.add(FirstNameL);
        content.add(blankPanel1).setBackground(Color.darkGray);
        content.add(InfoLabel);
        content.add(tb_update1);
        
        content.add(LastName);
        content.add(LastNameL);
        content.add(blankPanel2).setBackground(Color.darkGray);
        content.add(blankPanel10).setBackground(Color.darkGray);        
        content.add(tb_update2);
        
        content.add(Age);
        content.add(AgeL);
        content.add(blankPanel3).setBackground(Color.darkGray);
        content.add(blankPanel12).setBackground(Color.darkGray);
        content.add(tb_update3);

        content.add(Grade);
        content.add(GradeL);
        content.add(blankPanel4).setBackground(Color.darkGray);
        content.add(blankPanel11).setBackground(Color.darkGray);
        content.add(tb_update4);
        
        content.add(Program);
        content.add(ProgramL);
        content.add(blankPanel5).setBackground(Color.darkGray);
        content.add(blankPanel15).setBackground(Color.darkGray);
        content.add(tb_update5);

        content.add(blankPanel9).setBackground(Color.darkGray);
        content.add(blankPanel6).setBackground(Color.darkGray);
        content.add(blankPanel16).setBackground(Color.darkGray);
        content.add(blankPanel14).setBackground(Color.darkGray);
        content.add(upload);
		upload.addActionListener(this);

        content.add(prePerson);
		prePerson.addActionListener(this);
        content.add(blankPanel7).setBackground(Color.darkGray);
        content.add(nextPerson);
		nextPerson.addActionListener(this);
        content.add(Person);
        content.add(blankPanel19).setBackground(Color.darkGray);


        
        content.add(blankPanel8).setBackground(Color.darkGray);
        
        content.add(Position);
        

        


        





        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


       

    public void actionPerformed(ActionEvent evt) {
        
		
		if (evt.getSource() == upload) {

            int i = counter;          
            
            arrayOfFive[i].setPersonFName(tb_update1.getText());
            arrayOfFive[i].setPersonLName(tb_update2.getText());
            arrayOfFive[i].setPersonAge(Integer.parseInt(tb_update3.getText()));
            arrayOfFive[i].setPersonGrade(Double.parseDouble(tb_update4.getText()));
            arrayOfFive[i].setPersonProgram(tb_update5.getText());

            FirstNameL.setText(arrayOfFive[i].getPersonFName());
            LastNameL.setText(arrayOfFive[i].getPersonLName());
            AgeL.setText(Integer.toString(arrayOfFive[i].getPersonAge()));
            GradeL.setText(Double.toString(arrayOfFive[i].getPersonGrade()));
            ProgramL.setText(arrayOfFive[i].getPersonProgram());
            
            Position.setText(Integer.toString(counter));
        }

        
        if (evt.getSource() == nextPerson) {
                  counter++;    

            if (counter == N){counter = 0 ;}
            int i = counter;

            FirstNameL.setText(arrayOfFive[i].getPersonFName());
            LastNameL.setText(arrayOfFive[i].getPersonLName());
            AgeL.setText(Integer.toString(arrayOfFive[i].getPersonAge()));
            GradeL.setText(Double.toString(arrayOfFive[i].getPersonGrade()));
            ProgramL.setText(arrayOfFive[i].getPersonProgram());

            Position.setText(Integer.toString(counter));

        }

        if (evt.getSource() == prePerson) {
            counter--;

            if (counter == -1){ counter = 4;}
            int i = counter;

            FirstNameL.setText(arrayOfFive[i].getPersonFName());
            LastNameL.setText(arrayOfFive[i].getPersonLName());
            AgeL.setText(Integer.toString(arrayOfFive[i].getPersonAge()));
            GradeL.setText(Double.toString(arrayOfFive[i].getPersonGrade()));
            ProgramL.setText(arrayOfFive[i].getPersonProgram());

            Position.setText(Integer.toString(counter));

        }

        
    }




    public static void main(String[] args) {
		ArrayPerson myFrame = new ArrayPerson();
        myFrame.setVisible(true);
        
        

	}



}
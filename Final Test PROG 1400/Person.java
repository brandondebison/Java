public class Person {

    protected String personFName; 
    protected String personLName;
    protected int personAge;
    protected double personGrade;
    protected String personProgram;
    

// First Name Gets and Sets
    public String getPersonFName(){
        return personFName;
    }

    public void setPersonFName(String temp){
        this.personFName = temp;
    }
// Last Name Gets and Sets
    public String getPersonLName(){
        return personLName;
    }

    public void setPersonLName(String temp){
        this.personLName = temp;
    }
//Age Gets and Sets
    public int getPersonAge(){
        return personAge;
    }

    public void setPersonAge(int temp){
        this.personAge = temp;
    }
//Grade Gets and Sets
    public double getPersonGrade(){
        return personGrade;
    }

    public void setPersonGrade(double temp){
        this.personGrade = temp;
    }
//Address Gets and Sets
    public String getPersonProgram(){
        return personProgram;
    }

    public void setPersonProgram(String temp){
        this.personProgram = temp;
    }

    

    public Person() {
        personFName = "new "; 
        personLName = "person";
        personAge = 0;
        personGrade = 0.00;
        personProgram = "undeclaried";
    }

    public Person(String s1) {
        this.personFName = s1; 
        personLName = "person";
        personAge = 0;
        personGrade = 0.00;
        personProgram = "undeclaried";
    }

    public Person(String s1, String s2, int i1, double d1, String s3) {        

        this.personFName = s1; 
        this.personLName = s2;
        this.personAge = i1;
        this.personGrade = d1;
        this.personProgram = s3;
    }

    public void Display() {
        System.out.println("First Name for person is: "+personFName);
        System.out.println("Last Name for person is "+personLName);
        System.out.println("Age for person is "+personAge);
        System.out.println("Grade for person is "+personGrade);
        System.out.println("Program person is in is "+personProgram);
        System.out.println("\n");
    
    }



}
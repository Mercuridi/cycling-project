import java.io.File;  // imports the file class
import java.io.IOException; //imports exception class for handling file openings
import java.io.FileWriter;   // Import the FileWriter class
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.FileNotFoundException; //imports exception class for handling missing files
//import java.util.Random;
public class Rider {
    //attributes
    private int riderID; //example: 1111
    private String name; // example: Vincenzo Nibali
    private int teamID; //example 111
    private int yearOfBirth; //example 2003
    private String idFileContent; //stores the data extracted from the code_data file
    private int NewIdFileContent; //stores the data to be written to code_data file
    //getters and setters
    public int getRiderID(){
        return riderID;
    }
    public String getName(){
        return name;
    }
    public int getTeamID(){
        return teamID;
    }
    public int getYearOfBirth(){
        return yearOfBirth;
    }
    public void setRiderID(int riderID){
        this.riderID = riderID;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setTeamID(int teamID){
        this.teamID = teamID;
    }
    public void setYearOfBirth(int yearOfBirth){
        this.yearOfBirth = yearOfBirth;
    }
    //methods
   
    //constructors
    public Rider(int teamID, String name, int yearOfBirth) {
        this.teamID = teamID;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        try {
            File idFile = new File("code_data.txt");
            Scanner FileReader = new Scanner(idFile);
            while (FileReader.hasNextLine()) {
            idFileContent += FileReader.nextLine();
            }
            FileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        this.riderID = Integer.parseInt(idFileContent); //this could easilly be broken by adding mroe data to idFile!!!
        NewIdFileContent = Integer.parseInt(idFileContent) + 1;
        try {
            FileWriter idWriter = new FileWriter("code_data.txt", false); //second parameter causes existing data to be overwritten
            idWriter.write(NewIdFileContent);
            idWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
    }
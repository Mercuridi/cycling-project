package cycling;

//import java.util.Random;
public class Rider {
    //attributes
    private static int nextRiderID = 1111; 
    private int riderID; //example: 1111
    private String name; // example: Vincenzo Nibali
    private int teamID; //example 111
    private int yearOfBirth; //example 2003
    //getters and setters
    public int getRiderID(){
        return riderID;
    }
    public String getRiderName(){
        return name;
    }
    public int getTeamID(){
        return teamID;
    }
    public int getYearOfBirth(){
        return yearOfBirth;
    }
    public static int getNextRiderID() {
        return nextRiderID;
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
        this.riderID = ++nextRiderID;
    }
    }
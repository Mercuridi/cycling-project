package cycling;

// imports for Rider class
import java.io.Serializable;
// import java.util.Random;
// creation of Rider class
/**
 * class Rider for use in CyclingPortal.java and other project files
 * holds all relevant information and data for riders
 * @author Laurie Harbord and Kai Barber
 * @version 1.0
 */
public class Rider implements Serializable{
    // attributes setup
    private static int nextRiderID = 1111; 
    private int riderID; //example: 1111
    private String name; // example: Vincenzo Nibali
    private int teamID; //example 111
    private int yearOfBirth; //example 2003

    /**
     * Method to obtain riderID for a given rider
     * @return the int "riderID" of the current rider
     */
    public int getRiderID(){
        return riderID;
    }
    /**
     * Method to obtain riderName for a given rider
     * @return the String "riderName" of the current rider
     */
    public String getRiderName(){
        return name;
    }
    /**
     * Method to obtain teamID for a given rider
     * @return the int "teamID" of the current rider
     */
    public int getTeamID(){
        return teamID;
    }
    /**
     * Method to obtain the yearOfBirth for a given rider
     * @return the int "yearOfBirth" of the current rider
     */
    public int getYearOfBirth(){
        return yearOfBirth;
    }
    /**
     * Method to obtain the next riderID in relation to the given rider
     * @return the int "nextRiderID" in relation to the current rider
     */
    public static int getNextRiderID() {
        return nextRiderID;
    }
    /**
     * Method to set a custom/new riderID for the given rider
     * @param riderID the riderID to be set as the new riderID and overwrite the old one
     */
    public void setRiderID(int riderID){
        this.riderID = riderID;
    }
    /**
     * Method to set a custom/new name for the given rider
     * @param name the name to be set as the new name and overwrite the old one
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * Method to set a new yearOfBirth for the given rider
     * @param yearOfBirth the yearOfBirth to set as the new yearOfBirth and overwrite the old one
     */
    public void setYearOfBirth(int yearOfBirth){
        this.yearOfBirth = yearOfBirth;
    }
    
    /**
     * Constructor for any rider object - sets default values and creates relevant attributes
     * @param teamID the teamID of the team that the rider belongs to
     * @param name the name of the rider being created
     * @param yearOfBirth the year of birth of the rider being created
     */
    public Rider(int teamID, String name, int yearOfBirth) {
        this.teamID = teamID;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.riderID = ++nextRiderID;
    }
    }
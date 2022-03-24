package cycling;

// imports for Team class
import java.util.ArrayList;
import java.io.Serializable;

// creation of Team class
/**
 * class Team for use in CyclingPortal.java and other project files
 * holds all relevant information and data for teams
 * @author Laurie Harbord and Kai Barber
 * @version 1.0
 */
public class Team implements Serializable{
    // attributes setup
    private static int nextTeamID = 111;
    private int teamID;
    private String teamName;
    private ArrayList<Rider> ridersInTeam;
    private String teamDescription;
    /**
     * Method to obtain teamID for a given team
     * @return the int "teamID" of the current team
     */
    public int getTeamID() {
        return teamID;
    }
    /**
     * Method to obtain the teamName for a given team
     * @return the String "teamName" of the current team
     */
    public String getTeamName() {
        return teamName;
    }
    /**
     * Method to obtain the teamDescription for a given team
     * @return the String "teamDescription" of the current team
     */
    public String getTeamDescription() {
        return teamDescription;
    }
    /**
     * Method to obtain the list ridersInTeam of the riders that are members of a given team
     * @return the ArrayList of Rider objects of the current team
     */
    public ArrayList<Rider> getRidersInTeam() {
        return ridersInTeam;
    }
    /**
     * Method to obtain the next teamID in relation to the current team
     * @return the int "nextTeamID" in relation to the current team
     */
    public static int getNextTeamID() {
        return nextTeamID;
    }
    /**
     * Method to add a new Rider to the team
     * @param newRider the Rider "newRider" to be added to the team
     */
    public void addRider(Rider newRider) {
        this.ridersInTeam.add(newRider);
    } 
    /**
     * Method to remove a rider from the team
     * @param index the exact index of the rider to be removed in the ArrayList of Riders "ridersInTeam"
     */
    public void removeRider(int index) {
        this.ridersInTeam.remove(index);
    }
    /**
     * Method to set a custom/new teamName for the given team
     * @param teamName the teamName to be set as the new teamName and overwrite the old one
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    /**
     * Method to set a custom/new teamDesc for the given team
     * @param teamDesc the teamDescription to be set as the new teamDescription and overwrite the old one
     */
    public void setTeamDescription(String teamDesc) {
        this.teamDescription = teamDesc;
    }
    /**
     * Method to set a custom/new teamID for the given team
     * @param teamID the teamID to be set as the new teamID and overwrite the old one
     */
    public void setTeamID(int teamID){
        this.teamID = teamID;
    }
    /**
     * Constructor for any team object - sets default values and creates relevant attributes
     * @param name name of the team to be created
     * @param Description description of the team to be created
     */
    public Team(String name, String Description) {
        this.teamName = name;
        this.teamDescription = Description;
        this.teamID = ++nextTeamID;
        this.ridersInTeam = new ArrayList<Rider>();
    }
}
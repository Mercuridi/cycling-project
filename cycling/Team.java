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
    //getters and setters
    public int getTeamID() {
        return teamID;
    }
    public String getTeamName() {
        return teamName;
    }
    public String getTeamDescription() {
        return teamDescription;
    }
    public ArrayList<Rider> getRidersInTeam() {
        return ridersInTeam;
    }
    public static int getNextTeamID() {
        return nextTeamID;
    }
    public void addRider(Rider newRider) {
        this.ridersInTeam.add(newRider);
    } 
    // public boolean removeRider(int targetRiderId) {
    //     boolean riderFound = false;
    //     int searchCount = 0;
    //     Rider currentRider;
    //     int tempID;
    //     while (riderFound == false && searchCount < this.ridersInTeam.size()) {
    //         currentRider = (this.ridersInTeam.get(searchCount));
    //         tempID = currentRider.getRiderID();
    //         if (tempID == targetRiderId) {
    //             riderFound = true;
    //         }
    //         else {
    //             searchCount += 1;
    //         }
    //     }
    //     if (riderFound == true) {
    //         this.ridersInTeam.remove(searchCount);
    //         return true;
    //     }
    //     else
    //         return false;
    // }
    public void removeRider(int index) {
        this.ridersInTeam.remove(index);
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public void setTeamDescription(String teamDesc) {
        this.teamDescription = teamDesc;
    }
    public void setTeamID(int teamID){
        this.teamID = teamID;
    }
    //constructors
    public Team(String name, String Description) {
        this.teamName = name;
        this.teamDescription = Description;
        this.teamID = ++nextTeamID;
        this.ridersInTeam = new ArrayList<Rider>();
    }
}
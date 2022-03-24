package cycling;

import java.util.ArrayList;
import java.io.Serializable;

public class Team implements Serializable{
    //attributes
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
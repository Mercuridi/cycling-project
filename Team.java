import java.util.ArrayList;

public class Team {
    //attributes
    private static int nextTeamID;
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
    
    // public setTeamID(int team) {

    //}

    //methods

    //constructors
    public Team(String name, String Description) {
        this.teamName = name;
        this.teamDescription = Description;
        this.teamID = ++nextTeamID;
        this.ridersInTeam = new ArrayList<Rider>();
        
    }
}
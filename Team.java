import java.util.ArrayList;

public class Team {
    //attributes
    private static int nextTeamID;
    private int teamID;
    private String teamName;
    private ArrayList<Rider> ridersInTeam;
    private String teamDescription;
    private boolean riderFound;
    private int searchCount;
    private Rider currentRider;
    private int tempID;
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
    public boolean removeRider(String targetRiderId) {
        while (riderFound == false && searchCount < this.ridersInTeam.size()) {
            currentRider = (this.ridersInTeam.get(searchCount));
            tempID = currentRider.getRiderID();
            if (tempID == (Integer.parseInt(targetRiderId))) {
                riderFound = true;
            }
            else {
                searchCount += 1;
            }
        }
        if (riderFound == true) {
            this.ridersInTeam.remove(searchCount);
            return true;
        }
        else
            return false;

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
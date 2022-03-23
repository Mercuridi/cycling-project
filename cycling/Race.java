package cycling;

// imports for Race class
import java.io.Serializable;
import java.util.ArrayList;

// creation of Race class
public class Race implements Serializable {
    private static int nextRaceID = 11;
    private int raceID;
    private String raceName;
    private String raceDescription;
    private ArrayList<Rider> ridersInRace;
    private ArrayList<Stage> stages;
    private double raceLength;

    public int getRaceID(){
        return raceID;
    }
    public String getRaceName() {
        return raceName;
    }
    public String getRaceDescription(){
        return raceDescription;
    }
    public ArrayList<Rider> getRidersInRace(){
        return ridersInRace;
    }
    public ArrayList<Stage> getStages() {
        return stages;
    }
    public double getRaceLength(){
        return raceLength;
    }
    public int getNextRaceID(){
        return nextRaceID;
    }
    public void addRiderToRace(Rider newRider){
        this.ridersInRace.add(newRider);
    }
    public void removeRiderFromRace(int targetRiderID){
        boolean riderFound = false;
        int searchCount = 0;
        Rider currentRider;
        int tempID;
        while (riderFound == false && searchCount <= this.ridersInRace.size()) {
            currentRider = (this.ridersInRace.get(searchCount));
            tempID = currentRider.getRiderID();
            if (tempID == targetRiderID) {
                riderFound = true;
            }
            else {
                searchCount += 1;
            }
        }
        if (riderFound == true) {
            this.ridersInRace.remove(searchCount);
        }
    }
    public void addStageToRace(Stage newStage){
        this.stages.add(newStage);
    }
    public void removeStageFromRace(int index){
        this.stages.remove(index);
    }
    public void setRaceID(int raceID){
        this.raceID = raceID;
    }
    public void setRaceName(String raceName){
        this.raceName = raceName;
    }
    public void setRaceDescription(String raceDescription){
        this.raceDescription = raceDescription;
    }
    public void setRaceLength(double raceLength){
        this.raceLength = raceLength;
    }
    public String createDescription(){
        String output = "RaceID: " + this.raceID + "\n" + "Name: " + this.raceName + 
        "\n" + "Description: " + this.raceDescription + "\n" + "Number Of Stages: " + this.stages.size() + "\n"
        + "Race Length: " + this.raceLength;
        return output;
    }
    public int getNumberOfStages(){
        return this.stages.size();
    }

    //TODO add/remove results
    public Race(String name, String description) {
        this.raceID = ++nextRaceID;
        this.raceName = name;
        this.raceDescription = description;
        this.ridersInRace = new ArrayList<Rider>();
        this.raceLength = 0.0;
        this.stages = new ArrayList<Stage>();
    }
}
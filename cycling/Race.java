package cycling;

import java.security.DrbgParameters.NextBytes;
import java.util.ArrayList;

public class Race {
    private static int nextRaceID = 11;
    private int raceID;
    private String raceName;
    private String raceDescription;
    private ArrayList<Rider> ridersInRace;
    private ArrayList<RaceResult> raceResults;
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
    public ArrayList<RaceResult> raceResults() {
        return raceResults;
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
        while (riderFound == false && searchCount < this.ridersInRace.size()) {
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
            //TODO add some verification AND USE THE INDEX INSTEAD GRRR
        }
    }
    public void addStageToRace(Stage newStage){
        this.stages.add(newStage);
    }

    public void removeStageFromRace(int targetStageID){
        boolean stageFound = false;
        int searchCount = 0;
        Stage currentStage;
        int tempID;
        while (stageFound == false && searchCount < this.stages.size()) {
            currentStage = (this.stages.get(searchCount));
            tempID = currentStage.getStageID();
            if (tempID == targetStageID){
                stageFound = true;
            }
            else {
                searchCount += 1;
            }
        }
        if (stageFound == true) {
            this.stages.remove(searchCount);
        }
        //TODO add some verification innit
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
        String output = "Name: " + this.raceName + " Description: " + this.raceDescription;
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
        this.raceResults = new ArrayList<RaceResult>();
    }
}
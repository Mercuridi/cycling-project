package cycling;

// imports for Race class
import java.io.Serializable;
import java.util.ArrayList;

// creation of Race class
/**
 * class Race for use in CyclingPortal.java and other project files
 * holds all relevant information and data for races
 * @author Laurie Harbord and Kai Barber
 * @version 1.0
 */
public class Race implements Serializable {
    // attributes setup
    private static int nextRaceID = 11;
    private int raceID;
    private String raceName;
    private String raceDescription;
    private ArrayList<Rider> ridersInRace;
    private ArrayList<Stage> stages;
    private double raceLength;

    /**
     * Method to obtain raceID for a given race
     * @return the int "raceID" of the current race
     */
    public int getRaceID(){
        return raceID;
    }
    /**
     * Method to obtain raceName for a given race
     * @return the String "raceName" of the current race
     */
    public String getRaceName() {
        return raceName;
    }
    /**
     * Method to obtain raceDescription for a given race
     * @return the String "raceDescription" of the current race
     */
    public String getRaceDescription(){
        return raceDescription;
    }
    /**
     * Method to obtain the list of riders registered in a given race
     * @return the ArrayList "ridersInRace" of the current race
     */
    public ArrayList<Rider> getRidersInRace(){
        return ridersInRace;
    }
    /**
     * Method to obtain the list of stages registered in a given race
     * @return the ArrayList "stages" of the current race
     */
    public ArrayList<Stage> getStages() {
        return stages;
    }
    /**
     * Method to obtain the raceLength (type double) for a given race
     * @return the double "raceLength" of the current race
     */
    public double getRaceLength(){
        return raceLength;
    }
    /**
     * Method to obtain the next RaceID in relation to the given race
     * @return the int "nextRaceID" in relation to the current race
     */
    public int getNextRaceID(){
        return nextRaceID;
    }
    /**
     * Method to add a new rider to a race for participation and later allocation of results
     * @param newRider the new rider object (type Rider) to be added to the ArrayList "ridersInRace" for the race
     */
    public void addRiderToRace(Rider newRider){
        this.ridersInRace.add(newRider);
    }
    /**
     * Method to remove a rider from a race's list of riders, found via riderID
     * @param targetRiderID the int "riderID" of the rider to be removed from the race
     */
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
    /**
     * Method to add a stage to the given race
     * @param newStage the new stage (type Stage) to be added to the race's construction
     */
    public void addStageToRace(Stage newStage){
        this.stages.add(newStage);
    }
    /**
     * Method to remove a stage from the given race
     * @param index the exact int index of the stage to be removed in the ArrayList "Stages" contained within a race's attributes
     */
    public void removeStageFromRace(int index){
        this.stages.remove(index);
    }
    /**
     * Method to set a custom/new raceID for the given race
     * @param raceID the raceID to be set as the new raceID and overwrite the old one
     */
    public void setRaceID(int raceID){
        this.raceID = raceID;
    }
    /**
     * Method to set a custom/new raceName for the given race
     * @param raceName the raceName to be set as the new raceName and overwrite the old one (if it exists)
     */
    public void setRaceName(String raceName){
        this.raceName = raceName;
    }
    /**
     * Method to set a custom/new raceDescription for the given race
     * @param raceDescription the raceDescription to be set as the new raceDescription and overwrite the old one (if it exists)
     */
    public void setRaceDescription(String raceDescription){
        this.raceDescription = raceDescription;
    }
    /**
     * Method to set the raceLength of the given race in kilometres (KM)
     * @param raceLength the physical distance of the race in kilometres (KM) as a double
     */
    public void setRaceLength(double raceLength){
        this.raceLength = raceLength;
    }
    /**
     * Method to create a properly formatted and nice looking "info board" for a given race to display in plain text
     * @return String to be displayed in plain text; contains race info such as raceID, 
     * raceName, raceDescription, number of stages, and raceLength in an easily readable format
     */
    public String createDescription(){
        String output = "RaceID: " + this.raceID + "\n" + "Name: " + this.raceName + 
        "\n" + "Description: " + this.raceDescription + "\n" + "Number Of Stages: " + this.stages.size() + "\n"
        + "Race Length: " + this.raceLength;
        return output;
    }
    /**
     * Method to obtain the number of stages in a given race
     * @return integer quantity of unique stages in a race
     */
    public int getNumberOfStages(){
        return this.stages.size();
    }

    /**
     * Constructor for any race object - sets default values and creates relevant attributes
     * @param name name of the race to be created
     * @param description description of the race to be created
     */
    public Race(String name, String description) {
        this.raceID = ++nextRaceID;
        this.raceName = name;
        this.raceDescription = description;
        this.ridersInRace = new ArrayList<Rider>();
        this.raceLength = 0.0;
        this.stages = new ArrayList<Stage>();
    }
}
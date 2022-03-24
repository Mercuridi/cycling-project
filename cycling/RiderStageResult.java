package cycling;

// imports for RiderStageResult class
import java.time.LocalTime;

// creation of RiderStageResult class
/**
 * class RiderStageResult for use in CyclingPortal.java and other project files
 * holds all relevant information and data for stages
 * @author Laurie Harbord and Kai Barber
 * @version 1.0
 */
public class RiderStageResult{
    // attributes setup
    private static int nextResultID = 11111;
    private int resultID;
    private int stageID;
    private int riderID;
    private int mountainPoints;
    private int sprintPoints;
    private LocalTime[] checkpoints; //example [5:00, 5:34, 5:49, 6:10] 

    /**
     * Method to obtain the resultID for a given RiderStageResult
     * @return the int resultID of the current RiderStageResult (unique identifier of the object)
     */
    public int getResultID(){
        return resultID;
    }
    /**
     * Method to obtain the stageID for a given RiderStageResult
     * @return the int stageID corresponding to the stage of the current RiderStageResult
     */
    public int getStageID(){
        return stageID;
    }
    /**
     * Method to obtain the riderID for a given RiderStageResult
     * @return the int riderID corresponding to the rider of the current RiderStageResult
     */
    public int getRiderID(){
        return riderID;
    }
    /**
     * Method to obtain the checkpoints for a given RiderStageResult
     * @return the array of LocalTime that corresponds to the checkpoints of the current RiderStageResult
     */
    public LocalTime[] getCheckpoints(){
        return checkpoints;
    }
    /**
     * Method to obtain the mountainPoints for a given RiderStageResult
     * @return the int mountainPoints of the current RiderStageResult
     */
    public int getMountainPoints(){
        return mountainPoints;
    }
    /**
     * Method to obtain the sprintPoints for a given RiderStageResult
     * @return the int sprintPoints of the current RiderStageResult
     */
    public int getSprintPoints(){
        return sprintPoints;
    }
    /**
     * Method to add to the mountainPoints of the RiderStageResult
     * @param addValue the int of the number of points to add to mountainPoints
     */
    public void addToMountainPoints(int addValue){
        this.mountainPoints += addValue;
    }
    /**
     * Method to add to the sprintPoints of the RiderStageResult
     * @param addValue the int of the number of points to add to sprintPoints
     */
    public void addToSprintPoints(int addValue){
        this.sprintPoints += addValue;
    }
    /**
     * Method to set a custom/new resultID for teh given RiderStageResult
     * @param resultID the int resultID to be set as the new resultID (overwrites old value)
     */
    public void setResultID(int resultID){
        this.resultID = resultID;
    }
    /**
     * Method to obtain the total elapsed time spent in a segment by a rider
     * @return the int value of the total elapsed time in a segment
     */
    // TODO check the logic here? what exactly is this doing, maybe rewrite javadoc
    public LocalTime getElapsedTime(){
        int finalIndex = this.checkpoints.length - 1;
        return this.checkpoints[finalIndex];
    }
    /**
     * Constructor for any RiderStageResult object - sets default values and creates relevant attributes
     * @param stageID the stageID related to the stage where the rider has gained a result
     * @param riderID the riderID of the rider that completed a result for a stage
     * @param checkpoints the array of LocalTimes that comprises the times the rider scored in the stage for each segment
     */
    public RiderStageResult(int stageID, int riderID, LocalTime[] checkpoints){
        this.resultID = ++nextResultID; 
        this.stageID = stageID;
        this.riderID = riderID;
        this.mountainPoints = 0;
        this.sprintPoints = 0;
        this.raceID = 0; //rouge value - this will be set :) 
        this.checkpoints = checkpoints;
    }
    }
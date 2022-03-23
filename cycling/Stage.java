package cycling;

// imports for Stage class
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.io.Serializable;

// creation of Stage class
/**
 * class Stage for use in CyclingPortal.java and other project files
 * holds all relevant information and data for stages
 * @author Laurie Harbord and Kai Barber
 * @version 1.0
 */
public class Stage implements Serializable{
    // variables setup
    private static int nextStageID = 11;
    private int stageID;
    private int raceID;
    private String stageDescription;
    private LocalDateTime startTime;
    private String stageName;
    private String stageLocation;
    private StageType stageType;
    private ArrayList<Segment> segments;
    private double stageLength;
    private boolean concluded;

    /**
     * Method to obtain the stageID for a given stage
     * @return the int stageID of the current stage
     */
    public int getStageID(){
        return stageID;
    }
    /**
     * Method to obtain the raceID for a given stage - i.e. the raceID of the race the stage is contained in
     * @return the int raceID of the race that contains the current stage
     */
    public int getRaceID(){
        return raceID;
    }
    /**
     * Method to obtain the stageDescription for a given stage
     * @return the String stageDescription of the current stage
     */
    public String getStageDescription(){
        return stageDescription;
    }
    /**
     * Method to obtain the startTime for a given stage
     * @return the LocalDateTime startTime of the current stage
     */
    public LocalDateTime getStartTime(){
        return startTime;
    }
    /**
     * Method to obtain the stageName for a given stage
     * @return the String stageName of the current stage
     */
    public String getStageName(){
        return stageName;
    }
    /**
     * Method to obtain the stageLocation for a given stage
     * @return the String stageLocation of the current stage
     */
    public String getStageLocation(){
        return stageLocation;
    }
    /**
     * Method to obtain the stageType for a given stage
     * @return the StageType stageType of the current stage
     */
    public StageType getStageType(){
        return stageType;
    }
    /**
     * Method to obtain the ArrayList of segments that comprise a given stage
     * @return the ArrayList of Segments "segments" of the current stage
     */
    public ArrayList<Segment> getSegments(){
        return segments;
    }
    /**
     * Method to obtain the stageLength for a given stage
     * @return the double stageLength of the current stage
     */
    public double getStageLength(){
        return stageLength;
    }
    /**
     * Method to obtain the state of the stage - the boolean of whether the stage has concluded or not
     * @return the boolean concluded - "true" is a concluded/finished stage, "false" is a stage still ongoing/not started yet
     */
    public boolean getConcluded(){
        return concluded;
    }
    /**
     * Method to set a custom/new stageID for the given stage
     * @param stageID the int stageID to be set as the new stageID and overwrite the old one
     */
    public void setStageID(int stageID){
        this.stageID = stageID;
    }
    /**
     * Method to set a custom/new stageDescription for the given stage
     * @param stageDescription the String stageDescription to be set as the new stageDescription
     */
    public void setStageDescription(String stageDescription){
        this.stageDescription = stageDescription;
    }
    /**
     * Method to set a new startTime for the given stage
     * @param startTime the LocalDateTime startTime to be set as the new startTime
     */
    public void setStartTime(LocalDateTime startTime){
        this.startTime = startTime;
    }
    /**
     * Method to set a custom/new stageName for the given stage
     * @param stageName the String stageName to be set as the new stageName
     */
    public void setStageName(String stageName){
        this.stageName = stageName;
    }
    /**
     * Method to set a new stageLocation for the given stage
     * @param stageLocation the String stageLocation to be set as the new stageLocation
     */
    public void setStageLocation(String stageLocation){
        this.stageLocation = stageLocation;
    }
    /**
     * Method to set a new stageLength for the given stage
     * @param stageLength the double stageLength (measured in kilometres) to be set as the new stageLength
     */
    public void setStageLength(double stageLength){
        this.stageLength = stageLength;
    }
    /**
     * Method to add a new segment to the given stage (automatically increments the stageLength, as the segment should have a segmentLength)
     * @param newSegment the Segment newSegment to be added to the stage
     */
    public void addSegmentToStage(Segment newSegment){
        this.segments.add(newSegment);
        this.stageLength += newSegment.getSegmentLength();
    }
    /**
     * Method to remove a segment from the given stage
     * @param targetIndex the exact index of the segment to be removed in the ArrayList of Segments "segments"
     */
    public void removeSegment(int targetIndex){
        this.segments.remove(targetIndex);
    }
    /**
     * Method to set the current state of the attribute concluded; commonly invoked upon a stage ending
     * @param concluded the boolean of whether the stage is to be set as concluded or not
     */
    public void setConcluded(boolean concluded){
        this.concluded = concluded;
    }
    /**
     * Method to reduce the length of a stage by a given segment length; useful when a segment is removed
     * @param segLength the double segLength - the length of the reduction of the stageLength in kilometres
     */
    public void decreaseLength(double segLength){
        this.stageLength -= segLength;
    }

    /**
     * Constructor for any stage object - sets default values and creates relevant attributes
     * @param raceId the raceID of the race that contains the stage
     * @param stageName the name of the stage to be created
     * @param description the description of the stage to be created
     * @param length the length of the stage; should not include length of segments which will be added later
     * @param startTime the starting time and date of the stage
     * @param type the type of the stage; Enum, options found in StageType.java
     */
    public Stage(int raceId, String stageName, String description, double length, LocalDateTime startTime, StageType type){
        this.raceID = raceId;
        this.stageName = stageName;
        this.stageDescription = description;
        this.stageLength = length;
        this.startTime = startTime;
        this.stageType = type;
        this.stageID = ++nextStageID;
        this.concluded = false;
        this.segments = new ArrayList<Segment>();
    }

}
package cycling;

// imports for Segment class
import java.util.ArrayList;
import java.io.Serializable;

// creation of Segment class
/**
 * class Segment for use in CyclingPortal.java and other project files
 * holds all relevant information and data for segments
 * @author Laurie Harbord and Kai Barber
 * @version 1.0
 */
public class Segment implements Serializable{
    // attributes setup
    private static int nextSegmentID = 1111;
    private int segmentID;
    private SegmentType segmentType;
    private double segmentLength;
    private Double segmentLocation;
    private Double averageGradient;

    /**
     * Method to obtain the next SegmentID in relation to the given segment
     * @return the int "nextSegmentID" in relation to the current segment
     */
    public int getNextSegmentID(){
        return nextSegmentID;
    }
    /**
     * Method to obtain the segmentID of the given segment
     * @return the int "segmentID" of the current segment
     */
    public int getSegmentID(){
        return segmentID;
    }
    /**
     * Method to obtain the segmentLength of the given segment
     * @return the double "segmentLength" of the current segment
     */
    public double getSegmentLength(){
        return segmentLength;
    }
    /**
     * Method to obtain the segmentType of the given segment
     * @return the SegmentType "segmentType" of the current segment
     */
    public SegmentType getSegmentType(){
        return segmentType;
    }
    /**
     * Method to obtain the segmentLocation of the given segment
     * @return the Double "segmentLocation" of the current segment
     */
    public Double getSegmentLocation(){
        return segmentLocation;
    }
    /**
     * Method to obtain the averageGradient of the given segment
     * @return the Double "averageGradient" of the current segment
     */
    public Double getAverageGradient(){
        return averageGradient;
    }
    /**
     * Method to set a custom/new segmentID for the given segment
     * @param segmentID the int segmentID to be set as the new segmentID
     */
    public void setSegmentID(int segmentID){
        this.segmentID = segmentID;
    }
    /**
     * Method to set a custom/new raceID in relation to the given segment
     * Only used as a fallback when a more complex method fails to perform function correctly
     * @param raceID the int raceID to be set as the new raceID (overwrites the old one)
     */
    public void setRaceID(int raceID){
        this.raceID = raceID;
    }
    /**
     * Method to set the segmentLength for the given segment (overwrites old segmentLength if it exists)
     * @param segmentLength the double segmentLength to be set as the new segmentLength (measured in kilometres) of the current segment
     */
    public void setSegmentLength(double segmentLength){
        this.segmentLength = segmentLength;
    }
    /**
     * Method to set the segmentType of the given segment
     * @param segmentType the SegmentType segmentType to be set as the new segmentType of the current segment
     */
    public void setSegmentType(SegmentType segmentType){
        this.segmentType = segmentType;
    }
    /**
     * Method to set the segmentLocation of the given segment
     * @param segmentLocation the double segmentLocation to be set as the new segmentLocation of the current segment
     */
    public void setSegmentLocation(double segmentLocation){
        this.segmentLocation = segmentLocation;
    }
    /**
     * Method to set the averageGradient of the given segment
     * @param averageGradient the double averageGradient to be set as the averageGradient of the current segment
     */
    public void setAverageGradient(double averageGradient){
        this.averageGradient = averageGradient;
    }

    //option of 2 different constructors for the class

    /**
     * Constructor for segments that have most of their data already ready/filled out
     * @param stageId the stageID of the segment to be created
     * @param location type Double, the location of the stage to be created
     * @param type the SegmentType of the segment to be created; Enum, find in SegmentType.java
     * @param averageGradient the double averageGradient of the segment to be created
     * @param length the length of the segment to be created (in kilometres)
     */
    public Segment(int stageId, Double location, SegmentType type, Double averageGradient, Double length) {
        this.segmentID = ++nextSegmentID;
        this.stageID = stageId;
        this.segmentLocation = location;
        this.segmentType = type;
        this.averageGradient = averageGradient;
        this.segmentLength = length;
        this.raceID = 0; //rogue value!!! Will be set later :)
    }

    /**
     * Constructor for segments that will be finished later (not all information immediately available upon creation)
     * @param stageId the stageID of the segment to be created
     * @param location type double, the location of the stage to be created
     */
    public Segment(int stageId, double location){
        this.segmentID = ++nextSegmentID;
        this.stageID = stageId;
        this.segmentLocation = location;
        this.segmentType = SegmentType.SPRINT;
        this.averageGradient = 0.0; //not used
        this.segmentLength = 0.0; //not used
        this.raceID = 0; //rogue value!!! Will be set later :)
    }   
    }

package cycling;
import java.util.ArrayList;
import java.io.Serializable;
public class Segment implements Serializable{
    private static int nextSegmentID = 11;
    private int segmentID;
    private int stageID;
    private int raceID;
    private SegmentType segmentType;
    private ArrayList<SegmentResult> segmentResults;
    private double segmentLength;
    private Double segmentLocation;
    private Double averageGradient;

public int getNextSegmentID(){
    return nextSegmentID;
}

public int getSegmentID(){
    return segmentID;
}

public int getStageID(){
    return stageID;
}

public int getRaceID(){
    return raceID;
}

public double getSegmentLength(){
    return segmentLength;
}

public SegmentType getSegmentType(){
    return segmentType;
}

public double getSegmentLocation(){
    return segmentLocation;
}

public double getAverageGradient(){
    return averageGradient;
}

public void setSegmentID(int segmentID){
    this.segmentID = segmentID;
}

public void setStageID(int stageID){
    this.stageID = stageID;
}

public void setRaceID(int raceID){
    this.raceID = raceID;
}

public void setSegmentLength(double segmentLength){
    this.segmentLength = segmentLength;
}

public void setSegmentType(SegmentType segmentType){
    this.segmentType = segmentType;
}

public void setSegmentLocation(double segmentLocation){
    this.segmentLocation = segmentLocation;
}

public void setAverageGradient(double averageGradient){
    this.averageGradient = averageGradient;
}

//TODO add/remove results

public Segment(int stageId, Double location, SegmentType type, Double averageGradient, Double length) {
    this.segmentID = ++nextSegmentID;
    this.stageID = stageId;
    this.segmentLocation = location;
    this.segmentType = type;
    this.averageGradient = averageGradient;
    this.segmentLength = length;
    this.raceID = 0; //rogue value!!! Will be set later :)
    this.segmentResults = new ArrayList<SegmentResult>();
}

public Segment(int stageId, double location){
    this.segmentID = ++nextSegmentID;
    this.stageID = stageId;
    this.segmentLocation = location;
    this.segmentType = SegmentType.SPRINT;
    this.averageGradient = 0.0; //not used
    this.segmentLength = 0.0; //not used
    this.raceID = 0; //rogue value!!! Will be set later :)
    this.segmentResults = new ArrayList<SegmentResult>();
}   
}
package cycling;
import java.util.ArrayList;

public class Segment{
    private static int nextSegmentID = 11;
    private int segmentID;
    private int stageID;
    private int raceID;
    private SegmentType segmentType;
    private ArrayList<SegmentResult> segmentResults;
    private double segmentLength;
    private Double segmentLocation;
    private Double averageGradient;

public int getnextSegmentID(){
    return nextSegmentID;
}

public int getsegmentID(){
    return segmentID;
}

public int getstageID(){
    return stageID;
}

public int getraceID(){
    return raceID;
}

public double getsegmentLength(){
    return segmentLength;
}

public SegmentType getsegmentType(){
    return segmentType;
}

public double getsegmentLocation(){
    return segmentLocation;
}

public double getaverageGradient(){
    return averageGradient;
}

public void setsegmentID(int segmentID){
    this.segmentID = segmentID;
}

public void setstageID(int stageID){
    this.stageID = stageID;
}

public void setraceID(int raceID){
    this.raceID = raceID;
}

public void setsegmentLength(double segmentLength){
    this.segmentLength = segmentLength;
}

public void setsegmentType(SegmentType segmentType){
    this.segmentType = segmentType;
}

public void setsegmentLocation(double segmentLocation){
    this.segmentLocation = segmentLocation;
}

public void setaverageGradient(double averageGradient){
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
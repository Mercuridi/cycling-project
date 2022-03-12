package cycling;
import java.util.ArrayList;

public class Segment{
    private static int nextSegmentID = 11;
    private int segmentID;
    private int stageID;
    private int raceID;
    private String segmentName;
    private SegmentType segmentType;
    // private ArrayList<SegmentResult> segmentResults;
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

public String getsegmentName(){
    return segmentName;
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

public void setsegmentName(String segmentName){
    this.segmentName = segmentName;
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
}
package cycling;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Stage {
    private static int nextStageID = 11;
    private int stageID;
    private int raceID;
    private String stageDescription;
    private LocalDateTime startTime;
    private String stageName;
    private String stageLocation;
    private StageType stageType;
    private ArrayList<Segment> segments;
    //private ArrayList<StageResult> stageResults;
    private double stageLength;

    public int getStageID(){
        return stageID;
    }
    public int getRaceID(){
        return raceID;
    }
    public String getStageDescription(){
        return stageDescription;
    }
    public LocalDateTime getStartTime(){
        return startTime;
    }
    public String getStageName(){
        return stageName;
    }
    public String getStageLocation(){
        return stageLocation;
    }
    public StageType getStageType(){
        return stageType;
    }
    public ArrayList<Segment> getSegments(){
        return segments;
    }
    //public ArrayList<StageResult> getStageResults(){
        //return stageResults;
    //}
    public double getstageLength(){
        return stageLength;
    }
    public void setStageID(int stageID){
        this.stageID = stageID;
    }
    public void setRaceID(int raceID){
        this.raceID = raceID;
    }
    public void setStageDescription(String stageDescription){
        this.stageDescription = stageDescription;
    }
    public void setStartTime(LocalDateTime startTime){
        this.startTime = startTime;
    }
    public void setStageName(String stageName){
        this.stageName = stageName;
    }
    public void setStageLocation(String stageLocation){
        this.stageLocation = stageLocation;
    }
    public void setStageLength(double stageLength){
        this.stageLength = stageLength;
    }

    public Stage(int raceId, String stageName, String description, double length, LocalDateTime startTime, StageType type){
        this.raceID = raceId;
        this.stageName = stageName;
        this.stageDescription = description;
        this.stageLength = length;
        this.startTime = startTime;
        this.stageType = type;
        this.stageID = ++nextStageID;
        //TODO add array instantiation
    }

}
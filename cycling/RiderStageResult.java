package cycling;

import java.time.LocalTime;

public class RiderStageResult{
    private static int nextResultID = 11111;
    private int resultID;
    private int stageID;
    private int riderID;
    private int raceID;
    private int mountainPoints;
    private int sprintPoints;
    private LocalTime[] checkpoints; //example [5:00, 5:34, 5:49, 6:10] 

public int getResultID(){
    return resultID;
}
public int getStageID(){
    return stageID;
}
public int getRiderID(){
    return riderID;
}
public int getRaceID(){
    return raceID;
}
public LocalTime[] getCheckpoints(){
    return checkpoints;
}
public int getMountainPoints(){
    return mountainPoints;
}
public int getSprintPoints(){
    return sprintPoints;
}
public void addToMountainPoints(int addValue){
    this.mountainPoints += addValue;
}
public void addToSprintPoints(int addValue){
    this.sprintPoints += addValue;
}
public void setResultID(int resultID){
    this.resultID = resultID;
}
public void setStageID(int stageID){
    this.stageID = stageID;
}
public LocalTime getElapsedTime(){
    int finalIndex = this.checkpoints.length - 1;
	return this.checkpoints[finalIndex];
}
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
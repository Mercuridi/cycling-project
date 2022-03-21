package cycling;

import java.time.LocalTime;

public class RiderStageResult{
    public int stageID;
    public int riderID;
    public int raceID;
    public LocalTime[] checkpoints; //example [5:00, 5:34, 5:49, 6:10] 

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
public void setstageID(int stageID){
    this.stageID = stageID;
}
public LocalTime getElapsedTime(){
    int finalIndex = this.checkpoints.length - 1;
	return this.checkpoints[finalIndex];
}
public RiderStageResult(int stageID, int riderID, LocalTime[] checkpoints){
    this.stageID = stageID;
    this.riderID = riderID;
    this.raceID = 0; //rouge value - this will be set :) 
    this.checkpoints = checkpoints;
}
}
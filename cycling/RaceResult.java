package cycling;
import java.time.LocalTime;
import java.io.Serializable;
    
public class RaceResult implements Serializable{
    public static int nextRaceResultID = 1;
    public int raceResultID;
    public int riderID;
    public int raceID;
    public LocalTime[] time;
    public int SprintPnt;
    public int MountPnt;

public int getnextRaceResultID(){
    return nextRaceResultID;
}

public int getRaceResultID(){
    return raceResultID;
}

public int getRiderID(){
    return riderID;
}

public int getRaceID(){
    return raceID;
}

public LocalTime[] getTime(){
    return time;
}

public int getSprintPnt(){
    return SprintPnt;
}

public int getMountPnt(){
    return MountPnt;
}

public void setRaceResultID(int raceResultID){
    this.raceResultID = raceResultID;
}

public void setRiderID(int riderID){
    this.riderID = riderID;
}

public void setRaceID(int raceID){
    this.raceID = raceID;
}

public void setTime(LocalTime[] time){
    this.time = time;
}

public void setSprintPnt(int SprintPnt){
    this.SprintPnt = SprintPnt;
}

public void setMountPnt(int MountPnt){
    this.MountPnt = MountPnt;
}

public RaceResult(int newriderID, int newraceID, LocalTime[] newtime, int newSprintPnt, int newMountPnt){
    this.raceResultID = ++nextRaceResultID;
    this.riderID = newriderID;
    this.raceID = newraceID;
    this.time = newtime;
    this.SprintPnt = newSprintPnt;
    this.MountPnt = newMountPnt;
}
}
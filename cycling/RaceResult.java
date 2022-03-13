package cycling;
public class RaceResult {
    private int raceResultID;
    private int riderID;
    private int raceID;
    private double time;
    private int SprintPnt;
    private int MountPnt;

public int getRaceResultID(){
    return raceResultID;
}

public int getRiderID(){
    return riderID;
}

public int getRaceID(){
    return raceID;
}

public double getTime(){
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

public void setTime(double time){
    this.time = time;
}

public void setSprintPnt(int SprintPnt){
    this.SprintPnt = SprintPnt;
}

public void setMountPnt(int MountPnt){
    this.MountPnt = MountPnt;
}
}
//TODO constructors
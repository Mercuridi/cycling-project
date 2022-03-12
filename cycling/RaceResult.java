package cycling;
public class RaceResult {
    private int raceResultID;
    private int riderID;
    private int raceID;
    private double time;
    private int SprintPnt;
    private int MountPnt;

public int getraceResultID(){
    return raceResultID;
}

public int getriderID(){
    return riderID;
}

public int getraceID(){
    return raceID;
}

public double gettime(){
    return time;
}

public int getSprintPnt(){
    return SprintPnt;
}

public int getMountPnt(){
    return MountPnt;
}

public void setraceResultID(int raceResultID){
    this.raceResultID = raceResultID;
}

public void setriderID(int riderID){
    this.riderID = riderID;
}

public void setraceID(int raceID){
    this.raceID = raceID;
}

public void settime(double time){
    this.time = time;
}

public void setSprintPnt(int SprintPnt){
    this.SprintPnt = SprintPnt;
}

public void setMountPnt(int MountPnt){
    this.MountPnt = MountPnt;
}
}

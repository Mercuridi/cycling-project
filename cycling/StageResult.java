package cycling;
public class StageResult extends RaceResult {
    public int stageID;
    //public int stageSprintPnt;  do we need point value attributes for each stage/segment? concerns about complications especially at SegmentResult layer,
    //public int stageMountPnt;   where a SegmentResult object will contain 6 different instances of a "points" type attribute

public int getstageID(){
    return stageID;
}

public void setstageID(int stageID){
    this.stageID = stageID;
}

public StageResult(int newriderID, int newraceID, double newtime, int newSprintPnt, int newMountPnt, int newstageID){
    super (newriderID, newraceID, newtime, newSprintPnt, newMountPnt);
    this.stageID = newstageID;
}
}
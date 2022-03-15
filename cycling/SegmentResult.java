package cycling;
public class SegmentResult extends StageResult{
    public int segmentID;

public int getSegmentID(){
    return segmentID;
}

public void setSegmentID(int segmentID){
    this.segmentID = segmentID;
}

public SegmentResult(int newriderID, int newraceID, double newtime, int newSprintPnt, int newMountPnt, int newstageID, int newsegmentID){
    super (newriderID, newraceID, newtime, newSprintPnt, newMountPnt, newstageID);
    this.segmentID = newsegmentID;
}
}
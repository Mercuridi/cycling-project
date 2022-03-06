import java.util.ArrayList;

public class Race {
    private static int nextRaceID;
    private int raceID;
    private String raceName;
    private String raceDescription;
    private ArrayList<Rider> ridersInRace;
    //private ArrayList<RaceResult> raceResults just as we haven't added this yet!
    //private ArrayList<Stage> stages
    private double raceLength;

    public int getRaceID(){
        return raceID;
    }
    public String getRaceName() {
        return raceName;
    }
    public String getRaceDescription(){
        return raceDescription;
    }
    public ArrayList<Rider> getRidersInRace(){
        return ridersInRace;
    }
    //public ArrayList<RaceResult> raceResults() {
        //return ridersInRace;
    //}
    //public ArrayList<Stage> getStages() {
        //return stages;
    //}
    public double getRaceLength(){
        return raceLength;
    }
    public int getNextRaceID(){
        return nextRaceID;
    }
}
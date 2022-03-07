import java.security.DrbgParameters.NextBytes;
import java.util.ArrayList;

public class Race {
    private static int nextRaceID = 11;
    private int raceID;
    private String raceName;
    private String raceDescription;
    private ArrayList<Rider> ridersInRace;
    //private ArrayList<RaceResult> raceResults just as we haven't added this yet!
    private ArrayList<Stage> stages;
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
    public ArrayList<Stage> getStages() {
        return stages;
    }
    public double getRaceLength(){
        return raceLength;
    }
    public int getNextRaceID(){
        return nextRaceID;
    }
    //TODO add stage to race method
    
    public Race(String name, String description) {
        this.raceID = ++nextRaceID;
        this.raceName = name;
        this.raceDescription = description;
        this.ridersInRace = new ArrayList<Riders>();
        this.raceLength = 0.0;
        this.stages = new ArrayList<Stage>();
        //this.raceResults = new ArrayList<RaceResult>();
    }
}
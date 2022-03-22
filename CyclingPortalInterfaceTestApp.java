import cycling.CyclingPortalInterface;
import cycling.CyclingPortal;
import cycling.*;
import java.util.ArrayList;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the CyclingPortalInterface interface -- note you
 * will want to increase these checks, and run it on your CyclingPortal class
 * (not the BadCyclingPortal class).
 *
 * 
 * @author Diogo Pacheco
 * @version 1.0
 */
public class CyclingPortalInterfaceTestApp {

	/**
	 * Test method.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		System.out.println("The system compiled and started the execution...");
		CyclingPortalInterface portal = new CyclingPortal();
        try{
            portal.createTeam("Wild Planets", "This is a sample description for the team Wild Planets.");
            portal.createTeam("Road Blockers", "This is a sample description for the team Road Blockers.");
            portal.createTeam("Classy Lunatics", "This is a sample description for the team Classy Lunatics.");

        }
        catch (IllegalNameException ex){
            System.out.println("fail, name already exists");
        }
        catch(InvalidNameException ex){
            System.out.println("fail, name is empty or too long");
        }
        try{
            portal.getTeamRiders(112);
        }
        catch(IDNotRecognisedException ex){
            System.out.println("fail");
        }
        try{
            portal.createRider(112, "Wawa", 2003);
            portal.createRider(112, "Wewe", 2006);
            portal.createRider(112, "WooWoo", 2005);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("fail");
        }
        try{
            System.out.println("Team Riders: " + portal.getTeamRiders(112)[0]);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("failed to locate RiderID");
        }
        try{
            System.out.println("Team Riders: " + portal.getTeamRiders(112)[0] +", " + portal.getTeamRiders(112)[1] + ", " + portal.getTeamRiders(112)[2]);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("failed to locate RiderID");
        }
        if (portal.getRaceIds() instanceof int[]){
            System.out.println("empty array worked!");
        }
        String racesList;
        try{
            portal.createRace("CheepCheepBeach", "A temperate and sunny race along Cheep Cheep Beach.");
            portal.createRace("ShroomRidge", "A long race along a mountain ridge, with gorgeous views and a dangerous sheer drop.");
            portal.createRace("ExcitebikeArena", "A shorter race on a track constructed in the legendary Excitebike Arena.");
        }
        catch(IllegalNameException ex){
            System.out.println("failure - name already exists!");
        }
        catch(InvalidNameException ex){
            System.out.println("failure - name is not valid");
        }
        racesList = "Races: " + portal.getRaceIds()[0] + ", " + portal.getRaceIds()[1] + ", " + portal.getRaceIds()[2];
        System.out.println(racesList); 
        try{
            System.out.println(portal.viewRaceDetails(12));
        }
        catch (IDNotRecognisedException ex){
            System.out.println("that race doesn't exist dumbass");
        }
        // try{
        //      portal.removeRaceById(13);
        // }
        // catch (IDNotRecognisedException ex){
        //      System.out.println("that race doesn't exist dumbass");
        // }
        // racesList = "Races: " + portal.getRaceIds()[0] + ", " + portal.getRaceIds()[1];
        // System.out.println(racesList);
        LocalDateTime dateTime = LocalDateTime.now();
        try{
            portal.addStageToRace(12, "Test Flat Stage Innit", "It's just a stage bruh", 5.0, dateTime, StageType.FLAT);
            dateTime = LocalDateTime.now();
            portal.addStageToRace(12, "TestHighMountainStageInnit", "It's just a stage bruh", 5.0, dateTime, StageType.HIGH_MOUNTAIN);
            dateTime = LocalDateTime.now();
            portal.addStageToRace(12, "TestMediumMountainStageInnit", "It's just a stage bruh", 5.0, dateTime, StageType.MEDIUM_MOUNTAIN);
            System.out.println("Stages added!");
        }
        catch (IllegalNameException ex){
            System.out.println("Name already exists in race dumbass");
        }
        catch (InvalidNameException ex){
            System.out.println("Name is invalid dumbass");
        }
        catch (InvalidLengthException ex){
            System.out.println("Length is too short");
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Don't think that race exists mate");
        }
        try{
            System.out.println(portal.getNumberOfStages(12));
        }
        catch (IDNotRecognisedException ex){
            System.out.println("that race doesn't exist dumbass");
        }
        // try{
        //     System.out.println(portal.getRaceStages(12).length);
        //     System.out.println(portal.getRaceStages(12)[0]);
        // }
        // catch (IDNotRecognisedException ex){
        //     System.out.println("that race doesn't exist dumbass");
        // }
        try{
            System.out.println(portal.getStageLength(12));
        }
        catch (IDNotRecognisedException ex){
            System.out.println("that race doesn't exist dumbass");
        }
        // try{
        //     System.out.println(portal.getRaceStages(12).length);
        //     portal.removeStageById(14);
        //     System.out.println(portal.getRaceStages(12).length);
        // }
        // catch (IDNotRecognisedException ex){
        //     System.out.println("that stage doesn't exist dumbass");
        // }
        try{
            portal.addCategorizedClimbToStage(12, 3.0, SegmentType.C1, 15.0, 2.0);
            portal.addIntermediateSprintToStage(12, 4.0);
        }
        catch(IDNotRecognisedException ex){
            System.out.println("That stage don't exist bruv");
        }
        catch(InvalidLocationException ex){
            System.out.println("That location will not fit in the stage, duh");
        }
        catch(InvalidStageStateException ex){
            System.out.println("That stage is concluded forehead");
        }
        catch(InvalidStageTypeException ex){
            System.out.println("That stage is a time trial, idot");
        }
        try{
            System.out.println(portal.getStageSegments(12).length);
        }
        catch(IDNotRecognisedException ex){
            System.out.println("that stage doesn't exist dumbass");
        }
        try{
            portal.removeSegment(1112);
        }
        catch(IDNotRecognisedException ex){
            System.out.println("that segment doesn't exist dumbass");
        }
        catch(InvalidStageStateException ex){
            System.out.println("That stage is concluded forehead");
        }
        try{
            System.out.println(portal.getStageSegments(12).length);
        }
        catch(IDNotRecognisedException ex){
            System.out.println("that stage doesn't exist dumbass");
        }
        try{
            portal.concludeStagePreparation(12);
        }
        catch(IDNotRecognisedException ex){
            System.out.println("that stage doesn't exist dumbass");
        }
        catch(InvalidStageStateException ex){
            System.out.println("that stage is already concluded you silly goose!");
        }
        try{
            portal.addCategorizedClimbToStage(12, 3.0, SegmentType.C1, 15.0, 2.0);
        }
        catch(IDNotRecognisedException ex){
            System.out.println("That stage don't exist bruv");
        }
        catch(InvalidLocationException ex){
            System.out.println("That location will not fit in the stage, duh");
        }
        catch(InvalidStageStateException ex){
            System.out.println("That stage is concluded forehead");
        }
        catch(InvalidStageTypeException ex){
            System.out.println("That stage is a time trial, idot");
        }
        try{
            portal.removeTeam(113);
        }
        catch(IDNotRecognisedException ex){
            System.out.println("that team doesn't exist dumbass");
        }
        int[] testingArray = portal.getTeams();
        for (int x:testingArray){
            System.out.println(x);
        }
        try{
            portal.removeRider(1112);
        }
        catch(IDNotRecognisedException ex){
           System.out.println("ID not recognised you pillock");
        }
        try{
            System.out.println("Team Riders: " + portal.getTeamRiders(112)[0] + ", " + portal.getTeamRiders(112)[1]);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("failed to locate RiderID");
        }
        try{
            portal.saveCyclingPortal("SavedPortal.txt");
        }
        catch(IOException ex){
            System.out.println("saving failed! L");
        }
        portal.eraseCyclingPortal();
        try{
            portal.getTeamRiders(112);
        }
        catch(IDNotRecognisedException ex){
            System.out.println("fail");
        }
        try{
            portal.loadCyclingPortal("SavedPortal.txt");
        }
        catch(IOException ex){
            System.out.println("saving failed! L");
        }
        catch(ClassNotFoundException ex){
            System.out.println("class retrieval failure! L");
        }
        try{
            System.out.println(portal.getTeamRiders(112).length);
        }
        catch(IDNotRecognisedException ex){
            System.out.println("fail");
        }
        try{
            portal.removeRaceByName("CheepCheepBeach");
        }
        catch(NameNotRecognisedException ex){
           System.out.println("Name of said race is not recognised.");
        }
        testingArray = portal.getRaceIds();
        System.out.println("Race IDs:");
        for (int x:testingArray){
            System.out.println(x);
        }

        try{
            portal.registerRiderResultsInStage(11, 1113, LocalTime.of(00, 12, 41));
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Stage or Rider ID not recognised (registerRiderResultsInStage)");
        }
        catch (DuplicatedResultException ex){
            System.out.println("The result already exists in the stage (registerRiderResultsInStage)");
        }
        catch (InvalidCheckpointsException ex){
            System.out.println("Checkpoint times invalid (registerRiderResultsInStage)");
        }
        catch (InvalidStageStateException ex){
            System.out.println("Stage state incompatible with action (registerRiderResultsInStage)");
        }

        try{
            portal.getRiderResultsInStage(11, 1113);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Rider or Stage ID not recognised (getRiderResultsInStage)");
        }
        try{
            System.out.println(portal.getRiderResultsInStage(10000, 1113));
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Rider or Stage ID not recognised (getRiderResultsInStage) EXPECTED A FAILURE OK (bad StageID)");
        }
        try{
            portal.getRiderResultsInStage(11, 1);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Rider or Stage ID not recognised (getRiderResultsInStage) EXPECTED A FAILURE OK (bad RiderID)");
        }

        try{
            portal.getRiderAdjustedElapsedTimeInStage(11, 1113);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Rider or Stage ID not recogised (getRiderAdjustedTimeInStage)");
        }
        try{
            portal.getRiderAdjustedElapsedTimeInStage(0, 1113);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Rider or Stage ID not recogised (getRiderAdjustedTimeInStage) EXPECTED A FAILURE OK (bad RaceID)");
        }
        try{
            portal.getRiderAdjustedElapsedTimeInStage(11, 0);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Rider or Stage ID not recogised (getRiderAdjustedTimeInStage) EXPECTED A FAILURE OK (bad StageID)");
        }

        try{
            portal.deleteRiderResultsInStage(11, 1113);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Rider or Stage ID not recognised (deleteRiderResultsInStage)");
        }
        try{
            portal.deleteRiderResultsInStage(0, 1113);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Rider or Stage ID not recognised (deleteRiderResultsInStage) EXPECTED A FAILURE OK (bad RaceID)");
        }
        try{
            portal.deleteRiderResultsInStage(11, 0);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Rider or Stage ID not recognised (deleteRiderResultsInStage) EXPECTED A FAILURE OK (bad StageID)");
        }

        try{
            portal.getRidersRankInStage(11);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("StageID not recognised (getRidersRankInStage)");
        }
        try{
            portal.getRidersRankInStage(0);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("StageID not recognised (getRidersRankInStage) EXPECTED A FAILURE OK (bad StageID)");
        }

        try{
            portal.getRankedAdjustedElapsedTimesInStage(11);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("StageID not recognised (getRankedAdjustedElapsedTimesInStage)");
        }
        try{
            portal.getRankedAdjustedElapsedTimesInStage(0);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("StageID not recognised (getRankedAdjustedElapsedTimesInStage) EXPECTED A FAILURE OK (bad StageID)");
        }

        try{
            portal.getRidersPointsInStage(11);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Stage ID not recognised (getRidersPointsInStage)");
        }
        try{
            portal.getRidersPointsInStage(0);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Stage ID not recognised (getRidersPointsInStage) EXPECTED A FAILURE OK (bad StageID)");
        }
        try{
            portal.getRidersMountainPointsInStage(11);
        }
        catch (IDNotRecognisedException ex){ // method not programmed yet
            System.out.println("Stage ID not recognised (getRidersMountainPointsInStage)");
        }
        try{
            portal.saveCyclingPortal("testSavePortal");
        }
        catch (IOException ex){
            System.out.println("IO Error (saveCyclingPortal)");
        }

        try{
            portal.loadCyclingPortal("testSavePortal");
        }
        catch (IOException ex){
            System.out.println("IO Error (loadCyclingPortal)");
        }
        catch (ClassNotFoundException ex){
            System.out.println("Class not found - is the read data corrupt or formatted wrong? (loadCyclingPortal)");
        }

        try{
            portal.removeRaceByName("ShroomRidge");
            System.out.println("Attempted to remove ShroomRidge; races...");
            System.out.println(racesList);
        }
        catch (NameNotRecognisedException ex){
            System.out.println("Name of race does not exist - unexpected error (removeRaceByName)");
        }
    }
}
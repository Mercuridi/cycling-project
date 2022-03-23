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
        // setup for in depth team testing, teams stored in portal.
        // also tests the methods to create the team and add riders, etc
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
        // test methods to add riders to a team
        try{
            portal.createRider(112, "Wawa", 2003);
            portal.createRider(112, "Wewe", 2006);
            portal.createRider(112, "WooWoo", 2005);
            portal.createRider(112, "Confessie", 2008);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("fail");
        }
        // console print to find the riders in the team, can manually verify earlier createRider 
        // methods worked as expected (also tests getTeamRiders method)
        try{
            System.out.println("Team Riders: " + portal.getTeamRiders(112)[0]);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("failed to locate RiderID");
        }
        // same kind of test, prints all RiderIDs on the same line
        try{
            System.out.println("Team Riders: " + portal.getTeamRiders(112)[0] +", " + portal.getTeamRiders(112)[1] + ", " + portal.getTeamRiders(112)[2]);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("failed to locate RiderID");
        }
        // check that the method is acting as expected (should contain no values)
        if (portal.getRaceIds() instanceof int[]){
            System.out.println("empty array worked!");
        }
        // define racesList, create all the races for more rigorous testing
        // also tests methods to create races
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
        // print each member of racesList to console to check that they exist
        racesList = "Races: " + portal.getRaceIds()[0] + ", " + portal.getRaceIds()[1] + ", " + portal.getRaceIds()[2];
        System.out.println(racesList); 
        // check functionality of viewRaceDetails
        try{
            System.out.println(portal.viewRaceDetails(12));
        }
        catch (IDNotRecognisedException ex){
            System.out.println("that race doesn't exist dumbass");
        }
        // check errors are thrown correctly for viewRaceDetails (on purpose failure)
        try{
            System.out.println(portal.viewRaceDetails(0));
        }
        catch (IDNotRecognisedException ex){
            System.out.println("that race doesn't exist dumbass");
        }
        // test removeRaceByID (currently broken)
        // try{
        //      portal.removeRaceById(13);
        // }
        // catch (IDNotRecognisedException ex){
        //      System.out.println("that race doesn't exist dumbass");
        // }
        // racesList = "Races: " + portal.getRaceIds()[0] + ", " + portal.getRaceIds()[1];
        // System.out.println(racesList);
        
        // create time object for use in later tests
        LocalDateTime dateTime = LocalDateTime.now();
        // add example stages to a race for further testing of methods
        // also tests stage creation methods
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
        // TODO broken section of testing? 
        // try{
        //     System.out.println(portal.getNumberOfStages(12));
        // }
        // catch (IDNotRecognisedException ex){
        //     System.out.println("that race doesn't exist dumbass");
        // }
        // try{
        //     System.out.println(portal.getRaceStages(12).length);
        //     System.out.println(portal.getRaceStages(12)[0]);
        // }
        // catch (IDNotRecognisedException ex){
        //     System.out.println("that race doesn't exist dumbass");
        // }
        // try{
        //     System.out.println(portal.getStageLength(12));
        // }
        // catch (IDNotRecognisedException ex){
        //     System.out.println("that stage doesn't exist dumbass");
        // }
        // try{
        //     System.out.println(portal.getRaceStages(12).length);
        //     portal.removeStageById(14);
        //     System.out.println(portal.getRaceStages(12).length);
        // }
        // catch (IDNotRecognisedException ex){
        //     System.out.println("that stage doesn't exist dumbass");
        // }
        
        //test adding segments to stages
        try{
            portal.addCategorizedClimbToStage(12, 3.0, SegmentType.HC, 15.0, 2.0);
            portal.addIntermediateSprintToStage(12, 2.0);
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
        // test that stages were added correctly
        }
        try{
            System.out.println("Segments in stage:" + portal.getStageSegments(12).length);
        }
        catch(IDNotRecognisedException ex){
            System.out.println("that stage doesn't exist dumbass");
        }
        // test removal of specific segments
        // try{
        //     portal.removeSegment(1112);
        // }
        // catch(IDNotRecognisedException ex){
        //     System.out.println("that segment doesn't exist dumbass");
        // }
        // catch(InvalidStageStateException ex){
        //     System.out.println("That stage is concluded forehead");
        // }
        // verify last test was successful
        // try{
        //     System.out.println("Segments in stage after deletion:" + portal.getStageSegments(12).length);
        // }
        // catch(IDNotRecognisedException ex){
        //     System.out.println("that stage doesn't exist dumbass");
        // }
        // test stage prep state
        try{
            portal.concludeStagePreparation(12);
        }
        catch(IDNotRecognisedException ex){
            System.out.println("that stage doesn't exist dumbass");
        }
        catch(InvalidStageStateException ex){
            System.out.println("that stage is already concluded you silly goose!");
        }
        // test that the prep state is working correctly
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
            System.out.println("That stage is concluded forehead (EXPECTED ERROR OK)");
        }
        catch(InvalidStageTypeException ex){
            System.out.println("That stage is a time trial, idot");
        }
        // test removal of entire team
        try{
            portal.removeTeam(113);
        }
        catch(IDNotRecognisedException ex){
            System.out.println("that team doesn't exist dumbass");
        }
        // logic to check team was removed correctly
        int[] testingArray = portal.getTeams();
        for (int x:testingArray){
            System.out.println(x);
        }
        // TODO more broken tests?
        // try{
        //     portal.removeRider(1112);
        // }
        // catch(IDNotRecognisedException ex){
        //    System.out.println("ID not recognised you pillock");
        // }
        // try{
        //     System.out.println("Team Riders: " + portal.getTeamRiders(112)[0] + ", " + portal.getTeamRiders(112)[1]);
        // }
        // catch (IDNotRecognisedException ex){
        //     System.out.println("failed to locate RiderID");
        // }
        // try{
        //     portal.saveCyclingPortal("SavedPortal.txt");
        // }
        // catch(IOException ex){
        //     System.out.println("saving failed! L");
        // }
        // portal.eraseCyclingPortal();
        // try{
        //     portal.getTeamRiders(112);
        // }
        // catch(IDNotRecognisedException ex){
        //     System.out.println("fail");
        // }
        // try{
        //     portal.loadCyclingPortal("SavedPortal.txt");
        // }
        // catch(IOException ex){
        //     System.out.println("saving failed! L");
        // }
        // catch(ClassNotFoundException ex){
        //     System.out.println("class retrieval failure! L");
        // }
        // try{
        //     System.out.println(portal.getTeamRiders(112).length);
        // }
        // catch(IDNotRecognisedException ex){
        //     System.out.println("fail");
        // }
        // try{
        //     portal.removeRaceByName("CheepCheepBeach");
        // }
        // catch(NameNotRecognisedException ex){
        //    System.out.println("Name of said race is not recognised.");
        // }

        // setup for testing checkpoints and timing data
        testingArray = portal.getRaceIds();
        System.out.println("Race IDs:");
        for (int x:testingArray){
            System.out.println(x);
        }
        // add checkpoint data to stage
        try{
            LocalTime[] checkpointArray1;
            LocalTime[] checkpointArray2;
            LocalTime[] checkpointArray3;
            LocalTime[] checkpointArray4;
            checkpointArray1 = new LocalTime[]{LocalTime.of(00, 12, 41), LocalTime.of(00, 12, 42), LocalTime.of(00, 13, 00), LocalTime.of(00, 16, 42), LocalTime.of(00, 30, 0), LocalTime.of(01, 01, 12)};
            checkpointArray2 = new LocalTime[]{LocalTime.of(00, 11, 40), LocalTime.of(00, 12, 42), LocalTime.of(00, 14, 00), LocalTime.of(00, 14, 42), LocalTime.of(00, 35, 0), LocalTime.of(01, 01, 13)};
            checkpointArray3 = new LocalTime[]{LocalTime.of(00, 11, 40), LocalTime.of(00, 12, 42), LocalTime.of(00, 15, 00), LocalTime.of(00, 20, 42), LocalTime.of(00, 40, 0), LocalTime.of(01, 01, 14)};
            checkpointArray4 = new LocalTime[]{LocalTime.of(00, 11, 40), LocalTime.of(00, 12, 42), LocalTime.of(00, 16, 00), LocalTime.of(00, 21, 42), LocalTime.of(00, 45, 0), LocalTime.of(01, 01, 11)};
            portal.registerRiderResultsInStage(12, 1112, checkpointArray1);
            portal.registerRiderResultsInStage(12, 1114, checkpointArray3);
            portal.registerRiderResultsInStage(12, 1113, checkpointArray2);
            portal.registerRiderResultsInStage(12, 1115, checkpointArray4);
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
        // test fetching rider results
        try{
            LocalTime[] outputArray = portal.getRiderResultsInStage(12, 1113);
            System.out.println(outputArray.length);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Rider or Stage ID not recognised (getRiderResultsInStage)");
        }
        // test fetching rider results with bad data on purpose (should throw expected errors)
        try{
            LocalTime[] outputArray = portal.getRiderResultsInStage(10000, 1113);
            System.out.println(outputArray.length);
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
        // tests for getting adjusted times
        try{
            System.out.println(portal.getRiderAdjustedElapsedTimeInStage(12, 1114));
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Rider or Stage ID not recogised (getRiderAdjustedTimeInStage)");
        }
        // test for getting adjusted times with bad data (should throw expected errors)
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
        
        // TODO more tests commented out
        // try{
        //     portal.deleteRiderResultsInStage(12, 1113);
        // }
        // catch (IDNotRecognisedException ex){
        //     System.out.println("Rider or Stage ID not recognised (deleteRiderResultsInStage)");
        // }
        // try{
        //     portal.removeRider(1112);
        // }
        // catch(IDNotRecognisedException ex){
        //    System.out.println("ID not recognised you pillock");
        // }
        // try{
        //     portal.deleteRiderResultsInStage(0, 1113);
        // }
        // catch (IDNotRecognisedException ex){
        //     System.out.println("Rider or Stage ID not recognised (deleteRiderResultsInStage) EXPECTED A FAILURE OK (bad RaceID)");
        // }
        // try{
        //     portal.deleteRiderResultsInStage(11, 0);
        // }
        // catch (IDNotRecognisedException ex){
        //     System.out.println("Rider or Stage ID not recognised (deleteRiderResultsInStage) EXPECTED A FAILURE OK (bad StageID)");
        // }
        // try{
        //     portal.deleteRiderResultsInStage(0, 1113);
        // }
        // catch (IDNotRecognisedException ex){
        //     System.out.println("Rider or Stage ID not recognised (deleteRiderResultsInStage) EXPECTED A FAILURE OK (bad RaceID)");
        // }
        // try{
        //     portal.deleteRiderResultsInStage(11, 0);
        // }
        // catch (IDNotRecognisedException ex){
        //     System.out.println("Rider or Stage ID not recognised (deleteRiderResultsInStage) EXPECTED A FAILURE OK (bad StageID)");
        // }

        // test for pulling rider ranks (likely failure point, difficult logic in methods)
        try{
            portal.getRidersRankInStage(12);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("StageID not recognised (getRidersRankInStage)");
        // test with bad input (expecting errors)
        }
        // try{
        //     portal.getRidersRankInStage(0);
        // }
        // catch (IDNotRecognisedException ex){
        //     System.out.println("StageID not recognised (getRidersRankInStage) EXPECTED A FAILURE OK (bad StageID)");
        // }
        try{
            LocalTime[] cheeky = portal.getRankedAdjustedElapsedTimesInStage(12);
            for (LocalTime x:cheeky){
                System.out.println(x);
            }
            portal.getRidersRankInStage(0);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("StageID not recognised (getRidersRankInStage) EXPECTED A FAILURE OK (bad StageID)");
        }
        // test getting ranked adjusted times 
        try{
            portal.getRankedAdjustedElapsedTimesInStage(12);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("StageID not recognised (getRankedAdjustedElapsedTimesInStage)");
        }

        // try{
        //     portal.getRankedAdjustedElapsedTimesInStage(0);
        // }
        // catch (IDNotRecognisedException ex){
        //     System.out.println("StageID not recognised (getRankedAdjustedElapsedTimesInStage) EXPECTED A FAILURE OK (bad StageID)");
        // }

        // try{
        //     portal.getRidersPointsInStage(12);
        // }
        // catch (IDNotRecognisedException ex){
        //     System.out.println("Stage ID not recognised (getRidersPointsInStage)");
        // }
        // try{
        //     portal.getRidersPointsInStage(0);
        // }
        // catch (IDNotRecognisedException ex){
        //     System.out.println("Stage ID not recognised (getRidersPointsInStage) EXPECTED A FAILURE OK (bad StageID)");
        // }





        // same test again with bad data (expecting error)
        // try{
        //     portal.getRankedAdjustedElapsedTimesInStage(0);
        // }
        // catch (IDNotRecognisedException ex){
        //     System.out.println("StageID not recognised (getRankedAdjustedElapsedTimesInStage) EXPECTED A FAILURE OK (bad StageID)");
        // }
        // test points being retrieved per rider properly
        try{
            System.out.println(portal.getStageSegments(12).length);
            int[] pointArray = portal.getRidersPointsInStage(12);
            for (int j:pointArray){
                System.out.println(j);
            }
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Stage ID not recognised (getRidersPointsInStage)");
        }
        // test points retrieval with bad data (expecting errors)
        try{
            portal.getRidersPointsInStage(0);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Stage ID not recognised (getRidersPointsInStage) EXPECTED A FAILURE OK (bad StageID)");
        }
        try{
            System.out.println("Look here!");
            int[] pointArray = portal.getRidersMountainPointsInStage(12);
            for (int j:pointArray){
                System.out.println(j);
            }
        }
        catch (IDNotRecognisedException ex){ // method not programmed yet
            System.out.println("Stage ID not recognised (getRidersMountainPointsInStage)");
        }

        // test saving and loading the portal
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
        // test loading portal with name that doesn't exist (expecting error)
        try{
            portal.loadCyclingPortal("INVALIDNAME");
        }
        catch (IOException ex){
            System.out.println("IO Error (loadCyclingPortal)");
        }
        catch (ClassNotFoundException ex){
            System.out.println("Class not found - is the read data corrupt or formatted wrong? (loadCyclingPortal) EXPECTED A FAILURE OK (bad name)");
        }
        // test removing a race
        try{
            portal.removeRaceByName("ShroomRidge");
            System.out.println("Attempted to remove ShroomRidge; races...");
            System.out.println(racesList);
        }
        catch (NameNotRecognisedException ex){
            System.out.println("Name of race does not exist - unexpected error (removeRaceByName)");
        }
        // test removing race with name that doesn't exist (expecting error)
        try{
            portal.removeRaceByName("BADRACENAME");
            System.out.println("removing non existent race...");
            System.out.println(racesList);
        }
        catch (NameNotRecognisedException ex){
            System.out.println("Name of race does not exist - EXPECTED ERROR OK (removeRaceByName)");
        }
    }
}




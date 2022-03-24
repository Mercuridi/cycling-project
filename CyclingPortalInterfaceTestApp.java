import cycling.CyclingPortalInterface;
import cycling.CyclingPortal;
import cycling.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Greatly expanded test file to ensure functionality of CyclingPortal.
 * Originally authored by Diogo Pacheco, now changed enough to be a product
 * of the project itself.
 * 
 * The original docstring by Diogo is preserved ahead for clarity and legacy.
 * 
 * "A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the CyclingPortalInterface interface -- note you
 * will want to increase these checks, and run it on your CyclingPortal class
 * (not the BadCyclingPortal class)."
 *
 * @author Laurie Harbord and Kai Barber
 * @version 1.2
 */
public class CyclingPortalInterfaceTestApp {

	/**
	 * Test method.
	 * Present to auto-run the test code when compiled/ran correctly.
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


        // start of the Thorough Testing Framework with Example Real World Data.

        portal.eraseCyclingPortal();
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("The system has begun the construction of the Thorough Testing Framework...");
        CyclingPortalInterface thorough = new CyclingPortal();
        try{
            // creation of 5 teams
            System.out.println("Team ID of WHITE SPACE: " + thorough.createTeam("WHITE SPACE", "A group of close friends stuck together in a mental block. (WHITE SPACE)"));                                                                // expected team ID 115
            System.out.println("Team ID of Angel City Aces: " + thorough.createTeam("Angel City Aces", "The famous vigilantes of Angel City, led by Barker. (Angel City Aces)"));                                                            // expected team ID 116
            System.out.println("Team ID of Black Mesa: " + thorough.createTeam("Black Mesa", "A famous science team, responsible for the development of the Zero Point Energy Gun. (Black Mesa)"));                                          // expected team ID 117
            System.out.println("Team ID of Aperture Science: " + thorough.createTeam("Aperture Science", "A rival to Black Mesa, responsible for the development of the Aperture Science Handheld Portal Device. (Aperture Science)"));      // expected team ID 118
            System.out.println("Team ID of Ultra Mutants: " + thorough.createTeam("Ultra Mutants", "A ragtag band of irradiated mutants. (Ultra Mutants)"));                                                                                 // expected team ID 119
            System.out.println("All 5 teams created. Check print to verify: ");
            System.out.println("New teamIDs: ");
            for (int x:thorough.getTeams()){
                System.out.println(x);
            }
                                                 // added logic to the createTeam method to actually return the teamID upon team creation; currently using print lines to find out what the ID would be
        }
        catch (IllegalNameException ex){
            System.out.println("Team name already assigned in system. (Thorough system, team creation)");
        }
        catch (InvalidNameException ex){
            System.out.println("Team name invalid. (Thorough system, team creation)");
        }

        try{
            // creation of 22 riders
            // 1-3:     WHITE SPACE (3 riders)
            // 4-7:     Angel City Aces (4 riders)
            // 8-12:    Black Mesa (5 riders)
            // 13-17:   Aperture Science (5 riders)
            // 18-22:   Ultra Mutants (5 riders)
            thorough.createRider(115, "Sunny", 2006);
            thorough.createRider(115, "Celica", 2160);              // future birthday, test limits of system
            thorough.createRider(115, "Obama", 1961);               // last WHITE SPACE rider
            System.out.println("WHITE SPACE riders created...");

            thorough.createRider(116, "Ky Kiske", 2019);
            thorough.createRider(116, "Droz", 1980);
            thorough.createRider(116, "Jane Shepard", 1950);        // far in the past birthday, test limits of system. Consider limits tested...
            thorough.createRider(116, "Guy Spelunky", 2000);        // last Angel City Aces rider
            System.out.println("Angel City Aces riders created...");

            thorough.createRider(117, "Amanda Ripley", 2010);
            thorough.createRider(117, "Saffron", 1960);
            thorough.createRider(117, "Chell", 1983);
            thorough.createRider(117, "Baba", 1999);
            thorough.createRider(117, "John", 1992);                // last Black Mesa rider
            System.out.println("Black Mesa riders created...");

            thorough.createRider(118, "Harry Du Bois", 1979);
            thorough.createRider(118, "Kim Kitsuragi", 2002);
            thorough.createRider(118, "Kaidan Alenko", 2003);
            thorough.createRider(118, "Raptor", 2009);
            thorough.createRider(118, "Latch", 1970);               // last Aperture Science rider
            System.out.println("Aperture Science riders created...");

            thorough.createRider(119, "Yung Venuz", 1974);
            thorough.createRider(119, "Leshy", 1989);
            thorough.createRider(119, "Dr. Kleiner", 1989); 
            thorough.createRider(119, "A Repentant Devil", 1989);   // repeated birthdays, test limits of system
            thorough.createRider(119, "Maya Fey", 1995);            // last Ultra Mutants rider
            System.out.println("Ultra Mutants riders created...");

            System.out.println("All teams and riders created successfully.");
        }
        catch (IDNotRecognisedException ex){
            System.out.println("ID of team not recognised. (Thorough system, rider creation)");
        }
        catch (IllegalArgumentException ex){
            System.out.println("Invalid data was passed into the rider data (thorough system, rider creation");
        }
        try{
            System.out.println("Team Riders: WHITE SPACE:       " + thorough.getTeamRiders(115)[0] +", " + thorough.getTeamRiders(115)[1] + ", " + thorough.getTeamRiders(115)[2]);
            System.out.println("Team Riders: Angel City Aces:   " + thorough.getTeamRiders(116)[0] +", " + thorough.getTeamRiders(116)[1] + ", " + thorough.getTeamRiders(116)[2] + ", " + thorough.getTeamRiders(116)[3]);
            System.out.println("Team Riders: Black Mesa:        " + thorough.getTeamRiders(117)[0] +", " + thorough.getTeamRiders(117)[1] + ", " + thorough.getTeamRiders(117)[2] + ", " + thorough.getTeamRiders(117)[3] + ", " + thorough.getTeamRiders(117)[4]);
            System.out.println("Team Riders: Aperture Science:  " + thorough.getTeamRiders(118)[0] +", " + thorough.getTeamRiders(118)[1] + ", " + thorough.getTeamRiders(118)[2] + ", " + thorough.getTeamRiders(118)[3] + ", " + thorough.getTeamRiders(118)[4]);
            System.out.println("Team Riders: Ultra Mutants:     " + thorough.getTeamRiders(119)[0] +", " + thorough.getTeamRiders(119)[1] + ", " + thorough.getTeamRiders(119)[2] + ", " + thorough.getTeamRiders(119)[3] + ", " + thorough.getTeamRiders(119)[4]);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("ID of team not recognised. (Thorough system, rider creation, printing check)");
        }
        catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Index out of bounds. Error in adding riders to team. (Thorough system, rider creation, printing check)");
        }
        catch (IllegalArgumentException ex){
            System.out.println("Invalid data was passed into the rider data (thorough system, rider creation, printing check");
        }

        System.out.println("Creating races...");
        try{
            thorough.createRace("Silverstone", "A classic British track race.");
            thorough.createRace("CheepCheepBeach", "A nostalgic trip around a beach nearby a patio'd pavilion.");
        }
        catch (IllegalNameException ex){
            System.out.println("Race name already assigned in system. (Thorough system, race creation)");
        }
        catch (InvalidNameException ex){
            System.out.println("Race name invalid. (Thorough system, race creation)");
        }
        racesList = "Race IDs: " + thorough.getRaceIds()[0] + ", " + thorough.getRaceIds()[1];
        System.out.println(racesList); 
        try{
            dateTime = LocalDateTime.now();
            thorough.addStageToRace(15, "TestFlatStage", "Test flat stage in Silverstone.", 5.0, dateTime, StageType.FLAT);
            dateTime = LocalDateTime.now();
            thorough.addStageToRace(15, "TestHighMountainStage", "Test high mountain stage in Silverstone.", 8.5, dateTime, StageType.HIGH_MOUNTAIN);
            dateTime = LocalDateTime.now();
            thorough.addStageToRace(15, "TestMediumMountainStage", "Test medium mountain stage in Silverstone.", 10.3, dateTime, StageType.MEDIUM_MOUNTAIN);
            System.out.println("Silverstone stages added.");
        }
        catch (IllegalNameException ex){
            System.out.println("Stage name already assigned in system. (Thorough system, Silverstone stage creation)");
        }
        catch (InvalidNameException ex){
            System.out.println("Stage name is invalid. (Thorough system, Silverstone stage creation)");
        }
        catch (InvalidLengthException ex){
            System.out.println("Length of the stage is too short. (Thorough system, Silverstone stage creation)");
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Race ID not recognised. (Thorough system, Silverstone stage creation)");
        }

        try{
            dateTime = LocalDateTime.now();
            System.out.println("StageID for TestFlatStage: " +thorough.addStageToRace(16, "TestFlatStage", "Test flat stage in CheepCheepBeach.", 9.1, dateTime, StageType.FLAT));
            dateTime = LocalDateTime.now();
            thorough.addStageToRace(16, "TestHighMountainStage", "Test high mountain stage in CheepCheepBeach.", 5.0, dateTime, StageType.HIGH_MOUNTAIN);
            dateTime = LocalDateTime.now();
            thorough.addStageToRace(16, "TestMediumMountainStage", "Test medium mountain stage in CheepCheepBeach.", 16.21, dateTime, StageType.MEDIUM_MOUNTAIN);
            System.out.println("CheepCheepBeach stages added.");
        }
        catch (IllegalNameException ex){
            System.out.println("Stage name already assigned in system. (Thorough system, CheepCheepBeach stage creation)");
        }
        catch (InvalidNameException ex){
            System.out.println("Stage name is invalid. (Thorough system, CheepCheepBeach stage creation)");
        }
        catch (InvalidLengthException ex){
            System.out.println("Length of the stage is too short. (Thorough system, CheepCheepBeach stage creation)");
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Race ID not recognised. (Thorough system, CheepCheepBeach stage creation)");
        }

        // add rider checkpoint data for every rider; all of these results are being placed in TestFlatStage of CheepCheepBeach.
        try{
            // set up 22 unique arrays of checkpoints to assign to each rider
            LocalTime[] checkpointArray1;
            LocalTime[] checkpointArray2;
            LocalTime[] checkpointArray3;
            LocalTime[] checkpointArray4;
            LocalTime[] checkpointArray5;
            LocalTime[] checkpointArray6;
            LocalTime[] checkpointArray7;
            LocalTime[] checkpointArray8;
            LocalTime[] checkpointArray9;
            LocalTime[] checkpointArray10;
            LocalTime[] checkpointArray11;
            LocalTime[] checkpointArray12;
            LocalTime[] checkpointArray13;
            LocalTime[] checkpointArray14;
            LocalTime[] checkpointArray15;
            LocalTime[] checkpointArray16;
            LocalTime[] checkpointArray17;
            LocalTime[] checkpointArray18;
            LocalTime[] checkpointArray19;
            LocalTime[] checkpointArray20;
            LocalTime[] checkpointArray21;
            LocalTime[] checkpointArray22;

            // all checkpoint data is completely unique
            checkpointArray1  = new LocalTime[]{LocalTime.of(00, 12, 41), LocalTime.of(00, 12, 42), LocalTime.of(00, 13,  2), LocalTime.of(00, 16, 55), LocalTime.of(00, 30, 30), LocalTime.of(01, 01, 12)};
            checkpointArray2  = new LocalTime[]{LocalTime.of(00, 11, 12), LocalTime.of(00, 12, 37), LocalTime.of(00, 14,  1), LocalTime.of(00, 14, 58), LocalTime.of(00, 35, 35), LocalTime.of(01, 01, 13)};
            checkpointArray3  = new LocalTime[]{LocalTime.of(00, 24, 42), LocalTime.of(00, 25, 19), LocalTime.of(00, 31, 18), LocalTime.of(00, 42, 51), LocalTime.of(00, 45, 40), LocalTime.of(01, 10, 14)};
            checkpointArray4  = new LocalTime[]{LocalTime.of(00, 38, 32), LocalTime.of(00, 41, 23), LocalTime.of(00, 44,  9), LocalTime.of(00, 47, 48), LocalTime.of(00, 55, 42), LocalTime.of(01, 31, 34)};
            checkpointArray5  = new LocalTime[]{LocalTime.of(00, 21, 54), LocalTime.of(00, 31, 11), LocalTime.of(00, 34, 13), LocalTime.of(00, 44, 45), LocalTime.of(00, 45, 45), LocalTime.of(01, 46, 56)};
            checkpointArray6  = new LocalTime[]{LocalTime.of(00,  1, 31), LocalTime.of(00, 12,  2), LocalTime.of(00, 16, 16), LocalTime.of(00, 21, 44), LocalTime.of(00, 47, 48), LocalTime.of(01, 22, 32)};
            checkpointArray7  = new LocalTime[]{LocalTime.of(00,  9, 19), LocalTime.of(00, 10,  8), LocalTime.of(00, 25, 19), LocalTime.of(00, 29, 41), LocalTime.of(00, 32, 49), LocalTime.of(01, 24, 53)};
            checkpointArray8  = new LocalTime[]{LocalTime.of(00,  2, 47), LocalTime.of(00,  6, 43), LocalTime.of(00,  8, 20), LocalTime.of(00, 10, 39), LocalTime.of(00, 12, 50), LocalTime.of(01, 19, 42)};
            checkpointArray9  = new LocalTime[]{LocalTime.of(00, 59, 24), LocalTime.of(01, 12, 22), LocalTime.of(01, 34, 22), LocalTime.of(01, 45, 37), LocalTime.of(01, 50, 55), LocalTime.of(01, 59, 15)};
            checkpointArray10 = new LocalTime[]{LocalTime.of(01, 11, 47), LocalTime.of(01, 17, 33), LocalTime.of(02, 16, 14), LocalTime.of(02, 25, 36), LocalTime.of(02, 45, 52), LocalTime.of(03, 01, 31)};
            checkpointArray11 = new LocalTime[]{LocalTime.of(02, 22, 11), LocalTime.of(02, 42, 23), LocalTime.of(03, 19, 26), LocalTime.of(03, 21, 33), LocalTime.of(03, 42, 53), LocalTime.of(04, 19, 51)};
            checkpointArray12 = new LocalTime[]{LocalTime.of(03,  6, 33), LocalTime.of(03, 12, 32), LocalTime.of(03, 16, 28), LocalTime.of(03, 21, 34), LocalTime.of(03, 48, 57), LocalTime.of(04, 22, 41)};
            checkpointArray13 = new LocalTime[]{LocalTime.of(00, 53, 56), LocalTime.of(00, 59, 44), LocalTime.of(01, 16, 30), LocalTime.of(01, 19, 29), LocalTime.of(01, 33, 27), LocalTime.of(01, 39, 33)};
            checkpointArray14 = new LocalTime[]{LocalTime.of(00, 34, 48), LocalTime.of(00, 38, 55), LocalTime.of(00, 44, 33), LocalTime.of(00, 49, 26), LocalTime.of(00, 52, 22), LocalTime.of(01, 59, 22)};
            checkpointArray15 = new LocalTime[]{LocalTime.of(00, 42, 27), LocalTime.of(00, 47, 51), LocalTime.of(00, 55, 32), LocalTime.of(01, 01, 24), LocalTime.of(01, 06, 25), LocalTime.of(01, 13, 19)};
            checkpointArray16 = new LocalTime[]{LocalTime.of(00, 13, 32), LocalTime.of(00, 18, 49), LocalTime.of(00, 32, 35), LocalTime.of(00, 38, 22), LocalTime.of(00, 39, 20), LocalTime.of(01, 12, 32)};
            checkpointArray17 = new LocalTime[]{LocalTime.of(00, 19, 57), LocalTime.of(00, 22, 31), LocalTime.of(00, 22, 42), LocalTime.of(00, 35, 19), LocalTime.of(00, 45, 18), LocalTime.of(01, 26, 47)};
            checkpointArray18 = new LocalTime[]{LocalTime.of(00,  6, 32), LocalTime.of(00, 17, 46), LocalTime.of(00, 18, 46), LocalTime.of(00, 22, 15), LocalTime.of(00, 31,  9), LocalTime.of(01, 29, 59)};
            checkpointArray19 = new LocalTime[]{LocalTime.of(00, 13,  8), LocalTime.of(00, 19, 11), LocalTime.of(00, 24, 47), LocalTime.of(00, 36, 12), LocalTime.of(00, 39, 14), LocalTime.of(01, 33, 28)};
            checkpointArray20 = new LocalTime[]{LocalTime.of(00, 22, 17), LocalTime.of(00, 42, 22), LocalTime.of(00, 48, 50), LocalTime.of(00, 53, 10), LocalTime.of(00, 57, 12), LocalTime.of(01, 32, 46)};
            checkpointArray21 = new LocalTime[]{LocalTime.of(00, 16, 53), LocalTime.of(00, 17, 47), LocalTime.of(00, 34, 52), LocalTime.of(00, 41,  6), LocalTime.of(00, 48,  4), LocalTime.of(01, 52, 43)};
            checkpointArray22 = new LocalTime[]{LocalTime.of(00, 45, 23), LocalTime.of(00, 54, 28), LocalTime.of(01,  8, 56), LocalTime.of(01, 21,  2), LocalTime.of(01, 25,  8), LocalTime.of(01, 35, 34)};

            thorough.registerRiderResultsInStage(18, 1116, checkpointArray1);
            thorough.registerRiderResultsInStage(18, 1117, checkpointArray2);
            thorough.registerRiderResultsInStage(18, 1118, checkpointArray3);
            thorough.registerRiderResultsInStage(18, 1119, checkpointArray4);
            thorough.registerRiderResultsInStage(18, 1120, checkpointArray5);
            thorough.registerRiderResultsInStage(18, 1121, checkpointArray6);
            thorough.registerRiderResultsInStage(18, 1122, checkpointArray7);
            thorough.registerRiderResultsInStage(18, 1123, checkpointArray8);
            thorough.registerRiderResultsInStage(18, 1124, checkpointArray9);
            thorough.registerRiderResultsInStage(18, 1125, checkpointArray10);
            thorough.registerRiderResultsInStage(18, 1126, checkpointArray11);
            thorough.registerRiderResultsInStage(18, 1127, checkpointArray12);
            thorough.registerRiderResultsInStage(18, 1128, checkpointArray13);
            thorough.registerRiderResultsInStage(18, 1129, checkpointArray14);
            thorough.registerRiderResultsInStage(18, 1130, checkpointArray15);
            thorough.registerRiderResultsInStage(18, 1131, checkpointArray16);
            thorough.registerRiderResultsInStage(18, 1132, checkpointArray17);
            thorough.registerRiderResultsInStage(18, 1133, checkpointArray18);
            thorough.registerRiderResultsInStage(18, 1134, checkpointArray19);
            thorough.registerRiderResultsInStage(18, 1135, checkpointArray20);
            thorough.registerRiderResultsInStage(18, 1136, checkpointArray21);
            thorough.registerRiderResultsInStage(18, 1137, checkpointArray22);
            System.out.println("results added???????? pog???");

        }
        catch (IDNotRecognisedException ex){
            System.out.println("Stage or Rider ID not recognised. (Thorough system, result registration)");
        }
        catch (DuplicatedResultException ex){
            System.out.println("The result already exists in the stage. (Thorough system, result registration)");
        }
        catch (InvalidCheckpointsException ex){
            System.out.println("Checkpoint times invalid. (Thorough system, result registration)");
        }
        catch (InvalidStageStateException ex){
            System.out.println("Stage state incompatible with action. (Thorough system, result registration)");
        }
        try{
            System.out.println("Begin checking individual rider for results presence...");
            LocalTime[] outputArray1 = thorough.getRiderResultsInStage(18, 1116);
            System.out.println(outputArray1.length);
            LocalTime[] outputArray2 = thorough.getRiderResultsInStage(18, 1117);
            System.out.println(outputArray2.length);
            LocalTime[] outputArray3 = thorough.getRiderResultsInStage(18, 1118);
            System.out.println(outputArray3.length);
            LocalTime[] outputArray4 = thorough.getRiderResultsInStage(18, 1119);
            System.out.println(outputArray4.length);
            LocalTime[] outputArray5 = thorough.getRiderResultsInStage(18, 1120);
            System.out.println(outputArray5.length);
            LocalTime[] outputArray6 = thorough.getRiderResultsInStage(18, 1121);
            System.out.println(outputArray6.length);
            LocalTime[] outputArray7 = thorough.getRiderResultsInStage(18, 1122);
            System.out.println(outputArray7.length);
            LocalTime[] outputArray8 = thorough.getRiderResultsInStage(18, 1123);
            System.out.println(outputArray8.length);
            LocalTime[] outputArray9 = thorough.getRiderResultsInStage(18, 1124);
            System.out.println(outputArray9.length);
            LocalTime[] outputArray10 = thorough.getRiderResultsInStage(18, 1125);
            System.out.println(outputArray10.length);
            LocalTime[] outputArray11 = thorough.getRiderResultsInStage(18, 1126);
            System.out.println(outputArray11.length);
            LocalTime[] outputArray12 = thorough.getRiderResultsInStage(18, 1127);
            System.out.println(outputArray12.length);
            LocalTime[] outputArray13 = thorough.getRiderResultsInStage(18, 1128);
            System.out.println(outputArray13.length);
            LocalTime[] outputArray14 = thorough.getRiderResultsInStage(18, 1129);
            System.out.println(outputArray14.length);
            LocalTime[] outputArray15 = thorough.getRiderResultsInStage(18, 1130);
            System.out.println(outputArray15.length);
            LocalTime[] outputArray16 = thorough.getRiderResultsInStage(18, 1131);
            System.out.println(outputArray16.length);
            LocalTime[] outputArray17 = thorough.getRiderResultsInStage(18, 1132);
            System.out.println(outputArray17.length);
            LocalTime[] outputArray18 = thorough.getRiderResultsInStage(18, 1133);
            System.out.println(outputArray18.length);
            LocalTime[] outputArray19 = thorough.getRiderResultsInStage(18, 1134);
            System.out.println(outputArray19.length);
            LocalTime[] outputArray20 = thorough.getRiderResultsInStage(18, 1135);
            System.out.println(outputArray20.length);
            LocalTime[] outputArray21 = thorough.getRiderResultsInStage(18, 1136);
            System.out.println(outputArray21.length);
            LocalTime[] outputArray22 = thorough.getRiderResultsInStage(18, 1137);
            System.out.println(outputArray22.length);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Stage or Rider ID not recognised. (Thorough system, result registration checking)");
        }
        try{
            System.out.println("Organise elapsed times in stage from fastest to slowest...");
            LocalTime[] cheeky = thorough.getRankedAdjustedElapsedTimesInStage(18);
            for (LocalTime x:cheeky){
                System.out.println(x);
            }
            System.out.println("Organise riderID's in stage from fastest to slowest...");
            int[] riderIDArray = thorough.getRidersRankInStage(18);
            for (int y:riderIDArray){
                System.out.println(y);
            }
        }
        catch (IDNotRecognisedException ex){
            System.out.println("StageID not recognised (getRidersRankInStage) EXPECTED A FAILURE OK (bad StageID)");
        }
        // test getting ranked adjusted times 
        
        try{;
            System.out.println("Organise rider sprint points in stage, according to finish time...");
            int[] pointArray = thorough.getRidersPointsInStage(18);
            for (int j:pointArray){
                System.out.println(j);
            }
        }
        catch (IDNotRecognisedException ex){
            System.out.println("Stage ID not recognised (getRidersPointsInStage)");
        }
        try{
            System.out.println("Organise rider mountain points in stage, according to finish time...");
            int[] pointArray = thorough.getRidersMountainPointsInStage(18);
            for (int j:pointArray){
                System.out.println(j);
            }
        }
        catch (IDNotRecognisedException ex){ // method not programmed yet
            System.out.println("Stage ID not recognised (getRidersMountainPointsInStage)");
        }
    }
}

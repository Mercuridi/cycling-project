import cycling.CyclingPortalInterface;
import cycling.CyclingPortal;
import cycling.*;
import java.util.ArrayList;

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
        ArrayList<cycling.RiderStageResult> RiderStageResults = new ArrayList<RiderStageResult>();
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
        racesList = "Races: " + portal.getRaceIds()[0] + ", " + portal.getRaceIds()[1];
        System.out.println(racesList); 
        try{
            System.out.println(portal.viewRaceDetails(12));
        }
        catch (IDNotRecognisedException ex){
            System.out.println("that race doesn't exist dumbass");
        }
        try{
            portal.removeRaceById(13);
        }
        catch (IDNotRecognisedException ex){
            System.out.println("that race doesn't exist dumbass");
        }
        racesList = "Races: " + portal.getRaceIds()[0] + ", " + portal.getRaceIds()[1];
        System.out.println(racesList); 
        // System.out.println("======= Info Checks =======");
        // System.out.println("---------------------------");
        
        // System.out.println("========== Teams ==========");
        // System.out.println("Team Name: " + WildPlanets.getTeamName());
        // System.out.println("Team Description: " + WildPlanets.getTeamDescription());
        // System.out.println("Team ID: " + WildPlanets.getTeamID());
        // System.out.println("Team Riders: " + WildPlanets.getRidersInTeam());
        // System.out.println("---------------------------");
        // System.out.println("Team Name: " + RoadBlockers.getTeamName());
        // System.out.println("Team Description: " + RoadBlockers.getTeamDescription());
        // System.out.println("Team ID: " + RoadBlockers.getTeamID());
        // System.out.println("Team Riders: " + RoadBlockers.getRidersInTeam());
        // System.out.println("---------------------------");
        // System.out.println("Team Name: " + ClassyLunatics.getTeamName());
        // System.out.println("Team Description: " + ClassyLunatics.getTeamDescription());
        // System.out.println("Team ID: " + ClassyLunatics.getTeamID());
        // System.out.println("Team Riders: " + ClassyLunatics.getRidersInTeam());
        // System.out.println("---------------------------");
        // System.out.println("Team Name: " + RedEinsteins.getTeamName());
        // System.out.println("Team Description: " + RedEinsteins.getTeamDescription());
        // System.out.println("Team ID: " + RedEinsteins.getTeamID());
        // System.out.println("Team Riders: " + RedEinsteins.getRidersInTeam());
        // System.out.println("---------------------------");
        // System.out.println("Team Name: " + CobaltMonkeys.getTeamName());
        // System.out.println("Team Description: " + CobaltMonkeys.getTeamDescription());
        // System.out.println("Team ID: " + CobaltMonkeys.getTeamID());
        // System.out.println("Team Riders: " + CobaltMonkeys.getRidersInTeam());
        // System.out.println("---------------------------");

        // System.out.println("========== Races ==========");
        // System.out.println("Race Name: " + CheepBeach.getRaceName());
        // System.out.println("Race Description: " + CheepBeach.getRaceDescription());
        // System.out.println("Race ID: " + CheepBeach.getRaceID());
        // System.out.println("Race Stages Total: " + CheepBeach.getNumberOfStages());
        // System.out.println("Race Stages: " + CheepBeach.getStages());
        // System.out.println("Race Length: " + CheepBeach.getRaceLength());
        // System.out.println("Race Riders: " + CheepBeach.getRidersInRace());
        // System.out.println("---------------------------");
        // System.out.println("Race Name: " + ShroomRidge.getRaceName());
        // System.out.println("Race Description: " + ShroomRidge.getRaceDescription());
        // System.out.println("Race ID: " + ShroomRidge.getRaceID());
        // System.out.println("Race Stages Total: " + ShroomRidge.getNumberOfStages());
        // System.out.println("Race Stages: " + ShroomRidge.getStages());
        // System.out.println("Race Length: " + ShroomRidge.getRaceLength());
        // System.out.println("Race Riders: " + ShroomRidge.getRidersInRace());
        // System.out.println("---------------------------");
        // System.out.println("Race Name: " + AirshipFortress.getRaceName());
        // System.out.println("Race Description: " + AirshipFortress.getRaceDescription());
        // System.out.println("Race ID: " + AirshipFortress.getRaceID());
        // System.out.println("Race Stages Total: " + AirshipFortress.getNumberOfStages());
        // System.out.println("Race Stages: " + AirshipFortress.getStages());
        // System.out.println("Race Length: " + AirshipFortress.getRaceLength());
        // System.out.println("Race Riders: " + AirshipFortress.getRidersInRace());
        // System.out.println("---------------------------");
        // System.out.println("Race Name: " + Excitebike.getRaceName());
        // System.out.println("Race Description: " + Excitebike.getRaceDescription());
        // System.out.println("Race ID: " + Excitebike.getRaceID());
        // System.out.println("Race Stages Total: " + Excitebike.getNumberOfStages());
        // System.out.println("Race Stages: " + Excitebike.getStages());
        // System.out.println("Race Length: " + Excitebike.getRaceLength());
        // System.out.println("Race Riders: " + Excitebike.getRidersInRace());
        // System.out.println("---------------------------");
        
        //Kai work down here!

	}

}

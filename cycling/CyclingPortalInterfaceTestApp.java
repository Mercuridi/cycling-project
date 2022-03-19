import cycling.CyclingPortalInterface;
import cycling.MiniCyclingPortalInterface;

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

		ArrayList<cycling.Team> Teams = new ArrayList<Team>();
        ArrayList<cycling.Race> Races = new ArrayList<Race>();
        ArrayList<cycling.RiderStageResult> RiderStageResults = new ArrayList<RiderStageResult>();

        Team WildPlanets = new Team("Wild Planets", "This is a sample description for the team Wild Planets.");
        Teams.add(1, WildPlanets);
        Team RoadBlockers = new Team("Road Blockers", "This is a sample description for the team Road Blockers.");
        Teams.add(1, RoadBlockers);
        Team ClassyLunatics = new Team("Classy Lunatics", "This is a sample description for the team Classy Lunatics.");
        Teams.add(1, ClassyLunatics);
        Team RedEinsteins = new Team("Red Einsteins", "This is a sample description for the team Red Einsteins.");
        Teams.add(1, RedEinsteins);
        Team CobaltMonkeys = new Team("Cobalt Monkeys", "This is a sample description for the team Cobalt Monkeys.");
        Teams.add(1, CobaltMonkeys);

        Race CheepBeach = new Race("Cheep Cheep Beach", "A temperate and sunny race along Cheep Cheep Beach.");
        Races.add(1, CheepBeach);
        Race ShroomRidge = new Race("Shroom Ridge", "A long race along a mountain ridge, with gorgeous views and a dangerous sheer drop.");
        Races.add(1, ShroomRidge);
        Race AirshipFortress = new Race ("Airship Fortress", "An exciting race around a flying airship.");
        Races.add(1, AirshipFortress);
        Race Excitebike = new Race("Excitebike Arena", "A shorter race on a track constructed in the legendary Excitebike Arena.");
        Races.add(1, Excitebike);

        System.out.println("======= Info Checks =======");
        System.out.println("---------------------------");
        
        System.out.println("========== Teams ==========");
        System.out.println("Team Name: " + WildPlanets.getTeamName());
        System.out.println("Team Description: " + WildPlanets.getTeamDescription());
        System.out.println("Team ID: " + WildPlanets.getTeamID());
        System.out.println("Team Riders: " + WildPlanets.getRidersInTeam());
        System.out.println("---------------------------");
        System.out.println("Team Name: " + RoadBlockers.getTeamName());
        System.out.println("Team Description: " + RoadBlockers.getTeamDescription());
        System.out.println("Team ID: " + RoadBlockers.getTeamID());
        System.out.println("Team Riders: " + RoadBlockers.getRidersInTeam());
        System.out.println("---------------------------");
        System.out.println("Team Name: " + ClassyLunatics.getTeamName());
        System.out.println("Team Description: " + ClassyLunatics.getTeamDescription());
        System.out.println("Team ID: " + ClassyLunatics.getTeamID());
        System.out.println("Team Riders: " + ClassyLunatics.getRidersInTeam());
        System.out.println("---------------------------");
        System.out.println("Team Name: " + RedEinsteins.getTeamName());
        System.out.println("Team Description: " + RedEinsteins.getTeamDescription());
        System.out.println("Team ID: " + RedEinsteins.getTeamID());
        System.out.println("Team Riders: " + RedEinsteins.getRidersInTeam());
        System.out.println("---------------------------");
        System.out.println("Team Name: " + CobaltMonkeys.getTeamName());
        System.out.println("Team Description: " + CobaltMonkeys.getTeamDescription());
        System.out.println("Team ID: " + CobaltMonkeys.getTeamID());
        System.out.println("Team Riders: " + CobaltMonkeys.getRidersInTeam());
        System.out.println("---------------------------");

        System.out.println("========== Races ==========");
        System.out.println("Race Name: " + CheepBeach.getRaceName());
        System.out.println("Race Description: " + CheepBeach.getRaceDescription());
        System.out.println("Race ID: " + CheepBeach.getRaceID());
        System.out.println("Race Stages Total: " + CheepBeach.getNumberOfStages());
        System.out.println("Race Stages: " + CheepBeach.getStages());
        System.out.println("Race Length: " + CheepBeach.getRaceLength());
        System.out.println("Race Riders: " + CheepBeach.getRidersInRace());
        System.out.println("---------------------------");
        System.out.println("Race Name: " + ShroomRidge.getRaceName());
        System.out.println("Race Description: " + ShroomRidge.getRaceDescription());
        System.out.println("Race ID: " + ShroomRidge.getRaceID());
        System.out.println("Race Stages Total: " + ShroomRidge.getNumberOfStages());
        System.out.println("Race Stages: " + ShroomRidge.getStages());
        System.out.println("Race Length: " + ShroomRidge.getRaceLength());
        System.out.println("Race Riders: " + ShroomRidge.getRidersInRace());
        System.out.println("---------------------------");
        System.out.println("Race Name: " + AirshipFortress.getRaceName());
        System.out.println("Race Description: " + AirshipFortress.getRaceDescription());
        System.out.println("Race ID: " + AirshipFortress.getRaceID());
        System.out.println("Race Stages Total: " + AirshipFortress.getNumberOfStages());
        System.out.println("Race Stages: " + AirshipFortress.getStages());
        System.out.println("Race Length: " + AirshipFortress.getRaceLength());
        System.out.println("Race Riders: " + AirshipFortress.getRidersInRace());
        System.out.println("---------------------------");
        System.out.println("Race Name: " + Excitebike.getRaceName());
        System.out.println("Race Description: " + Excitebike.getRaceDescription());
        System.out.println("Race ID: " + Excitebike.getRaceID());
        System.out.println("Race Stages Total: " + Excitebike.getNumberOfStages());
        System.out.println("Race Stages: " + Excitebike.getStages());
        System.out.println("Race Length: " + Excitebike.getRaceLength());
        System.out.println("Race Riders: " + Excitebike.getRidersInRace());
        System.out.println("---------------------------");

	}

}

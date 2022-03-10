import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Tester {
    public ArrayList<Team> Teams;

    public int locateTeam(int teamID) {
		boolean teamFound = false;
		int searchCount = 0;
		Team currentTeam;
		int tempID; 
		while (teamFound == false && searchCount < Teams.size()) {
            currentTeam = (Teams.get(searchCount));
            tempID = currentTeam.getTeamID();
            if (tempID == (teamID)) {
                teamFound = true;
            }
            else {
                searchCount += 1;
            }
        }
        if (teamFound == true) {
            return searchCount; //returns location of team
        }
        else
			return 0; //0 is a rouge value
	}
    public int createTeam(String name, String description) {
		Team newTeam;
		newTeam = new Team(name, description);
		Teams.add(newTeam);
		return 0;
	}
    public void removeTeam(int teamId) {
			int targetIndex = locateTeam(teamId);
			if (targetIndex != 0) {
				Teams.remove(targetIndex);
            	System.out.println("Team removed successfully!");
			}
			else
				System.out.println("Team with entered teamID not found!");
        }
    public int[] getTeams() {
		int []outputArray = new int[Teams.size()];
		int indexCount = 0;
		for (Team x:Teams) {
			outputArray[indexCount] = x.getTeamID();
			++indexCount;
		}
		return outputArray;
	}
    public int[] getTeamRiders(int teamId) {
		int targetIndex = locateTeam(teamId);
		if (targetIndex != 0) {
			for (Rider x:Teams.get(targetIndex).getRidersInTeam()){
				System.out.println(Integer.toString(x.getRiderID()) + x.getName());
			}
		}
		else {
			System.out.println("Team with entered teamID not found!");
		}
		return null;
	}
    public int createRider(int teamID, String name, int yearOfBirth) {
			Rider newRider;
            boolean addCheck = false;
            newRider = new Rider(teamID, name, yearOfBirth);
            for (Team x:Teams) {
                if (x.getTeamID() == teamID){
                    x.addRider(newRider);
                    addCheck = true;
                    break;
                }
            }
            if (addCheck == false){
                System.out.println("TeamID not found!");
                //throw new IDNotRecognisedException();
            }
		return 0;
	}
    
    public static void main(String[] args) {
		
        }
    }
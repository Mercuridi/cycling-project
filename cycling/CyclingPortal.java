package cycling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * 
 * @author Laurie Harbord and Kai Barber
 * @version 1.0
 *
 */
public class CyclingPortal implements CyclingPortalInterface {
	public ArrayList<Team> Teams = new ArrayList<Team>();
	public ArrayList<Race> Races = new ArrayList<Race>();
	public ArrayList<RiderStageResult> RiderStageResults = new ArrayList<RiderStageResult>();

	//our own methods!!!
	public int locateTeam(int teamID) throws IDNotRecognisedException {
		boolean teamFound = false;
		int searchCount = 0;
		Team currentTeam;
		int tempID; 
		while (teamFound == false && searchCount < Teams.size()) {
            currentTeam = (Teams.get(searchCount));
            tempID = currentTeam.getTeamID();
            if (tempID == teamID) {
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
			return -1; //0 is a rouge value
	}
	public int locateRace(int raceID) throws IDNotRecognisedException {
		boolean raceFound = false;
		int searchCount = 0;
		Race currentRace;
		int tempID; 
		while (raceFound == false && searchCount < Races.size()-1) {
            currentRace = (Races.get(searchCount));
            tempID = currentRace.getRaceID();
            if (tempID == raceID) {
                raceFound = true;
            }
            else {
                searchCount += 1;
            }
        }
        if (raceFound == true) {
            return searchCount; //returns location of race
        }
        else
			return -1; //0 is a rouge value
	}
	//TODO finish up with exception catches after searchs and locations - these can all be removed!!
	public int[] findStage(int stageID) {
		int raceCount = 0;
		int stageCount = 0;
		boolean stageFound = false;
		int []output;
		output = new int[2];
		while (stageFound != true && raceCount < Races.size()-1){
			Race currentRace = Races.get(raceCount);
			int numberOfStages = currentRace.getStages().size();
			while (stageFound != true && stageCount < (numberOfStages-1)){
				Stage currentStage = Races.get(raceCount).getStages().get(stageCount);
				if (stageID == currentStage.getStageID()){
					stageFound = true;
				} 
				++stageCount; 
			}
			++raceCount;
		}
		if (stageFound == true) {
			output[0] = stageCount;
			output[1] = raceCount;
	
		}
		else{
			output[0] = -1;
		}
		return output;
	}
	public int locateRiderStageResult(int stageID, int riderID){
		boolean riderFound = false;
		boolean stageFound = true;
		int resultsCount = 0;
		while (riderFound != true & resultsCount < RiderStageResults.size()-1){
			if (stageID == RiderStageResults.get(resultsCount).getStageID()){
				stageFound = true;
				if (riderID == RiderStageResults.get(resultsCount).getRiderID()){
					riderFound = true;
					return resultsCount;
				}
				else
					++resultsCount;
			}
		}
		if ((stageFound == false) || (riderFound == false))
			return -1;
		else
			return resultsCount;
	}
	public ArrayList<RiderStageResult> retrieveResultsForStage(int stageID){
		ArrayList<RiderStageResult> outputArray = new ArrayList<RiderStageResult>();
		for (RiderStageResult x:RiderStageResults){
			if (x.getStageID() == stageID)
				outputArray.add(x);
		}
		return outputArray;
	}
	public RiderStageResult[] sortStageResultsByTime(ArrayList<RiderStageResult> unsortedArray){
		RiderStageResult[] outputArray;
		outputArray = new RiderStageResult[unsortedArray.size()];
		for (RiderStageResult x:unsortedArray){
			int count = 0;
			if (outputArray[count] == null){
				outputArray[count] = x;
			}
			else {
				LocalTime insertTime = x.getElapsedTime();
				int compareValue = insertTime.compareTo(outputArray[count].getElapsedTime());
				if (compareValue==0 || compareValue==-1){ //in the case that the current time is below/equal to the current value in the array
					++count;
				}
				else{ //in the case that the current time is above the current value in the array 
					int pushCount = unsortedArray.size();
					while (pushCount > count){
						if (outputArray[pushCount] != null){
							outputArray[pushCount + 1] = outputArray[pushCount];
							outputArray[pushCount] = null;
						}
						else
							--pushCount;
					}
					outputArray[count] = x;
				}
				}
			}
		return outputArray;
		}
	//end of our own methods

	@Override
	public int[] getRaceIds() {
		int[] outputArray;
		int count = 0;
		outputArray = new int[Races.size()];
		for (Race x:Races){
			outputArray[count] = x.getRaceID();
			++count;
		}
		System.out.println(outputArray); 
		return outputArray;
	}
	@Override
	public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
		Race newRace = new Race(name, description);
		Races.add(newRace);
		return 0;
	}
	@Override
	public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
		return Races.get(locateRace(raceId)).createDescription();
	}
	@Override
	public void removeRaceById(int raceId) throws IDNotRecognisedException {
		Races.remove(locateRace(raceId));
	}
	@Override
	public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
		int raceIndex = locateRace(raceId); 
		return Races.get(raceIndex).getNumberOfStages();
	}
	@Override
	public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
			StageType type)
			throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
			Stage newStage;
			newStage = new Stage(raceId, stageName, description, length, startTime, type);
			try {
				int targetIndex = locateRace(raceId);
				if (targetIndex == -1){
					throw new IDNotRecognisedException();
				}
				else {
			newStage = new Stage(raceId, stageName, description, length, startTime, type);
					Races.get(targetIndex).addStageToRace(newStage);
				}
			}
			catch(IDNotRecognisedException ex)	{
				System.out.println("RaceID not recognised!");
			}
		return 0;
	}
	@Override
	public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
		int raceIndex = locateRace(raceId);
		ArrayList<Stage> tempArrayList = new ArrayList<Stage>();
		int[] outputArray;
		tempArrayList = Races.get(raceIndex).getStages();
		outputArray = new int[tempArrayList.size()];
		int count = 0;
		for (Stage x:tempArrayList){
			outputArray[count] = x.getStageID();
			++count;
		}
		System.out.println(outputArray);
		return outputArray;
	}
	@Override
	public double getStageLength(int stageId) throws IDNotRecognisedException {
		int[] indexArray = findStage(stageId);
		return Races.get(indexArray[0]).getStages().get(indexArray[1]).getStageLength();
	}
	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException {
		int[] indexArray = findStage(stageId);
		Races.get(indexArray[0]).removeStageFromRace(indexArray[1]);
	}
	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type, Double averageGradient,
			Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {
		Segment newSegment;
		newSegment = new Segment(stageId, location, type, averageGradient, length);
		try {
			int []indexArray = findStage(stageId);
			if (indexArray[0] == -1){
				throw new IDNotRecognisedException();
			}
			else {
				if (Races.get(indexArray[0]).getStages().get(indexArray[1]).getConcluded() == false) {
					newSegment.setRaceID(Races.get(indexArray[0]).getRaceID());
					Races.get(indexArray[0]).getStages().get(indexArray[1]).addSegmentToStage(newSegment);	
				}
				else
					System.out.println("The concerned Stage has already been concluded and cannot be edited further!");
			}
		}
		catch (IDNotRecognisedException ex){
			System.out.println("Stage with entered stageID was not found!");
		}		
	 	return 0;
	}
	@Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
		Segment newSegment;
		newSegment = new Segment(stageId, location);
		try {
			int []indexArray = findStage(stageId);
			if (indexArray[0] == -1){
				throw new IDNotRecognisedException();
			}
			else {
				if (Races.get(indexArray[0]).getStages().get(indexArray[1]).getConcluded() == false) {
					newSegment.setRaceID(Races.get(indexArray[0]).getRaceID());
					Races.get(indexArray[0]).getStages().get(indexArray[1]).addSegmentToStage(newSegment);
				}
				else
					System.out.println("The concerned Stage has already been concluded and cannot be edited further!");
			}
		}
		catch (IDNotRecognisedException ex){
			System.out.println("Stage with entered stageID was not found!");
		}		
	 	return 0;
	}
	@Override
	public void removeSegment(int segmentId) throws IDNotRecognisedException, InvalidStageStateException {
		int raceCount = 0;
		int stageCount = 0;
		int segmentCount = 0;
		boolean segmentFound = false;
		while (segmentFound != true && raceCount < Races.size()-1){
			Race currentRace = Races.get(raceCount);
			int numberOfStages = currentRace.getStages().size();
			while (segmentFound != true && stageCount < (numberOfStages-1)){
				Stage currentStage = Races.get(raceCount).getStages().get(stageCount);
				int numberOfSegments = currentRace.getStages().get(stageCount).getSegments().size();
				while (segmentFound != true && segmentCount < (numberOfSegments-1)){
					Segment currentSegment = currentStage.getSegments().get(segmentCount);
					if (currentSegment.getSegmentID() == segmentId){
						segmentFound = true;
						if (Races.get(raceCount).getStages().get(stageCount).getConcluded() == false){
							Races.get(raceCount).getStages().get(stageCount).removeSegment(segmentCount);
							Races.get(raceCount).getStages().get(stageCount).decreaseLength(currentSegment.getSegmentLength());;
						}
						else
							System.out.println("The concerned Stage has already been concluded and cannot be edited further!");
					}
					++segmentCount;
				}
				++stageCount; 
			}
			++raceCount;
		}
		if (segmentFound == false) {
			throw new IDNotRecognisedException();
		}
	}
	@Override
	public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
		int[] indexArray = findStage(stageId);
		Races.get(indexArray[0]).getStages().get(indexArray[1]).setConcluded(true);
	}
	@Override
	public int[] getStageSegments(int stageId) throws IDNotRecognisedException {
		int[] indexArray = findStage(stageId);
		ArrayList<Segment> tempArrayList = new ArrayList<Segment>();
		tempArrayList = Races.get(indexArray[0]).getStages().get(indexArray[1]).getSegments();
		int[] outputArray;
		outputArray = new int[tempArrayList.size()];
		int count = 0;
		for (Segment x:tempArrayList){
			outputArray[count] = x.getSegmentID();
			++count;
		}
		System.out.println(outputArray);
		return outputArray;
	}
	@Override
	public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
		Team newTeam;
		newTeam = new Team(name, description);
		Teams.add(newTeam);
		return 0;
	}
	@Override
	public void removeTeam(int teamId) throws IDNotRecognisedException {
		try { 
			int targetIndex = locateTeam(teamId);
			if (targetIndex != 0) {
				Teams.remove(targetIndex);
            	System.out.println("Team removed successfully!");
			}
			else 
				throw new IDNotRecognisedException();
        }
        catch (IDNotRecognisedException ex ) {
			System.out.println("Team with entered teamID not found!");
		}
			
	}
	@Override
	public int[] getTeams() {
		int []outputArray = new int[Teams.size()];
		int indexCount = 0;
		for (Team x:Teams) {
			outputArray[indexCount] = x.getTeamID();
			++indexCount;
		}
		return outputArray;
	}
	@Override
	public int[] getTeamRiders(int teamId) throws IDNotRecognisedException { //this needs to be fixed!
		int targetIndex = locateTeam(teamId);
		try {
			if (targetIndex != 0) {
				for (Rider x:Teams.get(targetIndex).getRidersInTeam()){
					System.out.println(Integer.toString(x.getRiderID()) + x.getRiderName());
				}
			}
			else {
				throw new IDNotRecognisedException();
			}
		}
		catch (IDNotRecognisedException ex) {
			System.out.println("Team with entered teamID not found!");
		}	
		return null;
	}
	@Override
	public int createRider(int teamID, String name, int yearOfBirth)
			throws IDNotRecognisedException, IllegalArgumentException {
			Rider newRider;
            boolean addCheck = false;
            newRider = new Rider(teamID, name, yearOfBirth);
            for (Team x:Teams) {
                if (x.getTeamID() == teamID){
                    x.addRider(newRider);
                    addCheck = true;
                    break;
                }
				if (addCheck == false){
					System.out.println("TeamID not found!");
					//throw new IDNotRecognisedException();
				}
			}
			return 0;
        }
	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException {
		int teamCount = 0;
		int riderCount = 0;
		Rider currentRider;
		boolean riderFound = false;
		while (riderFound != true && teamCount < Teams.size() -1){
			Team currentTeam = Teams.get(teamCount);
			int numberOfRiders = currentTeam.getRidersInTeam().size();
			while (riderFound != true && riderCount < (numberOfRiders-1)){
				currentRider = currentTeam.getRidersInTeam().get(riderCount);
				if (riderId == currentRider.getRiderID()){
					Teams.get(teamCount).removeRider(riderCount);
					riderFound = true;
				} 
				++riderCount; 
			}
			++teamCount;
		}
		if (riderFound == false) {
			throw new IDNotRecognisedException();
		}
		//TODO Remove rider results!!!
	}
	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointsException,
			InvalidStageStateException {
		try {
			LocalTime[] temp = getRiderResultsInStage(stageId, riderId);
			if (temp != null)
				throw new DuplicatedResultException();
		}
		catch (IDNotRecognisedException ex) {
			RiderStageResult newStageResult; 
			newStageResult = new RiderStageResult(stageId, riderId, checkpoints);
			RiderStageResults.add(newStageResult);
		}
		catch (DuplicatedResultException ex){
			System.out.println("Stage result for rider already exists!");
		}
	}
	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		try {
			int targetIndex = locateRiderStageResult(stageId, riderId);
			if (targetIndex == -1){
				throw new IDNotRecognisedException();
			}
			else
				return RiderStageResults.get(targetIndex).getCheckpoints();
		}
		catch (IDNotRecognisedException ex){
			System.out.println("Rider results in concerned Stage not found!");
			return null;
		}
	}
	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		
		try{
			LocalTime[] timeArray = getRiderResultsInStage(stageId, riderId);
			if (timeArray == null) 
				throw new IDNotRecognisedException();
			else {
				int finalIndex = timeArray.length - 1;
				return timeArray[finalIndex];
			}
		}
		catch(IDNotRecognisedException ex){
			System.out.println("Rider result for the concerned stage were not found!");
			return null;
		}
		//TODO consider for time trails!
		//subtracting one time from another time can return a duration (can be converted back to LocalTime)
	}
	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		try{
			int targetIndex = locateRiderStageResult(stageId, riderId);
			if (targetIndex == -1){
				throw new IDNotRecognisedException();
			}
			else
				RiderStageResults.remove(targetIndex);
		}
		catch (IDNotRecognisedException ex){
			System.out.println("No result found for the entered riderID and stageID");
		}
		
	}
	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
		ArrayList<RiderStageResult> relevantStageResults = retrieveResultsForStage(stageId);
		RiderStageResult[] sortedResultArray = sortStageResultsByTime(relevantStageResults);
		int [] outputRanks;
		outputRanks = new int[relevantStageResults.size()];
		int count = 0; 
		for (RiderStageResult x:sortedResultArray){
			outputRanks[count] = x.getRiderID();
			++count;
		}
		return outputRanks;
	}
	@Override
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
		ArrayList<RiderStageResult> relevantStageResults = retrieveResultsForStage(stageId);
		RiderStageResult[] sortedResultArray = sortStageResultsByTime(relevantStageResults);
		LocalTime [] outputRanks;
		outputRanks = new LocalTime[relevantStageResults.size()];
		int count = 0; 
		for (RiderStageResult x:sortedResultArray){
			outputRanks[count] = x.getElapsedTime();
			++count;
		}
		return outputRanks;
	}
	@Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void eraseCyclingPortal() {
		Teams.clear();
		Races.clear();
		RiderStageResults.clear();
		System.out.println("Portal erased successfully!");
	}
	@Override
	public void saveCyclingPortal(String filename) throws IOException {
		try {
			ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream("SavedPortal.txt"));
			save.writeObject(Teams);
			save.writeObject(Races);
			save.writeObject(RiderStageResults);
			System.out.println("saved succesfully!");
			save.close();
		}
		catch (IOException ex){
			System.out.println("Data was not saved - an error occurred.");
		}
	}
	@Override
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		try{
			ObjectInputStream load = new ObjectInputStream(new FileInputStream("SavedPortal.txt"));
			eraseCyclingPortal();
			Object objectData = load.readObject();
			if (objectData instanceof ArrayList){
				Teams = (ArrayList<Team>) objectData;
				}
			objectData = load.readObject();
			if (objectData instanceof ArrayList){
				Races = (ArrayList<Race>) objectData;
			 	}
			// objectData = load.readObject();
			// if (objectData instanceof ArrayList){
			// 	RaceResults = (ArrayList<RaceResult>) objectData;
			// 	}		commented instead of deleted because i feel like this might break now it's commented and i want it here in case we have to leave it in to fix things
			objectData = load.readObject();
			if (objectData instanceof ArrayList){
				RiderStageResults = (ArrayList<RiderStageResult>) objectData;
				}
			// objectData = load.readObject();
			// if (objectData instanceof ArrayList){
			// 	SegmentResults = (ArrayList<SegmentResult>) objectData;
			// 	}		commented instead of deleted because i feel like this might break now it's commented and i want it here in case we have to leave it in to fix things
			load.close();
		}
		catch (IOException ex){
			System.out.println("Load failed - an error occurred.");
		}
	}
	@Override
	public void removeRaceByName(String name) throws NameNotRecognisedException {
		boolean raceFound = false;
		int searchCount = 0;
		Race currentRace;
		while (raceFound == false && searchCount < Races.size()) {
            currentRace = (Races.get(searchCount));
            String tempName = currentRace.getRaceName();
            if (tempName == name) {
                raceFound = true;
            }
            else {
                searchCount += 1;
            }
        }
		try {
			if (raceFound == true) {
				Races.remove(searchCount);
			}
			else
				throw new IDNotRecognisedException();
		}
		catch (IDNotRecognisedException ex){
			System.out.println("Input racename was not recognised! No race exists with this name.");
		}
	}


	@Override
	public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

}

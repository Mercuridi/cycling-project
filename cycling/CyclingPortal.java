package cycling;

import java.beans.IndexedPropertyChangeEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.lang.model.util.ElementScanner14;

import java.time.Duration;

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
	public int locateTeam(int teamID) throws IDNotRecognisedException { //complete!
		int searchCount = 0;
		Team currentTeam;
		int tempID; 
		while (searchCount <= Teams.size()-1) {
            currentTeam = (Teams.get(searchCount));
            tempID = currentTeam.getTeamID();
            if (tempID == teamID) {
                return searchCount;
            }
            else {
                searchCount += 1;
            }
        }
		return -1; //0 is a rouge value
	}
	public int locateRace(int raceID) throws IDNotRecognisedException { //complete!
		int searchCount = 0;
		Race currentRace;
		int tempID; 
		while (searchCount <= Races.size()-1) {
            currentRace = (Races.get(searchCount));
            tempID = currentRace.getRaceID();
            if (tempID == raceID) {
				return searchCount;
            }
            else {
                searchCount += 1;
            }
        }
		return -1; //-1 is a rouge value
	}
	//TODO finish up with exception catches after searchs and locations - these can all be removed!!
	public int[] findStage(int stageID) { //complete!
		int raceCount = 0;
		int[] output;
		output = new int[2];
		while (raceCount <= Races.size()-1){
			int stageCount = 0;
			Race currentRace = Races.get(raceCount);
			int numberOfStages = currentRace.getStages().size();
			while (stageCount <= numberOfStages-1){
				Stage currentStage = Races.get(raceCount).getStages().get(stageCount);
				if (stageID == currentStage.getStageID()){
					output[0] = raceCount;
					output[1] = stageCount;
					return output;
				} 
				++stageCount; 
			}
			++raceCount;
		}
		output[0] = -1;
		output[1] = -1;
		return output;
	}
	public int locateRiderStageResult(int stageID, int riderID){
		int resultsCount = 0;
		while (resultsCount <= RiderStageResults.size()-1){
			if (stageID == RiderStageResults.get(resultsCount).getStageID()){
				if (riderID == RiderStageResults.get(resultsCount).getRiderID()){
					return resultsCount;
				}
				else
					++resultsCount;
			}
		}
		return -1;
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
	public int[] getRaceIds() { //complete!
		int count = 0;
		int[] outputArray;
		if (Races.size() != 0){
			outputArray = new int[Races.size()];
			for (Race x:Races){
				outputArray[count] = x.getRaceID();
				++count;
			}
			return outputArray;
		}
		else {
			outputArray = new int[0];
			return outputArray;
		}
	}
	@Override
	public int createRace(String name, String description) throws IllegalNameException, InvalidNameException { //complete!
		if ((name.length() > 30) || (name.length() == 0) || (name == null) || (name.contains(" ") == true)){
			throw new InvalidNameException();
		}
		for (Race x:Races){
			if (x.getRaceName().equals(name)){
				throw new IllegalNameException();
			}
		}
		Race newRace = new Race(name, description);
		Races.add(newRace);
		return newRace.getRaceID();
	}
	@Override
	public String viewRaceDetails(int raceId) throws IDNotRecognisedException { //complete!
		int targetIndex = locateRace(raceId);
		if (targetIndex != -1){
			return Races.get(targetIndex).createDescription();
		}
		else
			throw new IDNotRecognisedException();
		
	}
	@Override
	public void removeRaceById(int raceId) throws IDNotRecognisedException { //complete!
		int targetIndex = locateRace(raceId);
		if (targetIndex != -1){
			Races.remove(targetIndex);
		}
		else
			throw new IDNotRecognisedException();
	}
	@Override
	public int getNumberOfStages(int raceId) throws IDNotRecognisedException { //complete!
		int raceIndex = locateRace(raceId); 
		if (raceIndex != -1){
			return Races.get(raceIndex).getNumberOfStages();
		}
		else{
			throw new IDNotRecognisedException();
		}
	}
	@Override
	public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime, //complete!
			StageType type)
			throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
		Stage newStage;
		if ((stageName.length() > 30) || (stageName.length() == 0) || (stageName == null)){
			throw new InvalidNameException();
		}
		if (length < 5)
			throw new InvalidLengthException();
		int targetIndex = locateRace(raceId);
		if (targetIndex == -1){
			throw new IDNotRecognisedException();
		}
		else {
			ArrayList<Stage> ExistingStages = Races.get(targetIndex).getStages(); 
			for (Stage x:ExistingStages){
				if (x.getStageName().equals(stageName)){
					throw new IllegalNameException();
				}
			}
			newStage = new Stage(raceId, stageName, description, length, startTime, type);
			Races.get(targetIndex).addStageToRace(newStage);
			return newStage.getStageID();
		}
		
	}
	@Override
	public int[] getRaceStages(int raceId) throws IDNotRecognisedException { //complete!
		int raceIndex = locateRace(raceId);
		if (raceIndex != -1){
			ArrayList<Stage> tempArrayList = new ArrayList<Stage>();
			int[] outputArray;
			tempArrayList = Races.get(raceIndex).getStages();
			outputArray = new int[tempArrayList.size()];
			int count = 0;
			for (Stage x:tempArrayList){
				outputArray[count] = x.getStageID();
				++count;
			}
			return outputArray;
		}
		else
			throw new IDNotRecognisedException();
	}
	@Override
	public double getStageLength(int stageId) throws IDNotRecognisedException { //complete!
		int[] indexArray = findStage(stageId);
		if (indexArray[0] != -1){
			return Races.get(indexArray[0]).getStages().get(indexArray[1]).getStageLength();
		}
		else
			throw new IDNotRecognisedException();
	}
	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException { //complete!
		int[] indexArray = findStage(stageId);
		if (indexArray[0] != -1){
			Races.get(indexArray[0]).removeStageFromRace(indexArray[1]);
		}
		else
			throw new IDNotRecognisedException();
	}
	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type, Double averageGradient, //complete!
			Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {
		Segment newSegment;
		newSegment = new Segment(stageId, location, type, averageGradient, length);
		int []indexArray = findStage(stageId);
		if (indexArray[0] == -1){
			throw new IDNotRecognisedException();
		}
		else {
			Stage concernedStage = Races.get(indexArray[0]).getStages().get(indexArray[1]);
			if (concernedStage.getConcluded() == true) {
				throw new InvalidStageStateException();
			}
			else if ((concernedStage.getStageLength()) < location){
				throw new InvalidLocationException();
			}
			else if (concernedStage.getStageType() == StageType.TT){
				throw new InvalidStageTypeException();
			}
			else{
				newSegment.setRaceID(Races.get(indexArray[0]).getRaceID());
				Races.get(indexArray[0]).getStages().get(indexArray[1]).addSegmentToStage(newSegment);
				return newSegment.getSegmentID();
			}
		}
	}
	@Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
		Segment newSegment;
		newSegment = new Segment(stageId, location);
		int []indexArray = findStage(stageId);
		if (indexArray[0] == -1){
			throw new IDNotRecognisedException();
		}
		else {
			Stage concernedStage = Races.get(indexArray[0]).getStages().get(indexArray[1]);
			if (concernedStage.getConcluded() == true) {
				throw new InvalidStageStateException();
			}
			else if ((concernedStage.getStageLength()) < location){
				throw new InvalidLocationException();
			}
			else if (concernedStage.getStageType() == StageType.TT){
				throw new InvalidStageTypeException();
			}
			else{
				newSegment.setRaceID(Races.get(indexArray[0]).getRaceID());
				Races.get(indexArray[0]).getStages().get(indexArray[1]).addSegmentToStage(newSegment);
				return newSegment.getSegmentID();
			}
		}		
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
	public int[] getStageSegments(int stageId) throws IDNotRecognisedException { //complete!
		int[] indexArray = findStage(stageId);
		if (indexArray[0] != -1) {
			ArrayList<Segment> tempArrayList = new ArrayList<Segment>();
			tempArrayList = Races.get(indexArray[0]).getStages().get(indexArray[1]).getSegments();
			int[] outputArray;
			outputArray = new int[tempArrayList.size()];
			int count = 0;
			for (Segment x:tempArrayList){
				outputArray[count] = x.getSegmentID();
				++count;
			}
			return outputArray;
		}
		else
			throw new IDNotRecognisedException();
		
	}
	@Override
	public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException { //complete!
		Team newTeam;
		if ((name.length() > 30) || (name.length() == 0) || (name == null)){
			throw new InvalidNameException();
		}
		for (Team x:Teams){
			if (x.getTeamName().equals(name)){
				throw new IllegalNameException();
			}
		}
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
	public int[] getTeamRiders(int teamId) throws IDNotRecognisedException { //complete!
		int targetIndex = locateTeam(teamId);
		int[] outputArray; 
		if (targetIndex != -1) {
			outputArray = new int[Teams.get(targetIndex).getRidersInTeam().size()];
			int count = 0;
			for (Rider x: Teams.get(targetIndex).getRidersInTeam()){
				outputArray[count] = x.getRiderID();
				++count;
			} 
		}
		else {
				throw new IDNotRecognisedException();
			}
		return outputArray;
	}
	@Override
	public int createRider(int teamID, String name, int yearOfBirth) //complete!
			throws IDNotRecognisedException, IllegalArgumentException {
			if ((yearOfBirth >= 1900) && (name.length() != 0) && (name != null)){
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
					throw new IDNotRecognisedException();
				}
			}
			return 0;
			}
			else{
				throw new IllegalArgumentException();
			}
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
		int targetIndex = locateRiderStageResult(stageId, riderId);
		if (targetIndex == -1){
			throw new IDNotRecognisedException();
		}
		else 
			return RiderStageResults.get(targetIndex).getCheckpoints();
		}
	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		int targetRiderIndex = locateRiderStageResult(stageId, riderId);
		LocalTime adjustTime = RiderStageResults.get(targetRiderIndex).getElapsedTime();
		ArrayList<RiderStageResult> relevantResults = retrieveResultsForStage(stageId);
		for (RiderStageResult x:relevantResults){
			if (x.getRiderID() == riderId){
				break;
			}
			else{
				LocalTime comparisonTime = x.getElapsedTime();
				Duration timeDifference = Duration.between(adjustTime, comparisonTime);
				if (timeDifference.getSeconds() < 1);
					if (comparisonTime.isBefore(adjustTime) == true){
						adjustTime = comparisonTime;
					}
			}
		}
		return adjustTime;
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
		int[] riderRankArray = getRidersRankInStage(stageId);
		int stageLocation[] = findStage(stageId);
		StageType stageType = Races.get(stageLocation[0]).getStages().get(stageLocation[1]).getStageType();
		int[] pointArray;
		int[] outputArray;
		switch (stageType){
			case TT:
				pointArray = new int[]{20,17,15,13,11,10,9,8,7,6,5,4,3,2,1};
				break;
			case FLAT:
				pointArray = new int[]{50,30,20,18,16,14,12,10,8,7,6,5,4,3,2};
				break;
			case MEDIUM_MOUNTAIN:
				pointArray = new int[]{30,25,22,19,17,15,13,11,9,7,6,5,4,3,2};
				break;
			case HIGH_MOUNTAIN:
				pointArray = new int[]{20,17,15,13,11,10,9,8,7,6,5,4,3,2,1};
				break;
			default:
				pointArray = new int[1];
				//this should never be called!
		}
		int ridersToAllocate = riderRankArray.length;
		if (ridersToAllocate > 15){
			outputArray = new int[ridersToAllocate];
			int count = 15;
			while (count <= ridersToAllocate - 1){ //TODO really test this!!
				outputArray[count] = 0;
				++count;
			} 
			return outputArray;
		}
		else if (ridersToAllocate == 15){
			return pointArray;
		}
		else{
			outputArray = new int[ridersToAllocate];
			int count = 0;
			while (count < ridersToAllocate-1){
				outputArray[count] = pointArray[count];
				++count;
			}
		}
		return outputArray;
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
	@SuppressWarnings("unchecked")
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		ObjectInputStream load = new ObjectInputStream(new FileInputStream("SavedPortal.txt"));
		eraseCyclingPortal();
		Object objectData = load.readObject();
		if (objectData instanceof ArrayList<?>){
			Teams = (ArrayList<Team>) objectData;
			}
		else{
			load.close();
			throw new IOException();
		
		}
		objectData = load.readObject();
		if (objectData instanceof ArrayList){
			Races = (ArrayList<Race>) objectData;
		}
		else{
			load.close();
			throw new IOException();
		}
		// objectData = load.readObject();
		// if (objectData instanceof ArrayList){
		// 	RaceResults = (ArrayList<RaceResult>) objectData;
		// 	}		commented instead of deleted because i feel like this might break now it's commented and i want it here in case we have to leave it in to fix things
		objectData = load.readObject();
		if (objectData instanceof ArrayList){
			RiderStageResults = (ArrayList<RiderStageResult>) objectData;
		}
		else{
			load.close();
			throw new IOException();
		}
		load.close();
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

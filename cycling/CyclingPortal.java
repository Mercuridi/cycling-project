package cycling;

// all imports for the package
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import java.time.Duration;

/**
 * 
 * @author Laurie Harbord and Kai Barber
 * @version 1.0
 *
 */

//TODO add some assertions grrr

 // class implements all relevant methods
public class CyclingPortal implements CyclingPortalInterface {
	// setup for all the main objects we use:
	// Teams contains all Riders
	// Races contains all Races, and by extension all Stages, Segments, and point allocations
	// RiderStageResults is used in conjunction with both to allocate correct points to each 
	// rider in correspondence with their placement in each race segment/stage
	public ArrayList<Team> Teams = new ArrayList<Team>();
	public ArrayList<Race> Races = new ArrayList<Race>();
	public ArrayList<RiderStageResult> RiderStageResults = new ArrayList<RiderStageResult>();
	/**
	 * 
	 * @param teamID the ID of the team to be located
	 * @return returns the index of the requested TeamID's team in the array of teams "Teams". If a team with the
	 * entered ID does not exist, the rogue value -1 is returned
	 */
	public int locateTeam(int teamID) { //complete!
		int searchCount = 0;
		Team currentTeam;
		int tempID; 
		while (searchCount <= Teams.size()-1) { //uses searchCount to index through Teams
			//and comparing retrieved teamID with input TeamID at every iteration
            currentTeam = (Teams.get(searchCount));
            tempID = currentTeam.getTeamID();
            if (tempID == teamID) { //if the two IDs are the same, the Team has been found
                return searchCount; 
            }
            else {
                searchCount += 1; //iterates searchCount
            }
        }
		return -1; //-1 is a rouge value
	}
	/**
	 * 
	 * @param raceID the ID of the race to be located
	 * @return returns the index of the requested RaceID's team in the array of races "Races". If a race with the
	 * entered ID does not exist, the rogue value -1 is returned
	 */
	public int locateRace(int raceID) { //complete!
		int searchCount = 0;
		Race currentRace;
		int tempID; 
		while (searchCount <= Races.size()-1) { //uses searchCount to index through Races
			//and comparing retrieved raceID with input raceID at every iteration
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
	/**
	 * 
	 * @param riderID the ID of the rider to be located
	 * @return returns a one-dimensional array of 2 indexes which contains the index addresses
	 * of the requested RiderID's rider in the array of Teams. The first part of the array equates to the index of the team
	 * the rider is in in the array of teams "Teams" while the second part of the array equates to the index
	 * of the rider within said Team. If a rider with the entered ID does not exist, 
	 * the both indexes of the output array are set to -1.
	 */
	public int[] locateRider(int riderID){
		int outputArray[];
		outputArray = new int[2];
		int teamCount = 0;
		Rider currentRider;
		while (teamCount <= Teams.size()-1){ //uses teamCount to index through Teams
			int riderCount = 0;
			Team currentTeam = Teams.get(teamCount);
			int numberOfRiders = currentTeam.getRidersInTeam().size();
			while (riderCount <= (numberOfRiders-1)){ //uses riderCount to index through all the riders of the current Team
				currentRider = currentTeam.getRidersInTeam().get(riderCount);
				if (riderID == currentRider.getRiderID()){ //compares current search item riderID with input riderID
					outputArray[0] = teamCount; 
					outputArray[1] = riderCount;
					return outputArray;
				} 
				++riderCount; 
			}
			++teamCount;
		}
		outputArray[0] = -1; //in case of the entered RiderID not being found. 
		outputArray[1] = -1; //fills output index with null values
		return outputArray;
	}
	/**
	 * 
	 * @param stageID the ID of the stage to be located
	 * @return returns a one-dimensional array of 2 indexes which contains the index addresses
	 * of the requested StageID's Stage in the array of Races. The first part of the array equates to the index of the race
	 * the rider is in in the array of races "Races" while the second part of the array equates to the index
	 * of the Stage within said Race. If a stage with the entered ID does not exist, 
	 * the both indexes of the output array are set to -1.
	 */
	public int[] findStage(int stageID) { //complete!
		int raceCount = 0;
		int[] output;
		output = new int[2];
		while (raceCount <= Races.size()-1){ //uses raceCount to index through Races
			int stageCount = 0;
			Race currentRace = Races.get(raceCount);
			int numberOfStages = currentRace.getStages().size();
			while (stageCount <= numberOfStages-1){ //uses stageCount to index through all the stages of the current Race
				Stage currentStage = Races.get(raceCount).getStages().get(stageCount);
				if (stageID == currentStage.getStageID()){ //compares current search item stageID with input stageID
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
	/**
	 * 
	 * @param stageID the stageID that the result to be found must relate to
	 * @param riderID the riderID that the result to be found must relate to
	 * @return returns the index of the requested RiderStageResult's team in the array of rider stage results "RiderStageResults". If a result with the
	 * entered ID does not exist, the rogue value -1 is returned
	 */
	public int locateRiderStageResult(int stageID, int riderID){ //complete!
		int resultsCount = 0;
		while (resultsCount <= RiderStageResults.size()-1){ //simple search algorithm using resultsCount to index
			if (stageID == RiderStageResults.get(resultsCount).getStageID()){ //if stageID of current search item equals the 
				//input stageID, the algorithm moves onto the next test to confirm if the result has been truly found 
				if (riderID == RiderStageResults.get(resultsCount).getRiderID()){ //compares riderID's
					return resultsCount;
				}
			}
			++resultsCount;
		}
		return -1; //returns the rogue value the case that the target result is not found
	}
	/**
	 * 
	 * @param stageID the stageID that the retrieved results must relate to
	 * @return an array list of RiderStageResult objects that each bear a stageID
	 * identical to the input stageID 
	 */
	public ArrayList<RiderStageResult> retrieveResultsForStage(int stageID){
		ArrayList<RiderStageResult> outputArray = new ArrayList<RiderStageResult>();
		for (RiderStageResult x:RiderStageResults){ //iterates through RiderStageResults
			if (x.getStageID() == stageID) //where the current RiderStageResult stageID is equal to inptu stageID
				outputArray.add(x); //add current RiderStageResult to outputArray
		}
		return outputArray;
	}
	/**
	 * 
	 * @param unsortedArray an arraylist of results to be sorted
	 * @param checkpointIndex an integer that determines by which time the results will be sorted.
	 * if this is set to -1, the results are sorted by their elapsed time. If not, they are sorted by
	 * the checkpoint at the index this checkpointIndex points to
	 * @return a sorted RiderStageResultArray, null if any of the entered data is invalid
	 */
	public RiderStageResult[] sortStageResultsByTime(ArrayList<RiderStageResult> unsortedArray, int checkpointIndex){
		RiderStageResult[] outputArray;
		outputArray = new RiderStageResult[unsortedArray.size()];
		for (RiderStageResult x:unsortedArray){ //iterates through every element of the input array (all elements to be inserted)
			boolean inserted = false;
			int internalCount = 0;
			while (inserted == false){ //iterates until an element has found it's place in the outpt array
				if (outputArray[internalCount] == null){ //if current index in the list is empty, element can be inserted
					outputArray[internalCount] = x;
					inserted = true;
				}
				else {
					LocalTime insertTime;
					if (checkpointIndex == -1){ //uses the input checkpointIndex int to determine whether the results will be sorted 
						//by their final result, or by another one.
						try{
							insertTime = getRiderAdjustedElapsedTimeInStage(x.getStageID(), x.getRiderID()); //retrieves adjusted 
							//elapsed time
						}
						catch (IDNotRecognisedException ex){
							return null;
						}
					}
					else{
						insertTime = x.getCheckpoints()[checkpointIndex];
					}
					int compareValue = 2; //can't be processed, will be set in future functions
					try{
						if (checkpointIndex == -1) //-1 equates to "use adjusted elapsed time" as opposed to a specific checkpoint time
							compareValue = insertTime.compareTo(getRiderAdjustedElapsedTimeInStage(outputArray[internalCount].getStageID(), outputArray[internalCount].getRiderID()));
						else
							compareValue = insertTime.compareTo(outputArray[internalCount].getCheckpoints()[checkpointIndex]);
						if (compareValue==-1){ //in the case that the current time is below the current value in the array
							int pushCount = unsortedArray.size() - 1;
							while (pushCount >= internalCount){ //this loops pushes back all other elements in the list to "insert"
							//the new value
								if ((outputArray[pushCount] != null) & (pushCount != unsortedArray.size() - 1)){
									outputArray[pushCount + 1] = outputArray[pushCount];
									outputArray[pushCount] = null;
								}
								--pushCount; //decrements push count
								}
							outputArray[internalCount] = x; //inserts element into its correct position
							inserted = true;
							}
					}
					catch(IDNotRecognisedException ex){
						return null;
					}
					}
				++internalCount; //increments internal counter (which finds an elements place in the list)
			}
		}
		return outputArray;
		}
	/**
	 * 
	 * @param stageID the stageID for the stage that sprint points are being calculated for 
	 * @return nothing - function returns void. However, the array list RiderStageResults is edited with
	 * potentially several results having their attribute SprintPoints affected
	 */
	public void gatherSprintPoints(int stageID){
		ArrayList<RiderStageResult> relevantResults = retrieveResultsForStage(stageID);
		int[] indexArray = findStage(stageID);
		if (Races.get(indexArray[0]).getStages().get(indexArray[1]).getSegments().size() == 0){
			//no segments present = no extra sprint points
		}
		else {
			int segmentIndex = 0; //segmentIndex is the value fed into sortStageResultsByTime
			//It points to a specific LocalTime in checkpoints that contains a rider's time within a segment 
			int segmentCount = 0; //the count for iterating through each segment retrieved
			for (Segment x:Races.get(indexArray[0]).getStages().get(indexArray[1]).getSegments()){
				segmentIndex = 2*segmentCount + 2; //the finish time of a checkpoint is always 2n+2 where n is segmentCount
				if (x.getSegmentType() == SegmentType.SPRINT){ //if the current segment is an Intermediate Sprint segment
					RiderStageResult[] sortedResults = sortStageResultsByTime(relevantResults, segmentIndex); //sorts results by segment finish time
					int[] pointsArray;
					pointsArray = new int[]{20,17,15,13,11,10,9,8,7,6,5,4,3,2,1};
					int count = 0;
					while ((count <= sortedResults.length - 1) & (count != 15)){
						sortedResults[count].addToSprintPoints(pointsArray[count]); //adds points to RiderStageResults items relative
						//to their position
						++count;
					}
					int replaceCount = 0; //establishes a new count for replacement iteration
					for (RiderStageResult z:sortedResults){ //replacement iteration that uses ResultID's to replace old
						//elements of RiderStageResults with new results (with updated SprintPoints)
						if (RiderStageResults.get(replaceCount).getResultID() == z.getResultID()){
							RiderStageResults.remove(replaceCount);
							RiderStageResults.add(z);
						}
						++replaceCount;
					}
				}
				++segmentCount; //increments
			}
		}
	}
	/**
	 * 
	 * @param stageID the stageID for the stage that mountain points are being calculated for 
	 * @return nothing - function returns void. However, the array list RiderStageResults is edited with
	 * potentially several results having their attribute MountainPoints affected
	 */
	public void gatherMountainPoints(int stageID){ 
		ArrayList<RiderStageResult> relevantResults = retrieveResultsForStage(stageID);
		int[] indexArray = findStage(stageID);
		if (Races.get(indexArray[0]).getStages().get(indexArray[1]).getSegments().size() == 0){
			//no segments present = no extra mountain points
		}
		else{ //exact same logic as the previous function
			int segmentIndex = 0;
			int segmentCount = 0;
			for (Segment x:Races.get(indexArray[0]).getStages().get(indexArray[1]).getSegments()){
				segmentIndex = 2*segmentCount + 2;
				if (x.getSegmentType() != SegmentType.SPRINT){
					RiderStageResult[] sortedResults = sortStageResultsByTime(relevantResults, segmentIndex);
					int[] pointsArray;
					switch (x.getSegmentType()){ //use of a case select determind by each Stage Type's individual 
					//points per rank
						case C1:
							pointsArray = new int[]{1};
							break;
						case C2:
							pointsArray = new int[]{2, 1};
							break;
						case C3:
							pointsArray = new int[]{5, 3, 2, 1};
							break;
						case C4:
							pointsArray = new int[]{10, 8, 6, 4, 2, 1};
							break;
						case HC:
							pointsArray = new int[]{20, 15, 12, 10, 8, 6, 4, 2};
						break;
						default:
							pointsArray = new int[1];
							//this should never be called!
					}
					int count = 0;
					while ((count <= sortedResults.length - 1) & (count != 15)){
						sortedResults[count].addToMountainPoints(pointsArray[count]);
						++count;
					}
					int replaceCount = 0;
					for (RiderStageResult z:sortedResults){
						if (RiderStageResults.get(replaceCount).getResultID() == z.getResultID()){
							RiderStageResults.remove(replaceCount);
							RiderStageResults.add(z);
						}
						++replaceCount;
					}
				}
				++segmentCount;
			}
		}
	}
	@Override
	public int[] getRaceIds() { //complete!
		int count = 0;
		int[] outputArray;
		System.out.println(Races.size() + " races to check (at getRaceIDs).");
		if (Races.size() != 0){ //checks that any races exist
			outputArray = new int[Races.size()]; //instantiates an array to store all the RaceIDs
			for (Race x:Races){ //iterates through Races, using count to write to outputArray
				outputArray[count] = x.getRaceID();
				++count;
			}
			return outputArray;
		}
		else { //in case of no races being present, an empty int array is returned
			outputArray = new int[0];
			return outputArray;
		}
	}
	@Override
	public int createRace(String name, String description) throws IllegalNameException, InvalidNameException { //complete!
		if ((name.length() > 30) || (name.length() == 0) || (name == null) || (name.contains(" ") == true)){
			//above check validates the data input into the function
			throw new InvalidNameException(); 
		}
		for (Race x:Races){
			if (x.getRaceName().equals(name)){ //iterates through Races and compares each existing name to the
				//proposed name for the new race. If there is a match, a race with the input name already exists
				throw new IllegalNameException();
			}
		}
		Race newRace = new Race(name, description); //with checks out the way, Race is constructed
		Races.add(newRace); //added to Races
		return newRace.getRaceID(); //RaceID returned
	}
	@Override
	public String viewRaceDetails(int raceId) throws IDNotRecognisedException { //complete!
		int targetIndex = locateRace(raceId); //locates race index in Races
		if (targetIndex != -1){ //if returned value isn't rogue...
			return Races.get(targetIndex).createDescription(); //return Description (method in Race class)
		}
		else
			throw new IDNotRecognisedException(); //in case of rogue value, throw exception
		
	}
	@Override
	public void removeRaceById(int raceId) throws IDNotRecognisedException { //complete!
		int targetIndex = locateRace(raceId);
		if (targetIndex != -1){ //if returned index isn't rogue...
			Races.remove(targetIndex); //race of entered raceID is removed
		}
		else
			throw new IDNotRecognisedException(); //exception thrown in the case of rogue
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
			//the above tests will assure teh validity of entered data, throwing an exception otherwise
			throw new InvalidNameException();
		}
		if (length < 5) //a stage can't be less than 5km
			throw new InvalidLengthException();
		int targetIndex = locateRace(raceId);
		if (targetIndex == -1){
			throw new IDNotRecognisedException();
		}
		else {
			ArrayList<Stage> ExistingStages = Races.get(targetIndex).getStages(); 
			for (Stage x:ExistingStages){ //iterates through all Stages within the race
				if (x.getStageName().equals(stageName)){ //will detect a Stage with the same name
					throw new IllegalNameException(); //and throw an exception
				}
			}
			newStage = new Stage(raceId, stageName, description, length, startTime, type); //if all checks are passed,
			//new Stage is constructed
			Races.get(targetIndex).addStageToRace(newStage); //and added to the correct Race in Races
			return newStage.getStageID(); //stageID returned
			}
	}
	@Override
	public int[] getRaceStages(int raceId) throws IDNotRecognisedException { //complete!
		int raceIndex = locateRace(raceId);
		if (raceIndex != -1){
			ArrayList<Stage> tempArrayList = new ArrayList<Stage>(); //creates a Stage array list to read from
			int[] outputArray; //creates an int array for output
			tempArrayList = Races.get(raceIndex).getStages();
			outputArray = new int[tempArrayList.size()]; //instantiates outputArray
			int count = 0;
			for (Stage x:tempArrayList){ //iterates through the array list, adding stage IDs to the output list
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
		if (indexArray[0] != -1){ //unless the returned index contains rogue values...
			return Races.get(indexArray[0]).getStages().get(indexArray[1]).getStageLength(); //uses the indexArray to point to StageLength
		}
		else
			throw new IDNotRecognisedException(); //throw exception if Stage isn't found
	}
	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException { //complete!
		int[] indexArray = findStage(stageId);
		if (indexArray[0] != -1){
			Races.get(indexArray[0]).removeStageFromRace(indexArray[1]); //uses indexArray to remove correct Stage from correct Race
			int removeCount = 0;
			for (RiderStageResult x:RiderStageResults){ //iterates through all the existing stage results
				if (x.getStageID() == stageId){ //locates any with the input stageID
					RiderStageResults.remove(removeCount); //and deletes them
				}
				++removeCount;
			}
		}
		else
			throw new IDNotRecognisedException(); //throws exception in case stage isn't found
	}
	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type, Double averageGradient, //complete!
			Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {
		Segment newSegment;
		newSegment = new Segment(stageId, location, type, averageGradient, length);
		int []indexArray = findStage(stageId);
		if (indexArray[0] == -1){ //in case stage of input stageID is not found
			throw new IDNotRecognisedException();
		}
		else {
			Stage concernedStage = Races.get(indexArray[0]).getStages().get(indexArray[1]); //stage is retrieved
			if (concernedStage.getConcluded() == true) { //state of stage is retrieved - if true, stage has been concluded
				throw new InvalidStageStateException();
			}
			else if ((concernedStage.getStageLength()) < location){ //if segment finishes after stage length, throw exception
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
	public int addIntermediateSprintToStage(int stageId, double location) //complete!
		throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
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
	public void removeSegment(int segmentId) throws IDNotRecognisedException, InvalidStageStateException { //complete!
		int raceCount = 0;
		boolean segmentFound = false;
		while (segmentFound != true && raceCount <= Races.size()-1){
			int stageCount = 0;
			Race currentRace = Races.get(raceCount);
			int numberOfStages = currentRace.getStages().size();
			while (segmentFound != true && stageCount <= (numberOfStages-1)){
				int segmentCount = 0;
				Stage currentStage = Races.get(raceCount).getStages().get(stageCount);
				int numberOfSegments = currentRace.getStages().get(stageCount).getSegments().size();
				while (segmentFound != true && segmentCount <= (numberOfSegments-1)){
					Segment currentSegment = currentStage.getSegments().get(segmentCount);
					if (currentSegment.getSegmentID() == segmentId){
						segmentFound = true;
						if (Races.get(raceCount).getStages().get(stageCount).getConcluded() == false){
							Races.get(raceCount).getStages().get(stageCount).removeSegment(segmentCount);
							Races.get(raceCount).getStages().get(stageCount).decreaseLength(currentSegment.getSegmentLength());;
						}
						else
							throw new InvalidStageStateException();
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
	public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException { //complete!
		int[] indexArray = findStage(stageId);
		if (indexArray[0] == -1){
			throw new IDNotRecognisedException();
		}
		else if (Races.get(indexArray[0]).getStages().get(indexArray[1]).getConcluded() == true){
			throw new InvalidStageStateException();
		}
		else
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
		return newTeam.getTeamID();
		
	}
	@Override
	public void removeTeam(int teamId) throws IDNotRecognisedException { //complete!
		int targetIndex = locateTeam(teamId);
		if (targetIndex != -1) {
			Teams.remove(targetIndex);
		}
		else 
			throw new IDNotRecognisedException();
	}
	@Override
	public int[] getTeams() { //complete!
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
            newRider = new Rider(teamID, name, yearOfBirth);
           	int teamCount = 0;
			while (teamCount <= Teams.size()-1) {
                if (Teams.get(teamCount).getTeamID() == teamID){ //idea what might be going wrong... riders are added to x, not the actual team concerned.
                	Teams.get(teamCount).addRider(newRider);
                 	return newRider.getRiderID();
				}
				++teamCount;
            }
			throw new IDNotRecognisedException();
			}
			else
				throw new IllegalArgumentException();
		}

	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException { //complete!
		int[] indexArray = locateRider(riderId);
		if (indexArray[0] == -1)
			throw new IDNotRecognisedException();
		ArrayList<RiderStageResult>	tempList = new ArrayList<RiderStageResult>();
		for (RiderStageResult x:RiderStageResults){
			if (x.getRiderID() != riderId){
				tempList.add(x);
			}
		}
		RiderStageResults = tempList;
		Teams.get(indexArray[0]).removeRider(indexArray[1]);
	}
	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints) //complete!
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointsException,
			InvalidStageStateException {
			int temp = locateRiderStageResult(stageId, riderId);
			if (temp != -1)
				throw new DuplicatedResultException();
			if (findStage(stageId)[0] == -1){
				System.out.println("stage wasn't found bruhhh");
				throw new IDNotRecognisedException();
			}	
			if (locateRider(riderId)[0] == -1){
				System.out.println("rider wasn't found bruhhh");
				throw new IDNotRecognisedException();
			}
			RiderStageResult newStageResult; 
			newStageResult = new RiderStageResult(stageId, riderId, checkpoints);
			RiderStageResults.add(newStageResult);
		}
	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException { //complete!
		int targetIndex = locateRiderStageResult(stageId, riderId);
		if (targetIndex == -1){
			throw new IDNotRecognisedException();
		}
		else 
			return RiderStageResults.get(targetIndex).getCheckpoints();
		}
	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException { //complete!
		int targetRiderIndex = locateRiderStageResult(stageId, riderId);
		if (targetRiderIndex == -1){
			throw new IDNotRecognisedException();
		}
		LocalTime adjustTime = RiderStageResults.get(targetRiderIndex).getElapsedTime();
		ArrayList<RiderStageResult> relevantResults = retrieveResultsForStage(stageId);
		for (RiderStageResult x:relevantResults){
			if (x.getRiderID() == riderId){
				break;
			}
			else{
				LocalTime comparisonTime = x.getElapsedTime();
				Duration timeDifference = Duration.between(adjustTime, comparisonTime);
				if ((timeDifference.getSeconds() <= 1) & (timeDifference.getSeconds() >= -1)){
					timeDifference = Duration.between(adjustTime, comparisonTime);
					if ((timeDifference.getNano() <= 999999999) & (timeDifference.getNano() >= -999999999)){
						if (comparisonTime.isBefore(adjustTime) == true){
							adjustTime = comparisonTime;
						}
					}
				}
				}
		}
		return adjustTime;
	}
	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException { //complete!
		int targetIndex = locateRiderStageResult(stageId, riderId);
		if (targetIndex == -1)
			throw new IDNotRecognisedException();
		else
			RiderStageResults.remove(targetIndex);
	}
	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException { //complete!
		ArrayList<RiderStageResult> relevantStageResults = retrieveResultsForStage(stageId);
		RiderStageResult[] sortedResultArray = sortStageResultsByTime(relevantStageResults, -1);
		if (sortedResultArray == null)
			throw new IDNotRecognisedException();
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
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException { //complete!
		ArrayList<RiderStageResult> relevantStageResults = retrieveResultsForStage(stageId);
		System.out.println(relevantStageResults.size());
		RiderStageResult[] sortedResultArray = sortStageResultsByTime(relevantStageResults, -1);
		if (sortedResultArray == null){
			throw new IDNotRecognisedException();
		}
		LocalTime [] outputRanks;
		outputRanks = new LocalTime[relevantStageResults.size()];
		int count = 0; 
		for (RiderStageResult x:sortedResultArray){
			outputRanks[count] = getRiderAdjustedElapsedTimeInStage(x.getStageID(), x.getRiderID());
			++count;
		}
		return outputRanks;
	}
	@Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException { //TODO not QUITE finished! test with 16 riders :)
		int stageLocation[] = findStage(stageId);
		if (stageLocation[0] == -1){
			throw new IDNotRecognisedException();
		}
		gatherSprintPoints(stageId);
		ArrayList<RiderStageResult> relevantResults = retrieveResultsForStage(stageId);
		RiderStageResult[] sortedResults = sortStageResultsByTime(relevantResults, -1);
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
		int ridersToAllocate = sortedResults.length;
		outputArray = new int[ridersToAllocate];
		int count = 0; 
		while ((count != 15) & (count <= ridersToAllocate - 1)){
			outputArray[count] = pointArray[count] + sortedResults[count].getSprintPoints();
			++count;
		}
		if (count == 15){
			while (count <= ridersToAllocate - 1){ 
					outputArray[count] = 0 + sortedResults[count].getSprintPoints();
					++count;
				}
		}
		return outputArray;	
	}
	@Override
	public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException { //not QuiTE FinIShed AS WEll
		int stageLocation[] = findStage(stageId);
		if (stageLocation[0] == -1){
			throw new IDNotRecognisedException();
		}
		gatherMountainPoints(stageId);
		ArrayList<RiderStageResult> relevantResults = retrieveResultsForStage(stageId);
		RiderStageResult[] sortedResults = sortStageResultsByTime(relevantResults, -1);
		int[] outputArray;
		int ridersToAllocate = sortedResults.length;
		outputArray = new int[ridersToAllocate];
		int count = 0; 
		while ((count != 15) & (count <= ridersToAllocate - 1)){
			outputArray[count] = sortedResults[count].getMountainPoints();
			++count;
		}
		if (count == 15){
			while (count <= ridersToAllocate - 1){ 
					outputArray[count] = 0 + sortedResults[count].getMountainPoints();
					++count;
				}
		}
		return outputArray;	
	}
	@Override
	public void eraseCyclingPortal() { //complete!
		Teams.clear();
		Races.clear();
		RiderStageResults.clear();
		System.out.println("Portal erased successfully!");
	}
	@Override
	public void saveCyclingPortal(String filename) throws IOException { //complete!
		try {
			ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(filename));
			save.writeObject(Teams);
			save.writeObject(Races);
			save.writeObject(RiderStageResults);
			System.out.println("saved succesfully!");
			save.close();
		}
		catch (IOException ex){
			throw new IOException();
		}
	}
	@Override
	@SuppressWarnings("unchecked")
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException { //complete!
		try{
			ObjectInputStream load = new ObjectInputStream(new FileInputStream(filename));
			eraseCyclingPortal();
		Object objectData = load.readObject();
		if (objectData instanceof ArrayList<?>){
			Teams = (ArrayList<Team>) objectData;
			}
		else{
			load.close();
			throw new ClassNotFoundException();
		
		}
		objectData = load.readObject();
		if (objectData instanceof ArrayList){
			Races = (ArrayList<Race>) objectData;
		}
		else{
			load.close();
			throw new ClassNotFoundException();
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
			throw new ClassNotFoundException();
		}
		load.close();
		}
		catch (IOException ex){
			throw new IOException();
		}
		
	}
	@Override
	public void removeRaceByName(String name) throws NameNotRecognisedException { //complete!
		boolean raceFound = false;
		int searchCount = 0;
		Race currentRace;
		while (raceFound == false && searchCount <= Races.size() - 1) {
            currentRace = (Races.get(searchCount));
            String tempName = currentRace.getRaceName();
            if (tempName.equals(name)) {
                raceFound = true;
            }
            else {
                searchCount += 1;
            }
        }
		if (raceFound == true) {
			Races.remove(searchCount);
		}
		else
			throw new NameNotRecognisedException();

	}


	@Override
	public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
		return null;
	}
	@Override
	public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
		return null;
	}
	@Override
	public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
		return null;
	}
	@Override
	public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
		return null;
	}
	@Override
	public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
		return null;
	}
	@Override
	public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
		return null;
	}

}

package paris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SubwayStation {
	public static int[][] manhatanDistance = {
		{ 0, 11, 20, 27, 40, 43, 39, 28, 18, 10, 18, 30, 30, 32}, // 1
		{11, 0,  9,  16, 29, 32, 28, 19, 11,  4, 17, 23, 21, 24}, // 2
		{20, 9,  0,  7,  20, 22, 19, 15, 10, 11, 21, 21, 13, 18}, // 3
		{27, 16, 7,  0,  13, 16, 12, 13, 13, 18, 26, 21, 11, 17}, // 4
		{40, 29, 20, 13, 0,  3,  2,  21, 25, 31, 38, 27, 16, 20}, // 5
		{43, 32, 22, 16, 3,  0,  4,  23, 28, 33, 41, 30, 17, 20}, // 6
		{39, 28, 19, 12, 2,  4,  0,  22, 25, 29, 38, 28, 13, 17}, // 7
		{28, 19, 15, 13, 21, 23, 22, 0,  9,  22, 18, 7,  25, 30}, // 8
		{18, 11, 10, 13, 25, 28, 25, 9,  0,  13, 12, 12, 23, 28}, // 9
		{10, 4,  11, 18, 31, 33, 29, 22, 13, 0,  20, 27, 20, 23}, // 10
		{18, 17, 21, 26, 38, 41, 38, 18, 12, 20, 0,  15, 35, 39}, // 11
		{30, 23, 21, 21, 27, 30, 28, 7,  12, 27, 15, 0,  31, 37}, // 12
		{30, 21, 13, 11, 16, 17, 13, 25, 23, 20, 35, 31,  0,  5}, // 13
		{32, 24, 18, 17, 20, 20, 17, 30, 28, 23, 39, 37,  5,  0}  // 14
	};
	public static int[][] realDistance = {
		{ 0, 11, 0,  0,  0,  0, 0, 0,  0,  0, 0,  0, 0,  0}, // 1
		{11, 0,  9,  0,  0,  0, 0, 0,  11, 4, 0,  0, 0,  0}, // 2
		{ 0, 9,  0,  7,  0,  0, 0, 0,  10, 0, 0,  0, 19, 0}, // 3
		{ 0, 0,  7,  0,  14, 0, 0, 16, 0,  0, 0,  0, 12, 0}, // 4
		{ 0, 0,  0,  14, 0,  3, 2, 33, 0,  0, 0,  0, 0,  0}, // 5
		{ 0, 0,  0,  0,  3,  0, 0, 0,  0,  0, 0,  0, 0,  0}, // 6
		{ 0, 0,  0,  0,  2,  0, 0, 0,  0,  0, 0,  0, 0,  0}, // 7
		{ 0, 0,  0,  16, 33, 0, 0, 0,  10, 0, 0,  7, 0,  0}, // 8
		{ 0, 11, 10, 0,  0,  0, 0, 10, 0,  0, 14, 0, 0,  0}, // 9
		{ 0, 4,  0,  0,  0,  0, 0, 0,  0,  0, 0,  0, 0,  0}, // 10
		{ 0, 0,  0,  0,  0,  0, 0, 0,  14, 0, 0,  0, 0,  0}, // 11
		{ 0, 0,  0,  0,  0,  0, 0, 7,  0,  0, 0,  0, 0,  0}, // 12
		{ 0, 0,  19, 12, 0,  0, 0, 0,  0,  0, 0,  0, 0,  5}, // 13
		{ 0, 0,  0,  0,  0,  0, 0, 0,  0,  0, 0,  0, 5,  0}  // 14
	};
	
	private int id;
	
	private int origin;
	
	private int destination;
	
	private int missingDistance;

	private int distanceTraveled;
	
	private SubwayStation prev;
	
	private SubwayStation next;
	
	
	public SubwayStation(int id, int destination, int distanceTraveled) {
		this.id = id;
		this.destination = destination - 1;
		this.distanceTraveled =  distanceTraveled;
		this.missingDistance = manhatanDistance[id - 1][destination - 1];
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrigin() {
		return origin;
	}

	public void setOrigin(int origin) {
		this.origin = origin;
	}

	public int getDestination() {
		return destination;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}

	public int getMissingDistance() {
		return missingDistance;
	}

	public void setMissingDistance(int missingDistance) {
		this.missingDistance = missingDistance;
	}

	public int getDistanceTraveled() {
		return distanceTraveled;
	}

	public void setDistanceTraveled(int distanceTraveled) {
		this.distanceTraveled = distanceTraveled;
	}

	public SubwayStation getPrev() {
		return prev;
	}

	public void setPrev(SubwayStation prev) {
		this.prev = prev;
	}

	public SubwayStation getNext() {
		return next;
	}

	public void setNext(SubwayStation next) {
		this.next = next;
	}
	
	public int heuristic(){
		return distanceTraveled + missingDistance;
	}
	
	public static double calculateTime(SubwayStation subway) {
		int RED = 1, BLUE = 2, GREEN = 3, YELLOW = 4;
		int extra = 0, finalDistance = subway.getDistanceTraveled();
		double kmPerHour = 30.0, timeFinal;
		
		Map<Integer, List<Integer>> lines = new HashMap<Integer, List<Integer>>();
		lines.put(RED, Arrays.asList(11,9,14));
		lines.put(BLUE, Arrays.asList(1,2,3,4,5,6));
		lines.put(GREEN, Arrays.asList(18,8,4,13,14));
		lines.put(YELLOW, Arrays.asList(10,2,9,8,5,7));
		
		List<Integer> result = new ArrayList<Integer>();
		while (subway.getPrev() != null) {
			result.add(subway.getId());
			subway = subway.getPrev();
		}
		
		for (int i = 0; i < result.size() - 1; i++) {
			if (result.get(i) != result.get(i + 1)) {
				extra += 5;
			}
		}
		
		timeFinal = ((finalDistance / kmPerHour) * 60) + extra;
		return timeFinal;
	}
}

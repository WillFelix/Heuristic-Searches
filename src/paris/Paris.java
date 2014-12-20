package paris;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Paris {
	
	public SubwayStation station;
	public List<SubwayStation> route = new ArrayList<SubwayStation>();
	public List<SubwayStation> visiteds = new ArrayList<SubwayStation>();
	
	public static void main(String[] args) {
		Paris paris = new Paris();
		paris.map();
	}
	
	public void map() {
		Scanner input = new Scanner(System.in);
		System.out.println("Inteligência Artificial");
		System.out.println("---------------------------------------------");
		
		System.out.print("\nDigite a Estação de Origem (1 a 14): ");
		int begin = input.nextInt();
		System.out.print("Digite a Estação de Destino (1 a 14): ");
		int end = input.nextInt();
		
		station = new SubwayStation(begin, end, 0);
		visiteds.add(station);
		tellMeWhichRoute(begin, end);
		
		input.close();
	}
	
	public void tellMeWhichRoute(int begin, int end) {
		int currentStation = begin;

		while (currentStation != end) {
			int[][] distances = SubwayStation.realDistance;
			for (int i = 1; i <= distances[0].length; i++) {
				
				int distance = distances[currentStation - 1][i - 1];
				if (distance != 0) {
					distance += station.getDistanceTraveled();
					
					SubwayStation sub = new SubwayStation(i, end, distance);
					sub.setPrev(station);
					if (!contains(visiteds, sub)) {
						visiteds.add(sub);
						route.add(sub);
					}
				}
				
			}
			
			sort(route);
			
			SubwayStation r = route.remove(0);
			while (isDeadEnd(r, end)) {
				r = route.remove(0);
			}
			
			station.setNext(r);
			station = station.getNext();
			currentStation = station.getId();
		}

		station.setNext(new SubwayStation(end, end, SubwayStation.manhatanDistance[begin - 1][end - 1]));
		printResult(station);
	}

	// Se não tem caminho e não é o objetivo 
	private boolean isDeadEnd(SubwayStation r, int goal) {
		int c = 0;
		int[] dist = SubwayStation.realDistance[r.getId() - 1];
		for (int i = 0; i < dist.length; i++) {
			if (dist[i] > 0) {
				c++;
			}
		}
		
		if (c > 1 || r.getId() == goal) {
			return false;
		}
		
		return true;
	}

	private boolean contains(List<SubwayStation> r, SubwayStation st) {
		for (int i = 0; i < r.size(); i++) {
			if (r.get(i).getId() == st.getId()) {
				return true;
			}
		}
		return false;
	}
	
	private void printResult(SubwayStation sub) {
		SubwayStation res = sub;
		
		System.out.print("\n\n ###### Caminho Percorrido: ");
		while (sub.getPrev() != null) {
			sub = sub.getPrev();
		}
		while (sub.getNext() != null) {
			System.out.print(" " + sub.getId());
			sub = sub.getNext();
		}
		
		
		double time = SubwayStation.calculateTime(res);
		System.out.println("\n ###### Horas Gastas: " + Math.round(time) + " min");
		System.out.println("\n\nFINISHED");
	}

	public void sort(List<SubwayStation> route) {
		Collections.sort(route, new Comparator<SubwayStation>() {
			@Override
			public int compare(SubwayStation o1, SubwayStation o2) {
				if (o1.heuristic() > o2.heuristic()) {
					return 1;
				} else if (o1.heuristic() <= o2.heuristic()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
	}
}
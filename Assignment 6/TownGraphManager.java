import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TownGraphManager implements TownGraphManagerInterface {
	private Graph roadNetwork;

	public TownGraphManager() {
		roadNetwork = new Graph();
	}

	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {

		if (roadNetwork.addEdge(new Town(town1), new Town(town2), weight, roadName) == null)
			return false;

		return true;

	}

	@Override
	public String getRoad(String town1, String town2) {
		return roadNetwork.getEdge(new Town(town1), new Town(town2)).getName();
	}

	@Override
	public boolean addTown(String v) {
		return roadNetwork.addVertex(new Town(v));
	}

	@Override
	public Town getTown(String name) {
		return roadNetwork.getVertex(new Town(name));
	}

	@Override
	public boolean containsTown(String v) {
		return roadNetwork.containsVertex(new Town(v));
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return roadNetwork.containsEdge(new Town(town1), new Town(town2));
	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> roads = new ArrayList<String>();

		for (Road road : roadNetwork.edgeSet()) {
			roads.add(road.getName());
		}
		if (roads.size() <= 0)
			return roads;

		Collections.sort(roads);

		return roads;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		if (roadNetwork.removeEdge(new Town(town1), new Town(town2), 0, road) == null)
			return false;

		return true;
	}

	@Override
	public boolean deleteTown(String v) {
		return roadNetwork.removeVertex(new Town(v));
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> towns = new ArrayList<String>();

		for (Town town : roadNetwork.vertexSet()) {
			towns.add(town.getName());
		}
		if (towns.size() <= 0)
			return towns;

		Collections.sort(towns);

		return towns;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		return roadNetwork.shortestPath(new Town(town1), new Town(town2));
	}

	public void populateTownGraph(File selectedFile) throws FileNotFoundException, IOException {
		String[] tokens;
		String currentLine;

		Scanner fileReader = new Scanner(selectedFile);

		while (fileReader.hasNextLine()) {
			currentLine = fileReader.nextLine();
			tokens = currentLine.split(";|,");
			roadNetwork.addVertex(new Town(tokens[2]));
			roadNetwork.addVertex(new Town(tokens[3]));
			roadNetwork.addEdge(new Town(tokens[2]), new Town(tokens[3]), Integer.parseInt(tokens[1]), tokens[0]);
		}

		fileReader.close();

	}

}
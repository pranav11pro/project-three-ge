// --== CS400 File Header Information ==--
// Name: Pranav Agrawal
// Email: pragrawal@wisc.edu
// Team: GE
// TA: Daniel K
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class FlightFinderBackEnd {
    
  private CS400Graph<Airport> graph;
  private Airport from;
  private Airport to;
  
  public FlightFinderBackEnd() {
    graph = new CS400Graph<Airport>();
    loadFile("airports.txt");
   
  }
  
  public void loadFile(String fileName) {
    try {
      File file = new File("airports.txt");
      Scanner s = new Scanner(file);
      while (s.hasNextLine()) {
          String[] line = s.nextLine().trim().split(" ");
          String name = line[0];
          String iataCode = line[1];
          String locationCity = line[2];
          String locationStateOrCountry = line[3];
          graph.insertVertex(new Airport(name, iataCode, locationCity, locationStateOrCountry));
      }
      s.close();
    } catch (FileNotFoundException e) {
        System.out.println("Could not load graph file.");
    }
    //  Method incomplete. Need to add edge data
  }
  
  public List<Airport> getCheapest(Airport from, Airport to) {
    return graph.shortestPath(from, to);
  }
  
  public int getCost(Airport from, Airport to) {
    return graph.getPathCost(from, to);
  }
  
//  public String airportToString(String input) {
//    Airport airport = getAirport(input);
//    if(!airport.equals(null)) {
//      return airport.toString();
//    } else {
//      return "Airport not found.";
//    }
//  }
  
  private Airport getAirport(String input) {
    for(int i = 0; i < graph.vertices.size(); i++) {
      Airport airport = graph.vertices.get(i).data;
      //    Compare against all names
      if(input.equals(airport.name)) {
        return airport;
      }
      //    Compare against all iataCodes
      else if(input.equals(airport.iataCode)) {
        return airport;
      }
      //    Compare against all locationCitys, assuming each city has only one airport
      else if(input.equals(airport.locationCity)) {
        return airport;
      }    
    }
    return null;
  }

}

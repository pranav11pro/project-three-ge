// --== CS400 File Header Information ==--
// Name: Jackson Glisczinski
// Email: jglisczinski@wisc.edu
// Team: GE
// Role: Data Wrangler
// TA: Dan Kiel
// Lecturer: Gary Dahl
// Notes to Grader: I also created the txt files: "airport.txt" and "flights.txt"

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

/**
 * This class offers static load and save methods, which is to be used with the Back End class.
 * 
 * @author jackson
 */
public class DataWrangler {

  // String = key (IATA code), Airport = value (Airport Object w/ other info)
  private static Hashtable<String, Airport> airports = new Hashtable<String, Airport>();

  /**
   * First loads airports into the graph based on a txt file holding 45 airports. Then loads edges
   * connecting the airports with weight representing flight cost ($). International flights are
   * more expensive than domestic flights.
   * 
   * @param graph holds airports and flights
   */
  public static void load(CS400Graph<String> graph) {
    // import airports (vertices)
    try {
      File airportsFile = new File("airports.txt");
      Scanner in = new Scanner(airportsFile);
      while (in.hasNextLine()) {
        String[] currLine = in.nextLine().trim().split(",");
        String name = currLine[0].trim();
        String iataCode = currLine[1].trim();
        String locationCity = currLine[2].trim();
        String locationStateOrCountry = currLine[3].trim();
        Airport toAdd = new Airport(name, iataCode, locationCity, locationStateOrCountry);
        airports.put(iataCode, toAdd);
        graph.insertVertex(iataCode);
      }
      in.close();
    } catch (FileNotFoundException e) {
      System.out.println(("File airports.txt not found."));
    } catch (Exception q) {
      System.out.println(q.getMessage());
      q.printStackTrace();
    }
    // import flights (edges)
    try {
      File flightsFile = new File("flights.txt");
      Scanner in = new Scanner(flightsFile);
      while (in.hasNextLine()) {
        String[] currLine = in.nextLine().trim().split(",");
        String start = currLine[0].trim();
        String end = currLine[1].trim();
        int cost = Integer.parseInt(currLine[2].trim());
        graph.insertEdge(start, end, cost);

      }
      in.close();
    } catch (FileNotFoundException e) {
      System.out.println(("File flights.txt not found."));
    } catch (Exception q) {
      System.out.println(q.getMessage());
      q.printStackTrace();
    }

  } // end load

  /**
   * Writes info to a file representing a ticket. Based on input, displays starting point,
   * destination, cost, and a breakdown of the path.
   * 
   * @param graph holds airports and flights
   * @param start Airport to start path from
   * @param end   Airport to end path at
   */
  public static void saveTicket(CS400Graph<String> graph, String start, String end) {
    List<String> airportSequence = graph.shortestPath(start, end);
    try {
      File ticket = new File("ticket.txt");
      FileWriter writer = new FileWriter(ticket);
      String ticketText =
          "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\nFLIGHT ROUTE\n\nFrom:\n"
              + DataWrangler.airportToString(airportSequence.get(0))
              + "\n\n\t\t|\n\t\t|\n\t\tV\n\nDestination:\n"
              + DataWrangler.airportToString(airportSequence.get(airportSequence.size() - 1))
              + "\n\nCost: $" + graph.getPathCost(start, end)
              + "\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
      if (airportSequence.size() > 2) {
        ticketText += "\n\nFlight Path: (" + (airportSequence.size() - 2) + " Connecting Flight";
        if (airportSequence.size() != 3) {
          ticketText += "s";
        }
        ticketText += ")\n\n";
        for (int i = 0; i < airportSequence.size(); i++) {
          String airport = airportSequence.get(i);
          ticketText += DataWrangler.airportToString(airport);
          if (i != airportSequence.size() - 1) {
            ticketText += "\n\n\t\t|\n\t\t|   $"
                + graph.getPathCost(airportSequence.get(i), airportSequence.get(i + 1))
                + "\n\t\tV\n\n";
          }
        }
        ticketText += "\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
      }
      writer.write(ticketText);
      writer.close();
    } catch (Exception q) {
      q.printStackTrace();
    }
  } // end saveTicket

  /**
   * Returns the toString method of the airport based on the given iataCode. Else returns null.
   * 
   * @param iataCode
   * @return
   */
  public static String airportToString(String iataCode) {
    if (airports.containsKey(iataCode)) {
      return airports.get(iataCode).toString();
    }
    return null;
  } // end airportToString

} // end DataWrangler

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class DataWrangler {

  private static Hashtable<String, Airport> airports = new Hashtable<String, Airport>();

  public static void load(CS400Graph<String> graph) {
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


    // for each airport in the text file
    // airports.put(iataCode, airport);
    // graph.insertVertex(airport);

    // for each pair of iataCodes with weight
    // airports.insertEdge(iataStart, iataEnd, cost);


  } // end load

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
  }

  public static String airportToString(String iataCode) {
    if (airports.containsKey(iataCode)) {
      return airports.get(iataCode).toString();
    }
    return null;
  } // end airportToString

} // end DataWrangler

// --== CS400 File Header Information ==--
// Name: Jackson Glisczinski
// Email: jglisczinski@wisc.edu
// Team: GE
// Role: Data Wrangler
// TA: Dan Kiel
// Lecturer: Gary Dahl
// Notes to Grader: N/A

/**
 * 
 * @author jackson
 */
public class Airport {

  protected String name; // name of airport
  protected String iataCode; // 2 or 3 letter IATA code representing the airport
  protected String locationCity; // city airport is in
  protected String locationStateOrCountry; // state or country airport is in

  /**
   * Constructor for an Airport object.
   * 
   * @param name
   * @param iataCode
   * @param locationCity
   * @param locationStateOrCountry
   */
  public Airport(String name, String iataCode, String locationCity, String locationStateOrCountry) {
    this.name = name;
    this.iataCode = iataCode;
    this.locationCity = locationCity;
    this.locationStateOrCountry = locationStateOrCountry;
  }

  /**
   * Returns the airport info in a readable format.
   */
  @Override
  public String toString() {
    return "" + name + "\n\t" + locationCity + ", " + locationStateOrCountry + "\n\t(" + iataCode
        + ")";
  }

} // end Airport

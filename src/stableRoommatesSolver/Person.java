package stableRoommatesSolver;
/**
 * Represents a person with preferences.
 */
class Person 
{
	// Person ID
	private int id;             
	
	// Array to store preferences
	private int[] preferences;  
	

	/**
     * Constructor to initialize a person with a given ID and preferences.
     *
     * @param id The person's ID.
     * @param preferences The array representing the person's preferences.
     */
	public Person(int id, int[] preferences) 
	{
		this.id = id;
		this.preferences = preferences;
	}

 
	/**
     * Gets the person's ID.
     *
     * @return The person's ID.
     */
	public int getId() 
	{
		return id;
	}

 
	/**
     * Gets the person's preferences.
     *
     * @return The array representing the person's preferences.
     */
	public int[] getPreferences() 
	{
		return preferences;
	}
}

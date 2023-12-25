package stableRoommatesSolver;
/**
 * Class representing a room with room number and occupants.
 */
class Room 
{
	// Room number
	private int number;        
   
	// Array to store occupants
    private Person[] occupants; 
    

    /**
     * Constructor to initialize a room with a given room number.
     *
     * @param number The room number.
     */
    public Room(int number) 
    {
        this.number = number;
        this.occupants = new Person[2];
    }

    /**
     * Assigns occupants to the room.
     *
     * @param person1 The first occupant.
     * @param person2 The second occupant.
     */
    public void assignOccupants(Person person1, Person person2) 
    {
        occupants[0] = person1;
        occupants[1] = person2;
    }

    /**
     * Displays the assignment of occupants to the room.
     */
    public void displayRoomAssignment() 
    {
        System.out.println("Room " + number + ": Person " + occupants[0].getId() + " and Person " + occupants[1].getId());
    }
}

/**
 * This project, StableRoommatesMatching, implements a solution to the Stable Roommates Problem,
 * where a group of participants each rank preferences for potential roommates, and the goal is
 * to find a stable matching of roommates. The program takes user input for the number of people
 * and their preferences, and then uses backtracking to find a stable matching. If a stable matching
 * is found, it displays the room assignments; otherwise, it indicates that no stable matching exists.
 * The project includes classes for representing people, rooms, and a custom stack for backtracking.
 */

package stableRoommatesSolver;
import java.util.*;
/**
 * Represents a stable roommates matching problem solver.
 */
public class StableRoommatesMatching 
{
	/**
     * Represents the stable roommates problem solver.
     */
    private static class StableRoommates 
    {
    	// Number of participants
        private int n;             
        
        // Array to store people
        private Person[] people;    
        

        /**
         * Constructor to initialize the StableRoommates solver.
         *
         * @param n The number of participants.
         * @param people Array to store people.
         */
        public StableRoommates(int n, Person[] people) 
        {
            this.n = n;
            this.people = people;
        }

        /**
         * Helper method to check if a single person is involved.
         *
         * @return True if a single person is involved, false otherwise.
         */
        private boolean isSinglePerson() 
        {
            return n == 1;
        }

        /**
         * Main method to find a stable matching.
         *
         * @return An array representing the stable matching or null if no stable matching is found.
         */
        public int[] findStableMatching() 
        {
            if (isSinglePerson()) 
            {
                return new int[]{0};
            }
            int[] matching = new int[n];
            Arrays.fill(matching, -1);
            boolean[] visited = new boolean[n];
            CustomStack<Integer> stack = new CustomStack<>();
            if (stableMatchingUtil(matching, visited, 0, stack)) 
            {
                return matching;
            } 
            else 
            {
                return null; 
                // No stable matching found
            }
        }

        /**
         * Backtracking method to find a stable matching.
         *
         * @param matching Array representing the current matching.
         * @param visited Array representing visited participants.
         * @param index The current index in the matching process.
         * @param stack CustomStack to keep track of visited indices.
         * @return True if a stable matching is found, false otherwise.
         */
       
        private boolean stableMatchingUtil(int[] matching, boolean[] visited, int index, CustomStack<Integer> stack) 
        {
            if (index == n) 
            {
                return isStable(matching);
            }

            for (int i = 0; i < n; i++) 
            {
                if (!visited[i]) 
                {
                    matching[index] = i;
                    visited[i] = true;
                    stack.push(i);

                    if (stableMatchingUtil(matching, visited, index + 1, stack)) 
                    {
                        return true;
                    }

                    matching[index] = -1;          
                    // Backtrack
                    visited[stack.pop()] = false;   
                    // Backtrack
                }
            }

            return false;
        }

        /**
         * Helper method to check if a matching is stable.
         *
         * @param matching Array representing the current matching.
         * @return True if the matching is stable, false otherwise.
         */
        
        private boolean isStable(int[] matching) 
        {
            Set<Integer> seen = new HashSet<>();

            for (int i = 0; i < n - 1; i += 2) 
            {
                int roommate1 = matching[i];
                int roommate2 = matching[i + 1];

                // Check if either roommate is preferred over the other by someone
                if (!isPreferredOver(roommate1, roommate2, matching[i + 1]) &&
                        !isPreferredOver(roommate2, roommate1, matching[i + 1])) 
                {
                    return false;
                }

                seen.add(roommate1);
                seen.add(roommate2);
            }

            // Check if there are no duplicate participants in the matching
            return seen.size() == n;
        }

        /**
         * Helper method to check if 'a' is preferred over 'b' for a participant.
         *
         * @param participant The participant for whom preferences are checked.
         * @param a Preference 'a'.
         * @param b Preference 'b'.
         * @return True if 'a' is preferred over 'b', false otherwise.
         */
        private boolean isPreferredOver(int participant, int a, int b) 
        {
            for (int pref : people[participant].getPreferences()) 
            {
                if (pref == a) return true;
                if (pref == b) return false;
            }
            return false; 
            // This line should not be reached
        }
    }

    // Main program
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        // Ask for the number of people (2 * n)
        System.out.print("Enter the number of people: ");
        int totalPeople = scanner.nextInt();
        int n = totalPeople / 2;

        if (totalPeople <= 0) 
        {
            System.out.println("Invalid number of people. Exiting program.");
            scanner.close();
            return;
        }
        if (totalPeople == 1) 
        {
            System.out.println("Stable Matching");
            scanner.close();
            return;
        }

        Person[] people = new Person[totalPeople];
        for (int i = 0; i < totalPeople; i++) 
        {
            System.out.print("Enter preferences for person " + i + ": ");
            int[] preferences = new int[totalPeople - 1];

            for (int j = 0, k = 0; j < totalPeople; j++) 
            {
                if (j != i) 
                {
                    preferences[k++] = scanner.nextInt();
                }
            }

            people[i] = new Person(i, preferences);
        }

        // Close the scanner
        scanner.close();

        // Create an instance of StableRoommates
        StableRoommates stableRoommates = new StableRoommates(totalPeople, people);
        int[] matching = stableRoommates.findStableMatching();

        // Print the result
        if (matching != null) 
        {
            System.out.println("Stable Matching:");

            // Display room assignments
            for (int i = 0; i < n; i++) 
            {
                Room room = new Room(i + 1);
                room.assignOccupants(people[matching[2 * i]], people[matching[2 * i + 1]]);
                room.displayRoomAssignment();
            }
        } 
        else 
        {
            System.out.println("No stable matching found.");
        }
    }
}


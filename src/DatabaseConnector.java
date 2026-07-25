import java.util.ArrayList;

public class DatabaseConnector {

    private boolean connected;
    private ArrayList<User> savedUsers;
    private ArrayList<Reservation> savedReservations;

    public DatabaseConnector() {
        connected = false;
        savedUsers = new ArrayList<User>();
        savedReservations = new ArrayList<Reservation>();
    }

    public void connect() {
        connected = true;
        System.out.println("Connected to the database.");
    }

    public void disconnect() {
        connected = false;
        System.out.println("Disconnected from the database.");
    }

    public void saveUser(User user) {

        if (connected) {
            savedUsers.add(user);
            System.out.println("User saved: " + user.getName());
        } else {
            System.out.println("Database is not connected.");
        }
    }

    public void saveReservation(Reservation reservation) {

        if (connected) {
            savedReservations.add(reservation);

            System.out.println(
                    "Reservation saved: "
                            + reservation.getReservationId()
            );
        } else {
            System.out.println("Database is not connected.");
        }
    }

    public ArrayList<User> fetchUsers() {

        if (!connected) {
            System.out.println("Database is not connected.");
        }

        return savedUsers;
    }

    public ArrayList<Reservation> fetchReservations() {

        if (!connected) {
            System.out.println("Database is not connected.");
        }

        return savedReservations;
    }
}

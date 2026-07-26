// Exception thrown when a reservation cannot be found.
public class ReservationNotFoundException extends Exception {

    public ReservationNotFoundException(String message) {
        super(message);
    }
}

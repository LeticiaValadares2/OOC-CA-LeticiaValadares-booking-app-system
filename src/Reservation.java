// Represents a reservation made by a user.
public class Reservation {
    private int reservationId;
    private int bookingId;
    private User user;
    private String reservationType;
    private String status;

    public Reservation(int reservationId, int bookingId, User user, String reservationType) {

        this.reservationId = reservationId;
        this.bookingId = bookingId;
        this.user = user;
        this.reservationType = reservationType;
        this.status = "Pending";
    }
        public int getReservationId() {
        return reservationId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public String getReservationType() {
        return reservationType;
    }

    public String getStatus() {
        return status;
    }
        public void cancel() {
        status = "Cancelled";

        System.out.println( "Reservation " + reservationId + " was cancelled for " + user.getName() + "." );
    }
    public void confirm() {
        status = "Confirmed";

        System.out.println(
                "Reservation " + reservationId
                        + " was confirmed for " + user.getName() + "."
        );
    }

    public void modify(String newType) {
        reservationType = newType;
        status = "Modified";

        System.out.println("Reservation " + reservationId + " was modified. New details: " + newType + ".");
    }

    // Return reservation information.
    public String toString() {
        return "Reservation ID: " + reservationId
                + ", Booking ID: " + bookingId
                + ", User: " + user.getName()
                + ", Type: " + reservationType
                + ", Status: " + status;
    }
}

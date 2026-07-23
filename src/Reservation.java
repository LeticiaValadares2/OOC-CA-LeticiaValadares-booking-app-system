public class Reservation {
    private int reservationId;
    private int bookingId;
    private User user;
    private String reservationType;
    private String status;

    public Reservation(int reservationId, int bookingId,
                       User user, String reservationType) {

        this.reservationId = reservationId;
        this.bookingId = bookingId;
        this.user = user;
        this.reservationType = reservationType;
        this.status = "Pending";
    }
}

import java.util.ArrayList;

public class Booking {

    private int bookingId;
    private User createdBy;
    private ArrayList<Reservation> reservations;

    public Booking(int bookingId, User createdBy) {
        this.bookingId = bookingId;
        this.createdBy = createdBy;
        reservations = new ArrayList<Reservation>();
    }

    public int getBookingId() {
        return bookingId;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void addReservation(Reservation reservation)
            throws OverbookingException {

        if (reservations.size() >= 5) {
            throw new OverbookingException(
                    "Booking " + bookingId
                            + " cannot have more than 5 reservations."
            );
        }

        reservations.add(reservation);
    }

    private Reservation findReservation(int reservationId)
            throws ReservationNotFoundException {

        for (Reservation reservation : reservations) {

            if (reservation.getReservationId() == reservationId) {
                return reservation;
            }
        }

        throw new ReservationNotFoundException(
                "Reservation " + reservationId
                        + " was not found in booking " + bookingId + "."
        );
    }

    public void confirmReservation(int reservationId)
            throws ReservationNotFoundException {

        Reservation reservation = findReservation(reservationId);
        reservation.confirm();
    }

    public void cancelReservation(int reservationId)
            throws ReservationNotFoundException {

        Reservation reservation = findReservation(reservationId);
        reservation.cancel();
    }

    public void modifyReservation(int reservationId, String newType)
            throws ReservationNotFoundException {

        Reservation reservation = findReservation(reservationId);
        reservation.modify(newType);
    }

    public String toString() {
        return "Booking ID: " + bookingId
                + ", Created by: " + createdBy.getName()
                + ", Number of reservations: " + reservations.size();
    }
}


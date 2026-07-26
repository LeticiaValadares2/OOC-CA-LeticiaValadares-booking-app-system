import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
// Read data from CSV files.

public class CsvReader {

    public static ArrayList<User> readUsers(String fileName)
            throws IOException, InvalidUserCategoryException {

        ArrayList<User> users = new ArrayList<User>();

        BufferedReader reader = new BufferedReader(
                new FileReader(fileName)
        );

        String line = reader.readLine();

        while ((line = reader.readLine()) != null) {

            String[] parts = line.split(",");

            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            String category = parts[2];
            double credits = Double.parseDouble(parts[3]);
            int loyaltyPoints = Integer.parseInt(parts[4]);
            double rating = Double.parseDouble(parts[5]);
            int reservationsMade = Integer.parseInt(parts[6]);

            User user = new User(
                    id,
                    name,
                    category,
                    credits,
                    loyaltyPoints,
                    rating,
                    reservationsMade
            );

            users.add(user);
        }

        reader.close();
        return users;
    }

    public static ArrayList<Booking> readBookings(
            String fileName,
            ArrayList<User> users
    ) throws IOException {

        ArrayList<Booking> bookings = new ArrayList<Booking>();

        BufferedReader reader = new BufferedReader(
                new FileReader(fileName)
        );

        String line = reader.readLine();

        while ((line = reader.readLine()) != null) {

            String[] parts = line.split(",");

            int bookingId = Integer.parseInt(parts[0]);
            int userId = Integer.parseInt(parts[1]);

            User user = findUser(users, userId);

            if (user != null) {
                Booking booking = new Booking(bookingId, user);
                bookings.add(booking);
            }
        }

        reader.close();
        return bookings;
    }

    public static ArrayList<Reservation> readReservations(
            String fileName,
            ArrayList<User> users,
            ArrayList<Booking> bookings
    ) throws IOException, OverbookingException {

        ArrayList<Reservation> reservations =
                new ArrayList<Reservation>();

        BufferedReader reader = new BufferedReader(
                new FileReader(fileName)
        );

        String line = reader.readLine();

        while ((line = reader.readLine()) != null) {

            String[] parts = line.split(",");

            int reservationId = Integer.parseInt(parts[0]);
            int bookingId = Integer.parseInt(parts[1]);
            int userId = Integer.parseInt(parts[2]);
            String reservationType = parts[3];

            User user = findUser(users, userId);
            Booking booking = findBooking(bookings, bookingId);

            if (user != null && booking != null) {

                Reservation reservation = new Reservation(
                        reservationId,
                        bookingId,
                        user,
                        reservationType
                );

                booking.addReservation(reservation);
                reservations.add(reservation);
            }
        }

        reader.close();
        return reservations;
    }

    private static User findUser(
            ArrayList<User> users,
            int userId
    ) {

        for (User user : users) {

            if (user.getUserId() == userId) {
                return user;
            }
        }

        return null;
    }

    private static Booking findBooking(
            ArrayList<Booking> bookings,
            int bookingId
    ) {

        for (Booking booking : bookings) {

            if (booking.getBookingId() == bookingId) {
                return booking;
            }
        }

        return null;
    }
}

import java.io.IOException;
import java.util.ArrayList;

public class Main{
    public static void main (String[]args){
        try {

            ArrayList<User> users = CsvReader.readUsers(
                    "data/users_dataset.csv"
            );

            ArrayList<Booking> bookings = CsvReader.readBookings(
                    "data/bookings_dataset.csv",
                    users
            );

            ArrayList<Reservation> reservations =
                    CsvReader.readReservations(
                            "data/reservations_dataset.csv",
                            users,
                            bookings
                    );

            System.out.println("----- USERS -----");

            for (User user : users) {
                System.out.println(user);
            }

            System.out.println();
            System.out.println("----- BOOKINGS -----");

            for (Booking booking : bookings) {

                System.out.println(booking);

                for (Reservation reservation
                        : booking.getReservations()) {

                    System.out.println("  " + reservation);
                }
            }

            System.out.println();
            System.out.println("----- RESERVATION ACTIONS -----");

            bookings.get(0).confirmReservation(1001);
            bookings.get(0).modifyReservation(
                    1002,
                    "Luxury Car Rental"
            );
            bookings.get(1).cancelReservation(1008);

            System.out.println();
            System.out.println("----- EXCEPTION TESTS -----");

            try {

                User wrongUser = new User(
                        9,
                        "Test User",
                        "Gold",
                        100,
                        10,
                        4.0,
                        0
                );

                System.out.println(wrongUser);

            } catch (InvalidUserCategoryException exception) {

                System.out.println(
                        "InvalidUserCategoryException caught: "
                                + exception.getMessage()
                );
            }

            try {

                bookings.get(1).modifyReservation(
                        9999,
                        "New Reservation"
                );

            } catch (ReservationNotFoundException exception) {

                System.out.println(
                        "ReservationNotFoundException caught: "
                                + exception.getMessage()
                );
            }

            try {

                Reservation extraReservation =
                        new Reservation(
                                1009,
                                201,
                                users.get(5),
                                "Extra Hotel Room"
                        );

                bookings.get(0).addReservation(extraReservation);

            } catch (OverbookingException exception) {

                System.out.println(
                        "OverbookingException caught: "
                                + exception.getMessage()
                );
            }

            System.out.println();
            System.out.println("===== DATABASE CONNECTOR =====");

            DatabaseConnector database =
                    new DatabaseConnector();

            database.connect();

            for (User user : users) {
                database.saveUser(user);
            }

            for (Reservation reservation : reservations) {
                database.saveReservation(reservation);
            }

            System.out.println();
            System.out.println("Users fetched from the database:");

            for (User user : database.fetchUsers()) {
                System.out.println(user);
            }

            System.out.println();
            System.out.println(
                    "Reservations fetched from the database:"
            );

            for (Reservation reservation
                    : database.fetchReservations()) {

                System.out.println(reservation);
            }

            database.disconnect();

        } catch (IOException exception) {

            System.out.println(
                    "There was a problem reading the CSV files: "
                            + exception.getMessage()
            );

        } catch (InvalidUserCategoryException exception) {

            System.out.println(
                    "User category error: "
                            + exception.getMessage()
            );

        } catch (OverbookingException exception) {

            System.out.println(
                    "Booking error: "
                            + exception.getMessage()
            );

        } catch (ReservationNotFoundException exception) {

            System.out.println(
                    "Reservation error: "
                            + exception.getMessage()
            );
        }

    }
}
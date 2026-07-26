// Represents a user of the booking system.
public class User {
    
    private int userId;
    private String name;
    private String category;
    private double credits;
    private int loyaltyPoints;
    private double rating;
    private int reservationsMade;

    public User(int userId, String name, String category, double credits,
                int loyaltyPoints, double rating, int reservationsMade)
                throws InvalidUserCategoryException {

        this.userId = userId;
        this.name = name;
        setCategory(category);
        this.credits = credits;
        this.loyaltyPoints = loyaltyPoints;
        this.rating = rating;
        this.reservationsMade = reservationsMade;
    }

    public void setCategory(String category)
            throws InvalidUserCategoryException {

        if (category.equalsIgnoreCase("Regular")
                || category.equalsIgnoreCase("Premium")
                || category.equalsIgnoreCase("VIP")
                || category.equalsIgnoreCase("Guest")) {

            this.category = category;
        } else {
            throw new InvalidUserCategoryException(
                    "Invalid user category: " + category
            );
        }
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getCredits() {
        return credits;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public double getRating() {
        return rating;
    }

    public int getReservationsMade() {
        return reservationsMade;
    }

    public void addReservationMade() {
        reservationsMade++;
    }

    public String toString() {
        return "User ID: " + userId
                + ", Name: " + name
                + ", Category: " + category
                + ", Credits: " + credits
                + ", Loyalty Points: " + loyaltyPoints
                + ", Rating: " + rating
                + ", Reservations Made: " + reservationsMade;
    }
}

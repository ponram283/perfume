import java.util.ArrayList;
import java.util.Scanner;

public class AirlineReservationSystem {
    private ArrayList<Flight> flights = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public AirlineReservationSystem() {
        // Initialize with sample flights
        flights.add(new Flight("AA101", "New York", "Los Angeles", 100));
        flights.add(new Flight("BA202", "London", "Paris", 50));
        flights.add(new Flight("CA303", "Beijing", "Tokyo", 80));
    }

    public void displayAvailableFlights() {
        System.out.println("\n--- Available Flights ---");
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }

    public void bookSeat() {
        System.out.println("\nEnter Flight Number: ");
        String flightNumber = scanner.nextLine();

        Flight flight = findFlight(flightNumber);
        if (flight != null) {
            if (flight.getAvailableSeats() > 0) {
                System.out.println("Enter your name: ");
                String passengerName = scanner.nextLine();
                flight.bookSeat();
                bookings.add(new Booking(passengerName, flight));
                System.out.println("Seat successfully booked for " + passengerName + " on flight " + flightNumber);
            } else {
                System.out.println("No available seats on this flight.");
            }
        } else {
            System.out.println("Flight not found.");
        }
    }

    public void cancelBooking() {
        System.out.println("\nEnter your booking name: ");
        String passengerName = scanner.nextLine();

        Booking booking = findBooking(passengerName);
        if (booking != null) {
            booking.getFlight().cancelSeat();
            bookings.remove(booking);
            System.out.println("Booking successfully canceled for " + passengerName);
        } else {
            System.out.println("No booking found under the name " + passengerName);
        }
    }

    public void viewBookingDetails() {
        System.out.println("\nEnter your name: ");
        String passengerName = scanner.nextLine();

        Booking booking = findBooking(passengerName);
        if (booking != null) {
            System.out.println("Booking found: " + booking);
        } else {
            System.out.println("No booking found under the name " + passengerName);
        }
    }

    private Flight findFlight(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

    private Booking findBooking(String passengerName) {
        for (Booking booking : bookings) {
            if (booking.getPassengerName().equalsIgnoreCase(passengerName)) {
                return booking;
            }
        }
        return null;
    }
}

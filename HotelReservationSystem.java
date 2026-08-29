package com.codealpha.hotel;

import java.util.ArrayList;
import java.util.Scanner;

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    // होटल के Rooms बनाना
    public static void initializeRooms() {
        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Standard", 1500));
        rooms.add(new Room(201, "Deluxe", 2500));
        rooms.add(new Room(202, "Deluxe", 2500));
        rooms.add(new Room(301, "Suite", 4000));
    }

    // सभी Available Rooms दिखाना
    public static void viewAvailableRooms() {
        System.out.println("\n===== AVAILABLE ROOMS =====");

        boolean found = false;

        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println("Room No: " + room.getRoomNumber()
                        + " | Category: " + room.getCategory()
                        + " | Price: Rs. " + room.getPrice());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Sorry! No rooms are currently available.");
        }
    }

    // Category के अनुसार Room Search करना
    public static void searchRoomByCategory() {
        System.out.print("Enter Room Category (Standard/Deluxe/Suite): ");
        String category = scanner.nextLine();

        boolean found = false;

        System.out.println("\n===== SEARCH RESULTS =====");

        for (Room room : rooms) {
            if (room.isAvailable()
                    && room.getCategory().equalsIgnoreCase(category)) {

                System.out.println("Room No: " + room.getRoomNumber()
                        + " | Price: Rs. " + room.getPrice());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No available rooms found in this category.");
        }
    }

    // Room Book करना
    public static void bookRoom() {
        viewAvailableRooms();

        System.out.print("\nEnter Room Number to Book: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();

        Room selectedRoom = null;

        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Invalid room number or room is already booked!");
            return;
        }

        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();

        System.out.println("\n===== PAYMENT =====");
        System.out.println("Amount to Pay: Rs. " + selectedRoom.getPrice());
        System.out.print("Proceed with payment? (yes/no): ");
        String payment = scanner.nextLine();

        if (payment.equalsIgnoreCase("yes")) {

            selectedRoom.bookRoom();

            Reservation reservation = new Reservation(
                    customerName,
                    selectedRoom,
                    selectedRoom.getPrice());

            reservations.add(reservation);

            System.out.println("\nPayment Successful!");
            System.out.println("Room booked successfully!");
            System.out.println("Booking Confirmed for " + customerName);

        } else {
            System.out.println("Payment cancelled. Booking was not completed.");
        }
    }

    // सभी Booking Details देखना
    public static void viewBookingDetails() {

        if (reservations.isEmpty()) {
            System.out.println("No bookings available!");
            return;
        }

        System.out.println("\n===== BOOKING DETAILS =====");

        for (Reservation reservation : reservations) {

            System.out.println("-----------------------------");
            System.out.println("Customer Name: "
                    + reservation.getCustomerName());
            System.out.println("Room Number: "
                    + reservation.getRoom().getRoomNumber());
            System.out.println("Room Category: "
                    + reservation.getRoom().getCategory());
            System.out.println("Amount Paid: Rs. "
                    + reservation.getPaymentAmount());
            System.out.println("Status: "
                    + reservation.getStatus());
        }
    }

    // Reservation Cancel करना
    public static void cancelReservation() {

        if (reservations.isEmpty()) {
            System.out.println("No reservations available to cancel!");
            return;
        }

        System.out.print("Enter Room Number to Cancel Booking: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();

        for (Reservation reservation : reservations) {

            if (reservation.getRoom().getRoomNumber() == roomNumber
                    && reservation.getStatus().equals("Confirmed")) {

                reservation.cancelReservation();
                reservation.getRoom().cancelRoom();

                System.out.println("Reservation cancelled successfully!");
                return;
            }
        }

        System.out.println("No active reservation found for this room.");
    }

    // Main Method
    public static void main(String[] args) {

        initializeRooms();

        int choice;

        do {
            System.out.println("\n==================================");
            System.out.println("   HOTEL RESERVATION SYSTEM 🏨");
            System.out.println("==================================");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Search Room by Category");
            System.out.println("3. Book a Room");
            System.out.println("4. View Booking Details");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    viewAvailableRooms();
                    break;

                case 2:
                    searchRoomByCategory();
                    break;

                case 3:
                    bookRoom();
                    break;

                case 4:
                    viewBookingDetails();
                    break;

                case 5:
                    cancelReservation();
                    break;

                case 6:
                    System.out.println("Thank you for using Hotel Reservation System!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 6);

        scanner.close();
    }
}
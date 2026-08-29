package com.codealpha.hotel;

public class Reservation {

    private String customerName;
    private Room room;
    private double paymentAmount;
    private String status;

    public Reservation(String customerName, Room room, double paymentAmount) {
        this.customerName = customerName;
        this.room = room;
        this.paymentAmount = paymentAmount;
        this.status = "Confirmed";
    }

    public String getCustomerName() {
        return customerName;
    }

    public Room getRoom() {
        return room;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public String getStatus() {
        return status;
    }

    public void cancelReservation() {
        status = "Cancelled";
    }
}
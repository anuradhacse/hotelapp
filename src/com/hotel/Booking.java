package com.hotel;

/**
 * This class represents a Booking made by a guest
 */
public class Booking {

  private Guest guest;
  private Room room;
  private String numberOfGuests;
  private int checkinDay;
  private int checkoutDay;

  public Booking(Guest guest, Room room, String numberOfGuests, int checkinDay, int checkoutDay) {
    this.guest = guest;
    this.room = room;
    this.numberOfGuests = numberOfGuests;
    this.checkinDay = checkinDay;
    this.checkoutDay = checkoutDay;
  }

  public Guest getGuest() {
    return guest;
  }

  public Room getRoom() {
    return room;
  }

  public String getNumberOfGuests() {
    return numberOfGuests;
  }

  public int getCheckinDay() {
    return checkinDay;
  }

  public int getCheckoutDay() {
    return checkoutDay;
  }
}

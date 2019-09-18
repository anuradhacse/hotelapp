package com.hotel;

/**
 * Java class that represents a guest
 */
public class Room {

  private Integer roomNumber;
  private Integer roomCapacity;
  private boolean[] bookedDays = new boolean[365];

  public Room(Integer roomNumber, Integer roomCapacity) {
    this.roomNumber = roomNumber;
    this.roomCapacity = roomCapacity;
  }

  public Integer getRoomNumber() {
    return roomNumber;
  }

  public Integer getRoomCapacity() {
    return roomCapacity;
  }

  public boolean setBooked(int startDayNumber, int endDayNumber) {
    //if days are already booked for this room we return false
    for (int i = startDayNumber - 1; i < endDayNumber; ++i) {
      if (bookedDays[i] == true) {
        System.out.println("Room is not available during that period.");
        return false;
      }
    }
    //if days are available make a new booking
    for (int i = startDayNumber - 1; i < endDayNumber; ++i) {
      bookedDays[i] = true;
    }
    return true;
  }
}

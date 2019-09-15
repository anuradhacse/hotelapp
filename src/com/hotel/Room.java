package com.hotel;

/**
 * Java class that represents a guest
 */
public class Room {

  private Integer roomNumber;
  private Integer roomCapacity;

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
}

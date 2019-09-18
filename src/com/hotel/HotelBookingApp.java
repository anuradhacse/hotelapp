package com.hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class HotelBookingApp {

  private static Scanner scanner = new Scanner(System.in);
  private static Integer guestId = 1;
  private static List<Guest> guests = new ArrayList<>();
  private static List<Room> rooms = new ArrayList<>();


  HotelBookingApp(){
    guests.add(new Guest("anu", 1));
    guests.add(new Guest("jay", 2));
    rooms.add(new Room(1, 2));
    rooms.add(new Room(2, 7));
  }

  void startApplication() {
    //todo number string validation, if user input strings instead of numbers handle that
    //todo roombooking
    System.out.print("-----------------------------------------------\n"
        + "------ Welcome to FedUni Hotel Bookings -------\n"
        + "-----------------------------------------------\n"
        + "Main Menu - please select an option:\n"
        + "1.) Add guest\n"
        + "2.) Add room\n"
        + "3.) Add booking\n"
        + "4.) View bookings\n"
        + "5.) Quit\n");

    //reading the input using Scanner class in java
    String mainMenuInput = scanner.nextLine();

    switch (mainMenuInput) {
      case "1":
        createGuest();
      case "2":
        addRoom();
      case "3":
        addBooking();
    }
  }

  private void createGuest() {

    while (true) {
      System.out.println("Please enter guest name:\n");
      String guestName = scanner.nextLine();

      //if the provided name is not empty, we create a new guest and add to out guests list
      if (!guestName.equals("")) {
        Guest guest = new Guest(guestName, guestId);
        guests.add(guest);
        System.out.println("Guest " + guestName + " has been created with guest ID: " + guestId);

        //then we increment the guestId value to be used by the next guest
        guestId += 1;
      }

      System.out.println("Would you like to [A]dd a new guest or [R]eturn to the previous menu?");
      String response = scanner.nextLine();

      if (response.equals("R")) {
        break;
      }
    }
  }

  private void addRoom() {

    while (true) {

      //check if a room exists with current room number
      boolean roomExistsWithGivenRoomNumber;
      String roomNumber;

      do {

        roomExistsWithGivenRoomNumber = false;

        System.out.println("Please enter room number:");
        roomNumber = scanner.nextLine();

        for (Room room : rooms) {
          if (room.getRoomNumber().equals(Integer.valueOf(roomNumber))) {
            System.out.print("Room already exists. ");
            roomExistsWithGivenRoomNumber = true;
          }
        }

      } while (roomExistsWithGivenRoomNumber);

      System.out.println("Please enter room capacity:");
      String roomCapacity = scanner.nextLine();

      rooms.add(new Room(Integer.valueOf(roomNumber), Integer.valueOf(roomCapacity)));

      System.out.println("Would you like to [A]dd a new room or [R]eturn to the previous menu?");

      String response = scanner.nextLine();
      if (response.equals("R")) {
        break;
      }
    }

  }

  private void addBooking() {
    while (true) {
      getBookingDetails();
      System.out.println("Would you like to [A]dd a new booking or [R]eturn to the previous menu?");
      String response = scanner.nextLine();
      if (response.equals("R")) {
        break;
      }
    }
  }

  private void getBookingDetails() {
    //check if the given guest exists
    checkGuestAvailability();

    String numberOfGuests;
    Integer roomCapacity;
    Room room;

    //check if given room exists and validate the room capacity against the number of guests
    do {
      String roomNumber = checkRoomAvailability();
      room = getRoomByRoomNumber(roomNumber);
      roomCapacity = room.getRoomCapacity();

      System.out.println("Please enter number of guests:");
      numberOfGuests = scanner.nextLine();

      if (Integer.valueOf(numberOfGuests) > roomCapacity) {
        System.out.println("Guest count exceeds room capacity of: " + roomCapacity);
      }

    } while (Integer.valueOf(numberOfGuests) > roomCapacity);

    int checkinDay;
    int checkoutDay;
    do {
      String checkinMonth;
      do {
        System.out.println("Please enter check-in month:");
        checkinMonth = scanner.nextLine();
      } while (isInvalidMonth(checkinMonth));

      String checkinDate;
      do {
        System.out.println("Please enter check-in day:");
        checkinDate = scanner.nextLine();
      } while (isInvalidDay(checkinDate));

      String checkoutMonth;
      do {
        System.out.println("Please enter check-out month:");
        checkoutMonth = scanner.nextLine();
      } while (isInvalidMonth(checkoutMonth));

      String checkoutDate;
      do {
        System.out.println("Please enter check-out day:");
        checkoutDate = scanner.nextLine();
      } while (isInvalidDay(checkoutDate));

      checkinDay = dateToDayNumber(Integer.valueOf(checkinMonth), Integer.valueOf(checkinDate));
      checkoutDay = dateToDayNumber(Integer.valueOf(checkoutMonth), Integer.valueOf(checkoutDate));

      if (checkinDay > checkoutDay) {
        System.out.println("Check-out Date should be a Date After Check-in Date");
      } else {

        while(Integer.valueOf(numberOfGuests) > roomCapacity || !room.setBooked(checkinDay, checkoutDay)){

          String roomNumber = checkRoomAvailability();
          room = getRoomByRoomNumber(roomNumber);
          roomCapacity = room.getRoomCapacity();

          if (Integer.valueOf(numberOfGuests) > roomCapacity) {
            System.out.println("Guest count exceeds room capacity of: " + roomCapacity);
          }
        }
      }
    } while (checkinDay > checkoutDay);

    System.out.println("*** Booking successful! ***");
  }

  private String checkGuestAvailability() {
    boolean guestNotExists;
    String guestId;
    do {

      System.out.println("Please enter guest ID:");
      guestId = scanner.nextLine();
      guestNotExists = true;

      for (Guest guest : guests) {
        if (guestId.equals(guest.getId().toString())) {
          guestNotExists = false;
        }
      }

      if (guestNotExists == true) {
        System.out.println("Guest does not exist.");
      }

    } while (guestNotExists);

    return guestId;
  }

  private String  checkRoomAvailability() {
    boolean roomNotExists;
    String roomNumber;

    do {

      System.out.println("Please enter room number:");
      roomNumber = scanner.nextLine();
      roomNotExists = true;

      for (Room room : rooms) {
        if (roomNumber.equals(room.getRoomNumber().toString())) {
          roomNotExists = false;
        }
      }

      if (roomNotExists == true) {
        System.out.println("Room does not exist.");
      }

    } while (roomNotExists);

    return roomNumber;
  }

  public Room getRoomByRoomNumber(String roomNumber){
    for(Room room: rooms){
      if(roomNumber.equals(room.getRoomNumber().toString())){
        return room;
      }
    }
    return null;
  }

  private boolean isInvalidMonth(String checkingMonth) {
    if (0 >= Integer.valueOf(checkingMonth) || Integer.valueOf(checkingMonth) >= 13) {
      System.out.print("Invalid month.");
      return true;
    }
    return false;
  }

  private boolean isInvalidDay(String checkingDay) {
    if (0 >= Integer.valueOf(checkingDay) || Integer.valueOf(checkingDay) >= 32) {
      System.out.print("Invalid day.");
      return true;
    }
    return false;
  }

  private int dateToDayNumber(int month, int day) {
  // Catch invalid input and return early
    if (month < 1 || month > 12 || day < 1 || day > 31) return 0;
    if (month == 1 ) return day;
    if (month == 2 ) return 31 + day;
    if (month == 3 ) return 59 + day;
    if (month == 4 ) return 90 + day;
    if (month == 5 ) return 120 + day;
    if (month == 6 ) return 151 + day;
    if (month == 7 ) return 181 + day;
    if (month == 8 ) return 212 + day;
    if (month == 9 ) return 243 + day;
    if (month == 10) return 273 + day;
    if (month == 11) return 304 + day;

    return 334 + day;
  }

}

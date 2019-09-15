package com.hotel;

/**
 * Java class that represents a guest
 */
public class Guest {

  private String name;
  private Integer id;

  public Guest(String name, Integer id) {
    this.name = name;
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public Integer getId() {
    return id;
  }

}

package com.flyaway.customer;

public class Customer {
    private int flight_id;
    private String name;
    private String email;
    private String phone;
    private Integer passenger_size;
    private int total_fare;

    public Customer(int flight_id, String name, String email, String phone, Integer passenger_size, int total_fare) {
        this.flight_id = flight_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.passenger_size = passenger_size;
        this.total_fare = total_fare;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getPassenger_size() {
        return passenger_size;
    }

    public int getTotal_fare() {
        return total_fare;
    }
}

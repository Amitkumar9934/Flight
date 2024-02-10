package com.webapp.flight.service;

import com.webapp.flight.entity.Flight;

import java.util.List;

public interface FlightService {

    List<Flight> findByDepartureDate(String departure, String arrival, String date);
}

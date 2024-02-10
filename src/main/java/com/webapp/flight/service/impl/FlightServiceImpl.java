package com.webapp.flight.service.impl;

import com.webapp.flight.entity.Flight;
import com.webapp.flight.repository.FlightRepository;
import com.webapp.flight.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }


    @Override
    public List<Flight> findByDepartureDate(String departure, String arrival, String date) {
        return flightRepository.findByDepartureAndArrivalAndDate(departure, arrival, date);
    }
}

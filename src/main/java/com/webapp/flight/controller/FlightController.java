package com.webapp.flight.controller;

import com.webapp.flight.entity.Flight;
import com.webapp.flight.entity.Passenger;
import com.webapp.flight.entity.Reservation;
import com.webapp.flight.payload.PassengerlistDTO;
import com.webapp.flight.payload.ReservationRequestDTO;
import com.webapp.flight.payload.SearchCriteriaDTO;
import com.webapp.flight.repository.FlightRepository;
import com.webapp.flight.repository.ReservationRepository;
import com.webapp.flight.service.FlightService;
import com.webapp.flight.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightController {

    private FlightService flightService;

    private ReservationService reservationService;

    public FlightController(FlightService flightService, ReservationService reservationService) {
        this.flightService = flightService;
        this.reservationService = reservationService;
    }

    @PostMapping("/flights/search")
    public ResponseEntity<List<Flight>> searchFlights(@RequestBody SearchCriteriaDTO searchCriteria) {
        // Logic to search for flights based on the provided criteria
        List<Flight> flights = flightService.findByDepartureDate(
                searchCriteria.getDeparture(),
                searchCriteria.getArrival(),
                searchCriteria.getDate()
        );
        if (flights.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(flights, HttpStatus.OK);
        }
    }

    @PostMapping("/reservation/book")
    public ResponseEntity<ReservationRequestDTO> bookReservation(@RequestBody ReservationRequestDTO reservationRequestdto) {
        ReservationRequestDTO dtos =  reservationService.bookReservation(reservationRequestdto);
        return new ResponseEntity<>(dtos,HttpStatus.CREATED);
    }




//@GetMapping("/reservation/{reservationId}")
//public Reservation getReservation(@PathVariable Long reservationId) {
//    // Logic to retrieve the reservation by ID
//    return reservationRepository.findById(reservationId)
//            .orElseThrow(() -> new NotFoundException("Reservation not found with id: " + reservationId));
//}
}

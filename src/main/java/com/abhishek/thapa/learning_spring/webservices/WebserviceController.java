package com.abhishek.thapa.learning_spring.webservices;

import com.abhishek.thapa.learning_spring.business.ReservationService;
import com.abhishek.thapa.learning_spring.business.RoomReservation;
import com.abhishek.thapa.learning_spring.data.Guest;
import com.abhishek.thapa.learning_spring.data.Room;
import com.abhishek.thapa.learning_spring.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//urls are additive in rest controller. Base url is /api. Any URL defined afterward in method body gets added to /api/ +
//Example: for /reservation, the full url is localhost:8080/api/reservations
@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final ReservationService reservationService;
    private final DateUtils dateUtils;

    public WebserviceController(ReservationService reservationService, DateUtils dateUtils) {
        this.reservationService = reservationService;
        this.dateUtils = dateUtils;
    }

    @RequestMapping(path="/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false) String dateString) {
        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);
    }

    @RequestMapping(path="/guests", method = RequestMethod.GET)
    public List<Guest> getGuests(){
        return this.reservationService.getHotelGuests();
    }

    //another way of writing get Mapping
    //method to return list of rooms in path /api/rooms
    @GetMapping(path="/rooms")
    public List<Room> getRooms(){
        return this.reservationService.getHotelRooms();
    }

    @PostMapping("/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest (@RequestBody Guest guest) {
        this.reservationService.addGuest(guest);

    }


}

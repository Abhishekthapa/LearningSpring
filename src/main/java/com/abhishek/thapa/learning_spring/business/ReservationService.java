package com.abhishek.thapa.learning_spring.business;

import com.abhishek.thapa.learning_spring.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ReservationService {

    private final RoomRepository roomRepository;

    private final GuestRepository guestRepository;

    private final ReservationRepository reservationRepository;

    //to have more than one constructor, one  of the constructor needs to be autowired.
    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = null;
    }


    /**
     * autowired will work however there is a requirement of roomRepository being must. It does the work however we can
     * change the variable so work with final variable
     **/

//    @Autowired
//    public void setRoomRepository(RoomRepository roomRepository) {
//        this.roomRepository = roomRepository;
//    }
//
//    @Autowired
//    public void setGuestRepository(GuestRepository guestRepository) {
//        this.guestRepository = guestRepository;
//    }
//
//    @Autowired
//    public void setReservationRepository(ReservationRepository reservationRepository) {
//        this.reservationRepository = reservationRepository;
//    }
    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });
        Iterable<Reservation> reservations = this.reservationRepository.findReservationByResDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getId());
        });
        List<RoomReservation> roomReservations = new ArrayList<>();
        for (Long id : roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }
        roomReservations.sort(new Comparator<RoomReservation>() {
            @Override
            public int compare(RoomReservation o1, RoomReservation o2) {
                if (o1.getRoomName().equals(o2.getRoomName())) {
                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
                }
                return o1.getRoomName().compareTo(o2.getRoomName());
            }
        });
        return roomReservations;
    }

    // pulls all the guest and sorts guests using last name
    public List<Guest> getHotelGuests() {
        List<Guest> guests = this.guestRepository.findAll();
        guests.sort((o1, o2) -> {
            if (o1.getLastName().equals(o2.getLastName())) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
            return o1.getLastName().compareTo(o2.getLastName());
        });
        return guests;
    }

    //gets all the rooms and sorts the room using room name
    public List<Room> getHotelRooms() {
        List<Room> rooms = this.roomRepository.findAll();
        rooms.sort(Comparator.comparing(Room::getRoomNumber));
        return rooms;
    }

    // method to add guest in guest DB. Since we are using embedded DB once the application is restarted the added
    // DB won't show because we are loading our data from resources/data.sql
    public void addGuest(Guest guest) {
        if (null == guest) {
            throw new RuntimeException("Guest cannot be null");
        }
        this.guestRepository.save(guest);
    }
}


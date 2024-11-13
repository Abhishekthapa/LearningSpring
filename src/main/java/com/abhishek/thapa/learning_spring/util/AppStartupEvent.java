package com.abhishek.thapa.learning_spring.util;

import com.abhishek.thapa.learning_spring.business.ReservationService;
import com.abhishek.thapa.learning_spring.business.RoomReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component //spring boot will automatically load component on startup.
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {

    private final ReservationService reservationService;

    private final DateUtils dateUtils;

    public AppStartupEvent(ReservationService reservationService, DateUtils dateUtils) {
        this.reservationService = reservationService;
        this.dateUtils = dateUtils;
    }


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Date date = this.dateUtils.createDateFromDateString("2022-01-01");
        List<RoomReservation> roomReservations = this.reservationService.getRoomReservationsForDate(date);
        roomReservations.forEach(System.out::println);
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}

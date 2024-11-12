package com.abhishek.thapa.learning_spring.util;

import com.abhishek.thapa.learning_spring.data.Room;
import com.abhishek.thapa.learning_spring.data.RoomRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component //spring boot will automatically load component on startup.
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {

    private final RoomRepository roomRepository;

    public AppStartupEvent(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override //prints all the DB rows in the log during start up
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(System.out::println);

    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}

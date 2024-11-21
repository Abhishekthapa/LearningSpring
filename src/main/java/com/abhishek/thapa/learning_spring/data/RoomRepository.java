package com.abhishek.thapa.learning_spring.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository //not required but just adding for better readability
public interface RoomRepository extends JpaRepository<Room, Long> {

}

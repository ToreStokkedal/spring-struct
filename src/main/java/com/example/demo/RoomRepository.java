package com.example.demo;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface RoomRepository extends CrudRepository<Room, String> {
    List<Room> findByID(String Id);
    //List<Room> findByName(String Name);
}

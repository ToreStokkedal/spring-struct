package com.example.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rooms")
public class RoomController {

    private Logger logger = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public Iterable<Room> findAll() {
        return roomRepository.findAll();
    }

    /**@GetMapping("/name/{roomName}")
    public List <Room> findByName(@PathVariable String roomName) {
        logger.info("findByName got", roomName);
        return roomRepository.findByName(roomName);
    } */

    @GetMapping("/{id}")
    public Room findOne(@PathVariable String id) {
        return roomRepository.findById(id)
          .orElseThrow(RoomNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room create(@RequestBody Room room) {
        logger.error(room.toString()); 
        return roomRepository.save(room); 
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        roomRepository.findById(id)
                .orElseThrow(RoomNotFoundException::new);
        roomRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@RequestBody Room room, @PathVariable String id) {
        if (room.getId() != id) {
            throw new RoomIdMismatchException();
        }
        roomRepository.findById(id)
                .orElseThrow(RoomNotFoundException::new);
        return roomRepository.save(room);
    }
}
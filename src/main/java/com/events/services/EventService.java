package com.events.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.events.models.Event;
import com.events.models.User;
import com.events.repositories.EventRepository;
import com.events.repositories.UserRepository;


@Service

public class EventService {
	@Autowired
	private  UserRepository userRepo;
	@Autowired
	private EventRepository eventRepo;
	
	public EventRepository getEventRepo() {
		return eventRepo;
	}
	public List<Event> allEvents(){
		return eventRepo.findAll();
	}
	
	public Event eventById(Long id){
		Optional<Event> event= eventRepo.findById(id);
		if (event.isPresent()) {
			Event e = event.get();
			return e;
		}
	return null;
	}
	
	public void createEvent(Event event) {
		this.eventRepo.save(event);
	}	
	public void delete(Long id) {
		this.eventRepo.deleteById(id);
	}
	public void joinAttendee(User user, Event event) {
		event.getAttendees().add(user);
		eventRepo.save(event);
	}
	public void cancelAttendee(User user, Event event) {
		event.getAttendees().remove(user);
		eventRepo.save(event);
	}
	public void update(Event event) {
		this.eventRepo.save(event);
	}
	
}

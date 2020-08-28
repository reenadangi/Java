package com.events.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.events.models.Event;
import com.events.models.Location;
import com.events.models.Message;
import com.events.models.User;
import com.events.repositories.EventRepository;
import com.events.services.EventService;
import com.events.services.LocationService;
import com.events.services.MessageService;
import com.events.services.UserService;


@Controller
public class EventController {
	@Autowired
	private  EventService eventService;
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private LocationService locationService;
	
//All events & add event page	
	@RequestMapping("/events")
	public String allEvents(@ModelAttribute("event") Event event,Model viewModel, HttpSession session) {
		viewModel.addAttribute("locations", locationService.allLocations());
		viewModel.addAttribute("events", eventService.allEvents());
		viewModel.addAttribute("event", event);
		Long userId = (Long) session.getAttribute("userId");
		viewModel.addAttribute("user", userService.getUserById(userId));
		return "events.jsp";
	}
	
//Add Event	
	@PostMapping("/events")
	public String addEvent(@Valid @ModelAttribute("event") Event event,
			BindingResult result,HttpSession session,Model viewModel) {
		
		if (result.hasErrors()) {
			viewModel.addAttribute("locations", locationService.allLocations());
			viewModel.addAttribute("events", eventService.allEvents());
			Long userId = (Long) session.getAttribute("userId");
			User user=userService.getUserById(userId);
			viewModel.addAttribute("user", user);
			return "events.jsp";
		}
			
		//If no error add new event
		Long userId = (Long) session.getAttribute("userId");
		User user=userService.getUserById(userId);
		event.setUser(user);
		eventService.createEvent(event);
		return "redirect:/events";
	}

	@RequestMapping("/events/{id}")
	public String eventDetails(@PathVariable("id") Long id,
			Model viewModel,
			HttpSession session,
			@ModelAttribute("message") Message message) {
		
		Long userId = (Long) session.getAttribute("userId");
		User user=userService.getUserById(userId);
		viewModel.addAttribute("user", user);
		
		Event event = eventService.eventById(id);
		viewModel.addAttribute("event", event);
		
		List<Message> messages = messageService.allMessages();
		viewModel.addAttribute("messages", messages);
		viewModel.addAttribute("message", message);
		return "eventDetails.jsp";
	}
	
	@RequestMapping("/events/edit/{id}")
	public String editEvent(@PathVariable("id") Long id, Model viewModel,
			@ModelAttribute("event") Event event) {
		
		viewModel.addAttribute("event", eventService.eventById(id));
		viewModel.addAttribute("locations", locationService.allLocations());
		return "/edit.jsp";
		
	}
	//update event 
	@RequestMapping(value = "/events/{id}", method = RequestMethod.PUT)
	public String updateEvent(@Valid @PathVariable("id") Long id, @ModelAttribute("event") Event event,
			
			BindingResult result,
			Model viewModel,
			HttpSession session) {
		
		System.out.println(result);
		if (result.hasErrors()) {
			Long userId = (Long) session.getAttribute("userId");
			viewModel.addAttribute("event", event);
			viewModel.addAttribute("locations",locationService.allLocations() );
			viewModel.addAttribute("userId", userId);
			return "/events/edit.jsp";
			
		}
			
		
		eventService.update(event);
		return "redirect:/events";
	}
	
	
	
//	add message
	@RequestMapping(value = "/events/message", method = RequestMethod.POST)
	public String addMessage(@Valid @ModelAttribute("message") Message message,
			BindingResult result,HttpSession session) {
		System.out.println("in controller");
		if (result.hasErrors())
			return "events.jsp";
		
//		Long userId = (Long) session.getAttribute("userId");
//		User user=userService.getUserById(userId);
//		message.setUser(user);
//	
		messageService.createMessage(message);
		return "redirect:/events";
	}
	
	// delete an event
	
	
	@DeleteMapping("/events/{id}")
	public String deleteEvent(@PathVariable("id") Long id) {
		eventService.delete(id);
		return "redirect:/events";
	}
	
//	join an event 
	@RequestMapping("/events/join/{id}")
	public String joinEvent(@PathVariable("id") Long id,HttpSession session) {
		
		Long userId = (Long) session.getAttribute("userId");
		User user=userService.getUserById(userId);
		Event event = eventService.eventById(id);
		eventService.joinAttendee(user,event);
		return "redirect:/events";
	}
	
//	cancelAttendee
	@RequestMapping("/events/cancel/{id}")
	public String cancelAttendee(@PathVariable("id") Long id,HttpSession session) {
		
		Long userId = (Long) session.getAttribute("userId");
		User user=userService.getUserById(userId);
		Event event = eventService.eventById(id);
		eventService.cancelAttendee(user, event);
		return "redirect:/events";
	}

	

}

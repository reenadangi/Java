package com.events.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.events.models.Event;
import com.events.models.Message;
import com.events.repositories.MessageRepository;

@Service

public class MessageService {

	@Autowired
	private MessageRepository messageRepo;
	
	public MessageRepository getMessageRepo() {
		return messageRepo;
	}
	public List<Message> allMessages(){
		return messageRepo.findAll();
	}
	public void createMessage(Message message) {
		this.messageRepo.save(message);
	}	
	
	
	
}

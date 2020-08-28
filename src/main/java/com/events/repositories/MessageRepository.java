package com.events.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.events.models.Message;

@Repository
public interface MessageRepository  extends CrudRepository<Message,Long>{

	List<Message> findAll();
//	List<Question> findByTags(Tag tag);
	Optional<Message> findById(Long id);
	
	
	

}
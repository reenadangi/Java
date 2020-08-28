package com.events.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.events.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event,Long>{

	List<Event> findAll();
//	List<Question> findByTags(Tag tag);
	Optional<Event> findById(Long id);
	

}
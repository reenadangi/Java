package com.events.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.events.models.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location,Long>{

	List<Location> findAll();
//	List<Question> findByTags(Tag tag);
	Optional<Location> findById(Long id);
	

}
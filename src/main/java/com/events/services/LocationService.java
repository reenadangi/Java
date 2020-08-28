package com.events.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.events.models.Location;
import com.events.repositories.LocationRepository;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepo;

	public LocationRepository getLocationRepo() {
		return locationRepo;
	}

	public void setLocationRepo(LocationRepository locationRepo) {
		this.locationRepo = locationRepo;
	}
	public List<Location> allLocations(){
		return locationRepo.findAll();
	}
	
}

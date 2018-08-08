package edu.iss.cab.service;

import java.util.ArrayList;

import edu.iss.cab.model.Facility;

public interface FacilityService {

	ArrayList<Facility> findAllFacilitiesWithStatus();

	Facility findFacility(Integer facilityId);

	Facility createFacility(Facility facility);

	Facility updateFacility(Facility facility);

	void removeFacility(Facility facility);

	/* Search Function - For Searching to book facility */
	ArrayList<Facility> searchFacilitiesByName(String content);
}
package edu.iss.cab.service;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iss.cab.model.Facility;
import edu.iss.cab.repository.FacilityRepository;

@Service
public class FacilityServiceImpl implements FacilityService {

	@Resource
	FacilityRepository facilityRepository;

	@Override
	@Transactional
	public Facility findFacility(Integer facilityId) {
		return facilityRepository.findOne(facilityId);
	}

	@Override
	@Transactional
	public Facility createFacility(Facility facility) {
		return facilityRepository.saveAndFlush(facility);
	}

	@Override
	@Transactional
	public Facility updateFacility(Facility facility) {
		return facilityRepository.saveAndFlush(facility);
	}

	@Override
	public void removeFacility(Facility facility) {
		facilityRepository.setDeleteStatus(facility.getFacilityId());
	}

	@Override
	public ArrayList<Facility> findAllFacilitiesWithStatus() {
		// TODO Auto-generated method stub
		return facilityRepository.findAllAvailableFacilities();
	}

	/* Search Function - For Searching to book facility - admin */
	@Transactional
	public ArrayList<Facility> searchFacilitiesByName(String content) {
		ArrayList<Facility> ul = (ArrayList<Facility>) facilityRepository.findAllAvailableFacilitiesByName(content);
		return ul;
	}
}

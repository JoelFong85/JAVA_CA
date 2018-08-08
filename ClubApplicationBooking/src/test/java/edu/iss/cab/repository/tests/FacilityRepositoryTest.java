package edu.iss.cab.repository.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.iss.cab.init.Initializer;
import edu.iss.cab.init.WebAppConfig;
import edu.iss.cab.model.Facility;
import edu.iss.cab.service.FacilityService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({ @ContextConfiguration(classes = Initializer.class),
		@ContextConfiguration(classes = WebAppConfig.class) })
public class FacilityRepositoryTest {

	@Autowired
	FacilityService fs;

	@Test
	public void test_searchFacilitiesByName() {

		Facility fac = new Facility();
		fac.setFacilityName("Tennis Court");
		fac.setDescription("Clementi");
		fac.setPrice(20);

		// fs.createFacility(fac);

		Facility facility = fs.searchFacilitiesByName("Hall").get(0);
		assertEquals(facility.getFacilityName(), "Hall");
	}
}

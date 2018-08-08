package edu.iss.cab.service;

import java.util.ArrayList;
import java.util.Date;

import edu.iss.cab.model.Booking;

public interface BookingService {

	Booking findBooking(Integer bookingId);

	ArrayList<Booking> currentBookingbyMem(Integer userId);

	ArrayList<Booking> historyBookingbyMem(Integer userId);

	ArrayList<Booking> currentBookingbyAdmin();

	ArrayList<Booking> historyBookingbyAdmin();

	Booking searchByBookingId(Integer bookId);

	ArrayList<Booking> searchByFacilityId(Integer facId);

	ArrayList<Booking> findAllBookings();

	Booking createBooking(Booking booking);

	Booking updateBooking(Booking booking);

	Booking findBookingByDate(Integer fId, Date date);

	void removeBooking(Booking booking);

	/* Search Function - For maintenance bookings current & history */
	ArrayList<Booking> searchBookingsByPeriod(String userType, Date StartDate, Date EndDate);

	// for member history search
	ArrayList<Booking> findMemberBookingByPeriod(Integer userId, Date startDate, Date endDate);
}
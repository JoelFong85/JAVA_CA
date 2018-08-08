package edu.iss.cab.service;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iss.cab.model.Booking;
import edu.iss.cab.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
	@Resource
	private BookingRepository bookingRepository;

	@Override
	@Transactional
	public ArrayList<Booking> findAllBookings() {
		ArrayList<Booking> bookings = (ArrayList<Booking>) bookingRepository.findAll();
		return bookings;
	}

	@Override
	@Transactional
	public Booking createBooking(Booking booking) {
		return bookingRepository.saveAndFlush(booking);
	}

	@Override
	@Transactional
	public Booking updateBooking(Booking booking) {
		return bookingRepository.saveAndFlush(booking);
	}

	@Override
	@Transactional
	public Booking findBooking(Integer bookingId) {
		return bookingRepository.findOne(bookingId);
	}

	@Override
	@Transactional
	public ArrayList<Booking> currentBookingbyMem(Integer userId) {
		return bookingRepository.currentBookingbyMem(userId);
	}

	@Override
	@Transactional
	public ArrayList<Booking> historyBookingbyMem(Integer userId) {
		return bookingRepository.historyBookingbyMem(userId);
	}

	@Override
	@Transactional
	public ArrayList<Booking> currentBookingbyAdmin() {
		return bookingRepository.currentBookingbyAdmin();
	}

	@Override
	@Transactional
	public ArrayList<Booking> historyBookingbyAdmin() {
		return bookingRepository.historyBookingbyAdmin();
	}

	@Override
	@Transactional
	public Booking searchByBookingId(Integer bookId) {
		return bookingRepository.searchByBookingId(bookId);
	}

	@Override
	@Transactional
	public ArrayList<Booking> searchByFacilityId(Integer facId) {
		return bookingRepository.searchByFacilityId(facId);
	}

	@Override
	@Transactional
	public Booking findBookingByDate(Integer fId, Date date) {
		return bookingRepository.findByDate(fId, date);
	}

	@Override
	@Transactional
	public void removeBooking(Booking booking) {
		bookingRepository.removebyBookingId(booking.getBookingId());
	}

	@Override
	@Transactional
	public ArrayList<Booking> findMemberBookingByPeriod(Integer userId, Date startDate, Date endDate) {
		ArrayList<Booking> booking = bookingRepository.findMemberBookingByPeriod(userId, startDate, endDate);
		return booking;
	}

	/* Search Function - For maintenance bookings current & history - admin */
	@Override
	@Transactional
	public ArrayList<Booking> searchBookingsByPeriod(String userType, Date StartDate, Date EndDate) {
		ArrayList<Booking> ul = (ArrayList<Booking>) bookingRepository.findAllbookingsByPeriod(userType, StartDate,
				EndDate);
		return ul;
	}
}

package edu.iss.cab.repository;

import java.util.ArrayList;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.iss.cab.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	@Query("SELECT b from Booking b WHERE b.user.userId= :uid AND b.date>=current_date()")
	ArrayList<Booking> currentBookingbyMem(@Param("uid") Integer userId);

	@Query("SELECT b from Booking b WHERE b.user.userId= :uid AND b.date <current_date()")
	ArrayList<Booking> historyBookingbyMem(@Param("uid") Integer userId);

	@Query(value = "SELECT * from Booking b WHERE b.type= 'A' AND b.date>=current_date()", nativeQuery = true)
	ArrayList<Booking> currentBookingbyAdmin();

	@Query(value = "SELECT * from Booking b WHERE b.type = 'A' AND b.date<current_date()", nativeQuery = true)
	ArrayList<Booking> historyBookingbyAdmin();

	@Query("SELECT b from Booking b WHERE b.bookingId = :bid")
	Booking searchByBookingId(@Param("bid") Integer bookingId);

	@Query("SELECT b from Booking b WHERE b.facility.facilityId = :fid")
	ArrayList<Booking> searchByFacilityId(@Param("fid") Integer facilityId);

	@Modifying
	@Query("DELETE from Booking b WHERE b.bookingId = :bid")
	void removebyBookingId(@Param("bid") Integer bookingId);

	@Query("SELECT b from Booking b WHERE b.facility.facilityId = :fid AND b.date = :bDate")
	Booking findByDate(@Param("fid") Integer fid, @Param("bDate") Date date);

	@Query(value = "SELECT * from Booking b WHERE b.user_id= :uid AND b.date>= :startDate AND b.date<= :endDate", nativeQuery = true)
	ArrayList<Booking> findMemberBookingByPeriod(@Param("uid") Integer userId, @Param("startDate") Date StartDate,
			@Param("endDate") Date EndDate);

	/*
	 * Search Function - For maintenance bookings current & history by StartDate &
	 * EndDate- admin
	 */
	@Query(value = "SELECT * from Booking b WHERE b.type = :uType AND b.date>=:StartDate AND b.date<=:EndDate ", nativeQuery = true)
	ArrayList<Booking> findAllbookingsByPeriod(@Param("uType") String userType, @Param("StartDate") Date StartDate,
			@Param("EndDate") Date EndDate);
}

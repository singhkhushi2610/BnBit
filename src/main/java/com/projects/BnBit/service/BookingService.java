package com.projects.BnBit.service;

import com.projects.BnBit.dto.BookingDto;
import com.projects.BnBit.dto.BookingRequest;
import com.projects.BnBit.dto.GuestDto;
import com.projects.BnBit.dto.HotelReportDto;
import com.projects.BnBit.entity.enums.BookingStatus;
import com.stripe.model.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BookingService {

    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<Long> guestIdList);

    String initiatePayments(Long bookingId);

    void capturePayment(Event event);

    void cancelBooking(Long bookingId);

    BookingStatus getBookingStatus(Long bookingId);

    List<BookingDto> getAllBookingsByHotelId(Long hotelId);

    HotelReportDto getHotelReport(Long hotelId, LocalDate startDate, LocalDate endDate);

    List<BookingDto> getMyBookings();
}

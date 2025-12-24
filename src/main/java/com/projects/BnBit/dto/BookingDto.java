package com.projects.BnBit.dto;

import com.projects.BnBit.entity.Hotel;
import com.projects.BnBit.entity.Room;
import com.projects.BnBit.entity.User;
import com.projects.BnBit.entity.enums.BookingStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {
    private Long id;
    private Integer roomsCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BookingStatus bookingStatus;
    private Set<GuestDto> guests;
    private BigDecimal amount;
}

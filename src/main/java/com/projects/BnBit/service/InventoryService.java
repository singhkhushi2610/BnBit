package com.projects.BnBit.service;

import com.projects.BnBit.dto.HotelPriceResponseDto;
import com.projects.BnBit.dto.HotelSearchRequest;
import com.projects.BnBit.dto.InventoryDto;
import com.projects.BnBit.dto.UpdateInventoryRequestDto;
import com.projects.BnBit.entity.Room;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelPriceResponseDto> searchHotels(HotelSearchRequest hotelSearchRequest);

    List<InventoryDto> getAllInventoryByRoom(Long roomId);

    void updateInventory(Long roomId, UpdateInventoryRequestDto updateInventoryRequestDto);
}

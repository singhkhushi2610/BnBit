package com.projects.BnBit.repository;

import com.projects.BnBit.entity.Hotel;
import com.projects.BnBit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByOwner(User user);
}

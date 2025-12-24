package com.projects.BnBit.repository;

import com.projects.BnBit.entity.Guest;
import com.projects.BnBit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> findByUser(User user);
}
package com.projects.BnBit.dto;

import com.projects.BnBit.entity.User;
import com.projects.BnBit.entity.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GuestDto {
    private Long id;
    private String name;
    private Gender gender;
    private LocalDate dateOfBirth;
}

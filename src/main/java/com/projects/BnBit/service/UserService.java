package com.projects.BnBit.service;

import com.projects.BnBit.dto.ProfileUpdateRequestDto;
import com.projects.BnBit.dto.UserDto;
import com.projects.BnBit.entity.User;

public interface UserService {

    User getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);

    UserDto getMyProfile();
}

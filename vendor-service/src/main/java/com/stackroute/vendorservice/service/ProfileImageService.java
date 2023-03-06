package com.stackroute.vendorservice.service;

import com.stackroute.vendorservice.domain.ProfileImage;

import java.util.Optional;

public interface ProfileImageService {
    void saveProfileImage(ProfileImage profileImage);

    Optional<ProfileImage> getImageById(String email);

    boolean deleteImageById(String email);
}

package com.stackroute.vendorservice.service;

import com.stackroute.vendorservice.domain.ProfileImage;
import com.stackroute.vendorservice.repository.ProfileImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileImageServiceImpl implements ProfileImageService {

    @Autowired
    private ProfileImageRepository profileImageRepository;

    public void saveProfileImage(ProfileImage profileImage)
    {
         profileImageRepository.save(profileImage);
    }

    @Override
    public Optional<ProfileImage> getImageById(String email) {
        return profileImageRepository.findById(email);
    }

    @Override
    public boolean deleteImageById(String email) {
        profileImageRepository.deleteById(email);
        return true;
    }
}


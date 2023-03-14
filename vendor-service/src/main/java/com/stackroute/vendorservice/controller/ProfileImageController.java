package com.stackroute.vendorservice.controller;

import com.stackroute.vendorservice.domain.ProfileImage;
import com.stackroute.vendorservice.service.ProfileImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class ProfileImageController {

        @Autowired
        private ProfileImageService profileImageService;

        @PostMapping("/profileImage/{email}")
        public ResponseEntity<String> uploadProfileImage(@PathVariable String email, @RequestBody String file) throws IOException {
                System.out.println(file);
                if (file != null) {
                        ProfileImage profileImage = new ProfileImage();
                        profileImage.setEmailId(email);
                        profileImage.setFile(file);
                        profileImageService.saveProfileImage(profileImage);
                        return ResponseEntity.ok("Profile image uploaded successfully.");
                } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
                }
        }


        @GetMapping("/getprofileImage/{email}")
        public ResponseEntity<?> getprofileImage(@PathVariable String email){
                return new ResponseEntity<>(profileImageService.getImageById(email),HttpStatus.OK);
        }


        @DeleteMapping("/deleteprofileImage/{email}")
        public ResponseEntity<?> deleteprofileImage(@PathVariable String email){
                return new ResponseEntity<>(profileImageService.deleteImageById(email),HttpStatus.OK);
        }
}




package com.fhict.profileservice.services;

import com.fhict.profileservice.models.Profile;
import com.fhict.profileservice.repositories.ProfileRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ProfileService {
    private final ProfileRepository profileRepo;

    public ProfileService(ProfileRepository profileRepo) {
        this.profileRepo = profileRepo;
    }

    public Profile getProfileByUsername(String username) {
        System.out.println("getting profile for " + username);
        return profileRepo.findByUsername(username);
    }

    public ResponseEntity<Profile> createProfile(Profile profile) {
        System.out.println("Creating user");
        profileRepo.save(profile);
        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }

    public List<Profile> getAll() {
        System.out.println("getting all profiles");
        return this.profileRepo.findAll();
    }

    public ResponseEntity<Void> updateProfile(Profile _profile) {
        Profile profile = this.profileRepo.findByUserId(_profile.getUserId());
        profile.setUsername(_profile.getUsername());
        profile.setBio(_profile.getBio());
        profile.setDateOfBirth(_profile.getDateOfBirth());
        profile.setUserEmail(_profile.getUserEmail());
        profile.setUserPicture(_profile.getUserPicture());

        try {
            this.profileRepo.save(profile);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}

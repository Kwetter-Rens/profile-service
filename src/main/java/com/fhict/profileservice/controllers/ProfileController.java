package com.fhict.profileservice.controllers;

import com.fhict.profileservice.models.DTO.ProfileWithFollowing;
import com.fhict.profileservice.models.Profile;
import com.fhict.profileservice.services.ProfileService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private final ProfileService profileService;
    private final RabbitTemplate rabbitTemplate;

    public ProfileController(ProfileService profileService, RabbitTemplate rabbitTemplate) {
        this.profileService = profileService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/getall")
    public List<Profile> getAllProfiles() {
        return this.profileService.getAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<ProfileWithFollowing> getProfileByUsername(@PathVariable String username) {
        System.out.println("retreiving user:" + username);
        Profile profile = profileService.getProfileByUsername(username);
        if(profile == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        List<String> followingUserIds = (List<String>) rabbitTemplate.convertSendAndReceive("kwetter", "following", profile.getUserId());
        ProfileWithFollowing profileWithFollowing = new ProfileWithFollowing(profile, followingUserIds);
        return new ResponseEntity<>(profileWithFollowing, HttpStatus.OK);
    }

    @PostMapping("/createprofile")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        System.out.println("createprofile controller");
        return profileService.createProfile(profile);
    }

    @PutMapping("/updateprofile")
    public ResponseEntity<Void> updateProfile(@RequestBody Profile profile) {
        return profileService.updateProfile(profile);
    }

    @GetMapping("/test")
    public String test() {
        return "test from profileservice";
    }
}

package com.fhict.profileservice.models.DTO;

import com.fhict.profileservice.models.Profile;

import java.time.LocalDate;
import java.util.List;


public class ProfileWithFollowing {
    private int id;
    private String userId;
    private String username;
    private String userEmail;
    private String userPicture;
    private String bio;
    private LocalDate dateOfBirth;
    private List<String> followingUserIds;

    public ProfileWithFollowing() {
    }

    public ProfileWithFollowing(Profile profile, List<String> followingUserIds) {
        this.id = profile.getId();
        this.userId = profile.getUserId();
        this.username = profile.getUsername();
        this.userEmail = profile.getUserEmail();
        this.userPicture = profile.getUserPicture();
        this.bio = profile.getBio();
        this.dateOfBirth = profile.getDateOfBirth();
        this.followingUserIds = followingUserIds;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public String getBio() {
        return bio;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public List<String> getFollowingUserIds() {
        return followingUserIds;
    }
}

package com.fhict.profileservice.repositories;

import com.fhict.profileservice.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile findByUserId(String userId);
    Profile findByUsername(String username);
}

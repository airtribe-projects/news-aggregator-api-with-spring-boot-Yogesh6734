package com.newsaggregator.repository;

import com.newsaggregator.Entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<VerificationToken,Long> {
    VerificationToken findByToken(String token);

}

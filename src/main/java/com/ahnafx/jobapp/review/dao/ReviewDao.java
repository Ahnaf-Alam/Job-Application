package com.ahnafx.jobapp.review.dao;

import com.ahnafx.jobapp.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewDao extends JpaRepository<Review, String> {
    List<Review> findByCompanyId(String companyId);
}

package com.ahnafx.jobapp.review.service;

import com.ahnafx.jobapp.review.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(String companyId);
    Review addReview(String companyId, Review review);
    Review getReview(String companyId, String reviewId);
    Review updatedReview(String companyId, String reviewId, Review updatedReview);
    boolean deleteReview(String companyId, String reviewId);
}

package com.ahnafx.jobapp.review.controller;

import com.ahnafx.jobapp.review.entity.Review;
import com.ahnafx.jobapp.review.service.ReviewService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable String companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> addReview(@PathVariable String companyId, @RequestBody Review review) {
        Review toReturn = reviewService.addReview(companyId, review);

        if(review == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toReturn, HttpStatus.CREATED);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable String companyId,
                                            @PathVariable String reviewId) {
        return new ResponseEntity<>(reviewService.getReview(companyId, reviewId), HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable String companyId,
                                               @PathVariable String reviewId,
                                               @RequestBody Review review) {

        Review updatedReview = reviewService.updatedReview(companyId, reviewId, review);

        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable String companyId,
                                               @PathVariable String reviewId) {
        boolean isReviewDeleted = false;

        try {
            isReviewDeleted = reviewService.deleteReview(companyId, reviewId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        if(isReviewDeleted) {
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
    }
}

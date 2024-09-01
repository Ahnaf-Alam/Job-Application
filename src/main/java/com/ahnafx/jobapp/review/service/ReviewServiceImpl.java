package com.ahnafx.jobapp.review.service;

import com.ahnafx.jobapp.company.entity.Company;
import com.ahnafx.jobapp.company.service.CompanyService;
import com.ahnafx.jobapp.review.dao.ReviewDao;
import com.ahnafx.jobapp.review.entity.Review;
import jakarta.persistence.PersistenceException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewDao reviewDao;
    private CompanyService companyService;

    public ReviewServiceImpl(ReviewDao reviewDao,
                             CompanyService companyService) {
        this.reviewDao = reviewDao;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(String companyId) {
        List<Review> reviews = reviewDao.findByCompanyId(companyId);

        return reviews;
    }

    @Override
    public Review addReview(String companyId, Review review) {
        Company company = companyService.getCompany(companyId);

        if(company != null) {
            review.setCompany(company);
            return reviewDao.saveAndFlush(review);
        }

        return null;
    }

    @Override
    public Review getReview(String companyId, String reviewId) {
        List<Review> reviewList = reviewDao.findByCompanyId(companyId);

        for(Review review: reviewList) {
            if(review.getId().equals(reviewId)) {
                return review;
            }
        }
        return null;
    }

    @Override
    public Review updatedReview(String companyId, String reviewId, Review updatedReview) {
        Company company = companyService.getCompany(companyId);

        if(company == null) {
            throw new PersistenceException("Company not found") {};
        }

        Review review = this.getReview(companyId, reviewId);

        if(review == null) {
            throw new PersistenceException("Review not found") {};
        }

        review.setDescription(updatedReview.getDescription());
        review.setTitle(updatedReview.getTitle());
        review.setRating(updatedReview.getRating());
        review.setCompany(company);

        try {
            review = reviewDao.saveAndFlush(review);
        } catch (DataAccessException e) {
            throw new DataAccessException(e.getMessage()) {};
        }

        return review;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteReview(String companyId, String reviewId) {
        Company company = companyService.getCompany(companyId);

        if(company == null) {
            throw new PersistenceException("Company not found") {};
        }

        Review review = this.getReview(companyId, reviewId);

        if(review == null) {
            throw new PersistenceException("Review not found") {};
        }

        try {
            company.getReviews().remove(review);
            companyService.updateCompany(companyId, company);
            reviewDao.delete(review);
        } catch (DataAccessException e) {
            throw new DataAccessException(e.getMessage()) {};
        }

        return true;
    }
}

package com.example.surveyapp.Model;

public class Review {
    private String reviewType;

    public Review(String reviewType) {
        this.reviewType = reviewType;
    }

    public String getReviewType() {
        return reviewType;
    }

    public void setReviewType(String reviewType) {
        this.reviewType = reviewType;
    }
}

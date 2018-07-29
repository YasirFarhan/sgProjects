/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Farhan
 */
public class Rating {
    private long ratingId;
    private String rating;

    public long getRatingId() {
        return ratingId;
    }

    public void setRatingId(long ratingId) {
        this.ratingId = ratingId;
    }

    public String getRating() { 
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    
    
}

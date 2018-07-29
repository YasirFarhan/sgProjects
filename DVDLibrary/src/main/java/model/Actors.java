 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Farhan
 */
public class Actors {
    private int actorId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    //private List<DvdLibrary> movies;

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

//    public List<DvdLibrary> getMovies() {
//        return movies;
//    }
//
//    public void setMovies(List<DvdLibrary> movies) {
//        this.movies = movies;
//    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.actorId;
        hash = 79 * hash + Objects.hashCode(this.firstName);
        hash = 79 * hash + Objects.hashCode(this.lastName);
        hash = 79 * hash + Objects.hashCode(this.birthDate);
//        hash = 79 * hash + Objects.hashCode(this.movies);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Actors other = (Actors) obj;
        if (this.actorId != other.actorId) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.birthDate, other.birthDate)) {
            return false;
        }
//        if (!Objects.equals(this.movies, other.movies)) {
//            return false;
//        }
        return true;
    }
    
    
    
    
           
}

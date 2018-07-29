 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Farhan
 */
public class Location {

    private Long locationId;
    @Length(min = 5, max = 15, message = "Location must between 5 and 15 characters")
    private String locationName;
    @NotEmpty(message = "Location name is required")
    private String locationDescription;
    private String locationAddress;
    @NotNull(message = "Longitude is required between -180 & 180")
    @DecimalMin(value = "-180.0000000", message = "Longitude greater or equal to  -180")
    @DecimalMax(value = "180.0000000", message = "Longitude less or equal to 180 ")
    private BigDecimal longitude;
    @NotNull(message = "Latitude is required between -90 & 90")
    @DecimalMin(value = "-90.0000000", message = "Latitude greater or equal to -90")
    @DecimalMax(value = "90.0000000", message = "Latitude less or equal to 90")
    private BigDecimal latitude;
    private List<CharacterDetails> characterDetails;

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public List<CharacterDetails> getCharacterDetails() {
        return characterDetails;
    }

    public void setCharacterDetails(List<CharacterDetails> characterDetails) {
        this.characterDetails = characterDetails;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.locationId);
        hash = 59 * hash + Objects.hashCode(this.locationName);
        hash = 59 * hash + Objects.hashCode(this.locationDescription);
        hash = 59 * hash + Objects.hashCode(this.locationAddress);
        hash = 59 * hash + Objects.hashCode(this.longitude);
        hash = 59 * hash + Objects.hashCode(this.latitude);
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
        final Location other = (Location) obj;
        if (this.locationId != other.locationId) {
            return false;
        }
        if (!Objects.equals(this.locationName, other.locationName)) {
            return false;
        }
        if (!Objects.equals(this.locationDescription, other.locationDescription)) {
            return false;
        }
        if (!Objects.equals(this.locationAddress, other.locationAddress)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        return true;
    }

}

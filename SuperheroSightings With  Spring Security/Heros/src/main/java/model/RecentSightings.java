 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Farhan
 */
public class RecentSightings {

    private long sightingId;
    private long characterId;
    private String characterName;
    private String characterType;
    private long locationId;
    private String locationName;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDate sightingDate;
    private String sightingTime;

    public String getSightingTime() {
        return sightingTime;
    }

    public void setSightingTime(String sightingTime) {
        this.sightingTime = sightingTime;
    }

    public LocalDate getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(LocalDate sightingDate) {
        this.sightingDate = sightingDate;
    }

    public long getSightingId() {
        return sightingId;
    }

    public void setSightingId(long sightingId) {
        this.sightingId = sightingId;
    }

    public long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(long characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterType() {
        return characterType;
    }

    public void setCharacterType(String characterType) {
        this.characterType = characterType;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal Latitude) {
        this.latitude = Latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal Longitude) {
        this.longitude = Longitude;
    }

}

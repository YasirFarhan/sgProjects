 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author Farhan
 */
public class CharacterLocationBridgeTable {

    private long bridgetTableId;
    private long orgId;
    private Long locationId;
    private Long characterId;
    private LocalDate sightingDate;
    private String sightingTime;

    public String getSightingTime() {
        return sightingTime;
    }

    public void setSightingTime(String sightingTime) {
        this.sightingTime = sightingTime;
    }

    public long getBridgetTableId() {
        return bridgetTableId;
    }

    public void setBridgetTableId(long bridgetTableId) {
        this.bridgetTableId = bridgetTableId;
    }

    /*
public Date getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(Date sightingDate) {
        this.sightingDate = sightingDate;
    }
     */
    public LocalDate getSightingDate() {
        return sightingDate;
    }

    public void setSightingDate(LocalDate sightingDate) {
        this.sightingDate = sightingDate;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }

}

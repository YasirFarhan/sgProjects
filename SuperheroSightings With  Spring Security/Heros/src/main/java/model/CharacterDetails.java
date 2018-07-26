 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Farhan
 */
public class CharacterDetails {

    private Long characterId;
    @NotEmpty(message = "You must provide a name")
    private String characterName;
    private String characterDescription;
    @NotEmpty(message = "Character Must have some powers")
    private String superPower;
    private String img;
    private String characterType;
    private List<Location> sightingLocations;
    private List<Organization> characterOrganization;

    public Long getCharacterId() {
        return characterId;
    }

    public List<Location> getSightingLocations() {
        return sightingLocations;
    }

    public void setSightingLocations(List<Location> sightingLocations) {
        this.sightingLocations = sightingLocations;
    }

    public List<Organization> getCharacterOrganization() {
        return characterOrganization;
    }

    public void setCharacterOrganization(List<Organization> characterOrganization) {
        this.characterOrganization = characterOrganization;
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterDescription() {
        return characterDescription;
    }

    public void setCharacterDescription(String characterDescription) {
        this.characterDescription = characterDescription;
    }

    public String getSuperPower() {
        return superPower;
    }

    public void setSuperPower(String superPower) {
        this.superPower = superPower;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCharacterType() {
        return characterType;
    }

    public void setCharacterType(String characterType) {
        this.characterType = characterType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.characterId);
        hash = 43 * hash + Objects.hashCode(this.characterName);
        hash = 43 * hash + Objects.hashCode(this.characterDescription);
        hash = 43 * hash + Objects.hashCode(this.superPower);
        hash = 43 * hash + Objects.hashCode(this.img);
        hash = 43 * hash + Objects.hashCode(this.characterType);
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
        final CharacterDetails other = (CharacterDetails) obj;
        if (this.characterId != other.characterId) {
            return false;
        }
        if (!Objects.equals(this.characterName, other.characterName)) {
            return false;
        }
        if (!Objects.equals(this.characterDescription, other.characterDescription)) {
            return false;
        }
        if (!Objects.equals(this.superPower, other.superPower)) {
            return false;
        }
        if (!Objects.equals(this.img, other.img)) {
            return false;
        }
        if (!Objects.equals(this.characterType, other.characterType)) {
            return false;
        }
        return true;
    }

}

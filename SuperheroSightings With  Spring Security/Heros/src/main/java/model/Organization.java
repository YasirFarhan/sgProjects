 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Farhan
 */
public class Organization {

    private long orgId;
    @NotEmpty(message = "You must provide a name")
    @Length(max = 50, message = "Organization name must not be more than 50 characters")
    private String orgName;
    @NotEmpty(message = "You must provide organization Description")
    @Length(max = 50, message = "Description name must not be more than 60 characters")
    private String orgDescription;
    private String orgAddress;
    @NotEmpty(message = "Email address is required")
    @Email(message = "enter a valid email address")
    @Length(max = 50, message = "Email not be more than 40 characters")
    private String email;
    @Length(max = 10, message = "Phone must be no more than 10 characters")
    private String phone;
    private List<CharacterDetails> characterDetails;

    public List<CharacterDetails> getCharacterDetails() {
        return characterDetails;
    }

    public void setCharacterDetails(List<CharacterDetails> characterDetails) {
        this.characterDetails = characterDetails;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.orgId ^ (this.orgId >>> 32));
        hash = 89 * hash + Objects.hashCode(this.orgName);
        hash = 89 * hash + Objects.hashCode(this.orgDescription);
        hash = 89 * hash + Objects.hashCode(this.orgAddress);
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + Objects.hashCode(this.phone);
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
        final Organization other = (Organization) obj;
        if (this.orgId != other.orgId) {
            return false;
        }
        if (this.phone != other.phone) {
            return false;
        }
        if (!Objects.equals(this.orgName, other.orgName)) {
            return false;
        }
        if (!Objects.equals(this.orgDescription, other.orgDescription)) {
            return false;
        }
        if (!Objects.equals(this.orgAddress, other.orgAddress)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

}

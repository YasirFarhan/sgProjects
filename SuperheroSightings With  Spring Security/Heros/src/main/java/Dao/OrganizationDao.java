 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;
import model.CharacterDetails;
import model.Organization;

/**
 *
 * @author Farhan
 */
public interface OrganizationDao {

    List<Organization> getAllOrganizations();

    Organization getSingleOrganization(long id);

    Organization createOrganization(Organization org);

    Organization updateOrganization(Organization org);

    void removeOrganization(long id);

    public List<CharacterDetails> findCharacterOrganization(List<CharacterDetails> c);
}

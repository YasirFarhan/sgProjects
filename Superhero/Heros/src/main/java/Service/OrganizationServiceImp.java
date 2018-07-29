 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Dao.OrganizationDao;
import java.util.List;
import javax.inject.Inject;
import model.CharacterDetails;
import model.Organization;

/**
 *
 * @author Farhan
 */
public class OrganizationServiceImp implements OrganizationService {

    OrganizationDao dao;

    @Inject
    public OrganizationServiceImp(OrganizationDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return dao.getAllOrganizations();
    }

    @Override
    public Organization getSingleOrganization(long id) {
        return dao.getSingleOrganization(id);
    }

    @Override
    public Organization createOrganization(Organization org) {
        return dao.createOrganization(org);
    }

    @Override
    public Organization updateOrganization(Organization org) {
        return dao.updateOrganization(org);
    }

    @Override
    public void removeOrganization(long id) {
        dao.removeOrganization(id);
    }

    @Override
    public List<CharacterDetails> findCharacterOrganization(List<CharacterDetails> c) {
        return dao.findCharacterOrganization(c);
    }

  

}

 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Dao.CharacterDao;
import Dao.SearchTerm;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import model.CharacterLocationBridgeTable;
import model.CharacterDetails;
import model.Location;
import model.Organization;
import model.RecentSightings;

/**
 *
 * @author Farhan
 */
public class CharacterServiceImp implements CharacterService {

    CharacterDao dao;

    @Inject
    public CharacterServiceImp(CharacterDao dao) {
        this.dao = dao;
    }

    @Override
    public List<CharacterDetails> getAllCharacters() {
        return dao.getAllCharacters();
    }

    @Override
    public CharacterDetails getSingleCharacter(long id) {
        return dao.getSingleCharacter(id);
    }

    @Override
    public CharacterDetails createCharacter(CharacterDetails c) {
        return dao.createCharacter(c);
    }

    @Override
    public void updateCharacter(CharacterDetails c) {
        dao.updateCharacter(c);
    }

    @Override
    public void removeCharacter(long id) {
        dao.removeCharacter(id);
    }

    @Override
    public void insertCharacterOrganization(CharacterLocationBridgeTable bt) {
        if (bt.getOrgId() > 0) {
            dao.insertCharacterOrganization(bt);
        }
    }

    @Override
    public void insertCharacterSighting(CharacterLocationBridgeTable bt) {
        if (bt.getLocationId() > 0) {
            dao.insertCharacterSighting(bt);
        }
    }

    @Override
    public List<RecentSightings> getRecentSightings() {
        return dao.getRecentSightings();
    }

    @Override
    public void findOrganizationCharacter(List<Organization> org) {
        dao.findOrganizationCharacter(org);
    }

    @Override
    public void findLocationCharacter(List<Location> loc) {
        dao.findLocationCharacter(loc);
    }

    @Override
    public void removeLocationFromCharacter(long characterId, long locationId) {
        dao.removeLocationFromCharacter(characterId, locationId);
    }

    @Override
    public List<CharacterDetails> search(Map<SearchTerm, String> criterialMap) {
    return     dao.search(criterialMap);
    }

    @Override
    public void removeOrgFromCharacter(long characterId, long orgId) {
dao.removeOrgFromCharacter(characterId, orgId);
    }

}

 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Dao.SearchTerm;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import model.CharacterLocationBridgeTable;
import model.CharacterDetails;
import model.Location;
import model.Organization;
import model.RecentSightings;

/**
 *
 * @author Farhan
 */
public interface CharacterService {

    List<CharacterDetails> getAllCharacters();

    CharacterDetails getSingleCharacter(long id);

    CharacterDetails createCharacter(CharacterDetails c);

    public void updateCharacter(CharacterDetails c);

    public void removeCharacter(long id);

    void insertCharacterOrganization(CharacterLocationBridgeTable bt);

    void insertCharacterSighting(CharacterLocationBridgeTable bt);

    public List<RecentSightings> getRecentSightings();

    public void findOrganizationCharacter(List<Organization> org);

    public void findLocationCharacter(List<Location> loc);

    public void removeLocationFromCharacter(long characterId, long locationId);

    public void removeOrgFromCharacter(long characterId, long orgId);

    public List<CharacterDetails> search(Map<SearchTerm, String> criterialMap);

}

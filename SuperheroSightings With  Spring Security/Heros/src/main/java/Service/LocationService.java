 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;
import model.CharacterDetails;
import model.Location;

/**
 *
 * @author Farhan
 */
public interface LocationService {

    List<Location> getAllLocation();

    Location getSingleLocation(long id);

    Location createLocation(Location loc);

    Location updateLocation(Location loc);

    public void removeLocation(long id);

    public void findCharacterLocations(List<CharacterDetails> c);

    public void findCharacterLocationsByDateAndTime(List<CharacterDetails> c, String sightingDate, String SightingTime);
}

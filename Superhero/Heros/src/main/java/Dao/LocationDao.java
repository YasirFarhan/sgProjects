 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;
import model.CharacterDetails;
import model.Location;

/**
 *
 * @author Farhan
 */
public interface LocationDao {

    List<Location> getAllLocation();

    Location getSingleLocation(long id);

    Location createLocation(Location loc);

    Location updateLocation(Location loc);

    void removeLocation(long id);

    public void findCharacterLocations(List<CharacterDetails> c);

    public void findCharacterLocationsByDateAndTime(List<CharacterDetails> c, String sightingDate, String SightingTime);

}

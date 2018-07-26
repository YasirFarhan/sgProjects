 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Dao.LocationDao;
import java.util.List;
import javax.inject.Inject;
import model.CharacterDetails;
import model.Location;

/**
 *
 * @author Farhan
 */
public class LocationServiceImp implements LocationService {

    LocationDao dao;

    @Inject
    public LocationServiceImp(LocationDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Location> getAllLocation() {
        return dao.getAllLocation();
    }

    @Override
    public Location getSingleLocation(long id) {
        return dao.getSingleLocation(id);
    }

    @Override
    public Location createLocation(Location loc) {
        return dao.createLocation(loc);
    }

    @Override
    public Location updateLocation(Location loc) {
        return dao.updateLocation(loc);
    }

    @Override
    public void removeLocation(long id) {
        dao.removeLocation(id);
    }

    @Override
    public void findCharacterLocations(List<CharacterDetails> c) {
        dao.findCharacterLocations(c);
    }

    @Override
    public void findCharacterLocationsByDateAndTime(List<CharacterDetails> c, String sightingDate, String SightingTime) {
dao.findCharacterLocationsByDateAndTime(c, sightingDate, SightingTime);
    }

}

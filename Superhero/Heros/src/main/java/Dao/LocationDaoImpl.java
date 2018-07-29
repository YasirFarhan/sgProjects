 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.CharacterDetails;
import model.Location;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Farhan
 */
public class LocationDaoImpl implements LocationDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_SELECT_LOCATION
            = " SELECT * FROM Location  ";

    private static final String SQL_SELECT_SINGLE_LOCATION
            = " SELECT * FROM Location  where LocationId = ? ";
    private static final String SQL_INSERT_LOCATION
            = "  insert into Location  (LocationName,	LocationDescription,	LocationAddress,	Latitude,    	Longitude) "
            + " values                   (?,?,?,?,?)  ";

    private static final String SQL_DELETE_LOCATION
            = " DELETE FROM Location where LocationId = ? ";

    private static final String SQL_UPDATE_LOCATION = " UPDATE Location set  "
            + " LocationName=? ,	LocationDescription=? ,	LocationAddress=? ,	Latitude=? ,    Longitude=?  "
            + " where LocationId = ?   ";

    private static final String SQL_SELECT_CHARACTER_LOCATION
            = " SELECT   c.CharacterId,  c.CharacterName,    c.CharacterDescription,  c.SuperPower,   c.IMG, "
            + "    loc.LocationId,    loc.LocationName, loc.LocationDescription, loc.Latitude, "
            + "    loc.Longitude, loc.LocationAddress FROM   characterdetails AS c  JOIN  characterdetailssightinglocation AS csl "
            + " ON csl.CharacterId = c.CharacterId   JOIN  location AS loc ON loc.LocationId = csl.LocationId "
            + "    where c.CharacterId=? ";

    @Override
    public List<Location> getAllLocation() {
        return jdbcTemplate.query(SQL_SELECT_LOCATION, new LocationMapper());
    }

    @Override
    public Location getSingleLocation(long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_SINGLE_LOCATION, new LocationMapper(), id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Location createLocation(Location loc) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                loc.getLocationName(),
                loc.getLocationDescription(),
                loc.getLocationAddress(),
                loc.getLatitude(),
                loc.getLongitude());
        long newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        loc.setLocationId(newId);
        return loc;
    }

    @Override
    public Location updateLocation(Location loc) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                loc.getLocationName(),
                loc.getLocationDescription(),
                loc.getLocationAddress(),
                loc.getLatitude(),
                loc.getLongitude(),
                loc.getLocationId());
        return loc;
    }

    @Override
    public void removeLocation(long id) {
        jdbcTemplate.update(" DELETE FROM CharacterDetailsSightingLocation where LocationId = ? ", id);
        jdbcTemplate.update(SQL_DELETE_LOCATION, id);
    }

    // row mapper
    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location loc = new Location();
            loc.setLocationId(rs.getLong("LocationId"));
            loc.setLocationName(rs.getString("LocationName"));
            loc.setLocationDescription(rs.getString("LocationDescription"));
            loc.setLatitude(rs.getBigDecimal("Latitude"));
            loc.setLongitude(rs.getBigDecimal("Longitude"));
            loc.setLocationAddress(rs.getString("LocationAddress"));
            return loc;
        }

    }

    // helper methods 
    @Override
    public void findCharacterLocations(List<CharacterDetails> c) {
        c.forEach((newC) -> {
            newC.setSightingLocations(jdbcTemplate.query(SQL_SELECT_CHARACTER_LOCATION, new LocationMapper(), newC.getCharacterId()));
        });
    }
    private static final String SQL_SELECT_CHARACTER_LOCATION_BY_DATE_TIME
            = " SELECT   c.CharacterId,  c.CharacterName,    c.CharacterDescription,  c.SuperPower,   c.IMG, "
            + "    loc.LocationId,    loc.LocationName, loc.LocationDescription, loc.Latitude, "
            + "    loc.Longitude, loc.LocationAddress FROM   characterdetails AS c  JOIN  characterdetailssightinglocation AS csl "
            + " ON csl.CharacterId = c.CharacterId   JOIN  location AS loc ON loc.LocationId = csl.LocationId "
            + "    where c.CharacterId=? and csl.SightingTimeStamp = ? and csl.SightingTime=?";

    @Override
    public void findCharacterLocationsByDateAndTime(List<CharacterDetails> c, String sightingDate, String SightingTime) {
        c.forEach((CharacterDetails newC) -> {
            newC.setSightingLocations(jdbcTemplate.query(
                    SQL_SELECT_CHARACTER_LOCATION_BY_DATE_TIME,
                    new LocationMapper(),
                    newC.getCharacterId(),
                    sightingDate,
                    SightingTime
            ));

        });
    }
}

 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.CharacterLocationBridgeTable;
import model.CharacterDetails;
import model.Location;
import model.Organization;
import model.RecentSightings;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Farhan
 */
public class CharacterDaoImpl implements CharacterDao {

    // jdbc setter injection
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
 
    // prepared statements starts here
    private static final String SQL_SELECT_ALL_CHARACTERS
            = " SELECT * FROM characterdetails ";
    private static final String SQL_SELECT_RECENT_SIGHTINGS
            = "select csl.SightingId ,c.CharacterId,c.CharacterName, "
            + " c.CharacterType,loc.LocationId,loc.LocationName,loc.Latitude,loc.Longitude,csl.SightingTimeStamp, csl.SightingTime "
            + "from CharacterDetailsSightingLocation AS csl "
            + "join characterdetails as c on csl.CharacterId = c.CharacterId "
            + "join location AS loc on csl.LocationId = loc.LocationId "
            + "order by csl.SightingTimeStamp  desc "
            + "limit 10; ";
    private static final String SQL_SELECT_SINGLE_CHARACTERS
            = "SELECT * FROM characterdetails "
            + " WHERE CharacterId= ? ";

    private static final String SQL_INSERT_CHARACTER
            = "INSERT INTO CharacterDetails (CharacterName, CharacterDescription, CharacterType, SuperPower , IMG ) "
            + " VALUES (?, ?, ?, ?, ?  )";
    private static final String SQL_DELETE_CHARACTER
            = " DELETE FROM CharacterDetails where  CharacterId =? ";

    private static final String SQL_UPDATE_CHARACTER
            = " UPDATE  CharacterDetails "
            + "SET CharacterName = ? , CharacterDescription = ?, "
            + " CharacterType = ?, SuperPower = ?  , IMG = ? "
            + " where  CharacterId =? ";

    private static final String SQL_INSERT_CHARACTER_LOCATION
            = "INSERT INTO CharacterDetailsSightingLocation ( CharacterId, LocationId, SightingTimeStamp,SightingTime ) "
            + " VALUES (?, ?, ?,? )";

    private static final String SQL_INSERT_CHARACTER_ORGANIZATION
            = "INSERT INTO CharacterDetailsOrganization (CharacterId, OrganizationId ) "
            + " VALUES (?, ?)";
    private static final String SQL_SELECT_CHARACTERS_BY_ORGANIZATION
            = " SELECT c.CharacterId, c.CharacterName,c.CharacterType,c.IMG,c.CharacterDescription,c.SuperPower FROM "
            + "    characterdetails AS C        JOIN    characterdetailsorganization co ON co.CharacterId = c.CharacterId     JOIN "
            + "   organization AS o ON o.OrganizationId  = co.OrganizationId    Where co    .OrganizationId  = ? ";
    private static final String SQL_SELECT_CHARACTERS_BY_LOCATION
            = "select loc.LocationId,c.CharacterId,c.CharacterName,c.CharacterType,c.IMG,c.SuperPower,c.CharacterDescription "
            + " from CharacterDetailsSightingLocation AS csl "
            + " join characterdetails as c on csl.CharacterId = c.CharacterId join location AS loc on csl.LocationId = loc.LocationId where c.CharacterId= ? ";

    @Override
    public List<CharacterDetails> getAllCharacters() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CHARACTERS, new CharacterMapper());
    }

    @Override
    public CharacterDetails getSingleCharacter(long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_SINGLE_CHARACTERS, new CharacterMapper(), id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public CharacterDetails createCharacter(CharacterDetails c) {
        jdbcTemplate.update(SQL_INSERT_CHARACTER,
                c.getCharacterName(),
                c.getCharacterDescription(),
                c.getCharacterType(),
                c.getSuperPower(),
                c.getImg());
        long newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        c.setCharacterId(newId);
        return c;
    }

    @Override
    public void updateCharacter(CharacterDetails c) {
        jdbcTemplate.update(SQL_UPDATE_CHARACTER,
                c.getCharacterName(),
                c.getCharacterDescription(),
                c.getCharacterType(),
                c.getSuperPower(),
                c.getImg(),
                c.getCharacterId()
        );
    }

    @Override
    public void removeCharacter(long id) {

        jdbcTemplate.update(" DELETE FROM CharacterDetailsSightingLocation where  CharacterId =? ", id);
        jdbcTemplate.update(" DELETE FROM CharacterDetailsOrganization where  CharacterId =? ", id);
        jdbcTemplate.update(SQL_DELETE_CHARACTER, id);
    }

    @Override
    public void insertCharacterOrganization(CharacterLocationBridgeTable bt) {
        jdbcTemplate.update(SQL_INSERT_CHARACTER_ORGANIZATION,
                bt.getCharacterId(),
                bt.getOrgId());
    }

    @Override
    public void insertCharacterSighting(CharacterLocationBridgeTable bt) {
        jdbcTemplate.update(SQL_INSERT_CHARACTER_LOCATION,
                bt.getCharacterId(),
                bt.getLocationId(),
                bt.getSightingDate().toString(),
                bt.getSightingTime()
        );

    }

    @Override
    public List<RecentSightings> getRecentSightings() {
        return jdbcTemplate.query(SQL_SELECT_RECENT_SIGHTINGS, new RecentSightingsMapper());
    }

    @Override
    public void removeLocationFromCharacter(long characterId, long locationId) {
        jdbcTemplate.update("delete from CharacterDetailsSightingLocation where CharacterId=? AND LocationId=? ", characterId, locationId);
    }

    @Override
    public void removeOrgFromCharacter(long characterId, long orgId) {
        jdbcTemplate.update("delete from CharacterDetailsOrganization where CharacterId=? AND OrganizationId=? ", characterId, orgId);
    }

    @Override
    public List<CharacterDetails> search(Map<SearchTerm, String> criteriaMap) {
        if (criteriaMap.isEmpty()) {
            return getAllCharacters();
        } else {

            StringBuilder sQuery = new StringBuilder(" SELECT   characterdetails.CharacterId, "
                    + " characterdetails.CharacterName, "
                    + " characterdetails.CharacterType, "
                    + " characterdetails.IMG, "
                    + " characterdetails.CharacterDescription, "
                    + " characterdetails.SuperPower"
                    + " FROM characterdetails ");

            if (criteriaMap.get(SearchTerm.OrganizationName) != null) {
                sQuery.append(" JOIN characterdetailsorganization "
                        + " ON characterdetailsorganization.CharacterId = characterdetails.CharacterId "
                        + " JOIN organization "
                        + " ON organization.OrganizationId = characterdetailsorganization.OrganizationId ");
            }
            if ((criteriaMap.get(SearchTerm.SightingTimeStamp) != null || criteriaMap.get(SearchTerm.LocationName) != null)
                    || (criteriaMap.get(SearchTerm.SightingTime) != null || criteriaMap.get(SearchTerm.SightingTime) != null)) {
                sQuery.append(" JOIN characterdetailssightinglocation "
                        + " ON characterdetailssightinglocation.CharacterId = characterdetails.CharacterId "
                        + " JOIN location "
                        + " ON characterdetailssightinglocation.LocationId = location.LocationId ");
            }
            sQuery.append(" where ");

            int numberOfParameters = criteriaMap.size();
            int paramPosition = 0;
            String[] parameterValues = new String[numberOfParameters];
            Set<SearchTerm> keySet = criteriaMap.keySet();
            Iterator<SearchTerm> iter = keySet.iterator();
            while (iter.hasNext()) {
                SearchTerm currentKey = iter.next();
                if (paramPosition > 0) {
                    sQuery.append(" and ");
                }
                sQuery.append(currentKey);
                sQuery.append(" = ? ");
                parameterValues[paramPosition] = criteriaMap.get(currentKey);
                paramPosition++;
            }
            return jdbcTemplate.query(sQuery.toString(),
                    new CharacterMapper(),
                    parameterValues);
        }
    }

    private static final class CharacterMapper implements RowMapper<CharacterDetails> {

        @Override
        public CharacterDetails mapRow(ResultSet rs, int i) throws SQLException {
            CharacterDetails character = new CharacterDetails();
            character.setCharacterId(rs.getLong("CharacterId"));
            character.setCharacterName(rs.getString("CharacterName"));
            character.setCharacterType(rs.getString("CharacterType"));
            character.setImg("IMG");
            character.setCharacterDescription(rs.getString("CharacterDescription"));
            character.setSuperPower(rs.getString("SuperPower"));
            return character;
        }

    }

    private static final class RecentSightingsMapper implements RowMapper<RecentSightings> {

        @Override
        public RecentSightings mapRow(ResultSet rs, int i) throws SQLException {
            RecentSightings sightings = new RecentSightings();
            sightings.setCharacterId(rs.getLong("CharacterId"));
            sightings.setCharacterName(rs.getString("CharacterName"));
            sightings.setCharacterType(rs.getString("CharacterType"));
            sightings.setLatitude(rs.getBigDecimal("Latitude"));
            sightings.setLocationId(rs.getLong("LocationId"));
            sightings.setLocationName(rs.getString("LocationName"));
            sightings.setLongitude(rs.getBigDecimal("Longitude"));
            sightings.setSightingId(rs.getLong("SightingId"));
            sightings.setSightingDate(rs.getTimestamp("SightingTimeStamp").toLocalDateTime().toLocalDate());
            sightings.setSightingTime(rs.getString("SightingTime"));
            return sightings;
        }
    }

    // helper method 
    @Override
    public void findOrganizationCharacter(List<Organization> org) {
        org.forEach((newOrg) -> {
            newOrg.setCharacterDetails(jdbcTemplate.query(SQL_SELECT_CHARACTERS_BY_ORGANIZATION, new CharacterMapper(), newOrg.getOrgId()));
        });
    }

    @Override
    public void findLocationCharacter(List<Location> loc) {
        loc.forEach((newLoc) -> {
            newLoc.setCharacterDetails(jdbcTemplate.query(SQL_SELECT_CHARACTERS_BY_LOCATION, new CharacterMapper(), newLoc.getLocationId()));
        });
    }
}

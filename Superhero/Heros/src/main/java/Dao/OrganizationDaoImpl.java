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
import model.Organization;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Farhan
 */
public class OrganizationDaoImpl implements OrganizationDao {

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_ORGANIZATION
            = " SELECT * FROM Organization  ";

    private static final String SQL_SELECT_SINGLE_ORGANIZATION
            = " SELECT * FROM Organization  WHERE OrganizationId = ? ";
 
    private static final String SQL_REMOVE_ORGANIZATION
            = " DELETE FROM Organization  WHERE OrganizationId = ? ";

    private static final String SQL_REMOVE_ORGANIZATION_CHARACTER_RELATIONSHIP
            = " DELETE FROM CharacterDetailsOrganization  WHERE OrganizationId = ? ";

    private static final String SQL_INSERT_ORGANIZATION
            = " INSERT into Organization (OrganizationName, OrganizationDescription, OrganizationAddress, Email, Phone) "
            + " values (?,?,?,?,?) ";

    private static final String SQL_SELECT_CHARACTER_ORGANIZATION
            = " SELECT c.CharacterId,c.CharacterName,c.CharacterDescription,c.SuperPower,c.IMG, o.OrganizationId,o.OrganizationName,o.OrganizationDescription, "
            + " o.OrganizationAddress , o.Email,o.Phone FROM characterdetails AS c JOIN characterdetailsorganization AS co on co.CharacterId  = c.CharacterId "
            + " join organization as o on o.OrganizationId  = co.OrganizationId    WHERE c.CharacterId  = ? ";

    private static final String SQL_UPDATE_ORGANIZATION
            = "update Organization set OrganizationName= ? , "
            + "OrganizationDescription= ?, OrganizationAddress = ? , Email=?, Phone=?   where OrganizationId = ? ";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return jdbcTemplate.query(SQL_SELECT_ORGANIZATION, new OrganizationMapper());
    }

    @Override
    public Organization getSingleOrganization(long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_SINGLE_ORGANIZATION, new OrganizationMapper(), id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Organization createOrganization(Organization org) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                org.getOrgName(),
                org.getOrgDescription(),
                org.getOrgAddress(),
                org.getEmail(),
                org.getPhone()
        );
        long newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        org.setOrgId(newId);
        return org;
    }

    @Override
    public Organization updateOrganization(Organization org) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                org.getOrgName(),
                org.getOrgDescription(),
                org.getOrgAddress(),
                org.getEmail(),
                org.getPhone(),
                org.getOrgId()
        );
        return org;
    }

    @Override
    public void removeOrganization(long id) {
        jdbcTemplate.update(SQL_REMOVE_ORGANIZATION_CHARACTER_RELATIONSHIP, id);
        jdbcTemplate.update(SQL_REMOVE_ORGANIZATION, id);
    }

    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setOrgId(rs.getLong("OrganizationId"));
            org.setOrgName(rs.getString("OrganizationName"));
            org.setOrgDescription(rs.getString("OrganizationDescription"));
            org.setOrgAddress(rs.getString("OrganizationAddress"));
            org.setEmail(rs.getString("Email"));
            org.setPhone(rs.getString("Phone"));
            return org;

        }

    }

    @Override
    public List<CharacterDetails> findCharacterOrganization(List<CharacterDetails> c) {

        c.forEach((newC) -> {
            newC.setCharacterOrganization(jdbcTemplate.query(SQL_SELECT_CHARACTER_ORGANIZATION, new OrganizationMapper(), newC.getCharacterId()));
        });
        return c;
    }

}

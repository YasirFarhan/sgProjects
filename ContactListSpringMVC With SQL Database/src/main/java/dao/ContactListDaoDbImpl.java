/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.Contact;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Farhan
 */
public class ContactListDaoDbImpl implements ContactListDao {

    // jdbc setter injection
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

// prepared statements Starts here    
    private static final String SQL_INSERT_CONTACT
            = "insert into contacts"
            + "(first_name, last_name, company, phone, email)"
            + "values(?,?,?,?,?)";

    private static final String SQL_DELETE_CONTACT
            = "delete from contacts where contact_id=?";
    private static final String SQL_SELECT_CONTACT
            = "select * from contacts where contact_id=?";
    private static final String SQL_UPDATE_CONTACT
            = "update contacts set "
            + "first_name=?, last_name=?, company=?, "
            + "phone=?,email=? "
            + "where contact_id=?";
    public static final String SQL_SELECT_ALL_CONTACTS
            = "select * from contacts";

    private static final String SQL_SELECT_CONTACTS_BY_LAST_NAME
            = "select * from contacts where last name =?";
    private static final String SQL_SELECT_CONTACTS_BY_COMPANY
            = "select * from contacts where company = ?";

// prepared statements ends here    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    // Trsaction annotation and Propagation is used 
    //because this method is making two seperate calls to the data base
    public Contact addContact(Contact contact) {
        jdbcTemplate.update(SQL_INSERT_CONTACT,
                contact.getFirstName(),
                contact.getLastName(),
                contact.getCompany(),
                contact.getPhone(),
                contact.getEmail());
        // query the data base to return the newly assigned id
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        contact.setContactId(newId);
        return contact;
    }

    //We don't need to use the @Transactional annotation here 
    //because we are executing only one query against the database.
    @Override
    public void removeContact(long contactId) {
        jdbcTemplate.update(SQL_DELETE_CONTACT, contactId);
    }

    @Override
    public void updateContact(Contact contact) {
        jdbcTemplate.update(SQL_UPDATE_CONTACT,
                contact.getFirstName(),
                contact.getLastName(),
                contact.getCompany(),
                contact.getPhone(),
                contact.getEmail(),
                contact.getContactId());
    }

    @Override
    public List<Contact> getAllContacts() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CONTACTS,
                new ContactMapper());
    }

    @Override
    public Contact getContactById(long contactId) {
        try {

            return jdbcTemplate.queryForObject(SQL_SELECT_CONTACT,
                    new ContactMapper(), contactId);

        } catch (EmptyResultDataAccessException ex) {
//        if there were no result we want to return null
            return null;
        }
    }

    @Override
    public List<Contact> searchContacts(Map<SearchTerm, String> criteria) {

        if (criteria.isEmpty()) {
            return getAllContacts();
        } else {
            // build a prepared statement based on the user's search
            // terms
            StringBuilder sQuery
                    = new StringBuilder("select * from contacts where ");
            // build the where clause
            int numParams = criteria.size();
            int paramPosition = 0;
            // we'll put the positional parameters into an array, the 
            // order of the parameters will match the order in which we 
            // get the search criteria from the map
            String[] paramVals = new String[numParams];
            Set<SearchTerm> keySet = criteria.keySet();
            Iterator<SearchTerm> iter = keySet.iterator();
            // build up the where clause based on the key/value pairs in 
            // the map build where clause and positional parameter array
            while (iter.hasNext()) {
                SearchTerm currentKey = iter.next();
                // if we are not the first one in, we must add an AND to 
                // the where clause
                if (paramPosition > 0) {
                    sQuery.append(" and ");
                }
                // now append our criteria name
                sQuery.append(currentKey);
                sQuery.append(" = ? ");
                // grab the value for this search criteria and put it into 
                // the paramVals array
                paramVals[paramPosition] = criteria.get(currentKey);
                paramPosition++;
            }

            return jdbcTemplate.query(sQuery.toString(),
                    new ContactMapper(),
                    paramVals);
        }

    }

// RowMapper for jdbc      -- nested class
    private static final class ContactMapper implements RowMapper<Contact> {

        @Override
        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
            Contact contact = new Contact();
            contact.setContactId(rs.getLong("contact_id"));
            contact.setFirstName(rs.getString("first_name"));
            contact.setLastName(rs.getString("last_name"));
            contact.setCompany(rs.getString("company"));
            contact.setPhone(rs.getString("phone"));
            contact.setEmail(rs.getString("email"));
            return contact;
        }
    }

}

   
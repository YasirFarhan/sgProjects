 package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Actors;
import model.Director;
import model.DvdLibrary;
import model.Rating;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Farhan
 */
public class DvdDaoDbImpl implements DvdDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

// prepared statement starts here
    private static final String SQL_INSERT_ACTOR
            = "INSERT INTO Actor (FirstName,LastName,BirthDate"
            + "values (?,?,?)";
    private static final String SQL_DELETE_ACTOR
            = "delete from Actor where ActorID=?";

    private static final String SQL_INSERT_MOVIE
            = "INSERT INTO Movie (GenreID, DirectorID, RatingID, Title, ReleaseDate ) "
            + "values (?,?,?,?,?)";

    private static final String SQL_DELETE_MOVIE
            = "delete from movie where MovieId = ? ";

    private static final String SQL_DELETE_MOVIE_ACTOR_RELATIONSHIP
            = "delete from CastMember where movieId=?  ";

    private static final String SQL_INSERT_DIRECTOR
            = "INSERT INTO director(FirstName,LastName,BirthDate)"
            + "value(?,?,?)";

    private static final String SQL_SELECT_ALL_MOVIES
            = "SELECT * FROM Movie ";

    private static final String SQL_SELECT_DVD
            = " select * from Movie where MovieID=? ";

    private static final String SQL_SELECT_DIRECTOR_BY_DVD
            = "select Director.DirectorId, Director.FirstName, Director.LastName, Director.BirthDate "
            + " from Director "
            + " join Movie on Movie.DirectorId = Director.DirectorId "
            + " where Movie.MovieId= ? ";
    private static final String SQL_SELECT_RATING_BY_DVD
            = " select Rating.RatingID, Rating.RatingName "
            + " from Rating join Movie on Movie.RatingID = Rating.RatingID "
            + " where Movie.MovieId=? ";
    private static final String SQL_FIND_RATING = " select * from Rating where Rating.RatingName= ? ";

    private static final String SQL_FIND_DIRECTOR = " select * from Director where FirstName = ? ";

    private static final String SQL_SELECT_ACTOR_BY_DVD
            = " select Actor.ActorId, Actor.FirstName, Actor.LastName, Actor.BirthDate,castmember.ActorID "
            + "from Movie "
            + "join castmember on Movie.MovieId = castmember.MovieID "
            + "join Actor on castmember.ActorID = actor.ActorID "
            + "where Movie.MovieId= ? ";

    private static final String SQL_UPDATE_DVD
            = "update Movie set DirectorID=?, Title=?, ReleaseDate =? "
            + "  where MovieId=? ";

// prepared statement ends here    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public DvdLibrary addDvd(DvdLibrary information) {
        Rating r = jdbcTemplate.queryForObject(SQL_FIND_RATING, new RatingMapper(), information.getRating().getRating());
        Director d = jdbcTemplate.queryForObject(SQL_FIND_DIRECTOR, new DirectorMapper(), information.getDirector().getFirstName());
        jdbcTemplate.update(SQL_INSERT_MOVIE,
                r.getRatingId(),
                d.getDirectorID(),
                r.getRatingId(),
                information.getTitle(),
                information.getReleaseDate().toString()
        );
        return information;
    }

    @Override
    public List<DvdLibrary> getAllDvds() {
        return jdbcTemplate.query(SQL_SELECT_ALL_MOVIES, new DvdMapper());
    }

    @Override
    public DvdLibrary getDvdInfo(Long id) {
        DvdLibrary dvd = jdbcTemplate.queryForObject(SQL_SELECT_DVD, new DvdMapper(), id);
        dvd.setDirector(findDirector(dvd));
        dvd.setRating(findRating(dvd));
        dvd.setActors(findActors(dvd));
        return dvd;
    } 

    @Override
    public void removeDvd(Long id) {
        jdbcTemplate.update(SQL_DELETE_MOVIE_ACTOR_RELATIONSHIP, id);
        jdbcTemplate.update(SQL_DELETE_MOVIE, id);
    }

    @Override
    public DvdLibrary editDvd(DvdLibrary information) {
        DvdLibrary dvd = jdbcTemplate.queryForObject(SQL_SELECT_DVD, new DvdMapper(), information.getId());
        information.setReleaseDate(dvd.getReleaseDate());
        Director d = jdbcTemplate.queryForObject(SQL_FIND_DIRECTOR, new DirectorMapper(), information.getDirector().getFirstName());

        jdbcTemplate.update(SQL_UPDATE_DVD,
                d.getDirectorID(),
                information.getTitle(),
                information.getReleaseDate().toString(),
                information.getId()
        );
        return information;
    }

    @Override
    public List<DvdLibrary> getDvdByAge(int ageInYears) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DvdLibrary> getDvdByMPAARating(String MPAARating) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static final class ActorsMapper implements RowMapper<Actors> {

        @Override
        public Actors mapRow(ResultSet rs, int i) throws SQLException {
            Actors ac = new Actors();

            ac.setFirstName(rs.getString("FirstName"));
            ac.setLastName(rs.getString("LastName"));
            ac.setBirthDate(rs.getTimestamp("BirthDate").toLocalDateTime().toLocalDate());
            ac.setActorId(rs.getInt("ActorID"));
            return ac;
        }

    }

    private final class DvdMapper implements RowMapper<DvdLibrary> {

        @Override
        public DvdLibrary mapRow(ResultSet rs, int i) throws SQLException {
            DvdLibrary dvd = new DvdLibrary();

            dvd.setTitle(rs.getString("Title"));
            dvd.setId(rs.getLong("MovieID"));
            dvd.setRating(findRating(dvd));
            dvd.setDirector(findDirector(dvd));
            dvd.setActors(findActors(dvd));
            if (rs.getTimestamp("ReleaseDate") == null) {
                dvd.setReleaseDate(null);
            } else {

                dvd.setReleaseDate(rs.getTimestamp("ReleaseDate").toLocalDateTime().toLocalDate());
            }

            return dvd;
        }

    }

    private static final class RatingMapper implements RowMapper<Rating> {

        @Override
        public Rating mapRow(ResultSet rs, int i) throws SQLException {
            Rating rating = new Rating();
            rating.setRatingId(rs.getLong("RatingID"));
            rating.setRating(rs.getString("RatingName"));
            return rating;
        }
    }

    private static final class DirectorMapper implements RowMapper<Director> {

        @Override
        public Director mapRow(ResultSet rs, int i) throws SQLException {
            Director director = new Director();
            director.setDirectorID(rs.getLong("DirectorID"));
            director.setFirstName(rs.getString("FirstName"));
            director.setLastName(rs.getString("LastName"));
            director.setBirthDate(rs.getTimestamp("BirthDate").toLocalDateTime().toLocalDate());
            return director;
        }

    }

// helper methods 
    private Director findDirector(DvdLibrary dvd) {
        return jdbcTemplate.queryForObject(SQL_SELECT_DIRECTOR_BY_DVD, new DirectorMapper(), dvd.getId());
    }

    private Rating findRating(DvdLibrary dvd) {
        return jdbcTemplate.queryForObject(SQL_SELECT_RATING_BY_DVD, new RatingMapper(), dvd.getId());
    }

    private List<Actors> findActors(DvdLibrary dvd) {
        return jdbcTemplate.query(SQL_SELECT_ACTOR_BY_DVD, new ActorsMapper(), dvd.getId());
    }

}

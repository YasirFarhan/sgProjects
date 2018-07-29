DROP DATABASE dvd_library;
CREATE DATABASE IF NOT EXISTS Dvd_Library;
USE Dvd_Library;

CREATE TABLE IF NOT EXISTS `Genre` (
    `GenreID` INT(11) NOT NULL AUTO_INCREMENT,
    `GenreName` VARCHAR(30) NOT NULL,
    PRIMARY KEY (`GenreID`)
);



CREATE TABLE IF NOT EXISTS `Rating` (
    `RatingID` INT(11) NOT NULL AUTO_INCREMENT,
    `RatingName` VARCHAR(5) NOT NULL,
    PRIMARY KEY (`RatingID`)
);


CREATE TABLE IF NOT EXISTS `Director` (
    `DirectorID` INT(11) NOT NULL AUTO_INCREMENT,
    `FirstName` VARCHAR(30),
    `LastName` VARCHAR(30),
    `BirthDate` DATE NULL,
    PRIMARY KEY (`DirectorID`)
);



CREATE TABLE IF NOT EXISTS `Actor` (
    `ActorID` INT(11) NOT NULL AUTO_INCREMENT,
    `FirstName` VARCHAR(30) NOT NULL,
    `LastName` VARCHAR(30) NOT NULL,
    `BirthDate` DATE NULL,
    PRIMARY KEY (`ActorID`)
);


CREATE TABLE IF NOT EXISTS `Movie` (
    `MovieID` INT(11) NOT NULL AUTO_INCREMENT,
    `GenreID` INT(11) NOT NULL,
    `DirectorID` INT(11) NULL,
    `RatingID` INT(11) NULL,
    `Title` VARCHAR(128) NOT NULL,
    `ReleaseDate` DATE DEFAULT '1900/01/01',
    PRIMARY KEY (`MovieID`)
);


CREATE TABLE IF NOT EXISTS `CastMember` (
    `ActorID` INT(11) NOT NULL,
    `MovieID` INT(11) NOT NULL,
    `Role` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`MovieID` , `ActorID`)
);

ALTER TABLE `Movie`
 ADD CONSTRAINT `fk_MovieGenre` FOREIGN KEY (`GenreID`) REFERENCES `Genre`
 (`GenreID`) ON DELETE NO ACTION;
ALTER TABLE `Movie`
 ADD CONSTRAINT `fk_MovieDirector` FOREIGN KEY (`DirectorID`) REFERENCES `Director`
  (`DirectorID`) ON DELETE NO ACTION;
ALTER TABLE `Movie`
 ADD CONSTRAINT `fk_MovieRating` FOREIGN KEY (`RatingID`) REFERENCES `Rating`
 (`RatingID`) ON DELETE NO ACTION;


ALTER TABLE `CastMember`
 ADD CONSTRAINT `fkCastMemberActor` FOREIGN KEY (`ActorID`) REFERENCES `Actor`
 (`ActorID`) ON DELETE NO ACTION;
 ALTER TABLE `CastMember`
 ADD CONSTRAINT `fkCastMemberMovie` FOREIGN KEY (`MovieID`) REFERENCES `Movie`
 (`MovieID`) ON DELETE NO ACTION;
 

INSERT INTO genre (GenreName) Value("PG-13");
INSERT INTO genre (GenreName) Value("R");
INSERT INTO genre (GenreName) Value("PG");
INSERT INTO genre (GenreName) Value("G");
INSERT INTO genre (GenreName) Value("NC-17");

INSERT INTO rating (RatingName) Value("PG-13");
INSERT INTO rating (RatingName) Value("R");
INSERT INTO rating (RatingName) Value("PG");
INSERT INTO rating (RatingName) Value("G");
INSERT INTO rating (RatingName) Value("NC-17");

INSERT INTO Director (FirstName, LastName,BirthDate) Value("DirectorFirstName1", "DirectorLastName1", "1980/01/01");
INSERT INTO Director (FirstName, LastName,BirthDate) Value("DirectorFirstName2", "DirectorLastName2", "1970/01/01");
INSERT INTO Director (FirstName, LastName,BirthDate) Value("DirectorFirstName3", "DirectorLastName3", "1965/06/06");

INSERT INTO Actor (FirstName, LastName, BirthDate) Value("ActorFirstName1", "ActorLastName1", "1985/03/08");
INSERT INTO Actor (FirstName, LastName, BirthDate) Value("ActorFirstName2", "ActorLastName2", "1985/03/08");
INSERT INTO Actor (FirstName, LastName, BirthDate) Value("ActorFirstName3", "ActorLastName3", "1985/03/08");


INSERT INTO Movie (GenreID, DirectorID, RatingID, Title, ReleaseDate ) Value(1, 1 , 1,"FirstMovie", "2000/03/08");
INSERT INTO Movie (GenreID, DirectorID, RatingID, Title, ReleaseDate) Value(2, 1 , 2,"SecondMovie", "2002/03/08");
INSERT INTO Movie (GenreID, DirectorID, RatingID, Title, ReleaseDate) Value(2, 2 , 1,"3rdMovie", "2005/03/08");
INSERT INTO Movie (GenreID, DirectorID, RatingID, Title, ReleaseDate) Value(1, 1 , 1,"4thMovie", "2010/03/08");
INSERT INTO Movie (GenreID, DirectorID, RatingID, Title, ReleaseDate) Value(1, 1 , 1,"5thMovie", "2222/03/08");

INSERT INTO CastMember (ActorId, MovieID, Role) values( 1,2,"RoleOne");
INSERT INTO CastMember (ActorId,MovieID, Role) values( 2,3,"RoleTwo");
INSERT INTO CastMember (ActorId,MovieID, Role) values( 2,1,"RoleThree");
INSERT INTO CastMember (ActorId, MovieID, Role) values( 1,4,"RoleFour");


USE Dvd_Library;
SELECT 
    *
FROM
    movie;

-- join query
SELECT 
    Movie.MovieID, movie.Title, movie.GenreID, actor.FirstName
FROM
    Movie
        LEFT JOIN
    CastMember ON movie.MovieId = CastMember.MovieId
        LEFT JOIN
    Actor ON actor.ActorID = CastMember.ActorID
GROUP BY Movie.MovieID
;

SELECT 
    Director.FirstName, Director.LastName, Director.BirthDate
FROM
    Director
        JOIN
    Movie ON Movie.DirectorId = Director.DirectorId
WHERE
    Movie.MovieId = 1;


SELECT 
    Rating.RatingID, Rating.RatingName, Movie.MovieId
FROM
    Rating
        JOIN
    Movie ON Movie.RatingID = Rating.RatingID
WHERE
    Movie.MovieId = 2; 

SELECT 
    Actor.ActorId,
    Actor.FirstName,
    Actor.LastName,
    Actor.BirthDate,
    castmember.ActorID
FROM
    Movie
        JOIN
    castmember ON Movie.MovieId = castmember.MovieID
        JOIN
    Actor ON castmember.ActorID = actor.ActorID
WHERE
    Movie.MovieId = 2;

USE Dvd_Library;
SELECT 
    *
FROM
    movie;





DELETE FROM Movie 
WHERE
    movieId = 8;


DROP DATABASE if exists Hero_DataBase;
CREATE DATABASE if not exists Hero_DataBase;
use Hero_DataBase;

CREATE TABLE IF NOT EXISTS CharacterDetails (
    CharacterId INT(11) NOT NULL AUTO_INCREMENT,
    CharacterName VARCHAR(30) NOT NULL,
    CharacterDescription VARCHAR(30) NOT NULL,
    SuperPower VARCHAR(30) NOT NULL,
    IMG BLOB,
    CharacterType ENUM('HERO', 'VILLAIN') NOT NULL,
    PRIMARY KEY (CharacterId)
);
-- bridge table between CharacterDetails and superpower ends here----------------------------------------------------------
CREATE TABLE IF NOT EXISTS Organization (
    OrganizationId INT(11) NOT NULL AUTO_INCREMENT,
    OrganizationName VARCHAR(30) NOT NULL,
    OrganizationDescription VARCHAR(30),
    OrganizationAddress VARCHAR(30),
    Email VARCHAR(30),
    Phone VARCHAR(30),
    PRIMARY KEY (OrganizationId)
);
-- character and organization bridge table starts here --------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS CharacterDetailsOrganization (
    OrganizationId INT(11) NOT NULL,
    CharacterId INT(11) NOT NULL,
    PRIMARY KEY (CharacterId , OrganizationId)
);
ALTER TABLE CharacterDetailsOrganization
 ADD CONSTRAINT fk_CharacterDetailsOrganization_CharacterDetails FOREIGN KEY (CharacterId ) REFERENCES CharacterDetails
(CharacterId ) ON DELETE NO ACTION;

ALTER TABLE CharacterDetailsOrganization
 ADD CONSTRAINT fk_CharacterDetailsOrganization_Organization FOREIGN KEY (OrganizationId ) REFERENCES Organization
(OrganizationId ) ON DELETE NO ACTION;

-- character and organization bridge table ends here -------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS Location (
    LocationId INT(11) NOT NULL AUTO_INCREMENT,
    LocationName VARCHAR(30),
    LocationDescription VARCHAR(30),
    Latitude DECIMAL(9 , 7 ) NOT NULL,
    Longitude DECIMAL(10 , 7 ) NOT NULL,
    LocationAddress VARCHAR(30),
    PRIMARY KEY (LocationId)
);
 
-- bridge table starts here----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS CharacterDetailsSightingLocation (
    SightingId INT(11) NOT NULL AUTO_INCREMENT,
    CharacterId INT(11) NOT NULL,
    LocationId INT(11) NOT NULL,
    SightingTimeStamp DATE NOT NULL,
    SightingTime TIME,
    PRIMARY KEY (SightingId)
);
ALTER TABLE CharacterDetailsSightingLocation 
 ADD CONSTRAINT fk_CharacterDetailsSightingLocation_SightingLocation FOREIGN KEY (LocationId ) REFERENCES Location  
(LocationId ) ON DELETE NO ACTION;
ALTER TABLE CharacterDetailsSightingLocation 
 ADD CONSTRAINT fk_CharacterDetailsSightingLocation_CharacterDetails FOREIGN KEY (CharacterId ) REFERENCES CharacterDetails
(CharacterId ) ON DELETE NO ACTION;

-- bridge table ends here-----------------------------------------------------------------------------------------------------


CREATE TABLE IF NOT EXISTS `users` (
    `user_id` INT(11) NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(20) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `enabled` TINYINT(1) NOT NULL,
    PRIMARY KEY (`user_id`),
    KEY `username` (`username`)
)  ENGINE=INNODB DEFAULT CHARSET=LATIN1 AUTO_INCREMENT=3;
--
-- Dumping data for table `users`
--
INSERT INTO `users` (`user_id`, `username`, `password`, `enabled`) VALUES
(1, 'admin', 'password', 1),
(2, 'user', 'password', 0);
CREATE TABLE IF NOT EXISTS `authorities` (
    `username` VARCHAR(20) NOT NULL,
    `authority` VARCHAR(20) NOT NULL,
    KEY `username` (`username`)
)  ENGINE=INNODB DEFAULT CHARSET=LATIN1;
--
-- Dumping data for table `authorities`
--
INSERT INTO `authorities` (`username`, `authority`) VALUES
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_USER'),
('user', 'ROLE_USER');
--
-- Constraints for table `authorities`
--
ALTER TABLE `authorities`
 ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`);
 

 -- user login information table ends here


-- INSERT DATA---------------------------------------------------------------------------------------------------

INSERT INTO CharacterDetails (CharacterName,CharacterDescription,CharacterType,     SuperPower ,  IMG )
VALUES ('SUPERMAN','HE IS A HERO', 'HERO', 'FLYING' , 'D:\SG DDWA\yasir-farhan-individual-work\AssignmentSuperheroSightings\pictures\superman.jpg');
INSERT INTO CharacterDetails (CharacterName,CharacterDescription,CharacterType ,      SuperPower , IMG )
VALUES ('FLASH','HE IS A HERO', 'HERO','SPEED' , 'D:\SG DDWA\yasir-farhan-individual-work\AssignmentSuperheroSightings\pictures\flash.jpg');
INSERT INTO CharacterDetails (CharacterName,CharacterDescription,CharacterType ,     SuperPower ,  IMG )
VALUES ('BATMAN','HE IS A HERO','HERO','STRENGTH' , 'D:\SG DDWA\yasir-farhan-individual-work\AssignmentSuperheroSightings\pictures\batman.jpg');
INSERT INTO CharacterDetails (CharacterName,CharacterDescription,CharacterType ,     SuperPower ,  IMG ) 
VALUES ('SPIDERMAN','HE IS A HERO','HERO','STAMINA','D:\SG DDWA\yasir-farhan-individual-work\AssignmentSuperheroSightings\pictures\spiderman.jpg');


 -- organization ----------------------------------------------------------------------------------------------------------------------------------------
 
INSERT into Organization (OrganizationName, OrganizationDescription, OrganizationAddress) values ('Air-org','AIR-DEFENCE','Air ADDRESS');
INSERT into Organization (OrganizationName, OrganizationDescription, OrganizationAddress) values ('Land-org','Land-DEFENCE','Land ADDRESS');
INSERT into Organization (OrganizationName, OrganizationDescription, OrganizationAddress) values ('Sea-org','Water-DEFENCE','Sea ADDRESS');

insert into CharacterDetailsOrganization (CharacterId , OrganizationId ) VALUES (1,1);
insert into CharacterDetailsOrganization (CharacterId , OrganizationId ) VALUES (1,2);
insert into CharacterDetailsOrganization (CharacterId , OrganizationId ) VALUES (1,3);
insert into CharacterDetailsOrganization (CharacterId , OrganizationId ) VALUES (2,2);
insert into CharacterDetailsOrganization (CharacterId , OrganizationId ) VALUES (2,3);
insert into CharacterDetailsOrganization (CharacterId , OrganizationId ) VALUES (3,2);
insert into CharacterDetailsOrganization (CharacterId , OrganizationId ) VALUES (4,2);
    
     
    
-- Insert  Location
insert into Location  (LocationName,	LocationDescription,	LocationAddress,	Latitude,    	Longitude)
values 						 ('Losangeles',	'LA-description'	,'LA-ddress',  			'34.052235',	'-118.243683') ;
insert into Location  (LocationName,LocationDescription,		LocationAddress,			Latitude,    	Longitude)
values 						 ('Chicago',	'Chicago-description'	,'Chicago-ddress',  		'41.881832',		'-87.623177') ;
insert into Location  (LocationName,LocationDescription,			LocationAddress,			Latitude,    Longitude)
values 						 ('California',	'California-description'	,'California-ddress',  '36.778259',	'-119.417931') ;

-- insert into sightingLocation and character bridge table
INSERT INTO CharacterDetailsSightingLocation (CharacterId, LocationId, SightingTimeStamp,SightingTime) VALUES  (1,1,'2015/01/01' ,'20:30:00');
INSERT INTO CharacterDetailsSightingLocation (CharacterId, LocationId, SightingTimeStamp,SightingTime) VALUES  (2,3,'2018/01/01','20:15:00' );
INSERT INTO CharacterDetailsSightingLocation (CharacterId, LocationId, SightingTimeStamp,SightingTime) VALUES  (1,2,'2018/01/01' ,'21:15:00');
INSERT INTO CharacterDetailsSightingLocation (CharacterId, LocationId, SightingTimeStamp,SightingTime) VALUES  (3,1,'2017/01/02' ,'23:15:00');
INSERT INTO CharacterDetailsSightingLocation (CharacterId, LocationId, SightingTimeStamp,SightingTime) VALUES  (4,3,'2017/01/05' ,'16:15:00');  
INSERT INTO CharacterDetailsSightingLocation (CharacterId, LocationId, SightingTimeStamp,SightingTime) VALUES  (4,2,'2018/06/03' ,'08:15:00');

-- INSERT DATA ENDS HERE-----------------------------------------------------------

use Hero_DataBase;
SELECT 
    csl.SightingId,
    c.CharacterId,
    c.CharacterName,
    c.CharacterType,
    loc.LocationId,
    loc.LocationName,
    loc.Latitude,
    loc.Longitude,
    csl.SightingTimeStamp
FROM
    CharacterDetailsSightingLocation AS csl
        JOIN
    characterdetails AS c ON csl.CharacterId = c.CharacterId
        JOIN
    location AS loc ON csl.LocationId = loc.LocationId
ORDER BY csl.SightingTimeStamp DESC
LIMIT 10; 

use Hero_DataBase;
SELECT 
    loc.LocationId,
    c.CharacterId,
    c.CharacterName,
    c.CharacterType,
    c.IMG,
    c.SuperPower,
    c.CharacterDescription
FROM
    CharacterDetailsSightingLocation AS csl
        JOIN
    characterdetails AS c ON csl.CharacterId = c.CharacterId
        JOIN
    location AS loc ON csl.LocationId = loc.LocationId
WHERE
    c.CharacterId = 1;

-- one -----------------------------
use Hero_DataBase;
SELECT 
    characterdetails.CharacterId,
    characterdetails.CharacterDescription,
    characterdetails.CharacterName,
    characterdetails.CharacterType,
    characterdetails.IMG,
    characterdetails.SuperPower,
    organization.OrganizationName
FROM
    characterdetails
        JOIN
    characterdetailsorganization ON characterdetailsorganization.CharacterId = characterdetails.CharacterId
        JOIN
    organization ON organization.OrganizationId = characterdetailsorganization.CharacterId
        JOIN
    characterdetailssightinglocation ON characterdetailssightinglocation.CharacterId = characterdetails.CharacterId
        JOIN
    location ON characterdetailssightinglocation.LocationId = location.LocationId
WHERE
    organization.OrganizationName = 'Sea-org'
        AND location.LocationName = 'Chicago';

-- 2nd --------------------------------------------------------------------------

use Hero_DataBase;
SELECT 
    characterdetails.CharacterId,
    characterdetails.CharacterDescription,
    characterdetails.CharacterName,
    characterdetails.CharacterType,
    characterdetails.IMG,
    characterdetails.SuperPower,
    organization.OrganizationName
FROM
    characterdetails
WHERE
    CharacterName = 'Sea-org';
    
    
    use Hero_DataBase;
SELECT 
    *
FROM
    organization;
    
SELECT 
    characterdetails.CharacterId,
    characterdetails.CharacterName,
    characterdetails.CharacterType,
    characterdetails.IMG,
    characterdetails.CharacterDescription,
    characterdetails.SuperPower
FROM
    characterdetails
        JOIN
    characterdetailsorganization ON characterdetailsorganization.CharacterId = characterdetails.CharacterId
        JOIN
    organization ON organization.OrganizationId = characterdetailsorganization.OrganizationId
WHERE
    OrganizationName = 'Air-org'
        AND SuperPower = 'FLYING';
DROP DATABASE if exists Hero_DataBase_Test;
CREATE DATABASE if not exists Hero_DataBase_Test;
use Hero_DataBase_Test;
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
    -- CharacterDetailsOrganizationId INT(11) NOT NULL AUTO_INCREMENT,
    OrganizationId INT(11) not null,
    CharacterId INT(11) not null,
    -- PRIMARY KEY (CharacterDetailsOrganizationId)
    PRIMARY KEY (CharacterId,OrganizationId)
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
    Latitude DECIMAL(9 , 7 ) NOT NULL ,
    Longitude DECIMAL(10 , 7 ) NOT NULL,
    LocationAddress VARCHAR(30),
    PRIMARY KEY (LocationId)
);

-- bridge table starts here----------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS CharacterDetailsSightingLocation (
    SightingId INT(11) NOT NULL AUTO_INCREMENT,
    CharacterId INT(11) NOT NULL,
    LocationId INT(11) NOT NULL,
    SightingTimeStamp Date NOT NULL,
    SightingTime TIme,
    PRIMARY KEY (SightingId)
);
ALTER TABLE CharacterDetailsSightingLocation 
 ADD CONSTRAINT fk_CharacterDetailsSightingLocation_SightingLocation FOREIGN KEY (LocationId ) REFERENCES Location  
(LocationId ) ON DELETE NO ACTION;
ALTER TABLE CharacterDetailsSightingLocation 
 ADD CONSTRAINT fk_CharacterDetailsSightingLocation_CharacterDetails FOREIGN KEY (CharacterId ) REFERENCES CharacterDetails
(CharacterId ) ON DELETE NO ACTION;

-- bridge table ends here-----------------------------------------------------------------------------------------------------
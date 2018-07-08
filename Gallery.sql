

DROP DATABASE IF EXISTS ArtBase;
CREATE DATABASE ArtBase;

USE ArtBase;

CREATE TABLE ARTIST 
(
	AName varchar(100) NOT NULL,
    Birthplace varchar(100) NOT NULL,
    Age int unsigned NOT NULL,
    Style varchar(100) NOT NULL,
    UNIQUE(AName),
    PRIMARY KEY(AName)
);

CREATE TABLE ARTWORK
(
	Title varchar(100) NOT NULL,
    ArtYear year NOT NULL,
    ArtType varchar(100) NOT NULL,
    Price  decimal(10,2) unsigned NOT NULL,
    AName varchar(100) NOT NULL,
    UNIQUE(Title),
    PRIMARY KEY(Title),
    FOREIGN KEY (AName) REFERENCES ARTIST(AName) ON UPDATE CASCADE
);

CREATE TABLE CUSTOMER
(
	CustID int unsigned NOT NULL AUTO_INCREMENT,
    CName varchar(100) NOT NULL,
    Address varchar(100) NOT NULL,
    Amount decimal(6,2) unsigned NOT NULL,
    UNIQUE(CustID),
    PRIMARY KEY (CustID)
);

CREATE TABLE ARTGROUP
(
	GName varchar(100) NOT NULL,
    UNIQUE(GName),
    PRIMARY KEY(GName)
);

CREATE TABLE CLASSIFY
(
	Title varchar(100) NOT NULL,
    GName varchar(100) NOT NULL,
    PRIMARY KEY (Title, GName),
    FOREIGN KEY (Title) REFERENCES ARTWORK(Title) ON UPDATE CASCADE,
    FOREIGN KEY (GName) REFERENCES ARTGROUP(GName) ON UPDATE CASCADE
);

CREATE TABLE LIKE_GROUP
(
	CustID int unsigned NOT NULL,
    GName varchar(100) NOT NULL,
    PRIMARY KEY (CustID, GName),
    FOREIGN KEY (CustID) REFERENCES CUSTOMER (CustID) ON UPDATE CASCADE,
    FOREIGN KEY (GName) REFERENCES ARTGROUP(GName) ON UPDATE CASCADE
);

CREATE TABLE LIKE_ARTIST
(
	CustID int unsigned NOT NULL,
    AName varchar(100) NOT NULL,
    PRIMARY KEY(CustID, AName),
    FOREIGN KEY(CustID) REFERENCES CUSTOMER(CustID) ON UPDATE CASCADE,
    FOREIGN KEY(AName) REFERENCES ARTIST(AName) ON UPDATE CASCADE
);

INSERT INTO ARTIST 
VALUES ('John Doe', 'China ', 28, 'futuristic art'), ('Tyler', 'Brazil ', 34, 'still lifes');

INSERT INTO ARTWORK
VALUES ('Art One', 2018, 'portrait ', 1000, 'Tyler'), ('Art Two', 2000, 'futuristic', 8020, 'John Doe');

INSERT INTO ARTGROUP
VALUES ('Group One'), ('Group Two');

INSERT INTO CUSTOMER (CName, Address, Amount)
VALUES ('Ellia', '12345 Fancy Blvd ', 0), ('Mark', '54321 Small Blvd ', 100);

INSERT INTO CLASSIFY
VALUES ('Art One', 'Group One'), ('Art Two', 'Group Two');








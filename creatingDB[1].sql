CREATE TABLE Users(
	email 		VARCHAR(40) NOT NULL,
	name		VARCHAR(30),
	phoneNum	INTEGER,
	password	VARCHAR(30),
	isMember	BOOLEAN,
	isAdmin	BOOLEAN,
	PRIMARY KEY (email));
    
CREATE TABLE Addresses (
	street	VARCHAR(30) NOT NULL,
	zip		INTEGER,
	city	VARCHAR(30),
	state	VARCHAR(30),
	PRIMARY KEY(street,zip));
    
CREATE TABLE Media(
	mediaID		INTEGER,
    releaseDate	DATE,
    genre		VARCHAR(30),
    title		VARCHAR(40),
    numCopiesAvaliable	INTEGER,
    PRIMARY KEY(mediaID));

CREATE TABLE Awards(
	awardID		INTEGER,
    aname		VARCHAR(40),
    PRIMARY KEY(awardID));

CREATE TABLE Plans(
	planID			INTEGER,
	cost			INTEGER,
	numMediaAllowed	INTEGER,
	PRIMARY KEY(planID));

CREATE TABLE Rental_Info(
	rentalID 		INTEGER,
	checkoutDate	DATE,
	dueDate			DATE,
	returnedDate	DATE,
	email			VARCHAR(40),		
	mediaID			INTEGER,
	PRIMARY KEY(rentalID),
	FOREIGN KEY(mediaID) REFERENCES Media(mediaID),
    FOREIGN KEY(email) REFERENCES Users(email) );

CREATE TABLE Games(
	gameID		INTEGER,
	version		VARCHAR(20),
	platform	VARCHAR(30),
	PRIMARY KEY(gameID),
	FOREIGN KEY(gameID) REFERENCES Media(mediaID) ON DELETE CASCADE ON UPDATE CASCADE);
    
CREATE TABLE Movies(
	movieID	INTEGER,
	PRIMARY KEY(movieID),
	FOREIGN KEY(movieID) REFERENCES Media(mediaID));

CREATE TABLE Workers(
	workerID	INTEGER,
    wname		VARCHAR(50),
    isActor		BOOLEAN,
    isDirector	BOOLEAN,
    PRIMARY KEY(workerID));

CREATE TABLE Lives_At1(
	email	VARCHAR(40),
    street	VARCHAR(30),
    zip		INTEGER,
    PRIMARY KEY(email,street,zip),
    FOREIGN KEY(email) REFERENCES Users(email),
    FOREIGN KEY(street,zip) REFERENCES Addresses(street,zip)ON DELETE CASCADE ON UPDATE CASCADE);
    
CREATE TABLE Won(
	awardID		INTEGER,
    movieID		INTEGER,
    PRIMARY KEY(awardID,movieID),
    FOREIGN KEY(awardID) REFERENCES Awards(awardID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(movieID) REFERENCES Movies(movieID) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE Sequel(
	prequelID	INTEGER,
    sequelID	INTEGER,
    PRIMARY KEY(prequelID, sequelID),
    FOREIGN KEY(prequelID) REFERENCES Movies(movieID)ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(sequelID) REFERENCES Movies(movieID)ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE Works_On(
	movieID		INTEGER,
    workerID	INTEGER,
    PRIMARY KEY(movieID,workerID),
    FOREIGN KEY(movieID) REFERENCES Movies(movieID)ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(workerID) REFERENCES Workers(workerID)ON DELETE CASCADE ON UPDATE CASCADE);
    
CREATE TABLE Lives_At2(
	workerID	INTEGER,
	street		VARCHAR(30),
    zip			INTEGER,
    FOREIGN KEY(street,zip) REFERENCES Addresses(street,zip)ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE Has_Plan(
	email	VARCHAR(40),
    planID	INTEGER,
    PRIMARY KEY(email,planID),
    FOREIGN KEY(email) REFERENCES Users(email)ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(planID)	REFERENCES Plans(planID));
    
    
    
    



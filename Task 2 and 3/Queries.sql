#create schema for qrapi if not created.
create schema qrapi;

#use qrapi database
use qrapi;

#create Account table
CREATE TABLE `account` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `EmailDomain` varchar(100) DEFAULT NULL,
  `TimeZoneCity` varchar(100) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

#create Contact Table
CREATE TABLE `contact` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `FName` varchar(50) DEFAULT NULL,
  `LName` varchar(50) DEFAULT NULL,
  `EmailID` varchar(50) DEFAULT NULL,
  `Gender` varchar(6) DEFAULT NULL,
  `PhoneNo` varchar(20) DEFAULT NULL,
  `Status` varchar(8) DEFAULT NULL,
  `addressID` int(11) NOT NULL,
  `accountID` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

#create address table
CREATE TABLE `address` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Street` varchar(20) DEFAULT NULL,
  `City` varchar(20) DEFAULT NULL,
  `State` varchar(20) DEFAULT NULL,
  `Country` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

#create alertprofile table
CREATE TABLE `alertprofile` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) DEFAULT NULL,
  `locationID` int(11) NOT NULL,
  `accountID` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

#create location table
CREATE TABLE `location` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `City` varchar(20) DEFAULT NULL,
  `Country` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



#drop schema
drop schema qrapi;

#drop tables
drop table qrapi.account;
drop table qrapi.contact;
drop table qrapi.address;
drop table qrapi.alertprofile;
drop table qrapi.location;

#show all records in Database
SELECT * FROM qrapi.account;
SELECT * FROM qrapi.contact;
SELECT * FROM qrapi.address;
SELECT * FROM qrapi.alertprofile;
SELECT * FROM qrapi.location;

select acc.Id,con.Id,addr.Id,addr.City, addr.Country,loc.city,loc.Country  from account as acc
inner join contact as con
on acc.ID=con.accountID
inner join address as addr
on con.addressID=addr.Id
inner join alertprofile as ap
on acc.Id=ap.Id
inner join location as loc
on ap.locationID=loc.Id
where (addr.City=loc.city or addr.Country=loc.Country) and (acc.Id=2);
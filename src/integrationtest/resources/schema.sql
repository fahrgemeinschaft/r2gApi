
SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT;
SET NAMES utf8;
SET NAMES utf8mb4;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

USE `fgtest`;

DROP TABLE IF EXISTS `events`;
CREATE TABLE IF NOT EXISTS `events` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `startdate` datetime DEFAULT NULL,
  `enddate` datetime DEFAULT NULL,
  `searchdate` date DEFAULT NULL,
  `origin_address` varchar(100) DEFAULT NULL,
  `origin_countrycode` varchar(2) DEFAULT NULL,
  `origin_latitude` double NOT NULL DEFAULT '0',
  `origin_longitude` double NOT NULL,
  `origin_radius` int(11) DEFAULT NULL,
  `destination_address` varchar(100) DEFAULT NULL,
  `destination_countrycode` varchar(2) DEFAULT NULL,
  `destination_latitude` double NOT NULL,
  `destination_longitude` double NOT NULL,
  `destination_radius` int(11) DEFAULT NULL,
  `url_event` varchar(255) DEFAULT NULL,
  `url_banner` varchar(255) DEFAULT NULL,
  `key` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=403 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fg_places`;
CREATE TABLE IF NOT EXISTS `fg_places` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` text NOT NULL,
  `category_popup` varchar(255) NOT NULL,
  `Address` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Latitude` double NOT NULL,
  `Longitude` double NOT NULL,
  `CountryCode` varchar(5) NOT NULL,
  `CountryName` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `show_in_autocomplete` int(11) NOT NULL DEFAULT '1',
  `embed_key` varchar(255) NOT NULL,
  `Search_extension` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `show_in_autocomplete` (`show_in_autocomplete`),
  KEY `category` (`category`),
  FULLTEXT KEY `Address` (`Address`)
) ENGINE=MyISAM AUTO_INCREMENT=10129381 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fg_privacy`;
CREATE TABLE IF NOT EXISTS `fg_privacy` (
  `requestID` varchar(36) NOT NULL,
  `IDuser` varchar(36) NOT NULL,
  `IDrequestor` varchar(36) NOT NULL,
  `area` enum('trip','profile') NOT NULL,
  `areavalue` varchar(255) NOT NULL,
  `requestdate` int(10) unsigned NOT NULL,
  `approvedate` int(10) unsigned DEFAULT NULL,
  `denydate` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`requestID`),
  UNIQUE KEY `IDuser` (`IDuser`,`IDrequestor`,`area`,`areavalue`),
  KEY `requestdate` (`requestdate`),
  KEY `denydate` (`denydate`),
  KEY `approvedate` (`approvedate`),
  KEY `PrivacyOnUserRequestor` (`IDrequestor`),
  CONSTRAINT `PrivacyOnUserOwner` FOREIGN KEY (`IDuser`) REFERENCES `sys_user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `PrivacyOnUserRequestor` FOREIGN KEY (`IDrequestor`) REFERENCES `sys_user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `fg_privacy2`;
CREATE TABLE IF NOT EXISTS `fg_privacy2` (
  `tripID` varchar(36) NOT NULL,
  `name` int(2) unsigned DEFAULT '0' COMMENT '0=Anfrage,1=Sichtbar,4=Nur Mitglieder,5=Versteckt',
  `email` int(2) unsigned DEFAULT '0',
  `landline` int(2) unsigned DEFAULT '0',
  `mobile` int(2) unsigned DEFAULT '0',
  `numberplate` int(2) unsigned DEFAULT '0',
  `car` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`tripID`),
  CONSTRAINT `fg_privacy2_ibfk_1` FOREIGN KEY (`tripID`) REFERENCES `fg_trips` (`tripID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `fg_trips`;
CREATE TABLE IF NOT EXISTS `fg_trips` (
  `tripID` varchar(36) NOT NULL DEFAULT '',
  `IDuser` varchar(36) NOT NULL,
  `startdate` int(11) DEFAULT NULL,
  `starttime` smallint(6) DEFAULT NULL,
  `smoker` enum('yes','no','flex') DEFAULT NULL,
  `prefgender` enum('w','m') DEFAULT NULL,
  `numberplate` varchar(10) DEFAULT NULL,
  `places` tinyint(4) DEFAULT NULL,
  `enterdate` int(11) DEFAULT NULL,
  `contactmail` varchar(100) DEFAULT NULL,
  `contactlandline` varchar(50) DEFAULT NULL,
  `contactmobile` varchar(50) DEFAULT NULL,
  `ip` varchar(15) NOT NULL DEFAULT '',
  `description` text,
  `price` float DEFAULT NULL,
  `currency` char(3) NOT NULL DEFAULT 'EUR',
  `COLUMNcurrency` char(3) NOT NULL DEFAULT 'EUR',
  `triptype` enum('offer','search') NOT NULL,
  `partner` enum('mfz') DEFAULT NULL,
  `deeplink` varchar(500) DEFAULT NULL,
  `relevance` tinyint(1) NOT NULL,
  `missingreoccurs` text NOT NULL,
  `from_title` varchar(200) DEFAULT NULL,
  `from_seotitle` varchar(200) DEFAULT NULL,
  `to_title` varchar(200) DEFAULT NULL,
  `to_seotitle` varchar(200) DEFAULT NULL,
  `int_id` int(11) NOT NULL AUTO_INCREMENT,
  `car` varchar(200) DEFAULT NULL,
  `baggage` enum('small','medium','large','huge') DEFAULT NULL,
  `animals` enum('ask','yes','no') DEFAULT NULL,
  `reminder_send_ids` text NOT NULL,
  `reminder_last_send` int(11) DEFAULT NULL,
  `reminder_active` int(11) DEFAULT '0',
  PRIMARY KEY (`tripID`),
  KEY `triptype` (`triptype`),
  KEY `startdate` (`startdate`),
  KEY `starttime` (`starttime`),
  KEY `TripsOnUser` (`IDuser`),
  KEY `relevance` (`relevance`),
  KEY `partner` (`partner`),
  KEY `int_id` (`int_id`),
  CONSTRAINT `TripsOnUser` FOREIGN KEY (`IDuser`) REFERENCES `sys_user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=521332 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `fg_trips_contact`;
CREATE TABLE IF NOT EXISTS `fg_trips_contact` (
  `tripID` varchar(36) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `mobile` varchar(36) CHARACTER SET utf8 DEFAULT NULL,
  `landline` varchar(36) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`tripID`),
  KEY `mobile` (`mobile`),
  KEY `landline` (`landline`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fg_trips_reoccurs`;
CREATE TABLE IF NOT EXISTS `fg_trips_reoccurs` (
  `IDtrip` varchar(36) NOT NULL DEFAULT '',
  `mo` tinyint(1) DEFAULT NULL,
  `tu` tinyint(1) DEFAULT NULL,
  `we` tinyint(1) DEFAULT NULL,
  `th` tinyint(1) DEFAULT NULL,
  `fr` tinyint(1) DEFAULT NULL,
  `sa` tinyint(1) DEFAULT NULL,
  `su` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IDtrip`),
  KEY `mo` (`mo`),
  KEY `tu` (`tu`),
  KEY `we` (`we`),
  KEY `th` (`th`),
  KEY `fr` (`fr`),
  KEY `sa` (`sa`),
  KEY `su` (`su`),
  CONSTRAINT `ReoccursOnTrips` FOREIGN KEY (`IDtrip`) REFERENCES `fg_trips` (`tripID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `fg_trips_reoccurs2`;
CREATE TABLE IF NOT EXISTS `fg_trips_reoccurs2` (
  `IDtrip` varchar(36) NOT NULL DEFAULT '',
  `mo` tinyint(1) DEFAULT NULL,
  `tu` tinyint(1) DEFAULT NULL,
  `we` tinyint(1) DEFAULT NULL,
  `th` tinyint(1) DEFAULT NULL,
  `fr` tinyint(1) DEFAULT NULL,
  `sa` tinyint(1) DEFAULT NULL,
  `su` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IDtrip`),
  KEY `mo` (`mo`),
  KEY `tu` (`tu`),
  KEY `we` (`we`),
  KEY `th` (`th`),
  KEY `fr` (`fr`),
  KEY `sa` (`sa`),
  KEY `su` (`su`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `fg_trips_reoccurs_save`;
CREATE TABLE IF NOT EXISTS `fg_trips_reoccurs_save` (
  `IDtrip` varchar(36) NOT NULL DEFAULT '',
  `mo` tinyint(1) DEFAULT NULL,
  `tu` tinyint(1) DEFAULT NULL,
  `we` tinyint(1) DEFAULT NULL,
  `th` tinyint(1) DEFAULT NULL,
  `fr` tinyint(1) DEFAULT NULL,
  `sa` tinyint(1) DEFAULT NULL,
  `su` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IDtrip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `fg_trips_routing`;
CREATE TABLE IF NOT EXISTS `fg_trips_routing` (
  `routingID` varchar(36) NOT NULL DEFAULT '',
  `IDorigin` varchar(36) NOT NULL,
  `IDdestination` varchar(36) NOT NULL,
  `IDtrip` varchar(36) DEFAULT NULL,
  `idx` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`routingID`),
  KEY `IDtrip_2` (`IDtrip`,`idx`),
  KEY `IDorigin` (`IDorigin`),
  KEY `IDdestination` (`IDdestination`),
  KEY `IDtrip` (`IDtrip`),
  KEY `idx` (`idx`),
  KEY `IDorigin_2` (`IDorigin`,`IDdestination`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `fg_trips_routing_places`;
CREATE TABLE IF NOT EXISTS `fg_trips_routing_places` (
  `placeID` varchar(36) NOT NULL,
  `IDcreator` varchar(36) NOT NULL,
  `IDtrip` varchar(36) NOT NULL,
  `address` varchar(100) NOT NULL,
  `accuracy` tinyint(3) unsigned NOT NULL,
  `countrycode` varchar(3) NOT NULL,
  `countryname` varchar(45) NOT NULL,
  `lon` double NOT NULL,
  `lat` double NOT NULL,
  `postcode` varchar(6) DEFAULT NULL,
  `placetype` enum('geo') NOT NULL DEFAULT 'geo',
  `boxN` double NOT NULL,
  `boxS` double NOT NULL,
  `boxE` double NOT NULL,
  `boxW` double NOT NULL,
  `stopprice` int(11) DEFAULT NULL,
  `stopcurrency` char(3) NOT NULL DEFAULT 'EUR',
  `stoptime` int(11) DEFAULT NULL,
  PRIMARY KEY (`placeID`),
  KEY `RoutingPlacesOnTrips` (`IDtrip`),
  KEY `RoutingPlacesOnUser` (`IDcreator`),
  KEY `LatLon` (`lon`,`lat`),
  KEY `lat` (`lat`),
  KEY `lon` (`lon`),
  CONSTRAINT `_RoutingPlacesOnTrips` FOREIGN KEY (`IDtrip`) REFERENCES `fg_trips` (`tripID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `_RoutingPlacesOnUser` FOREIGN KEY (`IDcreator`) REFERENCES `sys_user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `fg_trips_routing_places_new`;
CREATE TABLE IF NOT EXISTS `fg_trips_routing_places_new` (
  `placeID` varchar(36) NOT NULL,
  `IDcreator` varchar(36) NOT NULL,
  `IDtrip` varchar(36) NOT NULL,
  `address` varchar(100) NOT NULL,
  `accuracy` tinyint(3) unsigned NOT NULL,
  `countrycode` varchar(3) NOT NULL,
  `countryname` varchar(45) NOT NULL,
  `lon` double NOT NULL,
  `lat` double NOT NULL,
  `postcode` varchar(6) DEFAULT NULL,
  `placetype` enum('geo') NOT NULL DEFAULT 'geo',
  `boxN` double NOT NULL,
  `boxS` double NOT NULL,
  `boxE` double NOT NULL,
  `boxW` double NOT NULL,
  `stopprice` int(11) DEFAULT NULL,
  `stopcurrency` char(3) NOT NULL DEFAULT 'EUR',
  `stoptime` int(4) DEFAULT NULL,
  PRIMARY KEY (`placeID`),
  KEY `RoutingPlacesOnTrips` (`IDtrip`),
  KEY `RoutingPlacesOnUser` (`IDcreator`),
  KEY `LatLon` (`lon`,`lat`),
  KEY `lat` (`lat`),
  KEY `lon` (`lon`),
  CONSTRAINT `fg_trips_routing_places_new_ibfk_1` FOREIGN KEY (`IDcreator`) REFERENCES `sys_user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fg_trips_routing_places_new_ibfk_2` FOREIGN KEY (`IDtrip`) REFERENCES `fg_trips` (`tripID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `fg_trips_routing_places_to_delete`;
CREATE TABLE IF NOT EXISTS `fg_trips_routing_places_to_delete` (
  `placeID` varchar(36) NOT NULL,
  `IDcreator` varchar(36) NOT NULL,
  `IDtrip` varchar(36) NOT NULL,
  `address` varchar(100) NOT NULL,
  `accuracy` tinyint(3) unsigned NOT NULL,
  `countrycode` varchar(3) NOT NULL,
  `countryname` varchar(45) NOT NULL,
  `lon` double NOT NULL,
  `lat` double NOT NULL,
  `postcode` varchar(6) DEFAULT NULL,
  `placetype` enum('geo') NOT NULL DEFAULT 'geo',
  `boxN` double NOT NULL,
  `boxS` double NOT NULL,
  `boxE` double NOT NULL,
  `boxW` double NOT NULL,
  `stopprice` int(11) DEFAULT NULL,
  `stopcurrency` char(3) NOT NULL DEFAULT 'EUR',
  PRIMARY KEY (`placeID`),
  KEY `RoutingPlacesOnTrips` (`IDtrip`),
  KEY `RoutingPlacesOnUser` (`IDcreator`),
  KEY `LatLon` (`lon`,`lat`),
  KEY `lat` (`lat`),
  KEY `lon` (`lon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `mail_bounces_complains`;
CREATE TABLE IF NOT EXISTS `mail_bounces_complains` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `soft_bounce` int(11) NOT NULL,
  `hard_bounce` int(11) NOT NULL,
  `complaint` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `email` (`email`),
  KEY `email_2` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=60272 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `sys_apikeys`;
CREATE TABLE IF NOT EXISTS `sys_apikeys` (
  `apikey` varchar(128) NOT NULL,
  `publickey` varchar(64) NOT NULL,
  `keybase` varchar(100) NOT NULL,
  `client` varchar(45) NOT NULL,
  `trustlevel` enum('0','1','2','3','4') NOT NULL,
  `hide` int(11) DEFAULT NULL,
  PRIMARY KEY (`apikey`,`publickey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_astro`;
CREATE TABLE IF NOT EXISTS `sys_astro` (
  `astroID` int(11) NOT NULL AUTO_INCREMENT,
  `startdate` int(11) NOT NULL DEFAULT '0',
  `enddate` int(11) NOT NULL DEFAULT '0',
  `caption` varchar(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`astroID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_auth`;
CREATE TABLE IF NOT EXISTS `sys_auth` (
  `authKey` varchar(128) NOT NULL,
  `publicKey` varchar(64) NOT NULL,
  `IDuser` varchar(36) NOT NULL,
  `validuntil` datetime NOT NULL,
  `clientip` varchar(15) NOT NULL,
  `createdate` datetime NOT NULL,
  PRIMARY KEY (`authKey`),
  KEY `AuthOnUser` (`IDuser`),
  CONSTRAINT `AuthOnUser` FOREIGN KEY (`IDuser`) REFERENCES `sys_user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE IF NOT EXISTS `sys_captcha` (
  `captchaID` varchar(36) NOT NULL,
  `question` varchar(100) NOT NULL,
  `value` varchar(45) NOT NULL,
  `validuntil` datetime NOT NULL,
  `usedate` datetime DEFAULT NULL,
  `createip` varchar(15) NOT NULL,
  PRIMARY KEY (`captchaID`),
  KEY `createip` (`createip`),
  KEY `validuntil` (`validuntil`),
  KEY `usedate` (`usedate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_geo_cache`;
CREATE TABLE IF NOT EXISTS `sys_geo_cache` (
  `autoID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `address` varchar(100) NOT NULL,
  `requestinput` varchar(100) NOT NULL,
  `accuracy` tinyint(3) unsigned NOT NULL,
  `countrycode` varchar(3) NOT NULL,
  `countryname` varchar(45) NOT NULL,
  `lon` double NOT NULL,
  `lat` double NOT NULL,
  `postcode` varchar(6) DEFAULT NULL,
  `boxN` double NOT NULL,
  `boxS` double NOT NULL,
  `boxE` double NOT NULL,
  `boxW` double NOT NULL,
  PRIMARY KEY (`autoID`),
  KEY `requestinput` (`requestinput`)
) ENGINE=InnoDB AUTO_INCREMENT=509304 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_hobbies`;
CREATE TABLE IF NOT EXISTS `sys_hobbies` (
  `hobbyID` int(11) NOT NULL DEFAULT '0',
  `hobby` varchar(255) NOT NULL DEFAULT '',
  `parentID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`hobbyID`),
  KEY `parentID` (`parentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_imagevalidation`;
CREATE TABLE IF NOT EXISTS `sys_imagevalidation` (
  `wordID` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(15) NOT NULL DEFAULT '',
  `wordlen` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`wordID`)
) ENGINE=InnoDB AUTO_INCREMENT=939 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_invitecodes`;
CREATE TABLE IF NOT EXISTS `sys_invitecodes` (
  `invitecode` varchar(40) CHARACTER SET utf8 NOT NULL,
  `IDuser` varchar(40) CHARACTER SET utf8 NOT NULL,
  `sendtime` int(11) NOT NULL,
  `receiveremail` varchar(100) CHARACTER SET utf8 NOT NULL,
  `receivername` varchar(100) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`invitecode`),
  KEY `IDuser` (`IDuser`),
  CONSTRAINT `sys_invitecodes_ibfk_1` FOREIGN KEY (`IDuser`) REFERENCES `sys_user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `sys_notifications`;
CREATE TABLE IF NOT EXISTS `sys_notifications` (
  `notificationID` varchar(36) NOT NULL,
  `IDendpoint` varchar(36) NOT NULL,
  `headers` text NOT NULL,
  `subject` varchar(255) NOT NULL,
  `message` text NOT NULL,
  `shortmessage` varchar(255) NOT NULL,
  `priority` enum('1','2','3','4','5') NOT NULL DEFAULT '5',
  `senddate` datetime DEFAULT NULL,
  `swiftmailer_failed` int(11) NOT NULL DEFAULT '0',
  `swiftmailer_send` int(11) NOT NULL DEFAULT '0',
  `not_send_as_email_is_bounced` int(11) DEFAULT NULL,
  PRIMARY KEY (`notificationID`),
  KEY `NotificationsOnEndpoints` (`IDendpoint`),
  KEY `senddate` (`senddate`),
  KEY `priority` (`priority`),
  CONSTRAINT `NotificationsOnEndpoints` FOREIGN KEY (`IDendpoint`) REFERENCES `sys_user_endpoints` (`endpointID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `sys_online`;
CREATE TABLE IF NOT EXISTS `sys_online` (
  `sessionID` varchar(32) NOT NULL DEFAULT '',
  `IDuser` mediumint(9) NOT NULL DEFAULT '0',
  `onlineusername` varchar(100) NOT NULL DEFAULT '',
  `lastaction` int(11) NOT NULL DEFAULT '0',
  `akfile` varchar(255) NOT NULL DEFAULT '',
  `onlinegeschlecht` set('w','m','gast') NOT NULL DEFAULT '',
  PRIMARY KEY (`sessionID`),
  UNIQUE KEY `username` (`onlineusername`),
  KEY `IDuser` (`IDuser`),
  KEY `stand` (`lastaction`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_ort_dist`;
CREATE TABLE IF NOT EXISTS `sys_ort_dist` (
  `ort` varchar(40) CHARACTER SET latin1 COLLATE latin1_german2_ci NOT NULL DEFAULT '',
  `plzinradius` text CHARACTER SET latin1 COLLATE latin1_german2_ci NOT NULL,
  `ortinradius` text CHARACTER SET latin1 COLLATE latin1_german2_ci NOT NULL,
  `dist` tinyint(4) NOT NULL DEFAULT '0',
  `seo_ort` varchar(50) CHARACTER SET latin1 COLLATE latin1_german2_ci NOT NULL DEFAULT '',
  `seo_ortinradius` text CHARACTER SET latin1 COLLATE latin1_german2_ci NOT NULL,
  PRIMARY KEY (`ort`,`dist`),
  KEY `seo_ort` (`seo_ort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_photoalbums`;
CREATE TABLE IF NOT EXISTS `sys_photoalbums` (
  `albumID` varchar(36) NOT NULL,
  `name` varchar(150) NOT NULL,
  `IDuser` varchar(36) NOT NULL,
  `pathto` varchar(45) NOT NULL,
  PRIMARY KEY (`albumID`),
  KEY `PhotoAlbumsOnUser` (`IDuser`),
  CONSTRAINT `PhotoAlbumsOnUser` FOREIGN KEY (`IDuser`) REFERENCES `sys_user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_photoalbums_photos`;
CREATE TABLE IF NOT EXISTS `sys_photoalbums_photos` (
  `photoID` varchar(36) NOT NULL,
  `IDalbum` varchar(36) NOT NULL,
  `comment` varchar(255) NOT NULL,
  `pathto` varchar(45) NOT NULL,
  PRIMARY KEY (`photoID`),
  KEY `PhotosOnAlbums` (`IDalbum`),
  CONSTRAINT `PhotosOnAlbums` FOREIGN KEY (`IDalbum`) REFERENCES `sys_photoalbums` (`albumID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_places`;
CREATE TABLE IF NOT EXISTS `sys_places` (
  `placeID` varchar(36) NOT NULL,
  `IDcreator` varchar(36) NOT NULL,
  `address` varchar(100) NOT NULL,
  `accuracy` tinyint(3) unsigned NOT NULL,
  `countrycode` varchar(3) NOT NULL,
  `countryname` varchar(45) NOT NULL,
  `lon` double NOT NULL,
  `lat` double NOT NULL,
  `postcode` varchar(6) NOT NULL,
  `placetype` enum('geo','home','party') NOT NULL,
  `boxN` double NOT NULL,
  `boxS` double NOT NULL,
  `boxE` double NOT NULL,
  `boxW` double NOT NULL,
  `comment` varchar(255) NOT NULL,
  PRIMARY KEY (`placeID`),
  KEY `PlaceOnUsers` (`IDcreator`),
  KEY `placetype` (`placetype`),
  CONSTRAINT `PlacesOnUser` FOREIGN KEY (`IDcreator`) REFERENCES `sys_user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_plz`;
CREATE TABLE IF NOT EXISTS `sys_plz` (
  `stID` varchar(13) COLLATE latin1_german2_ci NOT NULL DEFAULT '',
  `plz` varchar(6) COLLATE latin1_german2_ci DEFAULT NULL,
  `plzint` int(11) NOT NULL DEFAULT '0',
  `ort` varchar(50) COLLATE latin1_german2_ci DEFAULT NULL,
  `seo_ort` varchar(50) COLLATE latin1_german2_ci NOT NULL DEFAULT '',
  `lang` double DEFAULT NULL,
  `breit` double DEFAULT NULL,
  `relevance` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`stID`),
  KEY `lang` (`lang`),
  KEY `breit` (`breit`),
  KEY `plz` (`plz`),
  KEY `relevance` (`relevance`),
  KEY `ort` (`ort`),
  KEY `seo_ort` (`seo_ort`),
  KEY `lang_2` (`lang`,`breit`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_german2_ci;

DROP TABLE IF EXISTS `sys_plz_dist`;
CREATE TABLE IF NOT EXISTS `sys_plz_dist` (
  `plz` varchar(5) CHARACTER SET latin1 COLLATE latin1_german2_ci NOT NULL DEFAULT '',
  `plzinradius` text CHARACTER SET latin1 COLLATE latin1_german2_ci NOT NULL,
  `ortinradius` text CHARACTER SET latin1 COLLATE latin1_german2_ci NOT NULL,
  `dist` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`plz`,`dist`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_pm`;
CREATE TABLE IF NOT EXISTS `sys_pm` (
  `msgID` int(11) NOT NULL AUTO_INCREMENT,
  `IDsender` varchar(36) NOT NULL,
  `IDreceiver` varchar(36) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `message` text NOT NULL,
  `typ` int(11) NOT NULL DEFAULT '0',
  `readfromuser` tinyint(1) NOT NULL DEFAULT '0',
  `forwarded` tinyint(1) NOT NULL DEFAULT '0',
  `replied` tinyint(1) NOT NULL DEFAULT '0',
  `sendtime` int(11) NOT NULL DEFAULT '0',
  `sendobject` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`msgID`),
  KEY `sender` (`IDsender`),
  KEY `receiver` (`IDreceiver`),
  CONSTRAINT `PmOnUserReceiver` FOREIGN KEY (`IDreceiver`) REFERENCES `sys_user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `PmOnUserSender` FOREIGN KEY (`IDsender`) REFERENCES `sys_user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=69120 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `sys_request_log`;
CREATE TABLE IF NOT EXISTS `sys_request_log` (
  `autoID` int(11) NOT NULL AUTO_INCREMENT,
  `clientIP` varchar(15) NOT NULL,
  `url` varchar(100) NOT NULL,
  `requestdate` int(10) unsigned NOT NULL,
  `inputstream` text NOT NULL,
  `apikey` varchar(128) DEFAULT NULL,
  `authkey` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`autoID`),
  KEY `requestdate` (`requestdate`,`url`)
) ENGINE=MyISAM AUTO_INCREMENT=373958 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE IF NOT EXISTS `sys_user` (
  `userID` varchar(36) NOT NULL,
  `olduid` int(11) DEFAULT NULL,
  `gender` enum('f','m','g') NOT NULL,
  `password` varchar(128) NOT NULL,
  `isoldpassword` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `regemail` varchar(100) NOT NULL,
  `regdate` datetime NOT NULL,
  `activatetoken` varchar(40) DEFAULT NULL,
  `activatedate` datetime DEFAULT NULL,
  `cookiekey` varchar(128) NOT NULL,
  `salt` varchar(100) NOT NULL,
  `archivedate` datetime DEFAULT NULL,
  `newpasswordtoken` varchar(40) DEFAULT NULL,
  `lastvisitdate` datetime DEFAULT NULL,
  `IDphotoavatar` varchar(36) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `emailnew` varchar(255) NOT NULL,
  `emailnewtoken` varchar(255) NOT NULL,
  `noads` date DEFAULT NULL,
  `verified` date DEFAULT NULL,
  `banned` date DEFAULT NULL,
  `no_newsletter` int(1) NOT NULL DEFAULT '0',
  `mail_bounced` int(1) NOT NULL DEFAULT '0',
  `ip` varchar(255) NOT NULL,
  `username` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `regemail` (`regemail`),
  UNIQUE KEY `cookiekey` (`cookiekey`),
  UNIQUE KEY `olduid` (`olduid`),
  UNIQUE KEY `usernameUnique` (`username`),
  KEY `regdate` (`regdate`),
  KEY `activatetoken` (`activatetoken`),
  KEY `UserOnPhoto` (`IDphotoavatar`),
  KEY `username` (`username`),
  CONSTRAINT `UserOnPhoto` FOREIGN KEY (`IDphotoavatar`) REFERENCES `sys_photoalbums_photos` (`photoID`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `sys_user_endpoints`;
CREATE TABLE IF NOT EXISTS `sys_user_endpoints` (
  `endpointID` varchar(36) NOT NULL,
  `IDuser` varchar(36) NOT NULL,
  `type` enum('email','mobile','smartphone','landline','skype','icq','numberplate') NOT NULL,
  `value` varchar(150) NOT NULL,
  `isdefault` tinyint(1) NOT NULL DEFAULT '0',
  `verified` int(11) NOT NULL,
  PRIMARY KEY (`endpointID`),
  UNIQUE KEY `IDuser` (`IDuser`,`type`,`value`),
  CONSTRAINT `EndpointsOnUser` FOREIGN KEY (`IDuser`) REFERENCES `sys_user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_user_hobbies`;
CREATE TABLE IF NOT EXISTS `sys_user_hobbies` (
  `IDuser` varchar(36) NOT NULL,
  `IDhobby` int(11) NOT NULL DEFAULT '0',
  `caption` varchar(255) NOT NULL DEFAULT '',
  `IDhobbyparent` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IDuser`,`IDhobby`),
  CONSTRAINT `HobbiesOnUser` FOREIGN KEY (`IDuser`) REFERENCES `sys_user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `sys_user_kvps`;
CREATE TABLE IF NOT EXISTS `sys_user_kvps` (
  `IDuser` varchar(36) NOT NULL,
  `key` varchar(45) NOT NULL,
  `value` varchar(400) NOT NULL,
  `valuetype` enum('string','bool') NOT NULL DEFAULT 'string',
  PRIMARY KEY (`IDuser`,`key`),
  KEY `value` (`value`(255)),
  CONSTRAINT `KvpsOnUser` FOREIGN KEY (`IDuser`) REFERENCES `sys_user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_user_photos`;
CREATE TABLE IF NOT EXISTS `sys_user_photos` (
  `photoID` varchar(36) NOT NULL,
  `IDuser` varchar(36) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `checksum` varchar(40) NOT NULL,
  PRIMARY KEY (`photoID`),
  KEY `PhotosOnUser` (`IDuser`),
  CONSTRAINT `PhotosOnUser` FOREIGN KEY (`IDuser`) REFERENCES `sys_user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sys_user_rating`;
CREATE TABLE IF NOT EXISTS `sys_user_rating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_userID` varchar(36) NOT NULL,
  `to_userID` varchar(36) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `increment` decimal(5,2) DEFAULT '1.00',
  `active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `from_userID` (`from_userID`),
  KEY `to_userID` (`to_userID`),
  CONSTRAINT `FromUserId` FOREIGN KEY (`from_userID`) REFERENCES `sys_user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ToUserId` FOREIGN KEY (`to_userID`) REFERENCES `sys_user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=658 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `sys_user_relations`;
CREATE TABLE IF NOT EXISTS `sys_user_relations` (
  `IDleftuser` varchar(36) NOT NULL,
  `IDrightuser` varchar(36) NOT NULL,
  `relationstatus` enum('pending','ignore','approved') NOT NULL DEFAULT 'pending',
  `updatedate` datetime NOT NULL,
  PRIMARY KEY (`IDleftuser`,`IDrightuser`),
  KEY `RleationsOnUsersRight` (`IDrightuser`),
  CONSTRAINT `RelationsOnUserLeft` FOREIGN KEY (`IDleftuser`) REFERENCES `sys_user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `RleationsOnUsersRight` FOREIGN KEY (`IDrightuser`) REFERENCES `sys_user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '');
SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS);
SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT;

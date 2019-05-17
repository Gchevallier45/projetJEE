-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 17 mai 2019 à 13:38
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `dbjee`
--

-- --------------------------------------------------------

--
-- Structure de la table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
CREATE TABLE IF NOT EXISTS `addresses` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Street` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `City` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `State` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ZipCode` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Country_id` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `country_fk` (`Country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Address table';

--
-- Déchargement des données de la table `addresses`
--

INSERT INTO `addresses` (`Id`, `Street`, `City`, `State`, `ZipCode`, `Country_id`) VALUES
(1, 'Rue Portalis', 'Tours', 'Centre', '37000', 1),
(2, 'Boulevard De Chinon', 'Tours', 'Centre', '25410', 1),
(4, 'blablabla', 'Tours', 'Centre', '37000', 1),
(5, '15 rue tours', 'Tours', 'Centre', '37000', 1),
(6, '12 rue des palmiers', 'Blois', 'Centre', '18000', 1),
(7, 'rfgeherher', 'rfdthrhehet', 'centre', '54000', 1);

-- --------------------------------------------------------

--
-- Structure de la table `countries`
--

DROP TABLE IF EXISTS `countries`;
CREATE TABLE IF NOT EXISTS `countries` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Country` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Country table';

--
-- Déchargement des données de la table `countries`
--

INSERT INTO `countries` (`Id`, `Country`) VALUES
(1, 'France');

-- --------------------------------------------------------

--
-- Structure de la table `openinghours`
--

DROP TABLE IF EXISTS `openinghours`;
CREATE TABLE IF NOT EXISTS `openinghours` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `SunOpen` varchar(50) NOT NULL,
  `SunClose` varchar(50) NOT NULL,
  `MonOpen` varchar(50) NOT NULL,
  `MonClose` varchar(50) NOT NULL,
  `TuesOpen` varchar(50) NOT NULL,
  `TuesClose` varchar(50) NOT NULL,
  `WedOpen` varchar(50) NOT NULL,
  `WedClose` varchar(50) NOT NULL,
  `ThuOpen` varchar(50) NOT NULL,
  `ThuClose` varchar(50) NOT NULL,
  `FriOpen` varchar(50) NOT NULL,
  `FriClose` varchar(50) NOT NULL,
  `SatOpen` varchar(50) NOT NULL,
  `SatClose` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 COMMENT='OpeningHour table';

--
-- Déchargement des données de la table `openinghours`
--

INSERT INTO `openinghours` (`Id`, `SunOpen`, `SunClose`, `MonOpen`, `MonClose`, `TuesOpen`, `TuesClose`, `WedOpen`, `WedClose`, `ThuOpen`, `ThuClose`, `FriOpen`, `FriClose`, `SatOpen`, `SatClose`) VALUES
(1, '12:00:00', '12:00:00', '18:31:02', '12:00:00', '12:00:00', '12:00:00', '12:00:00', '12:00:00', '12:00:00', '12:00:00', '12:00:00', '12:00:00', '12:00:00', '12:00:00'),
(2, '21:54:24', '21:54:24', '21:54:24', '21:54:24', '21:54:24', '21:54:24', '21:54:24', '21:54:24', '21:54:24', '21:54:24', '21:54:24', '21:54:24', '21:54:24', '21:54:24'),
(3, '21:56:33', '21:56:33', '22:02:11', '21:56:33', '21:56:33', '21:56:33', '21:56:33', '21:56:33', '21:56:33', '21:56:33', '21:56:33', '21:56:33', '21:56:33', '21:56:33'),
(5, '22:23:55', '22:23:55', '22:23:55', '22:23:55', '22:23:55', '22:23:55', '22:23:55', '22:23:55', '22:23:55', '22:23:55', '22:23:55', '22:23:55', '22:23:55', '22:23:55'),
(6, '23:34:14', '23:34:14', '23:34:14', '23:34:14', '23:34:14', '23:34:14', '23:34:14', '23:34:14', '23:34:14', '23:34:14', '23:34:14', '23:34:14', '23:34:14', '23:34:14'),
(7, '02:15:28', '02:15:28', '02:15:28', '02:15:28', '02:15:28', '02:15:28', '02:15:28', '02:15:28', '02:15:28', '02:15:28', '02:15:28', '02:15:28', '02:15:28', '02:15:28'),
(8, '11:09:44', '11:09:44', '11:09:44', '11:09:44', '11:09:44', '11:09:44', '11:09:44', '11:09:44', '11:09:44', '11:09:44', '11:09:44', '11:09:44', '11:09:44', '11:09:44'),
(9, '00:00:00', '23:59:00', '00:00:00', '23:59:00', '00:00:00', '23:59:00', '00:00:00', '23:59:00', '00:00:00', '23:59:00', '00:00:00', '23:59:00', '00:00:00', '23:59:00'),
(10, '00:00:00', '23:59:00', '00:00:00', '23:59:00', '00:00:00', '23:59:00', '00:00:00', '23:59:00', '00:00:00', '23:59:00', '00:00:00', '23:59:00', '00:00:00', '23:59:00'),
(11, '00:00:00', '00:00:00', '00:00:00', '00:00:00', '00:00:00', '00:00:00', '00:00:00', '00:00:00', '00:00:00', '00:00:00', '00:00:00', '00:00:00', '00:00:00', '00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `promotions`
--

DROP TABLE IF EXISTS `promotions`;
CREATE TABLE IF NOT EXISTS `promotions` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `KeyStr` varchar(50) NOT NULL,
  `Title` text NOT NULL,
  `ShortDescription` text NOT NULL,
  `LongDescription` text NOT NULL,
  `Position` int(11) NOT NULL,
  `Disabled` tinyint(1) NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date NOT NULL,
  `ImageURL` text NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COMMENT='Promotion table';

--
-- Déchargement des données de la table `promotions`
--

INSERT INTO `promotions` (`Id`, `KeyStr`, `Title`, `ShortDescription`, `LongDescription`, `Position`, `Disabled`, `StartDate`, `EndDate`, `ImageURL`) VALUES
(1, '', 'blabla', '', '', 1, 0, '2019-04-03', '2019-04-12', ''),
(2, '', 'get 12 cookies', 'fzeofzejfoiezho', 'ghzeiughziuh', 0, 0, '2000-02-23', '2000-03-27', ''),
(6, '', 'image', 'image', 'image', 0, 0, '2000-02-02', '2000-02-03', '487.png');

-- --------------------------------------------------------

--
-- Structure de la table `stores`
--

DROP TABLE IF EXISTS `stores`;
CREATE TABLE IF NOT EXISTS `stores` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `KeyValue` varchar(50) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `PhoneNumber` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Latitude` float NOT NULL,
  `Longitude` float NOT NULL,
  `LastModifiedDate` date NOT NULL,
  `LastModifiedBy_id` int(11) NOT NULL,
  `Address_id` int(11) NOT NULL,
  `OpeningHours_id` int(11) NOT NULL,
  `Owner_id` int(11) NOT NULL,
  `IsClosed` tinyint(1) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `lastmodifiedby_fk` (`LastModifiedBy_id`),
  KEY `address_fk` (`Address_id`),
  KEY `openinghours_fk` (`OpeningHours_id`),
  KEY `owner_id` (`Owner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COMMENT='Store table';

--
-- Déchargement des données de la table `stores`
--

INSERT INTO `stores` (`Id`, `KeyValue`, `Name`, `PhoneNumber`, `Email`, `Latitude`, `Longitude`, `LastModifiedDate`, `LastModifiedBy_id`, `Address_id`, `OpeningHours_id`, `Owner_id`, `IsClosed`) VALUES
(1, 'password', 'Magasin', '0123456789', 'Magasin@Magasin.fr', 47.3833, 0.683333, '2019-03-21', 3, 1, 1, 1, 0),
(10, '', 'McDo 2 Lions', '0125487895', 'mcdo@mcdo.fr', 47.3732, 0.692139, '2019-05-10', 3, 5, 9, 1, 0),
(11, '', 'McDo Blois', '0254879666', 'mcdoblois@blois.fr', 47.5877, 1.33376, '2019-05-10', 3, 6, 10, 1, 0),
(12, '', 'blaezvoin', '0145878978', 'fzefzef@zefzef.fr', 47.6839, 13.0975, '2019-05-17', 3, 7, 11, 3, 0);

-- --------------------------------------------------------

--
-- Structure de la table `types`
--

DROP TABLE IF EXISTS `types`;
CREATE TABLE IF NOT EXISTS `types` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Type table';

--
-- Déchargement des données de la table `types`
--

INSERT INTO `types` (`Id`, `Type`) VALUES
(1, 'Administrator'),
(2, 'Owner'),
(3, 'Client');

-- --------------------------------------------------------

--
-- Structure de la table `useraccounts`
--

DROP TABLE IF EXISTS `useraccounts`;
CREATE TABLE IF NOT EXISTS `useraccounts` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `LastName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `PhoneNumber` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Active` tinyint(1) NOT NULL,
  `CreationDate` date NOT NULL,
  `LastModificationDate` date NOT NULL,
  `ResetPasswordLink` text COLLATE utf8_unicode_ci NOT NULL,
  `ResetLinkValidateDate` date DEFAULT NULL,
  `IsRemoved` tinyint(1) NOT NULL,
  `UUID` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `Type_id` int(11) NOT NULL,
  `Address_id` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Address_id` (`Address_id`),
  KEY `type_fk` (`Type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='UserAccount table';

--
-- Déchargement des données de la table `useraccounts`
--

INSERT INTO `useraccounts` (`Id`, `FirstName`, `LastName`, `Email`, `Password`, `PhoneNumber`, `Active`, `CreationDate`, `LastModificationDate`, `ResetPasswordLink`, `ResetLinkValidateDate`, `IsRemoved`, `UUID`, `Type_id`, `Address_id`) VALUES
(1, 'Michel', 'Martin', 'coucou@coucou.fr', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', '0123456789', 1, '2019-03-23', '2019-03-23', '2Q99k7nuQVpC8MgyLrUanrwubYsm0fXCrAYtXhaQtzen6jioVpyBfeenEFsv', '2019-11-10', 0, 'camarche', 1, 1),
(2, 'Jean', 'Michel', 'email@email.fr', 'd74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1', '0254879854', 1, '2019-05-09', '2019-05-09', 'MLXTiGrjJ8MeJ2FZBsJ7vxgWcACKXME06YLdT9T9Bq5RDe3zUd4duYwb4LzF', '2019-05-09', 0, 'mdr', 1, 1),
(3, 'Guillaume', 'Chevallier', 'coucou@email.fr', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', '0125478877', 0, '2019-05-10', '2019-05-10', '', NULL, 0, '', 2, 4);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `addresses`
--
ALTER TABLE `addresses`
  ADD CONSTRAINT `country_fk` FOREIGN KEY (`Country_id`) REFERENCES `countries` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `stores`
--
ALTER TABLE `stores`
  ADD CONSTRAINT `address_fk` FOREIGN KEY (`Address_id`) REFERENCES `addresses` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lastmodifiedby_fk` FOREIGN KEY (`LastModifiedBy_id`) REFERENCES `useraccounts` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `openinghours_fk` FOREIGN KEY (`OpeningHours_id`) REFERENCES `openinghours` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `owner_id` FOREIGN KEY (`Owner_id`) REFERENCES `useraccounts` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `useraccounts`
--
ALTER TABLE `useraccounts`
  ADD CONSTRAINT `addressuser_fk` FOREIGN KEY (`Address_id`) REFERENCES `addresses` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `type_fk` FOREIGN KEY (`Type_id`) REFERENCES `types` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2019. Júl 12. 15:46
-- Kiszolgáló verziója: 10.1.38-MariaDB
-- PHP verzió: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `szotar`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `szavak`
--

CREATE TABLE `szavak` (
  `szoID` int(11) NOT NULL,
  `lecke` varchar(10) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `angol` varchar(60) COLLATE utf8mb4_hungarian_ci NOT NULL,
  `magyar` varchar(60) COLLATE utf8mb4_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `szavak`
--

INSERT INTO `szavak` (`szoID`, `lecke`, `angol`, `magyar`) VALUES
(1, '5pak01', 'I feel dizzy.', 'Szédülök.'),
(2, '5pak01', 'I have a hangover.', 'Másnapos vagyok.'),
(3, '5pak01', 'I have a temperature.', 'Hőemelkedésem van.'),
(4, '5pak02', 'divorce lawyer', 'válóperes ügyvéd'),
(6, '5pak02', 'secondary school', 'középiskola'),
(7, '5pak02', 'employer', 'munkaadó'),
(10, '5pak02', 'casual', 'hétköznapi'),
(11, '5pak02', 'beautician', 'kozmetikus'),
(12, '5pak02', 'carpenter', 'asztalos'),
(13, '5pak02', 'divorced', 'elvált'),
(14, '5pak02', 'dressmaker', 'varrónő'),
(15, '5pak02', 'electrician', 'villanyszerelő'),
(16, '5pak02', 'engaged', 'eljegyzett, foglalt'),
(17, '5pak02', 'fireman', 'tűzoltó'),
(18, '5pak02', 'marital status', 'családi állapot'),
(19, '5pak02', 'plumber', 'vízvezeték-szerelő'),
(20, '5pak02', 'vet', 'állatorvos'),
(21, '5pak02', 'widower', 'özvegyember'),
(22, '5pak02', 'I am separated.', 'Külön élünk'),
(23, '5pak02', 'estate agent', 'ingatlanügynök'),
(24, '5pak02', 'accountant', 'könyvelő'),
(25, '5pak02', 'mean', 'aljas, gonosz'),
(26, '5pak01', 'to complain', 'panaszkodni');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `szavak`
--
ALTER TABLE `szavak`
  ADD PRIMARY KEY (`szoID`),
  ADD UNIQUE KEY `angol` (`angol`),
  ADD KEY `magyar` (`magyar`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `szavak`
--
ALTER TABLE `szavak`
  MODIFY `szoID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

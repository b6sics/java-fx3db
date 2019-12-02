-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2019. Nov 15. 17:05
-- Kiszolgáló verziója: 10.4.8-MariaDB
-- PHP verzió: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `auto`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `kiadasok`
--

CREATE TABLE `kiadasok` (
  `az` int(4) NOT NULL,
  `kiadas` varchar(50) COLLATE utf8mb4_hungarian_ci DEFAULT NULL,
  `ar` int(7) DEFAULT NULL,
  `datum` date DEFAULT NULL,
  `km` int(7) DEFAULT NULL,
  `megjegyzes` varchar(255) COLLATE utf8mb4_hungarian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- A tábla adatainak kiíratása `kiadasok`
--

INSERT INTO `kiadasok` (`az`, `kiadas`, `ar`, `datum`, `km`, `megjegyzes`) VALUES
(1, 'javítás', 15000, '2019-04-01', 136000, 'ablaktörlő'),
(2, 'tankolás', 5000, '2019-05-02', 137000, 'MOL kút'),
(3, 'biztosítás', 7000, '2019-07-01', 137900, 'kötelező'),
(4, 'tankolás', 5000, '2019-07-06', 138000, 'MOL kút');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `kiadasok`
--
ALTER TABLE `kiadasok`
  ADD PRIMARY KEY (`az`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `kiadasok`
--
ALTER TABLE `kiadasok`
  MODIFY `az` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

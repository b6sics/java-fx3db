-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 14, 2020 at 10:09 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tortenelem`
--

-- --------------------------------------------------------

--
-- Table structure for table `evszamok`
--

CREATE TABLE `evszamok` (
  `evID` int(11) NOT NULL,
  `ev` int(4) NOT NULL,
  `esemeny` varchar(80) COLLATE utf8mb4_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- Dumping data for table `evszamok`
--

INSERT INTO `evszamok` (`evID`, `ev`, `esemeny`) VALUES
(3, 313, 'Nagy Konstantin engedélyezi a keresztény vallást'),
(4, 395, 'a Római Birodalom kettészakadása'),
(5, 476, 'a Nyugat-Római Birodalom bukása'),
(6, 896, 'a honfoglalás kezdete'),
(7, 955, 'a kalandozók döntő veresége Augsburgnál'),
(8, 1000, 'Szent István megkoronázása'),
(9, 1222, 'az Aranybulla kiadása'),
(10, 1301, 'az Árpád-ház kihalása'),
(11, 1456, 'a nándorfehérvári diadal'),
(12, 1492, 'Kolombusz felfedezi Amerikát'),
(13, 1517, 'Luther közzéteszi hittételeit, a reformáció kezdete'),
(14, 1526, 'a mohácsi csata'),
(15, 1703, 'Rákóczi-szabadságharc'),
(17, 1776, 'az amerikai gyarmatok Függetlenségi Nyilatkozata'),
(18, 1848, 'forradalmi hullám Európában'),
(19, 1825, 'Széchenyi felajánlásával a Tudományos Akadémia alapítása, a reformkor kezdete'),
(20, 1867, 'a kiegyezés'),
(21, 1914, 'első világháború kezdete'),
(23, 1939, 'a második világháború kitörése, német támadás Lengyelország ellen'),
(24, 1945, 'a japán fegyverletétel, a második világháború vége'),
(27, 1918, 'első világháború vége');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `evszamok`
--
ALTER TABLE `evszamok`
  ADD PRIMARY KEY (`evID`),
  ADD UNIQUE KEY `esemeny` (`esemeny`),
  ADD KEY `ev` (`ev`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `evszamok`
--
ALTER TABLE `evszamok`
  MODIFY `evID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

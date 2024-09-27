-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 27, 2024 at 06:57 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `venedictodevelopersdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` binary(16) NOT NULL,
  `created` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `created`, `email`, `is_active`, `last_login`, `name`, `password`, `token`) VALUES
(0x32e1d6b404ba426d8dc60afa5ea271ce, '2024-09-27 01:42:14.000000', 'brunoporfidio117@gmail.com', b'1', '2024-09-27 01:42:38.000000', 'Bruno Porfidio', '$2a$10$0p3gYyCmlFqWIbTKIuqn2.7Y71FXTij2HwB6NqBM3yXrC2tgihks6', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJicnVub3BvcmZpZGlvMTE3QGdtYWlsLmNvbSIsImlhdCI6MTcyNzQxMjE1OCwiZXhwIjoxNzI3NDQ4MTU4fQ.fUq83FznrTtgL8cnw37ea05eD07H6EE2h6D3GWy0ILE');

-- --------------------------------------------------------

--
-- Table structure for table `user_phone`
--

CREATE TABLE `user_phone` (
  `id` binary(16) NOT NULL,
  `city_code` int(11) NOT NULL,
  `contry_code` varchar(255) DEFAULT NULL,
  `number` bigint(20) DEFAULT NULL,
  `user_id` binary(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_phone`
--

INSERT INTO `user_phone` (`id`, `city_code`, `contry_code`, `number`, `user_id`) VALUES
(0xd28ceb185368499fbe01a75e7ba2c987, 11, '549', 63648636, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_phones`
--

CREATE TABLE `user_phones` (
  `user_id` binary(16) NOT NULL,
  `phones_id` binary(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_phones`
--

INSERT INTO `user_phones` (`user_id`, `phones_id`) VALUES
(0x32e1d6b404ba426d8dc60afa5ea271ce, 0xd28ceb185368499fbe01a75e7ba2c987);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_phone`
--
ALTER TABLE `user_phone`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKaqeg9vtqjgkgi9vw7xy66va7` (`user_id`);

--
-- Indexes for table `user_phones`
--
ALTER TABLE `user_phones`
  ADD UNIQUE KEY `UKlpx6wawt7f9hqolfy260iava4` (`phones_id`),
  ADD KEY `FKdfslj94kbjqvp71kjjc36iije` (`user_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `user_phone`
--
ALTER TABLE `user_phone`
  ADD CONSTRAINT `FKaqeg9vtqjgkgi9vw7xy66va7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `user_phones`
--
ALTER TABLE `user_phones`
  ADD CONSTRAINT `FKdfslj94kbjqvp71kjjc36iije` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKtitklvi2h9ufioyiuf0iba5si` FOREIGN KEY (`phones_id`) REFERENCES `user_phone` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 21, 2024 at 10:56 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `employee`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin'),
(2, 'admin12', '123');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` int(12) NOT NULL,
  `employee_id` int(100) NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `phoneNum` varchar(25) NOT NULL,
  `position` varchar(100) NOT NULL,
  `image` varchar(100) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `employee_id`, `firstName`, `lastName`, `gender`, `phoneNum`, `position`, `image`, `date`) VALUES
(6, 5, 'som3a', 'sherif', 'Male', '1017411583', 'App Developer', 'C:\\\\Users\\\\SAMEH\\\\Downloads\\\\image.png', '2024-12-21'),
(7, 1, 'som3a', 'sherif', 'Male', '1017411583', 'Web Developer (Back End)', 'C:\\\\\\\\Users\\\\\\\\SAMEH\\\\\\\\Downloads\\\\\\\\image.png', '2024-12-21'),
(8, 2, 'sameh', 'elnahasss', 'Male', '1017411583', 'Web Developer (Back End)', 'C:\\\\Users\\\\SAMEH\\\\Desktop\\\\things\\\\367729570_785138760069933_1848199194708037706_n.jpg', '2024-12-21'),
(14, 13, 'test', 'tes', 'Male', '1234567890', 'Web Developer (Back End)', 'C:\\Users\\SAMEH\\Downloads\\sub-buzz-760-1699634139-8.jpg', '2024-12-21'),
(16, 3, 'hamza', 'farghali', 'Female', '01017415831', 'App Developer', 'C:\\\\Users\\\\SAMEH\\\\Downloads\\\\image.png', '2024-12-21');

-- --------------------------------------------------------

--
-- Table structure for table `employee_info`
--

CREATE TABLE `employee_info` (
  `id` int(100) NOT NULL,
  `employee_id` varchar(100) NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `position` varchar(100) NOT NULL,
  `salary` double NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee_info`
--

INSERT INTO `employee_info` (`id`, `employee_id`, `firstName`, `lastName`, `position`, `salary`, `date`) VALUES
(2, '5', 'som3a', 'sherif', 'App Developer', 500, '2024-12-21'),
(3, '1', 'som3a', 'sherif', 'Web Developer (Back End)', 9000, '2024-12-21'),
(4, '2', 'sameh', 'elnahasss', 'Web Developer (Back End)', 0, '2024-12-21'),
(10, '13', 'test', 'tes', 'Web Developer (Back End)', 0.25, '2024-12-21'),
(12, '3', 'hamza', 'farghali', 'App Developer', 0.25, '2024-12-21');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employee_info`
--
ALTER TABLE `employee_info`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `employee_info`
--
ALTER TABLE `employee_info`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

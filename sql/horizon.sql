-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 12, 2025 at 05:59 PM
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
-- Database: `horizon`
--

-- --------------------------------------------------------

--
-- Table structure for table `flight_details`
--

CREATE TABLE `flight_details` (
  `Flight_ID` int(12) NOT NULL,
  `Flight Name` varchar(50) NOT NULL,
  `Source` varchar(50) NOT NULL,
  `Destination` varchar(50) NOT NULL,
  `Class` varchar(50) NOT NULL,
  `Date` date NOT NULL,
  `Departure_Time` time(6) NOT NULL DEFAULT '09:10:00.000000',
  `Arrival_Time` time(6) NOT NULL,
  `Arrival Date` date NOT NULL,
  `No_of_Stops` int(11) NOT NULL,
  `Transitional Point` varchar(50) NOT NULL,
  `Baggage Policy` varchar(10) NOT NULL,
  `Refundability` varchar(10) NOT NULL,
  `Price` int(11) NOT NULL,
  `Journey_Time` float NOT NULL,
  `S_Country` varchar(12) NOT NULL,
  `D_Country` varchar(12) NOT NULL,
  `Journey_Time(txt)` varchar(20) NOT NULL,
  `Flight Model` varchar(50) NOT NULL,
  `DepartAirportName` varchar(50) NOT NULL,
  `DepartTerminal` int(12) NOT NULL,
  `ArrivalAirportName` varchar(50) NOT NULL,
  `ArrivalTerminal` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;

--
-- Dumping data for table `flight_details`
--

INSERT INTO `flight_details` (`Flight_ID`, `Flight Name`, `Source`, `Destination`, `Class`, `Date`, `Departure_Time`, `Arrival_Time`, `Arrival Date`, `No_of_Stops`, `Transitional Point`, `Baggage Policy`, `Refundability`, `Price`, `Journey_Time`, `S_Country`, `D_Country`, `Journey_Time(txt)`, `Flight Model`, `DepartAirportName`, `DepartTerminal`, `ArrivalAirportName`, `ArrivalTerminal`) VALUES
(1214, 'Biman Bangladesh Airlines\r\n(Boeing 737-800)', 'Dhaka', 'Chittagong', 'Economy Class', '2025-04-25', '02:30:00.000000', '00:00:03.000000', '2025-04-25', 0, '', '10 Kg', 'No', 15000, 0, '', '', '', '', '', 0, '', 0),
(1219, 'Biman Bangladesh Airlines', 'Dhaka', 'Sylhet', 'Economy Class', '2025-04-18', '19:30:00.000000', '20:30:00.000000', '2025-04-18', 0, '', '10 Kg', 'No', 17000, 1, 'Bangladesh', 'Banglasesh', '01 hour 00 minute', 'Aircraft : DHC8 Dash 8', 'Hazrat Shahjalal International Airport', 4, 'Cox\'s Bazar Airport', 1),
(1220, 'Biman Bangladesh Airlines\r\n', 'Dhaka', 'Sylhet', 'Economy Class', '2025-01-01', '04:30:00.000000', '05:30:00.000000', '2025-01-01', 1, '', '10 Kg', 'No', 16000, 1, '', '', '', '', '', 0, '', 0),
(1221, 'Biman Bangladesh Airlines\r\n', 'Dhaka', 'Sylhet', 'Economy Class', '2025-01-01', '10:30:00.000000', '11:45:00.000000', '2025-01-01', 1, '', '10 Kg', 'Yes', 19000, 1.15, '', '', '', '', '', 0, '', 0),
(1225, 'Biman Bangladesh Airlines', 'Dhaka', 'Sylhet', 'Economy Class', '2025-01-01', '02:30:00.000000', '03:40:00.000000', '2025-01-01', 1, '', '10 Kg', 'No', 18500, 1.1, '', '', '', '', '', 0, '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `login_details`
--

CREATE TABLE `login_details` (
  `AccountNo` varchar(12) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Password` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;

--
-- Dumping data for table `login_details`
--

INSERT INTO `login_details` (`AccountNo`, `Username`, `Password`) VALUES
('1', 'Samsul', '1234567');

-- --------------------------------------------------------

--
-- Table structure for table `user_details`
--

CREATE TABLE `user_details` (
  `User Name` varchar(12) NOT NULL,
  `First Name` varchar(12) NOT NULL,
  `Last Name` varchar(12) NOT NULL,
  `Gender` varchar(12) NOT NULL,
  `Date of Birth` date NOT NULL,
  `Country` varchar(20) NOT NULL,
  `Contact No` varchar(11) NOT NULL,
  `Passport No` text NOT NULL,
  `Password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;

--
-- Dumping data for table `user_details`
--

INSERT INTO `user_details` (`User Name`, `First Name`, `Last Name`, `Gender`, `Date of Birth`, `Country`, `Contact No`, `Passport No`, `Password`) VALUES
('Sam17', 'Samsul', 'Arifin', 'Male', '2001-11-01', 'Bangladesh', '1683703530', '64hiu39845', '12345'),
('sa', 'a', 's', 'Male', '2005-04-06', 'Australia', '11111111111', '1111111', 'aaaaaa');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `flight_details`
--
ALTER TABLE `flight_details`
  ADD PRIMARY KEY (`Flight_ID`);

--
-- Indexes for table `login_details`
--
ALTER TABLE `login_details`
  ADD PRIMARY KEY (`AccountNo`),
  ADD UNIQUE KEY `AccountNo` (`AccountNo`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- Indexes for table `user_details`
--
ALTER TABLE `user_details`
  ADD PRIMARY KEY (`User Name`),
  ADD UNIQUE KEY `Passport No` (`Passport No`) USING HASH;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `flight_details`
--
ALTER TABLE `flight_details`
  MODIFY `Flight_ID` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1230;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

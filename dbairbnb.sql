-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-09-2020 a las 05:17:07
-- Versión del servidor: 10.3.16-MariaDB
-- Versión de PHP: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbairbnb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `listings`
--

CREATE TABLE `listings` (
  `id` bigint(20) NOT NULL,
  `adults` int(11) DEFAULT NULL,
  `base_price` double NOT NULL,
  `children` int(11) DEFAULT NULL,
  `cleaning_fee` double NOT NULL,
  `create_at` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `is_pets_allowed` bit(1) NOT NULL,
  `monthly_discount` double NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `ownerid` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `weekly_discount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `listings_special_prices_list`
--

CREATE TABLE `listings_special_prices_list` (
  `listings_id` bigint(20) NOT NULL,
  `special_prices_list_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservation`
--

CREATE TABLE `reservation` (
  `id` bigint(20) NOT NULL,
  `checkin` datetime(6) DEFAULT NULL,
  `checkout` datetime(6) DEFAULT NULL,
  `cleaning_fee` double NOT NULL,
  `create_at` date DEFAULT NULL,
  `discount` double NOT NULL,
  `nights_cost` double NOT NULL,
  `nights_count` int(11) DEFAULT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `special_prices`
--

CREATE TABLE `special_prices` (
  `id` bigint(20) NOT NULL,
  `create_at` date DEFAULT NULL,
  `date_price` datetime(6) DEFAULT NULL,
  `listing_id` bigint(20) DEFAULT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `create_at` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `listings`
--
ALTER TABLE `listings`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `listings_special_prices_list`
--
ALTER TABLE `listings_special_prices_list`
  ADD UNIQUE KEY `UK_dcn9pwn8nhdkfckpjtfmdj236` (`special_prices_list_id`),
  ADD KEY `FK4bkuugy7ftee5xj396cub9wsk` (`listings_id`);

--
-- Indices de la tabla `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `special_prices`
--
ALTER TABLE `special_prices`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `listings`
--
ALTER TABLE `listings`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `special_prices`
--
ALTER TABLE `special_prices`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `listings_special_prices_list`
--
ALTER TABLE `listings_special_prices_list`
  ADD CONSTRAINT `FK4bkuugy7ftee5xj396cub9wsk` FOREIGN KEY (`listings_id`) REFERENCES `listings` (`id`),
  ADD CONSTRAINT `FK86jxrm6px6vmxucp8bjweife3` FOREIGN KEY (`special_prices_list_id`) REFERENCES `special_prices` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 23 Mar 2020, 11:23
-- Wersja serwera: 10.4.10-MariaDB
-- Wersja PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `security_test`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `oauth_access_token`
--

CREATE TABLE `oauth_access_token` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `authentication` mediumblob DEFAULT NULL,
  `authentication_id` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `client_id` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `refresh_token` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `token` mediumblob DEFAULT NULL,
  `token_id` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `oauth_client_details`
--

CREATE TABLE `oauth_client_details` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `authorities` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `authorized_grant_types` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `autoapprove` tinyint(4) DEFAULT NULL,
  `client_id` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `client_name` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `client_secret` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT 1,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `resource_ids` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `scope` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `uuid` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `authentication_id` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `token` mediumblob DEFAULT NULL,
  `token_id` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `oauth_client_details`
--

INSERT INTO `oauth_client_details` (`id`, `access_token_validity`, `additional_information`, `authorities`, `authorized_grant_types`, `autoapprove`, `client_id`, `client_name`, `client_secret`, `created`, `enabled`, `refresh_token_validity`, `resource_ids`, `scope`, `uuid`, `web_server_redirect_uri`, `authentication_id`, `token`, `token_id`, `user_name`) VALUES
(1, 10800, NULL, 'USER', 'password,authorization_code,refresh_token,client_credentials,implicit', NULL, 'oauth2-jwt-client', NULL, '$2a$08$qvrzQZ7jJ7oy2p/msL4M0.l83Cd0jNsX6AJUitbgRXGzge4j035ha', NULL, 1, 2592000, 'resource-server-rest-api', 'read', NULL, 'http://localhost:8090/api/generateToken', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `oauth_code`
--

CREATE TABLE `oauth_code` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `authentication` mediumblob DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_role`
--

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(20) COLLATE utf8mb4_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `user_role`
--

INSERT INTO `user_role` (`id`, `role_name`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_role_mapper`
--

CREATE TABLE `user_role_mapper` (
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `user_role_mapper`
--

INSERT INTO `user_role_mapper` (`role_id`, `user_id`) VALUES
(1, 1),
(1, 2),
(2, 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_table`
--

CREATE TABLE `user_table` (
  `id` int(11) NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `username` varchar(20) COLLATE utf8mb4_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `user_table`
--

INSERT INTO `user_table` (`id`, `password`, `username`) VALUES
(1, '$2a$10$tHYJr6XVe/6A4dz7k.tvc.qClG90ahOynf0Myi.32OvtGfUmnD5AW', 'gg'),
(2, '$2a$10$sapBC1FqOXggeIEAajdE9ubWRpEvbL6VVvPnCrjFRiGFdcA3hl3C2', 'hh');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `oauth_access_token`
--
ALTER TABLE `oauth_access_token`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `oauth_client_details`
--
ALTER TABLE `oauth_client_details`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `oauth_code`
--
ALTER TABLE `oauth_code`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `user_role_mapper`
--
ALTER TABLE `user_role_mapper`
  ADD PRIMARY KEY (`role_id`,`user_id`),
  ADD KEY `FKg9q4whmjq0k5r1hjmej5ble1m` (`user_id`);

--
-- Indeksy dla tabeli `user_table`
--
ALTER TABLE `user_table`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username_unique_constraint` (`username`);

--
-- AUTO_INCREMENT dla tabel zrzutów
--

--
-- AUTO_INCREMENT dla tabeli `oauth_access_token`
--
ALTER TABLE `oauth_access_token`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `oauth_client_details`
--
ALTER TABLE `oauth_client_details`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `oauth_code`
--
ALTER TABLE `oauth_code`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `user_role`
--
ALTER TABLE `user_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `user_table`
--
ALTER TABLE `user_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `user_role_mapper`
--
ALTER TABLE `user_role_mapper`
  ADD CONSTRAINT `FK6j01384b4lh4jqhsgixh3iy75` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`id`),
  ADD CONSTRAINT `FKg9q4whmjq0k5r1hjmej5ble1m` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

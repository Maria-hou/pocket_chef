DROP DATABASE IF EXISTS finalproject;
CREATE DATABASE finalproject;

CREATE TABLE finalproject.`recipes` (
   `id` varchar(45) NOT NULL,
   `name_of_recipe` varchar(75) DEFAULT NULL,
   `ingredients` varchar(1000) DEFAULT NULL,
   `categories` varchar(500) DEFAULT NULL,
   `instructions` varchar(2000) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 
 CREATE TABLE finalproject.`user_info` (
   `email` varchar(45) NOT NULL,
   `username` varchar(45) DEFAULT NULL,
   `password` varchar(45) DEFAULT NULL,
   PRIMARY KEY (`email`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 
 CREATE TABLE finalproject.`user_favorites` (
   `id` varchar(45) NOT NULL,
   `favorites_email` varchar(45) DEFAULT NULL,
   `list_of_recipes` varchar(5000) DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `email_idx` (`favorites_email`),
   CONSTRAINT `favorites_email` FOREIGN KEY (`favorites_email`) REFERENCES `user_info` (`email`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 
 CREATE TABLE finalproject.`user_history` (
   `id` int NOT NULL,
   `history_email` varchar(45) DEFAULT NULL,
   `list_of_recipes_viewed` varchar(45) DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `history_email_idx` (`history_email`),
   CONSTRAINT `history_email` FOREIGN KEY (`history_email`) REFERENCES `user_info` (`email`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
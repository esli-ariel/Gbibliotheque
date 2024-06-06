-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 06 juin 2024 à 14:29
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bd_bibliothèque`
--

-- --------------------------------------------------------

--
-- Structure de la table `adherents`
--

CREATE TABLE `adherents` (
  `matricule_user` int(50) NOT NULL,
  `nom_user` varchar(100) NOT NULL,
  `prenom_user` varchar(100) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telephone` int(100) NOT NULL,
  `img_user` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `adherents`
--




-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `matricule_admin` int(50) NOT NULL,
  `nom_admin` varchar(50) NOT NULL,
  `prenom_admin` varchar(75) NOT NULL,
  `adresse` varchar(150) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telephone` int(255) NOT NULL,
  `img_admin` longblob NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `admin`
--




-- --------------------------------------------------------

--
-- Structure de la table `emprunts`
--

CREATE TABLE `emprunts` (
  `Num_emp` int(100) NOT NULL,
  `emprunteurs` int(100) NOT NULL,
  `nom_adhe` varchar(100) NOT NULL,
  `livre_empruntés` int(100) NOT NULL,
  `titre_liv` varchar(100) NOT NULL,
  `date_emprumts` date NOT NULL DEFAULT current_timestamp(),
  `retour_norm` date NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'emprunté'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `emprunts`
--



-- --------------------------------------------------------

--
-- Structure de la table `livres`
--

CREATE TABLE `livres` (
  `code_liv` int(100) NOT NULL,
  `titre_liv` varchar(100) NOT NULL,
  `auteur_liv` varchar(100) NOT NULL,
  `date_parution` date NOT NULL,
  `img_liv` longblob NOT NULL,
  `nb_exemplaires` int(100) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `livres`
--



--
-- Index pour les tables déchargées
--

--
-- Index pour la table `adherents`
--
ALTER TABLE `adherents`
  ADD PRIMARY KEY (`matricule_user`);

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`matricule_admin`);

--
-- Index pour la table `emprunts`
--
ALTER TABLE `emprunts`
  ADD PRIMARY KEY (`Num_emp`),
  ADD UNIQUE KEY `retour_norm` (`retour_norm`),
  ADD KEY `livre` (`livre_empruntés`),
  ADD KEY `emprunteurs` (`emprunteurs`);

--
-- Index pour la table `livres`
--
ALTER TABLE `livres`
  ADD PRIMARY KEY (`code_liv`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `matricule_admin` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `emprunts`
--
ALTER TABLE `emprunts`
  MODIFY `Num_emp` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=884;

--
-- AUTO_INCREMENT pour la table `livres`
--
ALTER TABLE `livres`
  MODIFY `code_liv` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=470;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `emprunts`
--
ALTER TABLE `emprunts`
  ADD CONSTRAINT `emprunteurs` FOREIGN KEY (`emprunteurs`) REFERENCES `adherents` (`matricule_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `livre` FOREIGN KEY (`livre_empruntés`) REFERENCES `livres` (`code_liv`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-05-2020 a las 00:03:18
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `colegio`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `anios`
--

CREATE TABLE `anios` (
  `id_anio` varchar(20) COLLATE utf8_bin NOT NULL,
  `anio` int(15) NOT NULL,
  `descripcion` varchar(30) COLLATE utf8_bin NOT NULL,
  `actual` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `anios`
--

INSERT INTO `anios` (`id_anio`, `anio`, `descripcion`, `actual`) VALUES
('2015', 2015, '', 0),
('2016', 2016, '', 0),
('2017', 2017, '', 0),
('2018', 2018, '', 0),
('2019', 2019, '', 0),
('2020', 2020, '', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calificaciones`
--

CREATE TABLE `calificaciones` (
  `id_estudiante_materia` varchar(20) COLLATE utf8_bin NOT NULL,
  `id_periodo` varchar(20) COLLATE utf8_bin NOT NULL,
  `nota` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `calificaciones`
--

INSERT INTO `calificaciones` (`id_estudiante_materia`, `id_periodo`, `nota`) VALUES
('1', '2020-1', 4.1),
('2', '2020-1', 5),
('3', '2020-1', 4.6),
('4', '2020-1', 5),
('5', '2020-1', 4.4),
('6', '2020-1', 5),
('7', '2020-1', 5),
('8', '2020-1', 4.3),
('9', '2020-1', 3.5),
('10', '2020-1', 3),
('11', '2020-1', 4.2),
('12', '2020-1', 3.6),
('13', '2020-1', 5),
('14', '2020-1', 4),
('15', '2020-1', 5),
('16', '2020-1', 4.8),
('17', '2020-1', 3.8),
('18', '2020-1', 4.5),
('19', '2020-1', 2.5),
('20', '2020-1', 5),
('21', '2020-1', 3.3),
('22', '2020-1', 4),
('23', '2020-1', 4.8),
('5', '2019-3', 4.4),
('5', '2020-2', 3.5),
('24', '2019-3', 2.8),
('25', '2020-1', 4.3),
('26', '2019-3', 3.8),
('27', '2019-3', 4.8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cursos`
--

CREATE TABLE `cursos` (
  `id_curso` varchar(20) COLLATE utf8_bin NOT NULL,
  `grado` varchar(20) COLLATE utf8_bin NOT NULL,
  `grupo` int(15) NOT NULL,
  `descripcion` varchar(30) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `cursos`
--

INSERT INTO `cursos` (`id_curso`, `grado`, `grupo`, `descripcion`) VALUES
('1', '6', 1, '6-1'),
('2', '6', 2, '6-2'),
('3', '7', 1, '7-1'),
('4', '7', 2, '7-2'),
('5', '8', 1, '8-1'),
('6', '8', 2, '8-2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiantes`
--

CREATE TABLE `estudiantes` (
  `id_estudiante` varchar(30) COLLATE utf8_bin NOT NULL,
  `apellidos` varchar(30) COLLATE utf8_bin NOT NULL,
  `nombres` varchar(30) COLLATE utf8_bin NOT NULL,
  `fecha_nacimiento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `estudiantes`
--

INSERT INTO `estudiantes` (`id_estudiante`, `apellidos`, `nombres`, `fecha_nacimiento`) VALUES
('1', 'Pinchao', 'David', '0000-00-00'),
('10', 'Gomez', 'Javier', '2020-08-13'),
('11', 'Cabrera', 'Julian', '2020-08-13'),
('12', 'Rosero', 'Tatiana', '2020-08-07'),
('13', 'Guerra', 'Luis', '2020-07-10'),
('14', 'Bolaños', 'Alejandro', '2020-10-15'),
('15', 'Lopez', 'Jose', '2020-10-16'),
('16', 'Kaggle', 'Brian', '2020-06-10'),
('17', 'Doe', 'Joe', '2020-04-09'),
('18', 'Fifi', 'Fufu', '2020-01-22'),
('2', 'Pinchao', 'Julian', '2020-04-09'),
('3', 'Vallejo', 'Daniela', '2020-03-17'),
('4', 'Vaus', 'Ñañela', '2020-06-18'),
('5', 'Ortiz', 'Sandra', '2020-05-07'),
('6', 'Zambrano', 'John', '2020-07-09'),
('7', 'Cardenas', 'Luis', '2020-07-10'),
('8', 'Muñoz', 'Daniel', '2020-08-13'),
('9', 'Ortega', 'Carlos', '2020-07-09');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiante_materias`
--

CREATE TABLE `estudiante_materias` (
  `id_estudiante_materia` varchar(20) COLLATE utf8_bin NOT NULL,
  `id_materia` varchar(20) COLLATE utf8_bin NOT NULL,
  `id_anio` varchar(20) COLLATE utf8_bin NOT NULL,
  `id_estudiante` varchar(30) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `estudiante_materias`
--

INSERT INTO `estudiante_materias` (`id_estudiante_materia`, `id_materia`, `id_anio`, `id_estudiante`) VALUES
('1', '1', '2020', '1'),
('10', '1', '2020', '2'),
('11', '1', '2020', '3'),
('12', '3', '2020', '7'),
('13', '3', '2020', '8'),
('14', '3', '2020', '9'),
('15', '4', '2020', '10'),
('16', '4', '2020', '11'),
('17', '4', '2020', '12'),
('18', '5', '2020', '13'),
('19', '5', '2020', '14'),
('2', '2', '2020', '2'),
('20', '5', '2020', '15'),
('21', '6', '2020', '16'),
('22', '6', '2020', '17'),
('23', '6', '2020', '18'),
('24', '1', '2019', '1'),
('25', '2', '2020', '1'),
('26', '1', '2019', '2'),
('27', '1', '2019', '3'),
('3', '2', '2020', '3'),
('4', '1', '2020', '4'),
('5', '1', '2020', '5'),
('6', '1', '2020', '6'),
('7', '2', '2020', '4'),
('8', '2', '2020', '5'),
('9', '2', '2020', '6');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materias`
--

CREATE TABLE `materias` (
  `id_materia` varchar(20) COLLATE utf8_bin NOT NULL,
  `materia` varchar(30) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `materias`
--

INSERT INTO `materias` (`id_materia`, `materia`) VALUES
('1', 'Matematicas'),
('10', 'Deportes'),
('11', 'Artes'),
('12', 'Filosofia'),
('2', 'Español'),
('3', 'Ingles'),
('4', 'Ciencias Naturales'),
('5', 'Historia'),
('6', 'Ciencias Sociales'),
('7', 'Quimica'),
('8', 'Fisica');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matriculas`
--

CREATE TABLE `matriculas` (
  `id_anio` varchar(20) COLLATE utf8_bin NOT NULL,
  `id_curso` varchar(20) COLLATE utf8_bin NOT NULL,
  `id_estudiante` varchar(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `matriculas`
--

INSERT INTO `matriculas` (`id_anio`, `id_curso`, `id_estudiante`) VALUES
('2020', '5', '1'),
('2020', '5', '2'),
('2020', '5', '3'),
('2020', '6', '4'),
('2020', '6', '5'),
('2020', '6', '6'),
('2020', '4', '7'),
('2020', '4', '8'),
('2020', '4', '9'),
('2019', '3', '1'),
('2019', '3', '2'),
('2019', '3', '4'),
('2020', '3', '10'),
('2020', '3', '11'),
('2020', '3', '12'),
('2020', '1', '13'),
('2020', '1', '14'),
('2020', '1', '15'),
('2020', '2', '16'),
('2020', '2', '17'),
('2020', '2', '18');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `periodos`
--

CREATE TABLE `periodos` (
  `id_periodo` varchar(20) COLLATE utf8_bin NOT NULL,
  `id_anio` varchar(20) COLLATE utf8_bin NOT NULL,
  `periodo` int(11) NOT NULL,
  `actual` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `periodos`
--

INSERT INTO `periodos` (`id_periodo`, `id_anio`, `periodo`, `actual`) VALUES
('2018-1', '2018', 1, 0),
('2019-1', '2019', 1, 0),
('2019-2', '2019', 2, 0),
('2019-3', '2019', 3, 0),
('2020-1', '2020', 1, 1),
('2020-2', '2020', 2, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `anios`
--
ALTER TABLE `anios`
  ADD PRIMARY KEY (`id_anio`);

--
-- Indices de la tabla `calificaciones`
--
ALTER TABLE `calificaciones`
  ADD KEY `fk_id_estudiante_materia_calificacion` (`id_estudiante_materia`),
  ADD KEY `fk_id_periodo` (`id_periodo`);

--
-- Indices de la tabla `cursos`
--
ALTER TABLE `cursos`
  ADD PRIMARY KEY (`id_curso`);

--
-- Indices de la tabla `estudiantes`
--
ALTER TABLE `estudiantes`
  ADD PRIMARY KEY (`id_estudiante`);

--
-- Indices de la tabla `estudiante_materias`
--
ALTER TABLE `estudiante_materias`
  ADD PRIMARY KEY (`id_estudiante_materia`),
  ADD KEY `fk_id_materia` (`id_materia`),
  ADD KEY `fk_id_estudiante_matricula` (`id_estudiante`),
  ADD KEY `fk_id_anio_matricula` (`id_anio`);

--
-- Indices de la tabla `materias`
--
ALTER TABLE `materias`
  ADD PRIMARY KEY (`id_materia`);

--
-- Indices de la tabla `matriculas`
--
ALTER TABLE `matriculas`
  ADD KEY `fk_id_curso` (`id_curso`),
  ADD KEY `fk_id_anio` (`id_anio`),
  ADD KEY `fk_id_estudiante` (`id_estudiante`);

--
-- Indices de la tabla `periodos`
--
ALTER TABLE `periodos`
  ADD PRIMARY KEY (`id_periodo`),
  ADD KEY `fk_id_anio_periodo` (`id_anio`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `calificaciones`
--
ALTER TABLE `calificaciones`
  ADD CONSTRAINT `fk_id_estudiante_materia_calificacion` FOREIGN KEY (`id_estudiante_materia`) REFERENCES `estudiante_materias` (`id_estudiante_materia`),
  ADD CONSTRAINT `fk_id_periodo` FOREIGN KEY (`id_periodo`) REFERENCES `periodos` (`id_periodo`);

--
-- Filtros para la tabla `estudiante_materias`
--
ALTER TABLE `estudiante_materias`
  ADD CONSTRAINT `fk_id_anio_matricula` FOREIGN KEY (`id_anio`) REFERENCES `anios` (`id_anio`),
  ADD CONSTRAINT `fk_id_estudiante_matricula` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiantes` (`id_estudiante`),
  ADD CONSTRAINT `fk_id_materia` FOREIGN KEY (`id_materia`) REFERENCES `materias` (`id_materia`);

--
-- Filtros para la tabla `matriculas`
--
ALTER TABLE `matriculas`
  ADD CONSTRAINT `fk_id_anio` FOREIGN KEY (`id_anio`) REFERENCES `anios` (`id_anio`),
  ADD CONSTRAINT `fk_id_curso` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`id_curso`),
  ADD CONSTRAINT `fk_id_estudiante` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiantes` (`id_estudiante`);

--
-- Filtros para la tabla `periodos`
--
ALTER TABLE `periodos`
  ADD CONSTRAINT `fk_id_anio_periodo` FOREIGN KEY (`id_anio`) REFERENCES `anios` (`id_anio`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

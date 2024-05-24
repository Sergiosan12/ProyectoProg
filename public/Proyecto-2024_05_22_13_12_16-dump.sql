--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0 (Debian 16.0-1.pgdg120+1)
-- Dumped by pg_dump version 16.0 (Debian 16.0-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: proyectodb; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE proyectodb WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'es_ES.UTF-8';


ALTER DATABASE proyectodb OWNER TO postgres;

\connect proyectodb

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: deporte; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.deporte (
    id_deporte integer NOT NULL,
    nombre_deporte character varying(50),
    intensidad character varying(10)
);


ALTER TABLE public.deporte OWNER TO postgres;

--
-- Name: fase_deporte; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fase_deporte (
    id_fase integer NOT NULL,
    id_deporte integer NOT NULL
);


ALTER TABLE public.fase_deporte OWNER TO postgres;

--
-- Name: fases_menstruacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fases_menstruacion (
    id_fase integer NOT NULL,
    nombre_fase character varying(50)
);


ALTER TABLE public.fases_menstruacion OWNER TO postgres;

--
-- Name: menstruacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.menstruacion (
    usuario character varying(20) NOT NULL,
    mediaciclo integer,
    mediasangrado integer,
    ultimoperiodo date
);


ALTER TABLE public.menstruacion OWNER TO postgres;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    usuario character varying(20) NOT NULL,
    nombre character varying(20) NOT NULL,
    contrasenha character varying(20) NOT NULL,
    email character varying(50) NOT NULL,
    edad integer NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- Data for Name: deporte; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.deporte (id_deporte, nombre_deporte, intensidad) FROM stdin;
1	Fútbol	Alta
2	Baloncesto	Alta
3	Tenis	Media
4	Natación	Baja
5	Atletismo	Alta
6	Ciclismo	Alta
7	Pilates	Baja
8	Yoga	Baja
9	Pádel	Media
10	Baile	Media
12	Andar	Baja
11	Gimnasia	Media
13	Levantamiento_pesas	Alta
14	Correr	Alta
\.


--
-- Data for Name: fase_deporte; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fase_deporte (id_fase, id_deporte) FROM stdin;
1	1
1	2
1	5
1	6
2	9
2	10
2	11
2	4
3	4
3	7
3	8
3	12
4	5
4	6
4	13
4	14
\.


--
-- Data for Name: fases_menstruacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fases_menstruacion (id_fase, nombre_fase) FROM stdin;
1	Folicular
2	Lútea
3	Menstrual
4	Ovulación
\.


--
-- Data for Name: menstruacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.menstruacion (usuario, mediaciclo, mediasangrado, ultimoperiodo) FROM stdin;
tony	28	5	\N
	\N	\N	\N
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (usuario, nombre, contrasenha, email, edad) FROM stdin;
tony	anthony	tony	tony@gml.com	20
				0
\.


--
-- Name: deporte deporte_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.deporte
    ADD CONSTRAINT deporte_pkey PRIMARY KEY (id_deporte);


--
-- Name: fase_deporte fase_deporte_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fase_deporte
    ADD CONSTRAINT fase_deporte_pkey PRIMARY KEY (id_fase, id_deporte);


--
-- Name: fases_menstruacion fases_menstruacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fases_menstruacion
    ADD CONSTRAINT fases_menstruacion_pkey PRIMARY KEY (id_fase);


--
-- Name: menstruacion menstruacion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menstruacion
    ADD CONSTRAINT menstruacion_pkey PRIMARY KEY (usuario);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (usuario);


--
-- Name: fase_deporte fase_deporte_id_deporte_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fase_deporte
    ADD CONSTRAINT fase_deporte_id_deporte_fkey FOREIGN KEY (id_deporte) REFERENCES public.deporte(id_deporte);


--
-- Name: fase_deporte fase_deporte_id_fase_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fase_deporte
    ADD CONSTRAINT fase_deporte_id_fase_fkey FOREIGN KEY (id_fase) REFERENCES public.fases_menstruacion(id_fase);


--
-- Name: menstruacion menstruacion_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menstruacion
    ADD CONSTRAINT menstruacion_usuario_fkey FOREIGN KEY (usuario) REFERENCES public.usuario(usuario);


--
-- PostgreSQL database dump complete
--


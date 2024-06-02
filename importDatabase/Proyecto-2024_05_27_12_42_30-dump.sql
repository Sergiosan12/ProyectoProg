--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.2 (Debian 16.2-1.pgdg120+2)

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
-- Name: deportes_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.deportes_usuario (
    usuario character varying(255) NOT NULL,
    deporte_fase_menstrual character varying(255),
    deporte_fase_folicular character varying(255),
    deporte_fase_ovular character varying(255),
    deporte_fase_lutea character varying(255)
);


ALTER TABLE public.deportes_usuario OWNER TO postgres;

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
    usuario character varying(255) NOT NULL,
    mediaciclo integer NOT NULL,
    mediasangrado integer NOT NULL,
    duracionfasefolicular integer,
    duracionfaseovular integer,
    duracionfaselutea integer,
    lastperiod date NOT NULL,
    nextperiod date,
    nextfasefolicular date,
    nextfaseovular date,
    nextfaselutea date
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

INSERT INTO public.deporte (id_deporte, nombre_deporte, intensidad) VALUES (1, 'Fútbol', 'Alta');
INSERT INTO public.deporte (id_deporte, nombre_deporte, intensidad) VALUES (2, 'Baloncesto', 'Alta');
INSERT INTO public.deporte (id_deporte, nombre_deporte, intensidad) VALUES (3, 'Tenis', 'Media');
INSERT INTO public.deporte (id_deporte, nombre_deporte, intensidad) VALUES (4, 'Natación', 'Baja');
INSERT INTO public.deporte (id_deporte, nombre_deporte, intensidad) VALUES (5, 'Atletismo', 'Alta');
INSERT INTO public.deporte (id_deporte, nombre_deporte, intensidad) VALUES (6, 'Ciclismo', 'Alta');
INSERT INTO public.deporte (id_deporte, nombre_deporte, intensidad) VALUES (7, 'Pilates', 'Baja');
INSERT INTO public.deporte (id_deporte, nombre_deporte, intensidad) VALUES (8, 'Yoga', 'Baja');
INSERT INTO public.deporte (id_deporte, nombre_deporte, intensidad) VALUES (9, 'Pádel', 'Media');
INSERT INTO public.deporte (id_deporte, nombre_deporte, intensidad) VALUES (10, 'Baile', 'Media');
INSERT INTO public.deporte (id_deporte, nombre_deporte, intensidad) VALUES (12, 'Andar', 'Baja');
INSERT INTO public.deporte (id_deporte, nombre_deporte, intensidad) VALUES (11, 'Gimnasia', 'Media');
INSERT INTO public.deporte (id_deporte, nombre_deporte, intensidad) VALUES (13, 'Levantamiento_pesas', 'Alta');
INSERT INTO public.deporte (id_deporte, nombre_deporte, intensidad) VALUES (14, 'Correr', 'Alta');


--
-- Data for Name: deportes_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: fase_deporte; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.fase_deporte (id_fase, id_deporte) VALUES (1, 1);
INSERT INTO public.fase_deporte (id_fase, id_deporte) VALUES (1, 2);
INSERT INTO public.fase_deporte (id_fase, id_deporte) VALUES (1, 5);
INSERT INTO public.fase_deporte (id_fase, id_deporte) VALUES (1, 6);
INSERT INTO public.fase_deporte (id_fase, id_deporte) VALUES (2, 9);
INSERT INTO public.fase_deporte (id_fase, id_deporte) VALUES (2, 10);
INSERT INTO public.fase_deporte (id_fase, id_deporte) VALUES (2, 11);
INSERT INTO public.fase_deporte (id_fase, id_deporte) VALUES (2, 4);
INSERT INTO public.fase_deporte (id_fase, id_deporte) VALUES (3, 4);
INSERT INTO public.fase_deporte (id_fase, id_deporte) VALUES (3, 7);
INSERT INTO public.fase_deporte (id_fase, id_deporte) VALUES (3, 8);
INSERT INTO public.fase_deporte (id_fase, id_deporte) VALUES (3, 12);
INSERT INTO public.fase_deporte (id_fase, id_deporte) VALUES (4, 5);
INSERT INTO public.fase_deporte (id_fase, id_deporte) VALUES (4, 6);
INSERT INTO public.fase_deporte (id_fase, id_deporte) VALUES (4, 13);
INSERT INTO public.fase_deporte (id_fase, id_deporte) VALUES (4, 14);


--
-- Data for Name: fases_menstruacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.fases_menstruacion (id_fase, nombre_fase) VALUES (1, 'Folicular');
INSERT INTO public.fases_menstruacion (id_fase, nombre_fase) VALUES (2, 'Lútea');
INSERT INTO public.fases_menstruacion (id_fase, nombre_fase) VALUES (3, 'Menstrual');
INSERT INTO public.fases_menstruacion (id_fase, nombre_fase) VALUES (4, 'Ovulación');


--
-- Data for Name: menstruacion; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: deporte deporte_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.deporte
    ADD CONSTRAINT deporte_pkey PRIMARY KEY (id_deporte);


--
-- Name: deportes_usuario deportes_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.deportes_usuario
    ADD CONSTRAINT deportes_usuario_pkey PRIMARY KEY (usuario);


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
-- Name: deportes_usuario deportes_usuario_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.deportes_usuario
    ADD CONSTRAINT deportes_usuario_usuario_fkey FOREIGN KEY (usuario) REFERENCES public.usuario(usuario);


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


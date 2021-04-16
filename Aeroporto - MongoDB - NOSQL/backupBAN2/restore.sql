--
-- NOTE:
--
-- File paths need to be edited. Search for $$PATH$$ and
-- replace it with the path to the directory containing
-- the extracted data files.
--
--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4 (Ubuntu 12.4-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.4 (Ubuntu 12.4-0ubuntu0.20.04.1)

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

DROP DATABASE "BAN2_FINAL";
--
-- Name: BAN2_FINAL; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "BAN2_FINAL" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'pt_BR.UTF-8' LC_CTYPE = 'pt_BR.UTF-8';


ALTER DATABASE "BAN2_FINAL" OWNER TO postgres;

\connect "BAN2_FINAL"

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
-- Name: atualizateste(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.atualizateste() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 begin
 	if TG_OP = 'DELETE' then
 		delete from verifica_estado WHERE numero_anac = old.numero_anac;
 	end if;
 	return null;
 end;
 $$;


ALTER FUNCTION public.atualizateste() OWNER TO postgres;

--
-- Name: criar_aviao(integer, integer, integer, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.criar_aviao(reg integer, model integer, mat integer, mem integer) RETURNS boolean
    LANGUAGE plpgsql
    AS $$begin
insert into aviao (numero_registro,codigo_modelo, numero_matricula, numero_membro) values (reg,model,mat,mem);
return true;
end;
$$;


ALTER FUNCTION public.criar_aviao(reg integer, model integer, mat integer, mem integer) OWNER TO postgres;

--
-- Name: criar_controlador(integer, integer, numeric, integer, numeric, character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.criar_controlador(mat integer, mem integer, datae numeric, sal integer, num numeric, ende character varying) RETURNS boolean
    LANGUAGE plpgsql
    AS $$begin
insert into controlador (numero_matricula, numero_membro, data_exame, salario, numero_telefone, endereco) values (mat,mem,datae,sal,num,ende);
return true;
end;
$$;


ALTER FUNCTION public.criar_controlador(mat integer, mem integer, datae numeric, sal integer, num numeric, ende character varying) OWNER TO postgres;

--
-- Name: criar_estado(integer, integer, numeric, numeric, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.criar_estado(reg integer, anac integer, datar numeric, num numeric, pont integer) RETURNS boolean
    LANGUAGE plpgsql
    AS $$begin
insert into verifica_estado (numero_registro,numero_anac, data_realizacao,numero_horas,pontuacao) values (reg,anac,datar,num,pont);
return true;
end;
$$;


ALTER FUNCTION public.criar_estado(reg integer, anac integer, datar numeric, num numeric, pont integer) OWNER TO postgres;

--
-- Name: criar_modelo(integer, integer, integer, character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.criar_modelo(cm integer, cap integer, pes integer, nome character varying) RETURNS boolean
    LANGUAGE plpgsql
    AS $$begin
insert into modelo (codigo_modelo,capacidade,peso,nome_modelo) values (cm,cap,pes,nome);
return true;
end;
$$;


ALTER FUNCTION public.criar_modelo(cm integer, cap integer, pes integer, nome character varying) OWNER TO postgres;

--
-- Name: criar_perito(integer, integer, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.criar_perito(model integer, mat integer, mem integer) RETURNS boolean
    LANGUAGE plpgsql
    AS $$begin
insert into perito (codigo_modelo, numero_matricula, numero_membro) values (model,mat,mem);
return true;
end;
$$;


ALTER FUNCTION public.criar_perito(model integer, mat integer, mem integer) OWNER TO postgres;

--
-- Name: criar_tecnico(integer, integer, integer, numeric, character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.criar_tecnico(mat integer, mem integer, sal integer, num numeric, ende character varying) RETURNS boolean
    LANGUAGE plpgsql
    AS $$begin
insert into tecnico (numero_matricula, numero_membro, salario, numero_telefone, endereco) values (mat,mem,sal,num,ende);
return true;
end;
$$;


ALTER FUNCTION public.criar_tecnico(mat integer, mem integer, sal integer, num numeric, ende character varying) OWNER TO postgres;

--
-- Name: criar_teste(integer, character varying, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.criar_teste(anac integer, nome character varying, pont integer) RETURNS boolean
    LANGUAGE plpgsql
    AS $$begin
insert into teste (numero_anac, nome, pontuacao_maxima) values (anac,nome,pont);
return true;
end;
$$;


ALTER FUNCTION public.criar_teste(anac integer, nome character varying, pont integer) OWNER TO postgres;

--
-- Name: novo_numeroanac(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.novo_numeroanac() RETURNS trigger
    LANGUAGE plpgsql
    AS $$ 
 begin
 	new.numero_anac := nextval('novo_numeroanac');
 	return new;
end;
 $$;


ALTER FUNCTION public.novo_numeroanac() OWNER TO postgres;

--
-- Name: novo_numeroregistro(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.novo_numeroregistro() RETURNS trigger
    LANGUAGE plpgsql
    AS $$ 
   begin
   	new.numero_registro := nextval('novo_numeroregistro');
   	return new;
   end;
   $$;


ALTER FUNCTION public.novo_numeroregistro() OWNER TO postgres;

--
-- Name: numeroinvalido(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.numeroinvalido() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
  declare
  aux_numero numeric default 0;
      begin
  	SELECT length(new.numero_telefone::text) INTO aux_numero;
      if new.numero_telefone is null then
          RAISE EXCEPTION 'Numero de telefone invalido';
  	elseif aux_numero > 11 then
 	    RAISE EXCEPTION 'Numero de telefone invalido';
  	end if;
  	return new;
      end;
  $$;


ALTER FUNCTION public.numeroinvalido() OWNER TO postgres;

--
-- Name: quantidadeavioes(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.quantidadeavioes() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
  begin
  	if new.numero_registro > 100 then
  		raise exception 'ERRO! Apenas 100 aviões sao permitidos no aeroporto.';
  	end if;
  	return new;
  end;
  $$;


ALTER FUNCTION public.quantidadeavioes() OWNER TO postgres;

--
-- Name: verificaboolean(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.verificaboolean() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
 begin
	if verificaPontuacaoMaxima(new.numero_anac, new.pontuacao) is true then
 		raise exception 'ERRO! Pontuacao do teste maior que a pontuacao maxima definida!';
 	end if;
 	return new;
 end;
 $$;


ALTER FUNCTION public.verificaboolean() OWNER TO postgres;

--
-- Name: verificapontuacaomaxima(integer, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.verificapontuacaomaxima(v_numero_anac integer, pontuacao_atual integer) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
 declare
 	pontuacao_maxima int default 0;
 begin
 	select t.pontuacao_maxima FROM teste t where t.numero_anac = v_numero_anac into pontuacao_maxima;
 	if pontuacao_maxima > pontuacao_atual then
 		return false;
 	else
 		return true;
 	end if;
 end;
 $$;


ALTER FUNCTION public.verificapontuacaomaxima(v_numero_anac integer, pontuacao_atual integer) OWNER TO postgres;

--
-- Name: verificarteste(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.verificarteste(nome_teste character varying) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
 declare
 	aux_teste varchar(50) default '';
 begin
	select t.nome from teste t where t.nome = nome_teste into aux_teste;
 	if aux_teste = nome_teste then
 		return true;
 	else
 		return false;
 	end if;
	return new;
 end;
 $$;


ALTER FUNCTION public.verificarteste(nome_teste character varying) OWNER TO postgres;

--
-- Name: verificatestemesmonome(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.verificatestemesmonome() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

 begin
 	if verificarTeste(new.nome) is true then
 		raise exception 'Nao se pode adicionar pois existe outro teste com o mesmo nome';
 	end if;
 	return new;
 end;
 $$;


ALTER FUNCTION public.verificatestemesmonome() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: aviao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.aviao (
    numero_registro integer NOT NULL,
    codigo_modelo integer NOT NULL,
    numero_matricula integer,
    numero_membro integer
);


ALTER TABLE public.aviao OWNER TO postgres;

--
-- Name: controlador; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.controlador (
    numero_matricula integer NOT NULL,
    numero_membro integer NOT NULL,
    data_exame numeric NOT NULL,
    salario integer,
    numero_telefone numeric,
    endereco character varying(50)
);


ALTER TABLE public.controlador OWNER TO postgres;

--
-- Name: modelo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.modelo (
    codigo_modelo integer NOT NULL,
    capacidade integer,
    peso integer,
    nome_modelo character varying(50)
);


ALTER TABLE public.modelo OWNER TO postgres;

--
-- Name: novo_numeroanac; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.novo_numeroanac
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.novo_numeroanac OWNER TO postgres;

--
-- Name: novo_numeroregistro; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.novo_numeroregistro
    START WITH 102
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.novo_numeroregistro OWNER TO postgres;

--
-- Name: perito; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.perito (
    codigo_modelo integer NOT NULL,
    numero_matricula integer NOT NULL,
    numero_membro integer NOT NULL
);


ALTER TABLE public.perito OWNER TO postgres;

--
-- Name: tecnico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tecnico (
    numero_matricula integer NOT NULL,
    numero_membro integer NOT NULL,
    salario integer,
    numero_telefone numeric,
    endereco character varying(50)
);


ALTER TABLE public.tecnico OWNER TO postgres;

--
-- Name: teste; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.teste (
    numero_anac integer NOT NULL,
    nome character varying(50),
    pontuacao_maxima integer
);


ALTER TABLE public.teste OWNER TO postgres;

--
-- Name: verifica_estado; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.verifica_estado (
    numero_registro integer NOT NULL,
    numero_anac integer NOT NULL,
    numero_horas numeric,
    pontuacao integer,
    data_realizacao numeric
);


ALTER TABLE public.verifica_estado OWNER TO postgres;

--
-- Data for Name: aviao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.aviao (numero_registro, codigo_modelo, numero_matricula, numero_membro) FROM stdin;
\.
COPY public.aviao (numero_registro, codigo_modelo, numero_matricula, numero_membro) FROM '$$PATH$$/3038.dat';

--
-- Data for Name: controlador; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.controlador (numero_matricula, numero_membro, data_exame, salario, numero_telefone, endereco) FROM stdin;
\.
COPY public.controlador (numero_matricula, numero_membro, data_exame, salario, numero_telefone, endereco) FROM '$$PATH$$/3034.dat';

--
-- Data for Name: modelo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.modelo (codigo_modelo, capacidade, peso, nome_modelo) FROM stdin;
\.
COPY public.modelo (codigo_modelo, capacidade, peso, nome_modelo) FROM '$$PATH$$/3035.dat';

--
-- Data for Name: perito; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.perito (codigo_modelo, numero_matricula, numero_membro) FROM stdin;
\.
COPY public.perito (codigo_modelo, numero_matricula, numero_membro) FROM '$$PATH$$/3036.dat';

--
-- Data for Name: tecnico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tecnico (numero_matricula, numero_membro, salario, numero_telefone, endereco) FROM stdin;
\.
COPY public.tecnico (numero_matricula, numero_membro, salario, numero_telefone, endereco) FROM '$$PATH$$/3033.dat';

--
-- Data for Name: teste; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.teste (numero_anac, nome, pontuacao_maxima) FROM stdin;
\.
COPY public.teste (numero_anac, nome, pontuacao_maxima) FROM '$$PATH$$/3037.dat';

--
-- Data for Name: verifica_estado; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.verifica_estado (numero_registro, numero_anac, numero_horas, pontuacao, data_realizacao) FROM stdin;
\.
COPY public.verifica_estado (numero_registro, numero_anac, numero_horas, pontuacao, data_realizacao) FROM '$$PATH$$/3039.dat';

--
-- Name: novo_numeroanac; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.novo_numeroanac', 3, true);


--
-- Name: novo_numeroregistro; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.novo_numeroregistro', 105, true);


--
-- Name: aviao aviao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aviao
    ADD CONSTRAINT aviao_pkey PRIMARY KEY (numero_registro);


--
-- Name: controlador controlador_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.controlador
    ADD CONSTRAINT controlador_pkey PRIMARY KEY (numero_matricula, numero_membro, data_exame);


--
-- Name: modelo modelo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.modelo
    ADD CONSTRAINT modelo_pkey PRIMARY KEY (codigo_modelo);


--
-- Name: perito perito_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perito
    ADD CONSTRAINT perito_pkey PRIMARY KEY (codigo_modelo, numero_matricula, numero_membro);


--
-- Name: tecnico tecnico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tecnico
    ADD CONSTRAINT tecnico_pkey PRIMARY KEY (numero_matricula, numero_membro);


--
-- Name: teste teste_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.teste
    ADD CONSTRAINT teste_pkey PRIMARY KEY (numero_anac);


--
-- Name: verifica_estado verifica_estado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.verifica_estado
    ADD CONSTRAINT verifica_estado_pkey PRIMARY KEY (numero_registro, numero_anac);


--
-- Name: teste atualizateste; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER atualizateste AFTER DELETE ON public.teste FOR EACH ROW EXECUTE FUNCTION public.atualizateste();


--
-- Name: teste novo_numeroanac; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER novo_numeroanac BEFORE INSERT ON public.teste FOR EACH ROW EXECUTE FUNCTION public.novo_numeroanac();


--
-- Name: aviao novo_numeroregistro; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER novo_numeroregistro BEFORE INSERT ON public.aviao FOR EACH ROW EXECUTE FUNCTION public.novo_numeroregistro();


--
-- Name: controlador numeroinvalido; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER numeroinvalido BEFORE INSERT OR UPDATE ON public.controlador FOR EACH ROW EXECUTE FUNCTION public.numeroinvalido();


--
-- Name: tecnico numeroinvalido; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER numeroinvalido BEFORE INSERT OR UPDATE ON public.tecnico FOR EACH ROW EXECUTE FUNCTION public.numeroinvalido();


--
-- Name: aviao quantidadeavioes; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER quantidadeavioes BEFORE INSERT OR UPDATE ON public.aviao FOR EACH ROW EXECUTE FUNCTION public.quantidadeavioes();


--
-- Name: verifica_estado verificaboolean; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER verificaboolean BEFORE INSERT OR UPDATE ON public.verifica_estado FOR EACH ROW EXECUTE FUNCTION public.verificaboolean();


--
-- Name: teste verificatestemesmonome; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER verificatestemesmonome BEFORE INSERT OR UPDATE ON public.teste FOR EACH ROW EXECUTE FUNCTION public.verificatestemesmonome();


--
-- Name: aviao aviao_codigo_modelo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aviao
    ADD CONSTRAINT aviao_codigo_modelo_fkey FOREIGN KEY (codigo_modelo) REFERENCES public.modelo(codigo_modelo);


--
-- Name: aviao aviao_numero_matricula_numero_membro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.aviao
    ADD CONSTRAINT aviao_numero_matricula_numero_membro_fkey FOREIGN KEY (numero_matricula, numero_membro) REFERENCES public.tecnico(numero_matricula, numero_membro);


--
-- Name: perito perito_codigo_modelo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perito
    ADD CONSTRAINT perito_codigo_modelo_fkey FOREIGN KEY (codigo_modelo) REFERENCES public.modelo(codigo_modelo);


--
-- Name: perito perito_numero_matricula_numero_membro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perito
    ADD CONSTRAINT perito_numero_matricula_numero_membro_fkey FOREIGN KEY (numero_matricula, numero_membro) REFERENCES public.tecnico(numero_matricula, numero_membro);


--
-- Name: verifica_estado verifica_estado_numero_anac_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.verifica_estado
    ADD CONSTRAINT verifica_estado_numero_anac_fkey FOREIGN KEY (numero_anac) REFERENCES public.teste(numero_anac);


--
-- Name: verifica_estado verifica_estado_numero_registro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.verifica_estado
    ADD CONSTRAINT verifica_estado_numero_registro_fkey FOREIGN KEY (numero_registro) REFERENCES public.aviao(numero_registro);


--
-- PostgreSQL database dump complete
--


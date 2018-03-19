-- Table: public."user"

-- DROP TABLE public."user";

CREATE TABLE public."user"
(
  id integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
  email character varying(255),
  password character varying(255),
  fname character varying(255),
  sname character varying(255),
  lname character varying(255),
  description text,
  active boolean NOT NULL DEFAULT false,
  CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."user"
  OWNER TO postgres;


-- Table: public.role

-- DROP TABLE public.role;

CREATE TABLE public.role
(
  id integer NOT NULL DEFAULT nextval('role_id_seq'::regclass),
  name character varying(255),
  CONSTRAINT role_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.role
  OWNER TO postgres;



-- Table: public.user_role

-- DROP TABLE public.user_role;

CREATE TABLE public.user_role
(
  user_id integer NOT NULL,
  role_id integer NOT NULL,
  CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id)
)

WITH (
  OIDS=FALSE
);
ALTER TABLE public.user_role
  OWNER TO postgres;

-- Index: public."FKI_user_role_role"

-- DROP INDEX public."FKI_user_role_role";

CREATE INDEX "FKI_user_role_role"
  ON public.user_role
  USING btree
  (role_id);

-- Foreign Key: public."FK_user_role_role"

-- ALTER TABLE public.user_role DROP CONSTRAINT "FK_user_role_role";

ALTER TABLE public.user_role
  ADD CONSTRAINT "FK_user_role_role" FOREIGN KEY (role_id)
      REFERENCES public.role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

-- Foreign Key: public."FK_user_role_user"

-- ALTER TABLE public.user_role DROP CONSTRAINT "FK_user_role_user";

ALTER TABLE public.user_role
  ADD CONSTRAINT "FK_user_role_user" FOREIGN KEY (user_id)
      REFERENCES public."user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

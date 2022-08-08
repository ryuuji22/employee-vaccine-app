-- Table: public.roles

--DROP TABLE IF EXISTS public.roles;

CREATE TABLE IF NOT EXISTS public.roles
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT roles_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.roles
    OWNER to postgres;
    
-- Table: public.users

--DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp without time zone,
    enabled boolean,
    identification character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;

-- Table: public.users_roles

--DROP TABLE IF EXISTS public.users_roles;

CREATE TABLE IF NOT EXISTS public.users_roles
(
    user_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    role_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT fk2o0jvgh89lemvvo17cbqvdxaa FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkj6m8fwv7oqv74fcehir1a9ffy FOREIGN KEY (role_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users_roles
    OWNER to postgres;

-- Table: public.employees

--DROP TABLE IF EXISTS public.employees;

CREATE TABLE IF NOT EXISTS public.employees
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    address character varying(255) COLLATE pg_catalog."default",
    birthday date,
    created_at timestamp without time zone,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    enabled boolean,
    first_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    identification character varying(255) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    phone character varying(255) COLLATE pg_catalog."default",
    vaccinated boolean,
    user_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT employees_pkey PRIMARY KEY (id),
    CONSTRAINT fk69x3vjuy1t5p18a5llb8h2fjx FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.employees
    OWNER to postgres;


-- Table: public.vaccine

-- DROP TABLE IF EXISTS public.vaccine;

CREATE TABLE IF NOT EXISTS public.vaccine
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT vaccine_pkey PRIMARY KEY (id),
    CONSTRAINT uk_i7tje2xf0ksd3mdasoxq6qkfb UNIQUE (name)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.vaccine
    OWNER to postgres;


-- Table: public.vaccine_registry

-- DROP TABLE IF EXISTS public.vaccine_registry;

CREATE TABLE IF NOT EXISTS public.vaccine_registry
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp without time zone,
    dose integer,
    enabled boolean,
    vaccination_date date,
    employee_id character varying(255) COLLATE pg_catalog."default",
    vaccine_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT vaccine_registry_pkey PRIMARY KEY (id),
    CONSTRAINT ukj3lsoo0e2ag2nfeggupl7oj6k UNIQUE (vaccine_id, employee_id, dose),
    CONSTRAINT fk933eekjav34m3oounredqvys6 FOREIGN KEY (employee_id)
        REFERENCES public.employees (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkmgwokaenk6m5wbbiawxm5yfeq FOREIGN KEY (vaccine_id)
        REFERENCES public.vaccine (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.vaccine_registry
    OWNER to postgres;
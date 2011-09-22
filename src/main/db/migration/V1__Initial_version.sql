/*
DROP TABLE IF EXISTS parameter_values_services;
DROP TABLE IF EXISTS categories_parameters;
DROP TABLE IF EXISTS parameter_values;
DROP TABLE IF EXISTS producers_services;
DROP TABLE IF EXISTS parameters;
DROP TABLE IF EXISTS producers;
DROP TABLE IF EXISTS services;
DROP TABLE IF EXISTS categories;
*/
--DROP TABLE IF EXISTS categories
CREATE TABLE IF NOT EXISTS categories
(
    id                  SERIAL,
    title               character varying(100)      NOT NULL,
    parent_id           integer                     NOT NULL        DEFAULT 0,
    CONSTRAINT  categories_id_pkey
        PRIMARY KEY (id)
);
--DROP TABLE IF EXISTS services
CREATE TABLE IF NOT EXISTS services
(
    id                  SERIAL,
    title               character varying(100)      NOT NULL,
    category_id         integer                     NOT NULL,
    CONSTRAINT  services_id_pkey
        PRIMARY KEY (id),
    CONSTRAINT  services_category_id_fkey
        FOREIGN KEY (category_id)
        REFERENCES categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);
--DROP TABLE IF EXISTS producers
CREATE TABLE IF NOT EXISTS producers
(
    id                  SERIAL,
    title               character varying(100)      NOT NULL,
    CONSTRAINT  producers_id_pkey
        PRIMARY KEY (id)
);
--DROP TABLE IF EXISTS parameters
CREATE TABLE IF NOT EXISTS parameters
(
    id                  SERIAL,
    title               character varying(100)      NOT NULL,
    kind                character varying(10)       NOT NULL    DEFAULT 'data_span'::character varying,
    description         text                        NOT NULL,
    CONSTRAINT  parameters_id_pkey
        PRIMARY KEY (id),
    CONSTRAINT  parameters_kind_check
        CHECK (kind::text = ANY (ARRAY[
                    'radio'::character varying,
                    'check_box'::character varying,
                    'data_span'::character varying]::text[])
              )
);
--DROP TABLE IF EXISTS parameter_values
CREATE TABLE IF NOT EXISTS parameter_values
(
    id                  SERIAL,
    int_value           integer                     NULL,
    text_value          character varying(20)       NULL,
    CONSTRAINT  parameter_values_id
        PRIMARY KEY (id)
);
--DROP TABLE IF EXISTS producers_services
CREATE TABLE IF NOT EXISTS producers_services
(
    producers_id        integer                     NOT NULL,
    services_id         integer                     NOT NULL,
    CONSTRAINT  producers_services_pkey
        PRIMARY KEY (producers_id, services_id),
    CONSTRAINT  producers_services_producers_id_fkey
        FOREIGN KEY (producers_id)
        REFERENCES producers (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT  producers_services_services_id_fkey
        FOREIGN KEY (services_id)
        REFERENCES services (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);
--DROP TABLE IF EXISTS categories_parameters
CREATE TABLE IF NOT EXISTS categories_parameters
(
    categories_id       integer                     NOT NULL,
    parameters_id       integer                     NOT NULL,
    CONSTRAINT  categories_parameters_pkey
        PRIMARY KEY (categories_id, parameters_id),
    CONSTRAINT  categories_parameters_categories_id_fkey
        FOREIGN KEY (categories_id)
        REFERENCES categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT  categories_parameters_id_fkey
        FOREIGN KEY (parameters_id)
        REFERENCES parameters (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);
--DROP TABLE IF EXISTS parameter_values_services
CREATE TABLE IF NOT EXISTS parameter_values_services
(
    parameter_values_id integer                     NOT NULL,
    services_id         integer                     NOT NULL,
    CONSTRAINT  parameter_values_services_pkey
        PRIMARY KEY (parameter_values_id, services_id),
    CONSTRAINT  parameter_values_services_parameter_values_id_fkey
        FOREIGN KEY (parameter_values_id)
        REFERENCES parameter_values (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT  parameter_values_services_services_id_fkey
        FOREIGN KEY (services_id)
        REFERENCES services (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);
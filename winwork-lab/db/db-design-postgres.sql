CREATE TABLE winwork.app_user
(
  id character varying(32) NOT NULL,
  active character(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  last_update_millis bigint,
  username character varying(255),
  password character varying(255),
  last_login_from character varying(100),
  last_login timestamp,
  display_lang character varying(5),
  first_name character varying(255),
  last_name character varying(255),
  CONSTRAINT app_user_pkey PRIMARY KEY (id)
);

CREATE TABLE winwork.app_role
(
  id character varying(32) NOT NULL,
  active character(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  last_update_millis bigint,
  name character varying(100),
  description character varying(255),
  CONSTRAINT app_role_pkey PRIMARY KEY (id)
);

CREATE TABLE winwork.app_user_role
(
  app_user_id character varying(32),
  app_role_id character varying(32)
 );

CREATE TABLE winwork.app_permission
(
  id character varying(32) NOT NULL,
  active character(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  last_update_millis bigint,
  name character varying(255),
  description character varying(255),
  http_path character varying(500),
  http_method character varying(25),
  parent_menu character varying(32),
  icon_file character varying(255),
  as_menu character varying(1) default 'N',
  menu_order integer default 0,
  CONSTRAINT app_permission_pkey PRIMARY KEY (id)
);

CREATE TABLE winwork.app_role_permission
(
  app_role_id character varying(32),
  app_permission_id character varying(32)
 );

CREATE TABLE winwork.app_option
(
  id character varying(32) NOT NULL,
  active character(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  last_update_millis bigint,
  name character varying(255),
  option_category character varying(255),
  description character varying(255),
  CONSTRAINT app_option_pkey PRIMARY KEY (id)
);

CREATE TABLE winwork.app_setting
(
  id character varying(32) NOT NULL,
  active character(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  last_update_millis bigint,
  code character varying(255),
  string_value character varying(500),
  setting_category character varying(500),
  description character varying(255),
  CONSTRAINT app_setting_pkey PRIMARY KEY (id)
);

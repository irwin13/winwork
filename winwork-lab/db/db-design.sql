-- APP
CREATE TABLE APP_USER
(
  id CHAR(32 BYTE) NOT NULL,
  active char(1) default 'Y',
  create_by VARCHAR2(255),
  create_date timestamp,
  last_update_by VARCHAR2(255),
  last_update_date timestamp,
  username VARCHAR2(255),
  password VARCHAR2(255),
  display_lang VARCHAR2(5),
  last_login date,
  last_login_from VARCHAR2(100),
  CONSTRAINT app_user_pk PRIMARY KEY (id)
);

ALTER TABLE APP_USER ADD FIRST_NAME VARCHAR2(255);
ALTER TABLE APP_USER ADD LAST_NAME VARCHAR2(255);
ALTER TABLE APP_USER ADD EMAIL VARCHAR2(255);
ALTER TABLE APP_USER ADD PHONE VARCHAR2(255);

CREATE TABLE APP_ROLE
(
  id CHAR(32 BYTE) NOT NULL,
  active char(1) default 'Y',
  create_by VARCHAR2(255),
  create_date timestamp,
  last_update_by VARCHAR2(255),
  last_update_date timestamp,
  name VARCHAR2(255),
  description VARCHAR2(500),
  CONSTRAINT app_role_pk PRIMARY KEY (id)
);

CREATE TABLE APP_PERMISSION
(
  id CHAR(32 BYTE) NOT NULL,
  active char(1) default 'Y',
  create_by VARCHAR2(255),
  create_date timestamp,
  last_update_by VARCHAR2(255),
  last_update_date timestamp,
  name VARCHAR2(255),
  description VARCHAR2(500),
  http_path VARCHAR2(255),
  http_method VARCHAR2(20),
  as_menu char(1) default 'N',
  menu_order integer DEFAULT 0,
  parent_menu CHAR(32),
  icon_file VARCHAR2(255),
  CONSTRAINT app_permission_pk PRIMARY KEY (id)
);

CREATE TABLE app_user_role
(
  app_user_id CHAR(32 BYTE),
  app_role_id CHAR(32 BYTE)
 );

CREATE TABLE app_role_permission
(
  app_role_id CHAR(32 BYTE),
  app_permission_id CHAR(32 BYTE)
 );

CREATE TABLE APP_OPTION
(
  id CHAR(32 BYTE) NOT NULL,
  active char(1) default 'Y',
  create_by VARCHAR2(255),
  create_date timestamp,
  last_update_by VARCHAR2(255),
  last_update_date timestamp,
  name VARCHAR2(255),
  option_category VARCHAR2(255),
  description VARCHAR2(500),
  CONSTRAINT app_option_pk PRIMARY KEY (id)
);

CREATE TABLE APP_SETTING
(
  id CHAR(32 BYTE) NOT NULL,
  active char(1) default 'Y',
  create_by VARCHAR2(255),
  create_date timestamp,
  last_update_by VARCHAR2(255),
  last_update_date timestamp,
  code VARCHAR2(255),
  setting_category VARCHAR2(255),
  string_value VARCHAR2(255),
  description VARCHAR2(500),
  CONSTRAINT app_setting_pk PRIMARY KEY (id)
);

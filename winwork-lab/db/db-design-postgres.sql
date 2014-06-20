CREATE role qreport LOGIN PASSWORD 'qreport789' CREATEDB VALID UNTIL 'infinity' CONNECTION LIMIT 100;

CREATE SCHEMA AUTHORIZATION qreport;

CREATE TABLESPACE ts_qreport LOCATION '/u01/postgresql/tablespace/';
CREATE TABLESPACE ts_activity LOCATION '/u01/postgresql/tablespace-activity/';

/* APPLICATION */
CREATE TABLE qreport.app_user
(
  id character varying(32) NOT NULL,
  active character(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  username character varying(255),
  password character varying(255),
  last_login_from character varying(100),
  last_login timestamp,
  display_lang character varying(5),
  employee character varying(32),
  CONSTRAINT app_user_pkey PRIMARY KEY (id)
)
TABLESPACE ts_qreport;
ALTER TABLE qreport.app_user OWNER TO qreport;

CREATE TABLE qreport.app_role
(
  id character varying(32) NOT NULL,
  active character(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  name character varying(100),
  description character varying(255),
  CONSTRAINT app_role_pkey PRIMARY KEY (id)
)
TABLESPACE ts_qreport;
ALTER TABLE qreport.app_role OWNER TO qreport;

CREATE TABLE qreport.app_user_role
(
  app_user_id character varying(32),
  app_role_id character varying(32)
 )
TABLESPACE ts_qreport;
ALTER TABLE qreport.app_user_role OWNER TO qreport;

CREATE TABLE qreport.app_permission
(
  id character varying(32) NOT NULL,
  active character(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  name character varying(255),
  description character varying(255),
  http_path character varying(500),
  http_method character varying(25),
  parent_menu character varying(32),
  icon_file character varying(255),
  as_menu character varying(1) default 'N',
  menu_order integer default 0,
  CONSTRAINT app_permission_pkey PRIMARY KEY (id)
)
TABLESPACE ts_qreport;
ALTER TABLE qreport.app_permission OWNER TO qreport;

CREATE TABLE qreport.app_role_permission
(
  app_role_id character varying(32),
  app_permission_id character varying(32)
 )
TABLESPACE ts_qreport;
ALTER TABLE qreport.app_role_permission OWNER TO qreport;

CREATE TABLE qreport.app_option
(
  id character varying(32) NOT NULL,
  active character(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  name character varying(255),
  option_category character varying(255),
  description character varying(255),
  CONSTRAINT app_option_pkey PRIMARY KEY (id)
)
TABLESPACE ts_qreport;
ALTER TABLE qreport.app_option OWNER TO qreport;

CREATE TABLE qreport.app_setting
(
  id character varying(32) NOT NULL,
  active character(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  code character varying(255),
  string_value character varying(500),
  setting_category character varying(500),
  description character varying(255),
  CONSTRAINT app_setting_pkey PRIMARY KEY (id)
)
TABLESPACE ts_qreport;
ALTER TABLE qreport.app_setting OWNER TO qreport;


CREATE TABLE qreport.app_sequence
(
  id character varying(32) NOT NULL,
  active char(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  sequence_key character varying(255),
  last_sequence integer DEFAULT 0,
  CONSTRAINT app_sequence_pk PRIMARY KEY (id)
)
TABLESPACE ts_qreport;
ALTER TABLE qreport.app_sequence OWNER TO qreport;

CREATE TABLE qreport.employee
(
  id character varying(32) NOT NULL,
  active char(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  first_name character varying(500),
  last_name character varying(500),
  email character varying(500),
  phone character varying(500),
  join_date date,
  leave_date date,
  employee_code character varying(20),
  status character varying(255), -- active, resign, holiday
  CONSTRAINT employee_pk PRIMARY KEY (id)
)
TABLESPACE ts_qreport;
ALTER TABLE qreport.employee OWNER TO qreport;

CREATE TABLE qreport.project
(
  id character varying(32) NOT NULL,
  active char(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  official_name character varying(500),
  short_name character varying(200),
  description character varying(500),
  employer character varying(255),
  start_date date,
  end_date date,
  status character varying(255), -- active, poc, pending, closed
  CONSTRAINT project_pk PRIMARY KEY (id)
)
TABLESPACE ts_qreport;
ALTER TABLE qreport.project OWNER TO qreport;


CREATE TABLE qreport.division
(
  id character varying(32) NOT NULL,
  active char(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  name character varying(255),
  description character varying(500),
  CONSTRAINT division_pk PRIMARY KEY (id)
)
TABLESPACE ts_qreport;
ALTER TABLE qreport.division OWNER TO qreport;


CREATE TABLE qreport.project_assignment
(
  id character varying(32) NOT NULL,
  active char(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  employee character varying(32),
  project character varying(32),
  project_role character varying(255),
  description character varying(500),
  assignment_date date,
  CONSTRAINT project_assignment_pk PRIMARY KEY (id)
)
TABLESPACE ts_qreport;
ALTER TABLE qreport.project_assignment OWNER TO qreport;


CREATE TABLE qreport.division_assignment
(
  id character varying(32) NOT NULL,
  active char(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  employee character varying(32),
  division character varying(32),
  division_role character varying(255),
  description character varying(500),
  assignment_date date,
  CONSTRAINT division_assignment_pk PRIMARY KEY (id)
)
TABLESPACE ts_qreport;
ALTER TABLE qreport.division_assignment OWNER TO qreport;


CREATE TABLE qreport.activity
(
  id character varying(32) NOT NULL,
  active char(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  employee character varying(32),
  project character varying(32),
  parent_activity character varying(32),
  activity_description character varying(1000),
  activity_date timestamp,
  activity_type character varying(100), -- timesheet, bug, change_request, test
  activity_code character varying(100), -- kode yg berkaitan dengan aktivitas khusus, seperti BUG BnoProjectNoUrutBug, CHANGE_REQUEST CRnoProjectNoUrutCR,
  activity_status character varying(100), -- IN PROGRESS, DONE, CANCEL
  CONSTRAINT activity_pk PRIMARY KEY (id)
)
TABLESPACE ts_activity;
ALTER TABLE qreport.activity OWNER TO qreport;


CREATE TABLE qreport.activity_attachment
(
  id character varying(32) NOT NULL,
  active char(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  activity character varying(32),
  activity_file bytea,
  activity_file_type character varying(255),
  CONSTRAINT activity_att_pk PRIMARY KEY (id)
)
TABLESPACE ts_activity;
ALTER TABLE qreport.activity_attachment OWNER TO qreport;


CREATE TABLE qreport.activity_tag
(
  id character varying(32) NOT NULL,
  active char(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  activity character varying(32),
  tagged_employee character varying(32),
  description character varying(255),
  CONSTRAINT activity_tag_pk PRIMARY KEY (id)
)
TABLESPACE ts_activity;
ALTER TABLE qreport.activity_tag OWNER TO qreport;


CREATE TABLE qreport.activity_notification
(
  id character varying(32) NOT NULL,
  active char(1) default 'Y',
  create_by character varying(255),
  create_date timestamp,
  last_update_by character varying(255),
  last_update_date timestamp,
  activity character varying(32),
  employee character varying(32),
  description character varying(500),
  is_read char(1) default 'N', -- Y read or unread N
  CONSTRAINT activity_notification_pk PRIMARY KEY (id)
)
TABLESPACE ts_activity;
ALTER TABLE qreport.activity_notification OWNER TO qreport;

ALTER TABLE qreport.employee add gender character varying(20);
ALTER TABLE qreport.employee add profile_picture bytea;
ALTER TABLE qreport.app_user add app_user_type character varying(50); -- internal or external
ALTER TABLE qreport.app_user add ldap_username character varying(250);
ALTER TABLE qreport.activity add activity_content text;
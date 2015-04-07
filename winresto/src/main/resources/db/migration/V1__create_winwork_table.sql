CREATE TABLE winwork_user (
  id char(32) NOT NULL,
  active bool DEFAULT NULL,
  create_by varchar(255) DEFAULT NULL,
  create_date datetime DEFAULT NULL,
  last_update_by varchar(255) DEFAULT NULL,
  last_update_date datetime DEFAULT NULL,
  username varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  email varchar(255) DEFAULT NULL,
  last_login_from varchar(255) NOT NULL,
  last_login_date datetime NOT NULL,
  display_lang varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE winwork_role (
  id char(32) NOT NULL,
  active bool DEFAULT NULL,
  create_by varchar(255) DEFAULT NULL,
  create_date datetime DEFAULT NULL,
  last_update_by varchar(255) DEFAULT NULL,
  last_update_date datetime DEFAULT NULL,
  name varchar(255) NOT NULL,
  description varchar(500) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE winwork_menu (
  id char(32) NOT NULL,
  active bool DEFAULT NULL,
  create_by varchar(255) DEFAULT NULL,
  create_date datetime DEFAULT NULL,
  last_update_by varchar(255) DEFAULT NULL,
  last_update_date datetime DEFAULT NULL,
  name varchar(255) NOT NULL,
  description varchar(500) DEFAULT NULL,
  menu_path varchar(255) DEFAULT NULL,
  menu_method varchar(255) DEFAULT NULL,
  menu_order integer DEFAULT 0,
  display_menu bool DEFAULT NULL,
  menu_icon varchar(500) DEFAULT NULL,
  parent_menu_id char(32) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE winwork_option (
  id char(32) NOT NULL,
  active bool DEFAULT NULL,
  create_by varchar(255) DEFAULT NULL,
  create_date datetime DEFAULT NULL,
  last_update_by varchar(255) DEFAULT NULL,
  last_update_date datetime DEFAULT NULL,
  option_name varchar(255) NOT NULL,
  option_category varchar(255) NOT NULL,
  description varchar(500) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE winwork_setting (
  id char(32) NOT NULL,
  active bool DEFAULT NULL,
  create_by varchar(255) DEFAULT NULL,
  create_date datetime DEFAULT NULL,
  last_update_by varchar(255) DEFAULT NULL,
  last_update_date datetime DEFAULT NULL,
  setting_code varchar(255) NOT NULL,
  setting_value varchar(500) NOT NULL,
  description varchar(500) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE winwork_sequence (
  id char(32) NOT NULL,
  active bool DEFAULT NULL,
  create_by varchar(255) DEFAULT NULL,
  create_date datetime DEFAULT NULL,
  last_update_by varchar(255) DEFAULT NULL,
  last_update_date datetime DEFAULT NULL,
  sequence_prefix varchar(255) NOT NULL,
  last_sequence integer DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

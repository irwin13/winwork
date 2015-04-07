CREATE TABLE winwork_user_role (
  user_id char(32) NOT NULL,
  role_id char(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE winwork_role_menu (
  menu_id char(32) NOT NULL,
  role_id char(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

Insert into winwork_user (id, active, create_by, create_date, last_update_by, last_update_date,
username, password, display_lang, last_login_date, last_login_from)
values
('eec5f679d2b144d1a95322c2cd39f14d',1, 'root',current_timestamp,'root',current_timestamp,
'root', '804307f829e4d68cf9666dc8bf17dcd94ba76600','id', current_timestamp,'127.0.0.1');
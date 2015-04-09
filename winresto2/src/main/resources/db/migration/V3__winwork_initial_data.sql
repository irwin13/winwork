insert into winwork_role
(id,active,create_by,create_date,last_update_by,last_update_date,
name,description)
values
('c6c2c31d117b4aae879b776349e116f5',1,'root',current_timestamp,'root',current_timestamp,
'root','root');

insert into winwork_user_role (user_id, role_id)
values ('eec5f679d2b144d1a95322c2cd39f14d', 'c6c2c31d117b4aae879b776349e116f5');

insert into winwork_option (id,active,create_by,create_date,last_update_by,last_update_date,
option_category,option_name)
values
(replace(uuid(), '-', ''),1,'root',current_timestamp,'root',current_timestamp,
'http_method','get');

insert into winwork_option (id,active,create_by,create_date,last_update_by,last_update_date,
option_category,option_name)
values
(replace(uuid(), '-', ''),1,'root',current_timestamp,'root',current_timestamp,
'http_method','post');

insert into winwork_setting (id,active,create_by,create_date,last_update_by,last_update_date,
setting_code, setting_value)
values
(replace(uuid(), '-', ''),1,'root',current_timestamp,'root',current_timestamp,
'winwork.default.displayLang', 'id');

Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391300',1,'root',current_timestamp,'root',current_timestamp,
'Application','#','get',1,1,null);

Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391301',1,'root',current_timestamp,'root',current_timestamp,
'Reset Password','winWorkUser/resetPassword','post',0,0,'2e29940ddd734441982c5625d5391300');

Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391302',1,'root',current_timestamp,'root',current_timestamp,
'User','winWorkUser/list','get',1,1,'2e29940ddd734441982c5625d5391300');

Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391303',1,'root',current_timestamp,'root',current_timestamp,
'Role','winWorkRole/list','get',1,2,'2e29940ddd734441982c5625d5391300');

Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391304',1,'root',current_timestamp,'root',current_timestamp,
'Menu','winWorkMenu/list','get',1,3,'2e29940ddd734441982c5625d5391300');

Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391305',1,'root',current_timestamp,'root',current_timestamp,
'Option','winWorkOption/list','get',1,4,'2e29940ddd734441982c5625d5391300');

Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391306',1,'root',current_timestamp,'root',current_timestamp,
'Setting','winWorkSetting/list','get',1,5,'2e29940ddd734441982c5625d5391300');

Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391307',1,'root',current_timestamp,'root',current_timestamp,
'Sequence','winWorkSequence/list','get',1,6,'2e29940ddd734441982c5625d5391300');

-- WINWORK_USER
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391308',1,'root',current_timestamp,'root',current_timestamp,
'User List Ajax','winWorkUser/listAjax','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391309',1,'root',current_timestamp,'root',current_timestamp,
'User Create','winWorkUser/create','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391310',1,'root',current_timestamp,'root',current_timestamp,
'User Create','winWorkUser/create','post',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391311',1,'root',current_timestamp,'root',current_timestamp,
'User Edit','winWorkUser/edit','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391312',1,'root',current_timestamp,'root',current_timestamp,
'User Edit','winWorkUser/edit','post',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391313',1,'root',current_timestamp,'root',current_timestamp,
'User Delete','winWorkUser/delete','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391314',1,'root',current_timestamp,'root',current_timestamp,
'User Detail','winWorkUser/detail','get',0,0,'2e29940ddd734441982c5625d5391300');

-- WINWORK_ROLE
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391315',1,'root',current_timestamp,'root',current_timestamp,
'Role List Ajax','winWorkRole/listAjax','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391316',1,'root',current_timestamp,'root',current_timestamp,
'Role Create','winWorkRole/create','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391317',1,'root',current_timestamp,'root',current_timestamp,
'Role Create','winWorkRole/create','post',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391318',1,'root',current_timestamp,'root',current_timestamp,
'Role Edit','winWorkRole/edit','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391319',1,'root',current_timestamp,'root',current_timestamp,
'Role Edit','winWorkRole/edit','post',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391320',1,'root',current_timestamp,'root',current_timestamp,
'Role Delete','winWorkRole/delete','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391321',1,'root',current_timestamp,'root',current_timestamp,
'Role Detail','winWorkRole/detail','get',0,0,'2e29940ddd734441982c5625d5391300');

-- WINWORK_MENU
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391322',1,'root',current_timestamp,'root',current_timestamp,
'Menu List Ajax','winWorkMenu/listAjax','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391323',1,'root',current_timestamp,'root',current_timestamp,
'Menu Create','winWorkMenu/create','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391324',1,'root',current_timestamp,'root',current_timestamp,
'Menu Create','winWorkMenu/create','post',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391325',1,'root',current_timestamp,'root',current_timestamp,
'Menu Edit','winWorkMenu/edit','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391326',1,'root',current_timestamp,'root',current_timestamp,
'Menu Edit','winWorkMenu/edit','post',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391327',1,'root',current_timestamp,'root',current_timestamp,
'Menu Delete','winWorkMenu/delete','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391328',1,'root',current_timestamp,'root',current_timestamp,
'Menu Detail','winWorkMenu/detail','get',0,0,'2e29940ddd734441982c5625d5391300');

-- WINWORK_OPTION
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391329',1,'root',current_timestamp,'root',current_timestamp,
'Option List Ajax','winWorkOption/listAjax','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391330',1,'root',current_timestamp,'root',current_timestamp,
'Option Create','winWorkOption/create','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391331',1,'root',current_timestamp,'root',current_timestamp,
'Option Create','winWorkOption/create','post',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391332',1,'root',current_timestamp,'root',current_timestamp,
'Option Edit','winWorkOption/edit','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391333',1,'root',current_timestamp,'root',current_timestamp,
'Option Edit','winWorkOption/edit','post',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391334',1,'root',current_timestamp,'root',current_timestamp,
'Option Delete','winWorkOption/delete','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391335',1,'root',current_timestamp,'root',current_timestamp,
'Option Detail','winWorkOption/detail','get',0,0,'2e29940ddd734441982c5625d5391300');

-- WINWORK_SETTING
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391336',1,'root',current_timestamp,'root',current_timestamp,
'Setting List Ajax','winWorkSetting/listAjax','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391337',1,'root',current_timestamp,'root',current_timestamp,
'Setting Create','winWorkSetting/create','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391338',1,'root',current_timestamp,'root',current_timestamp,
'Setting Create','winWorkSetting/create','post',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391339',1,'root',current_timestamp,'root',current_timestamp,
'Setting Edit','winWorkSetting/edit','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391340',1,'root',current_timestamp,'root',current_timestamp,
'Setting Edit','winWorkSetting/edit','post',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391341',1,'root',current_timestamp,'root',current_timestamp,
'Setting Delete','winWorkSetting/delete','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391342',1,'root',current_timestamp,'root',current_timestamp,
'Setting Detail','winWorkSetting/detail','get',0,0,'2e29940ddd734441982c5625d5391300');

-- WINWORK_SEQUENCE
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391343',1,'root',current_timestamp,'root',current_timestamp,
'Sequence List Ajax','winWorkSequence/listAjax','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391344',1,'root',current_timestamp,'root',current_timestamp,
'Sequence Create','winWorkSequence/create','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391345',1,'root',current_timestamp,'root',current_timestamp,
'Sequence Create','winWorkSequence/create','post',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391346',1,'root',current_timestamp,'root',current_timestamp,
'Sequence Edit','winWorkSequence/edit','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391347',1,'root',current_timestamp,'root',current_timestamp,
'Sequence Edit','winWorkSequence/edit','post',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391348',1,'root',current_timestamp,'root',current_timestamp,
'Sequence Delete','winWorkSequence/delete','get',0,0,'2e29940ddd734441982c5625d5391300');
Insert into winwork_menu
(ID,ACTIVE,CREATE_BY,CREATE_DATE,LAST_UPDATE_BY,LAST_UPDATE_DATE,
NAME,MENU_PATH,MENU_METHOD,DISPLAY_MENU,MENU_ORDER,PARENT_MENU_ID)
values
('2e29940ddd734441982c5625d5391349',1,'root',current_timestamp,'root',current_timestamp,
'Sequence Detail','winWorkSequence/detail','get',0,0,'2e29940ddd734441982c5625d5391300');

Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391300');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391301');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391302');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391303');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391304');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391305');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391306');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391307');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391308');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391309');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391310');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391311');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391312');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391313');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391314');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391315');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391316');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391317');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391318');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391319');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391320');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391321');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391322');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391323');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391324');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391325');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391326');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391327');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391328');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391329');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391330');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391331');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391332');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391333');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391334');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391335');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391336');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391337');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391338');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391339');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391340');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391341');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391342');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391343');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391344');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391345');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391346');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391347');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391348');
Insert into winwork_role_menu (ROLE_ID,MENU_ID) values ('c6c2c31d117b4aae879b776349e116f5',
'2e29940ddd734441982c5625d5391349');
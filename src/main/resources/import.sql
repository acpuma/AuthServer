INSERT INTO auth_role(Name, Created_Date, Updated_Date) values ('ROLE_USER', '2016-11-28 08:35:35', '2016-11-28 10:35:35');
INSERT INTO auth_role(Name, Created_Date, Updated_Date) values ('ROLE_ADMIN', '2016-11-28 08:35:35', '2016-11-28 10:35:35');

INSERT INTO auth_user(Id,Password, Salt, Email, Enabled, Created_Date, Updated_Date) values (1, '243b2e76fd07d358524dc6f7a3bb83c7315c0d07', '1234', 'user', 1, '2016-11-28 08:35:35', '2016-11-28 10:35:35');
INSERT INTO auth_user(Id,Password, Salt, Email, Enabled, Created_Date, Updated_Date) values (2, 'e53343d3490f48bb362002cb11d591972d831b3d', '1234', 'admin', 1, '2016-11-28 08:35:35', '2016-11-28 10:35:35');

INSERT INTO auth_user_roles(USER_ID, ROLE_ID) values (1, 1);
INSERT INTO auth_user_roles(USER_ID, ROLE_ID) values (2, 2);
set search_path to foundation_project_app;

-- create some base user roles
insert into user_roles (name) values ('ADMIN'), ('BASIC_USER'), ('APP_OWNER');


-- create access for app users
insert into app_users (first_name, last_name, username, password,  role_id)

values
    ('Michael', 'McMullan', 'mbmcmullan', '123456789',  1),
    ('Buford', 'McSnickens', 'bmcsnickens', 'b1212345',  2),
    ('Purvis', 'Jenkins', 'pjenkins', 'pj123456',  3),
    ('Wezley', 'Singleton', 'wsingleton', 'p4ssw0rd', 2);
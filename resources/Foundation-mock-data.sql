set search_path to foundation_project_app;

-- create some base user roles
insert into user_roles (name) values ('ADMIN'), ('BASIC_USER'), ('APP_OWNER');

-- create access for app users
insert into app_users (first_name, last_name, email, username, password, profile_pic, role_id)

values
    ('Michael', 'McMullan', 'mcmullan2013@gmail.com', 'mbmcmullan', '123456789', 'www.radomlink.com', 1),
    ('Buford', 'McSnickens', 'bmcsnickens@gmail.com', 'bmcsnickens', 'b1212345', 'www.radomlink.com', 2),
    ('Purvis', 'Jenkins', 'pjenkins@gmail.com', 'pjenkins', 'pj123456', 'www.radomlink.com', 3),
    ('Wezley', 'Singleton', 'Wezley.singleton@gmail.com', 'wsingleton', 'p4ssw0rd', 'www.radomlink.com', 2);
                                                                                                                                                                                                                                                                                                                                                                            
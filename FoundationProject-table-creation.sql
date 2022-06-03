create schema foundation_project_app;
set search_path to foundation_project_app;

create table user_roles (
    id int generated always as identity primary key,
    name varchar not null unique 
);

create table app_users (
    id int generated always as identity primary key,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
    username varchar NOT NULL unique check (length(username) > 4),
    password varchar NOT NULL CHECK (length(password) >= 8),
    role_id int,

    constraint app_users_fk
    foreign key (role_id)
    references user_roles(id)
);

-- create some base user roles
insert into user_roles (name) values ('ADMIN'), ('BASIC_USER'), ('APP_OWNER');


-- create access for app users
insert into app_users (first_name, last_name, username, password,  role_id)

values
    ('Michael', 'McMullan', 'mbmcmullan', '123456789',  1),
    ('Buford', 'McSnickens', 'bmcsnickens', 'b1212345',  2),
    ('Purvis', 'Jenkins', 'pjenkins', 'pj123456',  3),
    ('Wezley', 'Singleton', 'wsingleton', 'p4ssw0rd', 2);

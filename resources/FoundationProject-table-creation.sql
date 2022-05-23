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
    email varchar NOT NULL unique,
    username varchar NOT NULL unique check (length(username) > 4),
    password varchar NOT NULL CHECK (length(password) >= 8),
    profile_pic varchar,
    role_id int,

    constraint app_users_fk
    foreign key (role_id)
    references user_roles(id)
);

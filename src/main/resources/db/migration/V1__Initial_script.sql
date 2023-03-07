create or replace table users(
    id Serial not null primary key,
    full_name varchar(128),
    email varchar(128),
    login varchar(128),
    password varchar(512),
    job_title varchar(64),
    phone_number varchar(20),
    cn_ad_user varchar(1024),
    status int,
    create_date timestamp,
    create_by int
);

create or replace table roles(
    id Serial not null primary key,
    role_name varchar(32),
    create_date timestamp,
	is_read boolean,
	is_write boolean
);

create or replace table users_roles(
    id Serial not null primary key,
    users_id int,
    roles_id int,
    create_date timestamp,
    create_by int,
    active boolean
);

create or replace table groups(
    id Serial not null primary key,
    group_name varchar(128),
    active boolean,
    status int,
    create_date timestamp,
    owner_id int,
    create_by int
);

create or replace table users_groups(
    id Serial not null primary key,
    users_id int,
    groups_id int,
    create_date timestamp,
    create_by int
);
create or replace table users(
    id Serial not null primary key,
    full_name varchar(128),
    email varchar(128),
    login varchar(128),
    password varchar(512),
    job_title varchar(64),
    department varchar(256),
    phone_number varchar(20),
    cn_ad_user varchar(1024),
    status int,
    create_date timestamp,
    create_by_id int
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
    create_by_id int,
    active boolean
);

create or replace table groups(
    id Serial not null primary key,
    group_name varchar(128),
    active boolean,
    approve boolean,
    status int,
    create_date timestamp,
    owner_id int,
    create_by_id int
);

create table projects_groups(
	id Serial not null primary key,
	projects_id int,
	groups_id int,
	create_date timestamp,
	create_by_id int
);

create table projects(
	id Serial,
	name varchar(128),
	description text,
	prefix varchar(3),
	create_date timestamp,
	type int
);

create table comments(
	id Serial,
	user_id int,
	create_date timestamp,
	response boolean,
    comment_id int,
	body text
);

create table category(
	id Serial ,
	name varchar(256),
	project_id int
);

create table status(
	id Serial,
	name varchar(64),
	orders int,
	closed boolean,
	track_id int,
	project_id int,
	group_id int
);

create table track(
	id Serial,
	name varchar(256),
	project_id int
);

create table sprints(
	id Serial,
	start_date timestamp,
	end_date timestamp,
	create_date timestamp,
	name varchar(128),
	user_id int,
	closed boolean
);

create table sprint_issue(
	id Serial,
	add_user_by_id int,
	create_date timestamp,
	sprint_id int,
	issue_id int
);


create table bind_issue(
	id Serial,
	bind_user_id int,
	create_date timeStamp,
	issue_id int,
	child_issue_id int
);

create table projects_groups(
	projects_id int,
	groups_id int
);


create table issues(
	id Serial,
	external_id varchar(32),
	track_name varchar(128),
	short_description varchar(128),
	description_body text,
	resolution text,
	priority int,
	status_id int,
	project_id int,
	category_id int,
	create_by_id int,
	assign_id int,
	assign_group_id int,
	create_date timestamp,
	start_date timestamp,
	last_change_date timestamp,
	end_date timestamp,
	closed boolean,
	lose boolean,
	parent boolean,
	version varchar(128),
	state int,
	planing_time_cost int,
	actual_time_cost int
);

create table comments_issues(
	id Serial,
	users_id int,
	issues_id int,
	comments_id int,
	create_date timestamp
);

create table files(
	id Serial,
	name varchar(256),
	path varchar(1024),
	create_date timestamp,
	user_id int
);

create table issue_files(
	id Serial,
	issues_id int,
	attachment_id int,
	create_date timestamp
);

create table actions(
	id Serial,
	create_date timestamp,
	describe varchar(256),
	create_by_id int
);


create table issue_actions(
	id Serial,
	issue_id int,
	action_id int,
	create_date timestamp
);


create table time_cost(
	id Serial,
	issue_id int,
	create_by_id int,
	comment varchar(1024),
	time int,
	date_start date,
	date_stop date,
	create_date timestamp,
	day boolean,
	hours boolean,
	minutes boolean
);


create table issues_time_cost(
	id Serial,
	issue_id int,
	time_cost_id int,
	create_date timestamp
);

create table pages(
	id Serial not null primary key,
	external_id varchar(32),
	project_id int,
	create_by_id int,
	admin_by_id int,
	create_date timestamp,
	last_change_date timestamp,
	stared bool,
	open_comment bool,
	child_page bool,
	child_id int,
	short_name_page varchar(128),
	body text
);

create table childpages(
	id Serial not null primary key,
	external_id varchar(32),
	create_by_id int,
	admin_by_id int,
	create_date timestamp,
	last_change_date timestamp,
	stared bool,
	open_comment bool,
	short_name_page varchar(128),
	body text
);

create table pages_childpages(
	id Serial,
	pages_id int,
	childpages_id int,
	create_date timestamp
);
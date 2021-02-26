create sequence hibernate_sequence start 1 increment 1;

create table contacts (
    id              int8            not null,
    created_date    timestamp       not null,
    email           varchar(100),
    skype           varchar(100),
    telephone       varchar(50),
    primary key (id)
);

create table users (
    id              int8            not null,
    created_date    timestamp       not null,
    email           varchar(100)    not null,
    password        varchar(255)    not null,
    primary key (id)
);

create table vacancies (
    created_date    timestamp       not null,
    company_name    varchar(255)    not null,
    expected_salary float8,
    last_update     timestamp,
    link            varchar(255),
    position        varchar(255),
    status          varchar(100),
    recruiter_contact_id int8       not null,
    user_id         int8            not null,
    primary key (recruiter_contact_id)
);

alter table if exists vacancies
    add constraint FKcainuvx9t9824rdlk22ovbi8c
    foreign key (recruiter_contact_id)
    references contacts;

alter table if exists vacancies
    add constraint FKlk4s3j11s10gc7biicj1xocoq
    foreign key (user_id)
    references users;
create table exercises (
    id uuid not null,
    name varchar(255) not null unique,
    primary key (id)
);
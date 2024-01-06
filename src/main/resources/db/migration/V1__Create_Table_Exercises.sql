create EXTENSION if not exists "uuid-ossp";
create table exercises (
    deleted boolean,
    created_at timestamp(6),
    updated_at timestamp(6),
    id uuid not null,
    created_by varchar(255),
    description varchar(255),
    image varchar(255),
    name varchar(255) not null unique,
    rest_time varchar(255),
    updated_by varchar(255),
    video varchar(255),
    primary key (id)
);
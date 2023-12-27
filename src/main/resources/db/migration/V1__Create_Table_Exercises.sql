create EXTENSION if not exists "uuid-ossp";
create table exercises (
    rest_time integer,
    id uuid not null default uuid_generate_v4(),
    description varchar(255),
    image varchar(255),
    name varchar(255) not null unique,
    video varchar(255),
    primary key (id)
);
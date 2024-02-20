create table categories (
    id uuid not null,
    name varchar(255) not null unique,
    deleted boolean,
    created_at timestamp(6) with time zone,
    created_by varchar(255),
    updated_at timestamp(6) with time zone,
    updated_by varchar(255),
    deleted_at timestamp(6) with time zone,
    deleted_by varchar(255),
    primary key (id)
);
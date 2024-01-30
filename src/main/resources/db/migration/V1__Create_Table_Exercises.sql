create EXTENSION if not exists "uuid-ossp";

create table exercises (
   id uuid not null,
   name varchar(255) not null unique,
   description varchar(255),
   image varchar(255),
   video varchar(255),
   rest_time varchar(255),
   intensity integer,
   deleted boolean,
   created_at timestamp(6),
   created_by varchar(255),
   updated_at timestamp(6),
   updated_by varchar(255),
   primary key (id)
);
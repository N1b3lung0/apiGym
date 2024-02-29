create EXTENSION if not exists "uuid-ossp";

create table exercises (
   id uuid not null,
   name varchar(255) not null unique,
   description varchar(255),
   image_name varchar(255),
   image_description varchar(255),
   image_url varchar(255),
   video_name varchar(255),
   video_description varchar(255),
   video_url varchar(255),
   intensity integer,
   deleted boolean,
   created_at timestamp(6) with time zone,
   created_by varchar(255),
   updated_at timestamp(6) with time zone,
   updated_by varchar(255),
   deleted_at timestamp(6) with time zone,
   deleted_by varchar(255),
   primary key (id)
);
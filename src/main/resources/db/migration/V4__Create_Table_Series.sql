create table series (
    id uuid not null,
    exercise_series_id uuid,
    serial_number integer,
    repetitions_to_do integer,
    repetitions_done integer,
    created_at timestamp(6) with time zone,
    created_by varchar(255),
    updated_at timestamp(6) with time zone,
    updated_by varchar(255),
    primary key (id)
);

alter table if exists series
    add constraint FK1qx5l532ouca7x77w1gfaujh9
    foreign key (exercise_series_id)
    references exercise_series;
create table workouts (
    id uuid not null,
    start_workout timestamp(6) with time zone,
    end_workout timestamp(6) with time zone,
    created_at timestamp(6) with time zone,
    created_by varchar(255),
    updated_at timestamp(6) with time zone,
    updated_by varchar(255),
    primary key (id)
);
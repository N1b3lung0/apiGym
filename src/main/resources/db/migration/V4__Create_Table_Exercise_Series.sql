create table exercise_series (
    id uuid not null,
    exercise_id uuid unique,
    start_series timestamp(6) with time zone,
    end_series timestamp(6) with time zone,
    rest_time smallint check (rest_time between 0 and 4),
    created_at timestamp(6) with time zone,
    created_by varchar(255),
    updated_at timestamp(6) with time zone,
    updated_by varchar(255),
    primary key (id)
);

alter table exercise_series
add constraint FKtnyf6oasxae29c5upt1f6extb foreign key (exercise_id) references exercises;
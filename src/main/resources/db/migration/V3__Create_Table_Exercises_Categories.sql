create table exercises_categories (
    category_id uuid not null,
    exercise_id uuid not null,
    primary key (category_id, exercise_id)
);

alter table exercises_categories
add constraint FKt9ahncaa6qk278o2c13q6tre9 foreign key (category_id) references categories;

alter table exercises_categories
add constraint FKo2elmwfwoq66su17ve96a6xp7 foreign key (exercise_id) references exercises;
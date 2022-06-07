create table movie
(
    id   uuid not null,
    name varchar(50),
    primary key (id)
);

create table screening
(
    id        uuid not null,
    cinema_id uuid,
    room_id   uuid,
    movie_id  uuid,
    date      date,
    primary key (id)
);

create table room
(
    id        uuid not null,
    number    integer,
    cinema_id uuid,
    seats     jsonb,
    primary key (id)
);

create table cinema
(
    id   uuid not null,
    city varchar(20),
    primary key (id)
);
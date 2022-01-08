create schema if not exists hexagonal;
drop table if exists hexagonal.LYRICS;

create table hexagonal.LYRICS
(
    ID                   UUID DEFAULT gen_random_uuid(),
    PARTICIPATING_ARTIST VARCHAR(100) NOT NULL,
    LYRICS               VARCHAR(100) NOT NULL
);
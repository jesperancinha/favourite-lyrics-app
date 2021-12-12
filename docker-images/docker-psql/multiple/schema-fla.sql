create schema if not exists hexadecimal;
drop table if exists hexadecimal.LYRICS;

create table hexadecimal.LYRICS
(
    ID                   UUID DEFAULT gen_random_uuid(),
    PARTICIPATING_ARTIST VARCHAR(100) NOT NULL,
    LYRICS               VARCHAR(100) NOT NULL
);
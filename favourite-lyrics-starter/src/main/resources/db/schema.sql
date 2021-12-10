create schema if not exists hexadecimal;
drop table if exists hexadecimal.LYRICS;

create table hexadecimal.LYRICS
(
    LYRICS_ID            serial primary key not null,
    PARTICIPATING_ARTIST VARCHAR(100)       NOT NULL,
    LYRICS               VARCHAR(100)       NOT NULL
);
drop table if exists LYRICS;

create table LYRICS
(
    LYRICS_ID bigint auto_increment primary key not null,
    AUTHOR      VARCHAR(100)                      NOT NULL,
    LYRICS      VARCHAR(100)                      NOT NULL
);
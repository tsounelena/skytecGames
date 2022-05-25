create table clan
(
    id   bigserial primary key,
    name text,
    gold integer default 0
);

create table gold_track
(
    id          bigserial primary key,
    user_id     bigint,
    gold_amount integer,
    clan_id     bigint references clan
);

INSERT INTO clan (name, gold) VALUES ('test_clan1', 2);
INSERT INTO clan (name, gold) VALUES ('test_clan2', 3);
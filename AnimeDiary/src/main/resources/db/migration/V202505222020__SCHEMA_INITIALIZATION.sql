create table anime_statuses
(
    status_id   smallint generated always as identity primary key,
    status_name varchar(30) not null unique
);

create table anime_types
(
    type_id   smallint generated always as identity primary key,
    type_name varchar(30) not null unique
);

create table anime_genres
(
    genre_id   int generated always as identity primary key,
    genre_name varchar(30) not null unique
);

create table anime_studios
(
    studio_id                  bigint generated always as identity primary key,
    studio_name                varchar(50) not null,
    studio_foundation_year     smallint check (studio_foundation_year >= 1900),
    studio_founder_name        varchar(30),
    studio_official_site       varchar(50) unique,
    studio_number_of_employees int check (studio_number_of_employees >= 0)
);

create table animes
(
    anime_id                 bigint generated always as identity primary key,
    anime_name               varchar(100)  not null unique,
    anime_poster_img_path    text          not null default 'default_poster.jpg',
    anime_number_of_episodes smallint      not null check (anime_number_of_episodes >= 0),
    anime_year               smallint check (anime_year >= 1917),
    anime_rating             decimal(4, 2) not null check (anime_rating >= 0 and anime_rating <= 10),
    anime_synopsis           text          not null,
    anime_link_to_watch      text,
    anime_status             smallint      not null references anime_statuses (status_id),
    anime_type               smallint      not null references anime_types (type_id)
);

create table fact_anime_belongs_genre
(
    fabg_id bigint generated always as identity primary key,
    anime   bigint not null references animes (anime_id),
    genre   int    not null references anime_genres (genre_id)
);

create table fact_studio_working_on_anime
(
    fswoa_id bigint generated always as identity primary key,
    anime    bigint not null references animes (anime_id),
    studio   bigint not null references anime_studios (studio_id)
);

create table authorities
(
    authority_id   int generated always as identity primary key,
    authority_name varchar(30) not null unique
);

create table "users"
(
    user_id        bigint generated always as identity primary key,
    user_nickname  varchar(30) not null,
    user_email     text        not null unique,
    user_photo_url text        not null default 'default_avatar.jpg',
    user_age       smallint check (user_age >= 0),
    user_password  text        not null,
    is_non_locked  boolean     not null default true
);

create table users_authorities
(
    users_authorities_id bigint generated always as identity primary key,
    "user"               bigint not null references "users" (user_id),
    authority            int    not null references authorities (authority_id)
);

create table anime_reviews
(
    review_id       bigint generated always as identity primary key,
    anime           bigint                   not null references animes (anime_id),
    "user"          bigint                   not null references "users" (user_id),
    review_date     timestamp with time zone not null default now(),
    rate_drawing    smallint                 not null check (rate_drawing >= 0 and rate_drawing <= 10),
    rate_plot       smallint                 not null check (rate_plot >= 0 and rate_plot <= 10),
    rate_characters smallint                 not null check (rate_characters >= 0 and rate_characters <= 10),
    rate_opening    smallint                 not null check (rate_opening >= 0 and rate_opening <= 10),
    result_score    decimal(4, 2)            not null check (result_score >= 0 and result_score <= 10),
    review_text     text
);

create table comment_on_forum
(
    cof_id       bigserial primary key,
    anime        bigint                   not null references animes (anime_id),
    "user"       bigint                   not null references "users" (user_id),
    comment_date timestamp with time zone not null default now(),
    comment_text text                     not null
);

create table user_anime_list_status
(
    uals_id     bigserial primary key,
    anime       bigint  not null references animes (anime_id),
    "user"      bigint  not null references "users" (user_id),
    is_in_plans boolean not null,
    is_watching boolean not null,
    is_watched  boolean not null,
    is_dropped  boolean not null,
    is_liked    boolean not null,
    is_disliked boolean not null
);

ALTER TABLE anime_studios
    ADD CONSTRAINT unique_studio_name UNIQUE (studio_name);

ALTER TABLE animes
    DROP CONSTRAINT animes_anime_name_key;
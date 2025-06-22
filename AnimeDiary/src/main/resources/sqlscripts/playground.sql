delete from user_anime_list_status;
delete from comment_on_forum;
delete from anime_reviews;
delete from users_authorities;
delete from "users";

insert into users_authorities("user", authority) VALUES (10, 2);

delete from fact_anime_belongs_genre where anime = 26;
delete from fact_studio_working_on_anime where anime = 26;
delete from comment_on_forum where anime = 23;
delete from user_anime_list_status where anime = 23;
delete from animes where anime_id = 26;
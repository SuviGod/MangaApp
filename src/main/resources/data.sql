insert into ROLES(name)
values ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_MODERATOR');


insert into users(email, nickname, password)
values ('FirstUserEmail', 'FirstUserNickname', 'FirstUserPassword');

INSERT INTO creators(name, description) VALUES ('MOCHIZUKI Jun', '');

INSERT INTO groups(group_name, link1) values ('FirstTranslationTeam', 'mylink1.com');

insert into mangas(name, author_id, artist_id, release_year, group_id, alternative_names, add_datetime, update_datetime, approval_datetime, preview_image_path, is_approved)
VALUES ('Pandora hearts', 1, 1, 1000, 1, '', now(), now(), now(), 'path', false);

insert into ROLES(name)
values ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_MODERATOR');

insert into users(email, nickname, password)
values ('FirstUserEmail', 'FirstUserNickname', 'FirstUserPassword');

INSERT INTO creator(name, description) VALUES ('MOCHIZUKI Jun', '');

insert into manga(name, author_id, artist_id, release_year, translator_id, alternative_names, add_datetime, update_datetime, approval_datetime, preview_image_path, is_approved)
VALUES ('Pandora hearts', 1, 1, 1000, 1, '', now(), now(), now(), 'path', false);

ALTER TABLE `team`
    ADD COLUMN `team_logo_src` VARCHAR(100) COMMENT '로고 img src' AFTER `hometown`;

UPDATE `team`
SET team_logo_src = '/img/logo_yellow.jpg'
WHERE id = 1
;

INSERT INTO `team`
    (`name`, `hometown`, `team_logo_src`)
VALUES ('YKFC', '서울 마포구', '/img/otherteam/ykfc_logo.jpg'),
       ('FC PASSIONE', '서울 강남구', '/img/otherteam/fcpassione_logo.jpg'),
       ('ZENITHFC', '서울 서초구', null),
       ('풋플러FC', '서울 금천구', '/img/otherteam/footplr_logo.jpg'),
       ('IEMU11', '서울 강북구', '/img/otherteam/iemu11_logo.jpg')
;
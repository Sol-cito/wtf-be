RENAME TABLE `match` TO `match_result`;

ALTER TABLE `match_result`
    DROP COLUMN `opposing_team_name`;

ALTER TABLE `match_result`
    ADD `opposing_team_id` INT NOT NULL COMMENT '팀 id(FK)' AFTER `id`;

UPDATE `match_result`
SET `opposing_team_id` = 2
WHERE `id` = 1;

ALTER TABLE `match_result`
    ADD FOREIGN KEY (`opposing_team_id`) REFERENCES `team` (`id`);
ALTER TABLE `score`
    CHANGE `match_id` `match_result_id` INT NOT NULL COMMENT '경기 결과 id(FK)';

ALTER TABLE `score`
    DROP COLUMN `scorer_name`;

ALTER TABLE `score`
    ADD `player_id` INT NOT NULL COMMENT '플레이어 id(FK)' AFTER `id`;


ALTER TABLE `score`
    ADD FOREIGN KEY (`match_result_id`) REFERENCES `match_result` (`id`);

UPDATE `score`
SET player_id = 4
WHERE id = 1;

UPDATE `score`
SET player_id = 5
WHERE id = 2;

UPDATE `score`
SET player_id = 6
WHERE id = 3;

ALTER TABLE `score`
    ADD FOREIGN KEY (`player_id`) REFERENCES `player` (`id`);


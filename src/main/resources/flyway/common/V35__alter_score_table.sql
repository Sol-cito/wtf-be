ALTER TABLE `score`
    MODIFY COLUMN `player_id` INT NULL COMMENT '플레이어 id(FK)' AFTER `match_result_id`;
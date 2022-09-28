ALTER TABLE `match`
    ADD COLUMN `match_type_id` INT NOT NULL DEFAULT 1 COMMENT '매치 타입 id(FK)' AFTER `opposing_team_name`;

ALTER TABLE `match` ADD FOREIGN KEY(`match_type_id`) REFERENCES `match_type`(`id`);

ALTER TABLE `match`
    ADD COLUMN `match_location` VARCHAR(100) COMMENT '매치 장소' AFTER `match_type_id`;
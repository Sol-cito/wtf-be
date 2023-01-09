CREATE TABLE `match_type`
(
    `id`              INT          NOT NULL AUTO_INCREMENT COMMENT 'id',
    `match_type_name` VARCHAR(100) NOT NULL COMMENT '매치 타입명',
    `match_season`    VARCHAR(4) COMMENT '매치 시즌(연도)',
    PRIMARY KEY (id)
) COMMENT = '매치 타입'
;

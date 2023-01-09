CREATE TABLE `team`
(
    `id`       INT          NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`     VARCHAR(100) NOT NULL COMMENT '팀명',
    `hometown` VARCHAR(100) NULL COMMENT '연고지',
    PRIMARY KEY (id)
) COMMENT = '팀'
;
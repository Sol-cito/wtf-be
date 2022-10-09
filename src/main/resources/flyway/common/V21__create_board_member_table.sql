CREATE TABLE `board_member`
(
    `id`            INT          NOT NULL AUTO_INCREMENT COMMENT 'id',
    `player_id`     INT          NOT NULL COMMENT 'player id(FK)',
    `board_name`    VARCHAR(100) NOT NULL COMMENT '지위명',
    `assigned_date` DATETIME     NOT NULL DEFAULT NOW() COMMENT '최초 임명일',
    PRIMARY KEY (id),
    FOREIGN KEY (player_id) REFERENCES `player` (id)
) COMMENT = '운영진 정보'
;
CREATE TABLE `score`
(
    `id`          INT          NOT NULL AUTO_INCREMENT COMMENT 'id',
    `match_id`    INT          NOT NULL COMMENT '매치 id',
    `scorer_name` VARCHAR(100) NOT NULL COMMENT '득점자 이름',
    `goals`       INT          NOT NULL DEFAULT 1 COMMENT '득점 개수',
    `goal_kind`   VARCHAR(10)  NOT NULL DEFAULT 'field' COMMENT '득점 유형',
    PRIMARY KEY (id)
) COMMENT = '스코어 기록'
;
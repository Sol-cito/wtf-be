CREATE TABLE `team_history`
(
    `id`        INT      NOT NULL AUTO_INCREMENT COMMENT 'id',
    `year`      VARCHAR(8)         NOT NULL COMMENT '연도',
    `history`   VARCHAR(4000)      NOT NULL COMMENT '연혁',
    `regi_date` DATETIME     NOT NULL DEFAULT NOW() COMMENT '등록일',
    `modi_date` DATETIME     NOT NULL DEFAULT NOW() COMMENT '최종수정일',
    PRIMARY KEY (id)
) COMMENT = '연혁'
;

INSERT INTO
    `team_history`(`year`, `history`)
VALUES
    ('2019', '창단'),
    ('2019', '강북구 K7 준우승'),
    ('2020', '강동구 K7 우승'),
    ('2021', '서초구 K7 준우승'),
    ('2022', '서초구 K7 우승, K6 진출')
;
CREATE TABLE `match`
(
    `id`       INT          NOT NULL AUTO_INCREMENT COMMENT 'match id',
    `opposing_team_name` VARCHAR(100) NOT NULL COMMENT '상대팀',
    `goals_scored` INT NOT NULL default 0 COMMENT '스코어(넣은 골)',
    `goals_lost` INT NOT NULL default 0 COMMENT '스코어(먹힌 골)',
    `match_result` VARCHAR(10) NOT NULL default 'WIN' COMMENT '경기 결과',
    `shoot_out_yn` VARCHAR(1) NOT NULL default 'N' COMMENT '승부차기여부yn',
    `match_date` DATETIME NOT NULL default now() COMMENT '시합 날짜',
    PRIMARY KEY (id)
) COMMENT = '경기 결과'
;

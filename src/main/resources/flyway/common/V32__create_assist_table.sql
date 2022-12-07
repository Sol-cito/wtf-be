CREATE TABLE `assist`
(
    `id`                  INT NOT NULL AUTO_INCREMENT COMMENT 'id',
    `match_result_id`	  INT NOT NULL COMMENT '매치 id(FK)',
    `score_id`            INT NOT NULL COMMENT '스코어(골) id(FK)',
    `player_id`           INT COMMENT '어시스트 플레이어(FK)',
    PRIMARY KEY (id),
    FOREIGN KEY (match_result_id) REFERENCES `match_result` (id),
    FOREIGN KEY (score_id) REFERENCES `score` (id),
    FOREIGN KEY (player_id) REFERENCES `player` (id)
) COMMENT = '어시스트 기록'
;
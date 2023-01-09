ALTER TABLE `score`
    CHANGE `goal_kind` `goal_type`  VARCHAR(10)  NOT NULL DEFAULT 'Field' COMMENT '득점 유형';


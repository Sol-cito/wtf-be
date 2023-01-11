# assist table

ALTER TABLE `assist`
    ADD `regi_datetime` DATETIME NOT NULL DEFAULT NOW() COMMENT '최초 등록일' AFTER `player_id`
;

ALTER TABLE `assist`
    ADD `regi_user` VARCHAR(100) NULL COMMENT '등록한 user' AFTER `regi_datetime`
;

ALTER TABLE `assist`
    ADD `modi_datetime` DATETIME NOT NULL DEFAULT NOW() COMMENT '수정일' AFTER `regi_user`
;

ALTER TABLE `assist`
    ADD `modi_user` VARCHAR(100) NULL COMMENT '수정한 user' AFTER `modi_datetime`
;

# board_member table

ALTER TABLE `board_member`
    ADD `regi_datetime` DATETIME NOT NULL DEFAULT NOW() COMMENT '최초 등록일' AFTER `assigned_date`
;

ALTER TABLE `board_member`
    ADD `regi_user` VARCHAR(100) NULL COMMENT '등록한 user' AFTER `regi_datetime`
;

ALTER TABLE `board_member`
    ADD `modi_datetime` DATETIME NOT NULL DEFAULT NOW() COMMENT '수정일' AFTER `regi_user`
;

ALTER TABLE `board_member`
    ADD `modi_user` VARCHAR(100) NULL COMMENT '수정한 user' AFTER `modi_datetime`
;

# email_receiver table

ALTER TABLE `email_receiver`
    CHANGE `regi_date` `regi_datetime` DATETIME NOT NULL DEFAULT NOW() COMMENT '최초 등록일'
;

ALTER TABLE `email_receiver`
    ADD `regi_user` VARCHAR(100) NULL COMMENT '등록한 user' AFTER `regi_datetime`
;

ALTER TABLE `email_receiver`
    ADD `modi_datetime` DATETIME NOT NULL DEFAULT NOW() COMMENT '수정일' AFTER `regi_user`
;

ALTER TABLE `email_receiver`
    ADD `modi_user` VARCHAR(100) NULL COMMENT '수정한 user' AFTER `modi_datetime`
;

# match_result table

ALTER TABLE `match_result`
    ADD `regi_datetime` DATETIME NOT NULL DEFAULT NOW() COMMENT '최초 등록일' AFTER `match_date`
;

ALTER TABLE `match_result`
    ADD `regi_user` VARCHAR(100) NULL COMMENT '등록한 user' AFTER `regi_datetime`
;

ALTER TABLE `match_result`
    ADD `modi_datetime` DATETIME NOT NULL DEFAULT NOW() COMMENT '수정일' AFTER `regi_user`
;

ALTER TABLE `match_result`
    ADD `modi_user` VARCHAR(100) NULL COMMENT '수정한 user' AFTER `modi_datetime`
;

# match_type table

ALTER TABLE `match_type`
    ADD `regi_datetime` DATETIME NOT NULL DEFAULT NOW() COMMENT '최초 등록일' AFTER `match_type_name`
;

ALTER TABLE `match_type`
    ADD `regi_user` VARCHAR(100) NULL COMMENT '등록한 user' AFTER `regi_datetime`
;

ALTER TABLE `match_type`
    ADD `modi_datetime` DATETIME NOT NULL DEFAULT NOW() COMMENT '수정일' AFTER `regi_user`
;

ALTER TABLE `match_type`
    ADD `modi_user` VARCHAR(100) NULL COMMENT '수정한 user' AFTER `modi_datetime`
;

# player table

ALTER TABLE `player`
    ADD `regi_datetime` DATETIME NOT NULL DEFAULT NOW() COMMENT '최초 등록일' AFTER `cur_yn`
;

ALTER TABLE `player`
    ADD `regi_user` VARCHAR(100) NULL COMMENT '등록한 user' AFTER `regi_datetime`
;

ALTER TABLE `player`
    ADD `modi_datetime` DATETIME NOT NULL DEFAULT NOW() COMMENT '수정일' AFTER `regi_user`
;

ALTER TABLE `player`
    ADD `modi_user` VARCHAR(100) NULL COMMENT '수정한 user' AFTER `modi_datetime`
;

# score table

ALTER TABLE `score`
    ADD `regi_datetime` DATETIME NOT NULL DEFAULT NOW() COMMENT '최초 등록일' AFTER `goal_type`
;

ALTER TABLE `score`
    ADD `regi_user` VARCHAR(100) NULL COMMENT '등록한 user' AFTER `regi_datetime`
;

ALTER TABLE `score`
    ADD `modi_datetime` DATETIME NOT NULL DEFAULT NOW() COMMENT '수정일' AFTER `regi_user`
;

ALTER TABLE `score`
    ADD `modi_user` VARCHAR(100) NULL COMMENT '수정한 user' AFTER `modi_datetime`
;

# team table

ALTER TABLE `team`
    ADD `regi_datetime` DATETIME NOT NULL DEFAULT NOW() COMMENT '최초 등록일' AFTER `team_logo_src`
;

ALTER TABLE `team`
    ADD `regi_user` VARCHAR(100) NULL COMMENT '등록한 user' AFTER `regi_datetime`
;

ALTER TABLE `team`
    ADD `modi_datetime` DATETIME NOT NULL DEFAULT NOW() COMMENT '수정일' AFTER `regi_user`
;

ALTER TABLE `team`
    ADD `modi_user` VARCHAR(100) NULL COMMENT '수정한 user' AFTER `modi_datetime`
;

# team_history table

ALTER TABLE `team_history`
    CHANGE `regi_date` `regi_datetime` DATETIME NOT NULL DEFAULT NOW() COMMENT '최초 등록일'
;

ALTER TABLE `team_history`
    ADD `regi_user` VARCHAR(100) NULL COMMENT '등록한 user' AFTER `regi_datetime`
;

ALTER TABLE `team_history`
    CHANGE `modi_date` `modi_datetime` DATETIME NOT NULL DEFAULT NOW() COMMENT '최초 등록일'
;

ALTER TABLE `team_history`
    ADD `modi_user` VARCHAR(100) NULL COMMENT '수정한 user' AFTER `modi_datetime`
;

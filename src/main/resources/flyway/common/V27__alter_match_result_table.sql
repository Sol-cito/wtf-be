ALTER TABLE `match_result`
    MODIFY `match_date` VARCHAR(100) COMMENT '경기 날짜' AFTER `shoot_out_yn`;

UPDATE `match_result` SET match_date = "2022-09-17" WHERE id = 1;
UPDATE `match_result` SET match_date = "2022-04-23" WHERE id = 2;
UPDATE `match_result` SET match_date = "2022-06-04" WHERE id = 3;
UPDATE `match_result` SET match_date = "2022-07-02" WHERE id = 4;
UPDATE `match_result` SET match_date = "2022-08-06" WHERE id = 5;
UPDATE `match_result` SET match_date = "2022-08-20" WHERE id = 6;

ALTER TABLE `match_result`
    MODIFY `match_date` VARCHAR(10) COMMENT '경기 날짜' AFTER `shoot_out_yn`;

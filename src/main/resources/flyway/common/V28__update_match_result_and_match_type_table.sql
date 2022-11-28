UPDATE `match_result`
SET match_type_id = 2;

DELETE FROM `match_type`
WHERE id > 4;

UPDATE `match_type`
SET match_type_name = 'S league'
WHERE id = 4;

UPDATE `match_type`
SET match_type_name = 'K-league 6'
WHERE id = 3;

ALTER TABLE `match_type` DROP `match_season`;





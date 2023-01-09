ALTER TABLE player
 ADD COLUMN profile_img_src VARCHAR(100) COMMENT '프로필 사진 경로' AFTER moto;

ALTER TABLE player
    ADD COLUMN first_name_eng VARCHAR(100) COMMENT '영문 이름' AFTER name;

ALTER TABLE player
    ADD COLUMN family_name_eng VARCHAR(100) COMMENT '영문 성' AFTER first_name_eng;
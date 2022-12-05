CREATE TABLE `email_receiver`
(
    `id`       INT          NOT NULL AUTO_INCREMENT COMMENT 'id',
    `email`    VARCHAR(100) NOT NULL COMMENT 'email',
    `who`      VARCHAR(100) NOT NULL COMMENT '이메일 정보',
    `regi_date` DATETIME    NOT NULL DEFAULT NOW() COMMENT '최초 등록일',
    PRIMARY KEY (id)
) COMMENT = 'contact email to 주소'
;

INSERT INTO `email_receiver`
    (`email`, `who`)
VALUES
    ('dataenggu@gmail.com', '개발자 김다솔')
;


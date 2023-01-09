CREATE TABLE player
(
    id       INT          NOT NULL AUTO_INCREMENT COMMENT 'id',
    name     VARCHAR(100) NOT NULL COMMENT '이름',
    birth    DATE         NOT NULL COMMENT '생년월일',
    position VARCHAR(10)  NOT NULL COMMENT '포지션',
    back_no  INT          NOT NULL COMMENT '등번호',
    moto     VARCHAR(100) NULL COMMENT '좌우명(or 경기에 임하는 각오)',
    cur_yn   VARCHAR(1)   NOT NULL COMMENT '팀 소속 여부',
    PRIMARY KEY (id)
) COMMENT = 'WTF 선수 테이블'
;

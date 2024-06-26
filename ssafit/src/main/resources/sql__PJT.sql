DROP DATABASE IF EXISTS ssafit;
CREATE DATABASE ssafit DEFAULT CHARACTER SET utf8mb4;
USE ssafit;

-- create user 'ssafy'@'localhost' identified by 'ssafy';

CREATE TABLE Routine (
	id INT AUTO_INCREMENT,
    fitnessList VARCHAR(307) UNIQUE,
    PRIMARY KEY(id)
);

INSERT IGNORE INTO Routine(fitnessList)
VALUES ("[1, 12, 3, 14, 5, 16, 7, 18]"),
        ("[1, 4, 6, 17, 23, 45, 51]"),
        ("[6, 12, 13, 15, 23, 36, 37, 44, 48, 52, 54]"),
        ("[2, 4, 6, 17, 23, 35, 45, 51]"),
        ("[5, 4, 6, 17, 23, 35, 36, 45, 51]"),
        ("[3, 6, 17, 23, 35, 36, 45, 51]");

CREATE TABLE User (
	id VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    name VARCHAR(20) NOT NULL,
    address text NOT NULL,
    favorite TEXT,
    done TEXT,
    PRIMARY KEY(id)
);


INSERT INTO User(id, password, name, address, favorite, done)
VALUES ("ssafy","1234","김싸피","대전광역시 둔산로 15","[1, 2, 3]","[1, 2, 3, 4]"),
		("ssafy1","1234","김싸피","대전광역시 둔산로 15","[1, 2, 3]","[1, 2, 3, 4]");


CREATE TABLE review (
    reviewId INT AUTO_INCREMENT,
    title VARCHAR(20) NOT NULL,
    userId VARCHAR(20) NOT NULL,
    userName VARCHAR(20) NOT NULL,
    createTime TIMESTAMP DEFAULT now(),
    routineId int not null,
    content TEXT NOT NULL,
    viewCnt INT DEFAULT 0,
    PRIMARY KEY (reviewId)
);

INSERT INTO review (userId, userName, title, content, routineId) VALUES
('ssafy', '홍길동', '좋은 운동', '이 루틴 정말 좋습니다!',1),
('ssafy', '김철수', '괜찮아요', '할만한 루틴입니다.',2),
('ssafy', '이영희', '추천합니다', '친구들에게 추천할 만한 루틴입니다.',1),
('ssafy1', '박민수', '별로입니다', '기대에 미치지 못했습니다.',2),
('ssafy1', '박민수', '별로입니다', '기대에 미치지 못했습니다.',1),
('ssafy1', '박민수', '별로입니다', '기대에 미치지 못했습니다.',2),
('ssafy1', '박민수', '별로입니다', '기대에 미치지 못했습니다.',3),
('ssafy1', '최지은', '훌륭해요', '정말 훌륭한 루틴입니다. 만족합니다.',1);

select * from review;
SELECT * FROM User;
SELECT * FROM Routine ORDER BY id ASC;
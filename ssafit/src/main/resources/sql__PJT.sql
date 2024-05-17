DROP DATABASE IF EXISTS ssafit;
CREATE DATABASE ssafit DEFAULT CHARACTER SET utf8mb4;
USE ssafit;

CREATE TABLE Routine (
	id INT AUTO_INCREMENT,
    fitnessList TEXT,
    PRIMARY KEY(id)
);

INSERT INTO Routine(fitnessList) 
VALUES ("Dumbbell_Curl%Dumbbell_Seated_Curl"),
		("Dumbbell_Curl%Dumbbell_Seated_Curl%Cable_Biceps_Curl"),
	   ("Dumbbell_Curl%Dumbbell_Seated_Curl%Cable_Biceps_Curl%Barbell_Lying_Triceps_Extension");

CREATE TABLE User (
	id VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
    name VARCHAR(20) NOT NULL,
    address text NOT NULL,
    favorite TEXT,
    done TEXT,
    PRIMARY KEY(id)
);

INSERT INTO User(id, password, name, address) 
VALUES ("ssafy","1234","김싸피","대전광역시 둔산로 15"),
		("ssafy1","1234","김싸피","대전광역시 둔산로 15");
        
        
CREATE TABLE review (
    reviewId INT AUTO_INCREMENT,
    title VARCHAR(20) NOT NULL,
    userId VARCHAR(20) NOT NULL,
    userName VARCHAR(20) NOT NULL,
    createTime TIMESTAMP DEFAULT now(),
    routineList text not null,
    content TEXT NOT NULL,
    viewCnt INT DEFAULT 0,
    PRIMARY KEY (reviewId)
);

INSERT INTO review (userId, userName, title, content, routineList) VALUES
('ssafy', '홍길동', '좋은 운동', '이 루틴 정말 좋습니다!','asdf'),
('ssafy', '김철수', '괜찮아요', '할만한 루틴입니다.','asdf'),
('ssafy', '이영희', '추천합니다', '친구들에게 추천할 만한 루틴입니다.','asdf'),
('ssafy1', '박민수', '별로입니다', '기대에 미치지 못했습니다.','asdf'),
('ssafy1', '최지은', '훌륭해요', '정말 훌륭한 루틴입니다. 만족합니다.','asdf');

select * from review;        
SELECT * FROM User;
SELECT * FROM Routine;
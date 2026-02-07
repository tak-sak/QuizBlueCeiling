DROP TABLE IF EXISTS quizzes;

CREATE TABLE quizzes (
                         id INT PRIMARY KEY,
                         question_text TEXT NOT NULL,
                         choice1 VARCHAR(255),
                         choice2 VARCHAR(255),
                         choice3 VARCHAR(255),
                         choice4 VARCHAR(255),
                         answer_id INT,
                         category VARCHAR(50),
                         difficulty INT
);
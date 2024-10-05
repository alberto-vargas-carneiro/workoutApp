INSERT INTO tb_user (name, email, password) VALUES ('Bruno Garcia', 'bruno@gmail.com', '123456');
INSERT INTO tb_user (name, email, password) VALUES ('Eduardo Ribas', 'ribas@gmail.com', '123456');

INSERT INTO tb_exercise (name, video) VALUES ('Flexão', 'https://www.youtube.com/watch?v=UwRLWMcOdwI');
INSERT INTO tb_exercise (name, video) VALUES ('Supino Inclinado', 'https://www.youtube.com/watch?v=UwRLWMcOdwI');
INSERT INTO tb_exercise (name, video) VALUES ('Puxada com Triângulo', 'https://www.youtube.com/watch?v=UwRLWMcOdwI');
INSERT INTO tb_exercise (name, video) VALUES ('Rosca Barra W', 'https://www.youtube.com/watch?v=UwRLWMcOdwI');
INSERT INTO tb_exercise (name, video) VALUES ('Testa', 'https://www.youtube.com/watch?v=UwRLWMcOdwI');
INSERT INTO tb_exercise (name, video) VALUES ('Leg 45°', 'https://www.youtube.com/watch?v=UwRLWMcOdwI');

INSERT INTO tb_workout (name, date, user_id) VALUES ('Treino de Peito', TIMESTAMP WITH TIME ZONE '2022-05-25T13:00:00Z', 1);
INSERT INTO tb_workout (name, date, user_id) VALUES ('Treino de Costas', TIMESTAMP WITH TIME ZONE '2023-02-05T13:00:00Z', 2);
INSERT INTO tb_workout (name, date, user_id) VALUES ('Treino de Braço', TIMESTAMP WITH TIME ZONE '2024-01-20T13:00:00Z', 2);
INSERT INTO tb_workout (name, date, user_id) VALUES ('Treino de Perna', TIMESTAMP WITH TIME ZONE '2022-07-13T13:00:00Z', 1);

INSERT INTO tb_workout_item (workout_id, exercise_id, sets, reps, rest) VALUES (1, 1, 4, 12, 120);
INSERT INTO tb_workout_item (workout_id, exercise_id, sets, reps, rest) VALUES (1, 2, 4, 12, 120);
INSERT INTO tb_workout_item (workout_id, exercise_id, sets, reps, rest) VALUES (2, 3, 4, 12, 120);
INSERT INTO tb_workout_item (workout_id, exercise_id, sets, reps, rest) VALUES (3, 4, 4, 12, 120);
INSERT INTO tb_workout_item (workout_id, exercise_id, sets, reps, rest) VALUES (3, 5, 4, 12, 120);
INSERT INTO tb_workout_item (workout_id, exercise_id, sets, reps, rest) VALUES (4, 6, 4, 12, 120);
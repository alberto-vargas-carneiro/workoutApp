INSERT INTO tb_user (name, email, password) VALUES ('Bruno Garcia', 'bruno@gmail.com', '$2a$10$N7SkKCa3r17ga.i.dF9iy.BFUBL2n3b6Z1CWSZWi/qy7ABq/E6VpO');
INSERT INTO tb_user (name, email, password) VALUES ('Eduardo Ribas', 'ribas@gmail.com', '$2a$10$N7SkKCa3r17ga.i.dF9iy.BFUBL2n3b6Z1CWSZWi/qy7ABq/E6VpO');

INSERT INTO tb_role (authority) VALUES ('ROLE_USER');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);

INSERT INTO tb_exercise (name, video) VALUES ('Flexão', 'https://i.imgur.com/zbBLV1t.png');
INSERT INTO tb_exercise (name, video) VALUES ('Supino Inclinado', 'https://i.imgur.com/hLl9NKv.png');
INSERT INTO tb_exercise (name, video) VALUES ('Puxada com Triângulo', 'https://i.imgur.com/HELlBze.png');
INSERT INTO tb_exercise (name, video) VALUES ('Rosca Barra W', 'https://i.imgur.com/SvzC9zg.png');
INSERT INTO tb_exercise (name, video) VALUES ('Tríceps Testa', 'https://i.imgur.com/5WNjy4g.png');
INSERT INTO tb_exercise (name, video) VALUES ('Leg Press 45°', 'https://i.imgur.com/eJz6xv3.png');
INSERT INTO tb_exercise (name, video) VALUES ('Rosca Scott', 'https://i.imgur.com/8PifBi0.png');
INSERT INTO tb_exercise (name, video) VALUES ('Leg Press Horizontal', 'https://i.imgur.com/VNXi6vK.png');
INSERT INTO tb_exercise (name, video) VALUES ('Agachamento Livre', 'https://i.imgur.com/uh01OOh.png');
INSERT INTO tb_exercise (name, video) VALUES ('Cadeira Extensora', 'https://i.imgur.com/vtuaK5F.png');
INSERT INTO tb_exercise (name, video) VALUES ('Cross Over', 'https://i.imgur.com/F092gwl.png');
INSERT INTO tb_exercise (name, video) VALUES ('Remada Curvada', 'https://i.imgur.com/usRrRIb.png');
INSERT INTO tb_exercise (name, video) VALUES ('Puxada', 'https://i.imgur.com/AOvz0PY.png');
INSERT INTO tb_exercise (name, video) VALUES ('Tríceps Polia', 'https://i.imgur.com/9nhspju.png');
INSERT INTO tb_exercise (name, video) VALUES ('Desenvolvimento', 'https://i.imgur.com/nfrS3jS.png');

INSERT INTO tb_workout (name, date, user_id) VALUES ('Treino de Peito', TIMESTAMP WITH TIME ZONE '2022-05-25T13:00:00Z', 1);
INSERT INTO tb_workout (name, date, user_id) VALUES ('Treino de Costas', TIMESTAMP WITH TIME ZONE '2023-02-05T13:00:00Z', 2);
INSERT INTO tb_workout (name, date, user_id) VALUES ('Treino de Braço', TIMESTAMP WITH TIME ZONE '2024-01-20T13:00:00Z', 2);
INSERT INTO tb_workout (name, date, user_id) VALUES ('Treino de Perna', TIMESTAMP WITH TIME ZONE '2022-07-13T13:00:00Z', 1);

INSERT INTO tb_workout_item (workout_id, exercise_id, set_number, reps, rest, weight) VALUES (1, 1, 1, '12', 60, 20);
INSERT INTO tb_workout_item (workout_id, exercise_id, set_number, reps, rest, weight) VALUES (1, 1, 2, '10', 100, 30);
INSERT INTO tb_workout_item (workout_id, exercise_id, set_number, reps, rest, weight) VALUES (1, 1, 3, '8', 120, 55);
INSERT INTO tb_workout_item (workout_id, exercise_id, set_number, reps, rest, weight) VALUES (1, 2, 4, '12', 120, 10);
INSERT INTO tb_workout_item (workout_id, exercise_id, set_number, reps, rest, weight) VALUES (2, 3, 4, '12', 120, 70);
INSERT INTO tb_workout_item (workout_id, exercise_id, set_number, reps, rest, weight) VALUES (3, 4, 4, '12', 120, 40);
INSERT INTO tb_workout_item (workout_id, exercise_id, set_number, reps, rest, weight) VALUES (3, 5, 4, '12', 120, 20);
INSERT INTO tb_workout_item (workout_id, exercise_id, set_number, reps, rest, weight) VALUES (4, 6, 4, '12', 120, 60);
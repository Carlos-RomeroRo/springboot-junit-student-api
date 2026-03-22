CREATE TABLE IF NOT EXISTS students (
                                        id BIGSERIAL PRIMARY KEY,
                                        name VARCHAR(255),
    university_program VARCHAR(255),
    age INTEGER,
    average DOUBLE PRECISION,
    current_semester INTEGER
    );

INSERT INTO students (name, university_program, age, average, current_semester) VALUES
                                                                                    ('Carlos Romero', 'Software Engineering', 22, 4.3, 7),
                                                                                    ('Laura Gomez', 'Computer Science', 21, 4.6, 6),
                                                                                    ('Andres Martinez', 'Information Systems', 23, 4.1, 8),
                                                                                    ('Maria Lopez', 'Data Science', 20, 4.8, 5),
                                                                                    ('Juan Perez', 'Cybersecurity', 24, 3.9, 9),
                                                                                    ('Sofia Ramirez', 'Software Engineering', 19, 4.0, 3),
                                                                                    ('Daniel Torres', 'Computer Science', 22, 3.8, 7),
                                                                                    ('Valentina Castro', 'Data Science', 21, 4.7, 6),
                                                                                    ('Felipe Herrera', 'Information Systems', 25, 3.7, 10),
                                                                                    ('Camila Rojas', 'Cybersecurity', 20, 4.4, 4);
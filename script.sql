DROP TABLE IF EXISTS student CASCADE;
DROP TABLE IF EXISTS instructor CASCADE;
DROP TABLE IF EXISTS department CASCADE;
DROP TABLE IF EXISTS prereq CASCADE;
DROP TABLE IF EXISTS course CASCADE;

-- create department table
CREATE TABLE department (
  id SERIAL PRIMARY KEY,
  dept_name VARCHAR(50) NOT NULL,
  building VARCHAR(50) NOT NULL,
  budget VARCHAR(50) NOT NULL DEFAULT '0',
  UNIQUE (dept_name)
);

-- create instructor table
CREATE TABLE instructor (
  id SERIAL PRIMARY KEY,
  inst_name VARCHAR(30) NOT NULL,
  salary INT  NOT NULL,
  inst_dept VARCHAR(50) NOT NULL,
  FOREIGN KEY (inst_dept) REFERENCES department (dept_name)
    ON UPDATE CASCADE ON DELETE RESTRICT
);

-- create student table
CREATE TABLE student (
  id SERIAL PRIMARY KEY,
  advisor_id INT,
  stud_name VARCHAR(30) NOT NULL,
  tot_cred INT NOT NULL,
  stud_dept VARCHAR(50) NOT NULL,
  FOREIGN KEY (advisor_id) REFERENCES instructor (id)
    ON UPDATE CASCADE ON DELETE SET NULL,
  FOREIGN KEY (stud_dept) REFERENCES department (dept_name)
    ON UPDATE CASCADE ON DELETE RESTRICT
);

-- create course table
CREATE TABLE course (
  course_id SERIAL PRIMARY KEY,
  title VARCHAR(50) NOT NULL,
  credits INT NOT NULL DEFAULT '0',
  course_dept VARCHAR(50) NOT NULL,
  FOREIGN KEY (course_dept) REFERENCES department (dept_name)
    ON UPDATE CASCADE ON DELETE RESTRICT
);

-- create prereq table
CREATE TABLE prereq (
  course_id INT NOT NULL,
  prereq_id INT NOT NULL,
  PRIMARY KEY (course_id, prereq_id),
  FOREIGN KEY (course_id) REFERENCES course (course_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (prereq_id) REFERENCES course (course_id)
    ON UPDATE CASCADE ON DELETE CASCADE
);

-- insert data
INSERT INTO department (dept_name, building, budget) VALUES
  ('Computer Science', 'Network', '1600000'),
  ('Economics', 'Efficient', '1200000'),
  ('Art', 'Pretty', '600000'),
  ('Business', 'Big', '3200000'),
  ('Music', 'Sound', '120000'),
  ('Math', 'Theory', '40000'),
  ('Statistics', 'Probably', '70000');

INSERT INTO instructor (inst_name, salary, inst_dept) VALUES
  ('John Bate', 65000, 'Computer Science'),
  ('Alan Smith', 40000, 'Computer Science'),
  ('Cristiano Ronaldo', 38000, 'Economics'),
  ('John Marry', 34000, 'Economics'),
  ('David Lopez', 60000, 'Music'),
  ('Peter Parker', 120000, 'Business');

INSERT INTO student (advisor_id, stud_name, tot_cred, stud_dept) VALUES
  (1, 'David Barrientos', 14, 'Computer Science'),
  (2, 'Paul Granados', 18, 'Computer Science'),
  (2, 'Ryan Smith', 20, 'Computer Science'),
  (6, 'Mac Marshall', 15, 'Economics'),
  (NULL, 'Eminem', 17, 'Music');

INSERT INTO course (title, credits, course_dept) VALUES
  ('Linear Regression', 3, 'Math'),
  ('Inferential Statistics', 3, 'Statistics'),
  ('Descriptive Statistics', 3, 'Statistics'),
  ('Machine Learning', 4, 'Computer Science');

INSERT INTO prereq (course_id, prereq_id) VALUES
  (4, 3),
  (4, 2),
  (4, 1),
  (3, 2);
  
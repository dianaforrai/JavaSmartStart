-- --------------------------------------------------
-- Employees Table Initial Data
-- --------------------------------------------------

-- Admin user
INSERT INTO employee (name, username, password, role, position, department, salary)
VALUES ('Admin User', 'admin', '$2a$10$7QwU7p/5eLzWfj4X7T0aJeYHbsR6E9p9Y3XvH0c1nK3ULe5PdKJH6', 'ROLE_ADMIN', 'Manager', 'HR', 120000);

-- Regular users
INSERT INTO employee (name, username, password, role, position, department, salary)
VALUES ('John Doe', 'johndoe', '$2a$10$7QwU7p/5eLzWfj4X7T0aJeYHbsR6E9p9Y3XvH0c1nK3ULe5PdKJH6', 'ROLE_USER', 'Developer', 'IT', 80000);

INSERT INTO employee (name, username, password, role, position, department, salary)
VALUES ('Jane Smith', 'janesmith', '$2a$10$7QwU7p/5eLzWfj4X7T0aJeYHbsR6E9p9Y3XvH0c1nK3ULe5PdKJH6', 'ROLE_USER', 'Analyst', 'Finance', 75000);

INSERT INTO employee (name, username, password, role, position, department, salary)
VALUES ('Bob Johnson', 'bobjohnson', '$2a$10$7QwU7p/5eLzWfj4X7T0aJeYHbsR6E9p9Y3XvH0c1nK3ULe5PdKJH6', 'ROLE_USER', 'Tester', 'QA', 70000);

show databases;
use student_management;
show tables;
select * from students;

update students
set address="Pune"
where sid=12;

insert into students (name, surname, address,age)
values('Om', 'Masal', 'Sangali',30);

delete from students
where sid in (14,15);

select name,surname from students
where surname='Manjalkar';

alter table students
add age int;

update students
set age=22
WHERE sid > 0;

create database department;
CREATE TABLE departments (
    dept_id INT PRIMARY KEY AUTO_INCREMENT,
    dept_name VARCHAR(100) NOT NULL
);

describe departments;
show tables;
alter table students
add dept_id int,
add constraint fk_students_departments
foreign key (dept_id)
references departments(dept_id);



insert into departments (dept_name)
VALUES ('Computer'), ('Electronics'), ('Mechanical');

select * from departments;

UPDATE students SET dept_id = 1 WHERE sid IN (1,5,13);
UPDATE students SET dept_id = 2 WHERE sid IN (4,6,10);
UPDATE students SET dept_id = 3 WHERE sid IN (11,14);

-- Count Students Per Department
select d.dept_name, Count(s.sid) as student_count
from students s
join departments d on s.dept_id=d.dept_id
group by d.dept_name
order by student_count DESC;

-- Find Average Age Per Department
select d.dept_name, Round(AVG(s.age)) as student_age
from students s
join departments d on s.dept_id=d.dept_id
group by d.dept_name;

-- Top 3 Oldest Students
select name,surname,age
from students
order by age Desc
limit 3;

-- Pagination Practice
select * from students
limit 3,3;

-- Departments Having More Than 2 Students
select d.dept_name, Count(s.sid) as stundent_count
from students s
join departments d on s.dept_id=d.dept_id
group by d.dept_name
having COUNT(s.sid) > 2;

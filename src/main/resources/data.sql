-- INSERT INTO accounts( name, role, enabled, created_date, email, password, version )
-- VALUES ( 'Tester', 'ADMIN', TRUE, '2022-01-01 01:30:00.000', 'test001@email.com', '$2a$10$ARdjIR.M0TQWoS0WiRzY0en06.Td/jdndur.70FrKd6iJM7wPuu7a', 0 );



-- INSERT INTO grades( class_id, category, student_id, grade )
-- VALUES ( 'A', '國文', 'A001', 86 );

-- INSERT INTO grades( class_id, category, student_id, grade )
-- VALUES ( 'A', '數學', 'A001', 97 );


-- SELECT category as A班科目, count( grade ) as A級人數80分以上
-- FROM grades
-- WHERE grade >= 80 and class_id = 'A'
-- GROUP BY category

-- SELECT category as A班科目, 
--        COUNT( CASE WHEN grade BETWEEN 80 AND 100 THEN 1 END )   AS "A級人數80分以上",
--        COUNT( CASE WHEN grade BETWEEN 60 and 79 THEN 1 END )    AS "B級人數60~79",
--        COUNT( CASE WHEN grade BETWEEN 0 and 59 THEN 1 END )     AS "C級人數0~59",
--        AVG( CASE WHEN grade BETWEEN 80 AND 100 THEN grade END ) AS "A級平均分數80分以上",
--        AVG( CASE WHEN grade BETWEEN 60 and 79 THEN grade END )  AS "B級平均分數60~79",
--        AVG( CASE WHEN grade BETWEEN 0 and 59 THEN grade END )   AS "C級平均分數0~59"
-- FROM grades
-- WHERE class_id = 'A'
-- GROUP BY category
-- ORDER BY 2 DESC;

-- https://www.db-fiddle.com/f/4tJhQHe4NGtpv9vnP5B7g6/0
-- SELECT P.id, P.text, L.likes
-- FROM posts AS P
-- JOIN ( SELECT post_id, COUNT(created_at) AS likes 
--        FROM post_likes 
--        GROUP BY post_id ) AS L
-- ON P.id = L.post_id

-- https://www.db-fiddle.com/f/du7jwSHtebHnLwjAM5SYv1/0

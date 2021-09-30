--
-- User java
--

create user 'java'@'localhost' identified by 'root';
GRANT SELECT, INSERT, UPDATE, DELETE ON `java`.* TO 'java'@'localhost';
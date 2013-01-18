CREATE USER 'myam-sa' IDENTIFIED BY 'myam-toor';
CREATE DATABASE IF NOT EXISTS `myam-db`;
GRANT USAGE ON *.* TO 'myam-sa'@'localhost' IDENTIFIED BY 'myam-toor';
GRANT USAGE ON *.* TO 'myam-sa'@'127.0.0.1' IDENTIFIED BY 'myam-toor';
GRANT ALL PRIVILEGES ON `myam-db`.* TO 'myam-sa'@'127.0.0.1','myam-sa'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;
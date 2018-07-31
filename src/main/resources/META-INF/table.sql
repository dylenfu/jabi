drop database if exists demodb01;
CREATE database demodb01 DEFAULT CHARACTER SET utf8;

CREATE TABLE demodb00.user_1 (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) DEFAULT NULL,
  age int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
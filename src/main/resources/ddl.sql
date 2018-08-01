drop database if exists ds_master0;
CREATE database ds_master0 DEFAULT CHARACTER SET utf8;

drop database if exists ds_master0_slave0;
CREATE database ds_master0_slave0 DEFAULT CHARACTER SET utf8;

drop database if exists ds_master1;
CREATE database ds_master1 DEFAULT CHARACTER SET utf8;

drop database if exists ds_master1_slave0;
CREATE database ds_master1_slave0 DEFAULT CHARACTER SET utf8;

CREATE TABLE ds_master0.user_a (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) DEFAULT NULL,
  age int(11) DEFAULT NULL,
  market varchar(10) DEFAULT NULL,
  address varchar(100) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ds_master0.user_b (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) DEFAULT NULL,
  age int(11) DEFAULT NULL,
  market varchar(10) DEFAULT NULL,
  address varchar(100) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ds_master0_slave0.user_a (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) DEFAULT NULL,
  age int(11) DEFAULT NULL,
  market varchar(10) DEFAULT NULL,
  address varchar(100) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ds_master0_slave0.user_b (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) DEFAULT NULL,
  age int(11) DEFAULT NULL,
  market varchar(10) DEFAULT NULL,
  address varchar(100) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ds_master1.user_a (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) DEFAULT NULL,
  age int(11) DEFAULT NULL,
  market varchar(10) DEFAULT NULL,
  address varchar(100) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ds_master1.user_b (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) DEFAULT NULL,
  age int(11) DEFAULT NULL,
  market varchar(10) DEFAULT NULL,
  address varchar(100) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ds_master1_slave0.user_a (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) DEFAULT NULL,
  age int(11) DEFAULT NULL,
  market varchar(10) DEFAULT NULL,
  address varchar(100) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ds_master1_slave0.user_b (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) DEFAULT NULL,
  age int(11) DEFAULT NULL,
  market varchar(10) DEFAULT NULL,
  address varchar(100) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

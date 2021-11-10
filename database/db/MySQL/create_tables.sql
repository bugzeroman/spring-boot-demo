DROP TABLE IF EXISTS TBL_USER;

CREATE TABLE TBL_USER
(
    id BIGINT(20) NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    update_time DATETIME COMMENT '修改时间',
    PRIMARY KEY (id)
);


DROP TABLE IF EXISTS TBL_ROLE;

CREATE TABLE TBL_ROLE
(
  id            BIGINT (20) NOT NULL COMMENT '主键ID',
  role_name     VARCHAR(30) NULL DEFAULT NULL COMMENT '角色名',
  role_describe VARCHAR(30) NULL DEFAULT NULL COMMENT '角色描述',
  create_time DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (id)
);
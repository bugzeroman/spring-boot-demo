/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/11/16 10:16:37                          */
/*==============================================================*/


drop table if exists tbl_role;

drop table if exists tbl_user;

drop table if exists tbl_user_role;

/*==============================================================*/
/* Table: tbl_role                                              */
/*==============================================================*/
create table tbl_role
(
   id                   int not null comment '角色ID',
   name                 varchar(100) not null comment '角色名称',
   description          text comment '角色描述',
   status               bool not null comment '角色状态，true：有效，false：无效',
   create_time          datetime not null comment '创建时间',
   primary key (id)
);

alter table tbl_role comment '角色表';

/*==============================================================*/
/* Table: tbl_user                                              */
/*==============================================================*/
create table tbl_user
(
   id                   bigint not null comment '用户ID',
   name                 varchar(100) not null comment '姓名',
   email                varchar(100) comment '邮箱',
   birthday             date comment '生日',
   create_time          datetime not null comment '创建时间',
   update_time          varchar(100) comment '修改时间',
   primary key (id)
);

alter table tbl_user comment '用户表';

/*==============================================================*/
/* Table: tbl_user_role                                         */
/*==============================================================*/
create table tbl_user_role
(
   user_id              bigint not null comment '用户ID',
   role_id              varchar(100) not null comment '角色ID',
   create_time          datetime not null comment '创建时间',
   primary key (user_id, role_id)
);

alter table tbl_user_role comment '用户角色表，一个用户可以关联到多个角色';


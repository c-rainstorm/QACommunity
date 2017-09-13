/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/9/7 13:19:10                            */
/*==============================================================*/

grant select, insert, update, delete
  on qac.*
  to 'qac'@'localhost' identified by 'qac';
flush privileges;

drop database if exists qac;
create database qac
      character set utf8mb4
      collate utf8mb4_unicode_ci;

use qac;

/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table admin
(
   id                   int not null AUTO_INCREMENT  comment '',
   password             varchar(64) not null  comment '',
   name                 varchar(32) comment '',
   PRIMARY KEY (id)
);
/*==============================================================*/
/* Table: report_reason                                         */
/*==============================================================*/
create table report_reason
(
   id                   int not null AUTO_INCREMENT  comment '',
   title                varchar(16) not null  comment '',
   content              varchar(32)  comment '',
   primary key (id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   int not null AUTO_INCREMENT comment '',
   name                 varchar(32) not null  comment '',
   email                varchar(32) not null  comment '',
   password             varchar(64) not null  comment '',
   create_date          date not null  comment '',
   sex                  bool  comment '',
   short_intro          varchar(32)  comment '',
   intro                varchar(128)  comment '',
   college              varchar(16)  comment '',
   major                varchar(16)  comment '',
   avatar               varchar(64)  comment '',
   prestige             int default 0  comment '',
   browse_num           int default 0  comment '',
   status               smallint default 0  comment '',
   unban_datetime       datetime  comment '',
   primary key (id)
);

/*==============================================================*/
/* Table: "label"                                               */
/*==============================================================*/
create table `label`
(
   id                   int not null AUTO_INCREMENT  comment '',
   title                varchar(16) not null  comment '',
   primary key (id)
);

/*==============================================================*/
/* Table: article                                               */
/*==============================================================*/
create table article
(
   id                   int not null AUTO_INCREMENT  comment '',
   author_id            int not null  comment '',
   title                varchar(32) not null  comment '',
   content              varchar(10000) not null  comment '',
   datetime             datetime not null  comment '',
   browse_num           int default 0  comment '',
   up                   int default 0  comment '',
   down                 int default 0  comment '',
   status               smallint default 0  comment '',
   status_remarks       int default -1  comment '',
   primary key (id),
   FOREIGN KEY (author_id) REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: question                                              */
/*==============================================================*/
create table question
(
   id                   int not null AUTO_INCREMENT  comment '',
   author_id            int not null  comment '',
   title                varchar(32) not null  comment '',
   content              varchar(1000) not null  comment '',
   datetime             datetime not null  comment '',
   browse_num           int default 0  comment '',
   up                   int default 0  comment '',
   down                 int default 0  comment '',
   status               smallint default 0  comment '状态：表示该问题目前的状态，如“正常”、“被关闭“、”被删除“等。
             ',
   status_remarks       int default -1  comment '',
   primary key (id),
   FOREIGN KEY (author_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: answer                                                */
/*==============================================================*/
create table answer
(
   id                   int not null AUTO_INCREMENT comment '',
   question_id          int not null  comment '',
   author_id            int not null  comment '',
   content              varchar(10000) not null  comment '',
   datetime             datetime not null  comment '',
   up                   int default 0  comment '',
   down                 int default 0  comment '',
   status               smallint default 0  comment '',
   status_remarks       int default -1  comment '',
   primary key (id),
   FOREIGN KEY (author_id)REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE ,
   FOREIGN KEY (question_id)REFERENCES question(id) ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: answer_collect                                        */
/*==============================================================*/
create table answer_collect
(
   user_id              int not null  comment '',
   answer_id            int not null  comment '',
   datetime             datetime not null  comment '',
   primary key (user_id, answer_id),
   FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (answer_id) REFERENCES answer(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: answer_comment                                        */
/*==============================================================*/
create table answer_comment
(
   id                   int not null AUTO_INCREMENT comment '',
   user_id              int not null  comment '',
   answer_id            int not null  comment '',
   content              varchar(100) not null  comment '',
   datetime             datetime not null  comment '',
   up                   int default 0  comment '',
   down                 int default 0  comment '',
   reply_comment_id     int  comment '',
   primary key (user_id, answer_id),
   INDEX (id),
   FOREIGN KEY (user_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (answer_id)REFERENCES answer(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: answer_report                                         */
/*==============================================================*/
create table answer_report
(
   user_id              int not null  comment '',
   answer_id           int not null  comment '',
   report_reason_id     int not null  comment '',
   remarks              varchar(100)  comment '',
   status               bool default false  comment '',
   datetime             datetime not null  comment '',
   primary key (user_id, answer_id),
   FOREIGN KEY (user_id) REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (answer_id) REFERENCES answer(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (report_reason_id)REFERENCES report_reason(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: answer_up_down                                        */
/*==============================================================*/
create table answer_up_down
(
   user_id              int not null  comment '',
   answer_id            int not null  comment '',
   datetime             datetime not null  comment '',
   is_up                int default 0 comment '',
   is_down              INT DEFAULT  0 COMMENT '',
   PRIMARY KEY (user_id, answer_id),
   FOREIGN KEY (user_id) REFERENCES user (id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (answer_id) REFERENCES answer(id)ON DELETE CASCADE ON UPDATE CASCADE
);



/*==============================================================*/
/* Table: article_collect                                       */
/*==============================================================*/
create table article_collect
(
   user_id              int not null  comment '',
   article_id            int not null  comment '',
   datetime             datetime not null  comment '',
   primary key (user_id, article_id),
   FOREIGN KEY (user_id) REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (article_id)REFERENCES article(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: article_comment                                       */
/*==============================================================*/
create table article_comment
(
   id                   int not null AUTO_INCREMENT  comment '',
   user_id              int not null  comment '',
   answer_id            int not null  comment '',
   content              varchar(100) not null  comment '',
   datetime             datetime not null  comment '',
   up                   int default 0  comment '',
   down                 int default 0  comment '',
   reply_comment_id     int  comment '',
   primary key (id),
   FOREIGN KEY (user_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (answer_id)REFERENCES answer(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: article_label                                         */
/*==============================================================*/
create table article_label
(
   article_id           int not null  comment '',
   label_id             int not null  comment '',
   primary key (label_id, article_id),
   FOREIGN KEY (article_id)REFERENCES article(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (label_id)REFERENCES label(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: article_report                                        */
/*==============================================================*/
create table article_report
(
   user_id              int not null  comment '',
   article_id           int not null  comment '',
   report_reason_id     int not null  comment '',
   remarks              varchar(100)  comment '',
   status               bool default false  comment '',
   datetime             datetime not null  comment '',
   primary key (user_id, article_id),
   FOREIGN KEY (user_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (article_id)REFERENCES article(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (report_reason_id)REFERENCES report_reason(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: article_up_down                                       */
/*==============================================================*/
create table article_up_down
(
   user_id              int not null  comment '',
   article_id            int not null  comment '',
   datetime             datetime not null  comment '',
   is_up                   int default 0 comment '',
   primary key (user_id, article_id),
   FOREIGN KEY (user_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (article_id)REFERENCES article(id)ON DELETE CASCADE ON UPDATE CASCADE
);


/*==============================================================*/
/* Table: notice                                                */
/*==============================================================*/
create table notice
(
   id                   int not null AUTO_INCREMENT comment '',
   user_id              int not null  comment '',
   content              varchar(100) not null  comment '',
   datetime             datetime not null  comment '',
   primary key (id),
   FOREIGN KEY (user_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: question_follow                                       */
/*==============================================================*/
create table question_follow
(
   user_id              int not null  comment '',
   question_id          int not null  comment '',
   datetime             datetime not null  comment '',
   primary key (user_id, question_id),
   FOREIGN KEY (user_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (question_id)REFERENCES question(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: question_label                                        */
/*==============================================================*/
create table question_label
(
   question_id           int not null  comment '',
   label_id             int not null  comment '',
   primary key (question_id, label_id),
   FOREIGN KEY (question_id)REFERENCES  question(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (label_id)REFERENCES label(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: question_report                                       */
/*==============================================================*/
create table question_report
(
   user_id              int not null  comment '',
   question_id           int not null  comment '',
   report_reason_id     int not null  comment '',
   remarks              varchar(100)  comment '',
   status               bool default false  comment '',
   datetime             datetime not null  comment '',
   primary key (user_id, question_id),
   FOREIGN KEY (user_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (question_id)REFERENCES question(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (report_reason_id)REFERENCES report_reason(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: question_up_down                                      */
/*==============================================================*/
create table question_up_down
(
   user_id              int not null  comment '',
   question_id          int not null  comment '',
   datetime             datetime not null  comment '',
   is_up                   int default 0 comment '',
   primary key (user_id, question_id),
   FOREIGN KEY (user_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (question_id)REFERENCES question(id)ON DELETE CASCADE ON UPDATE CASCADE
);


/*==============================================================*/
/* Table: user_follow                                           */
/*==============================================================*/
create table user_follow
(
   user_id              int not null  comment '',
   follow_id            int not null  comment '',
   datetime             datetime not null  comment '',
   primary key (user_id, follow_id),
   FOREIGN KEY (user_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (follow_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE
);
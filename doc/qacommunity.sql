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
   id                   int not null AUTO_INCREMENT  comment '编号',
   password             varchar(64) not null  comment '密码',
   name                 varchar(32) comment '用户名',
   PRIMARY KEY (id)
);
/*==============================================================*/
/* Table: report_reason                                         */
/*==============================================================*/
create table report_reason
(
   id                   int not null AUTO_INCREMENT  comment '举报原因编号',
   title                varchar(16) not null  comment '标题',
   content              varchar(32)  comment '举报内容',
   primary key (id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   int not null AUTO_INCREMENT comment '用户编号，自动生成',
   name                 varchar(32) not null  comment '姓名',
   email                varchar(32) not null  comment '邮箱',
   password             varchar(64) not null  comment '密码',
   create_date          TIMESTAMP not null DEFAULT current_timestamp  comment '创建时间,自动获取',
   sex                  bool  comment '性别',
   short_intro          varchar(32)  comment '个人签名',
   intro                varchar(128)  comment '个人介绍',
   college              varchar(16)  comment '大学',
   major                varchar(16)  comment '专业',
   avatar               varchar(64)  comment '头像，可以设置一个默认头像',
   prestige             int default 0  comment '声望值，默认为0',
   browse_num           int default 0  comment '被浏览数',
   status               smallint default 0  comment '是否被封禁状态，0为没有封禁，1为被封禁',
   unban_datetime       datetime  comment '解封时间',
   primary key (id)
);

/*==============================================================*/
/* Table: "label"                                               */
/*==============================================================*/
create table `label`
(
   id                   int not null AUTO_INCREMENT  comment '标签编号，自动生成',
   title                varchar(16) not null  comment '标签内容',
   primary key (id)
);

/*==============================================================*/
/* Table: article                                               */
/*==============================================================*/
create table article
(
   id                   int not null AUTO_INCREMENT  comment '文章编号',
   author_id            int not null  comment '作者编号',
   title                varchar(32) not null  comment '标题',
   content              varchar(10000) not null  comment '内容',
   datetime             TIMESTAMP not null DEFAULT current_timestamp   comment '创建时间',
   browse_num           int default 0  comment '被浏览数',
   up                   int default 0  comment '被赞数',
   down                 int default 0  comment '被踩数',
   status               smallint default 0  comment '文章状态',
   status_remarks       int default -1  comment '默认为-1',
   primary key (id),
   FOREIGN KEY (author_id) REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: question                                              */
/*==============================================================*/
create table question
(
   id                   int not null AUTO_INCREMENT  comment '问题编号',
   author_id            int not null  comment '作者编号',
   title                varchar(32) not null  comment '文章标题',
   content              varchar(1000) not null  comment '文章内容',
   datetime             TIMESTAMP not null DEFAULT current_timestamp  comment '发表时间',
   browse_num           int default 0  comment '被浏览数',
   up                   int default 0  comment '被赞数',
   down                 int default 0  comment '被踩数',
   status               smallint default 0  comment '状态：表示该问题目前的状态，如“正常”、“被关闭“、”被删除“等。',
   status_remarks       int default -1  comment '？',
   primary key (id),
   FOREIGN KEY (author_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: answer                                                */
/*==============================================================*/
create table answer
(
   id                   int not null AUTO_INCREMENT comment '答案编号',
   question_id          int not null  comment '问题编号',
   author_id            int not null  comment '作者编号',
   content              varchar(10000) not null  comment '答案内容',
   datetime             TIMESTAMP not null DEFAULT current_timestamp  comment '发表时间',
   up                   int default 0  comment '被赞数',
   down                 int default 0  comment '被踩数',
   status               smallint default 0  comment '状态',
   status_remarks       int default -1  comment '？',
   primary key (id),
   FOREIGN KEY (author_id)REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE ,
   FOREIGN KEY (question_id)REFERENCES question(id) ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: answer_collect                                        */
/*==============================================================*/
create table answer_collect
(
   user_id              int not null  comment '用户编号',
   answer_id            int not null  comment '答案编号',
   datetime             TIMESTAMP not null DEFAULT current_timestamp  comment '收藏时间',
   primary key (user_id, answer_id),
   FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (answer_id) REFERENCES answer(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: answer_comment                                        */
/*==============================================================*/
create table answer_comment
(
   id                   int not null AUTO_INCREMENT comment '答案评论编号',
   user_id              int not null  comment '用户编号',
   answer_id            int not null  comment '答案编号',
   content              varchar(100) not null  comment '内容',
   datetime             TIMESTAMP not null DEFAULT current_timestamp   comment '发表时间',
   up                   int default 0  comment '被赞数',
   down                 int default 0  comment '被踩数',
   reply_comment_id     int  comment '回复评论编号',
   status               int DEFAULT 0 COMMENT '评论状态，默认为0，-1为删除',
   primary key (id),
   INDEX (id),
   FOREIGN KEY (user_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (answer_id)REFERENCES answer(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (reply_comment_id) REFERENCES answer_comment(id) on DELETE CASCADE
);

/*==============================================================*/
/* Table: answer_report                                         */
/*==============================================================*/
create table answer_report
(
   user_id              int not null  comment '用户编号',
   answer_id           int not null  comment '答案编号',
   report_reason_id     int not null  comment '举报原因编号',
   remarks              varchar(100)  comment '内容',
   status               bool default false  comment '状态',
   datetime             TIMESTAMP not null DEFAULT current_timestamp  comment '举报时间',
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
   user_id              int not null  comment '用户编号',
   answer_id            int not null  comment '答案编号',
   datetime             TIMESTAMP not null DEFAULT current_timestamp  comment '发表时间',
   is_up                int default 0 comment '是否被赞或被踩-1为踩，0为默认，1为赞',
   PRIMARY KEY (user_id, answer_id),
   FOREIGN KEY (user_id) REFERENCES user (id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (answer_id) REFERENCES answer(id)ON DELETE CASCADE ON UPDATE CASCADE
);



/*==============================================================*/
/* Table: article_collect                                       */
/*==============================================================*/
create table article_collect
(
   user_id              int not null  comment '用户编号',
   article_id            int not null  comment '文章编号',
   datetime              TIMESTAMP not null DEFAULT current_timestamp   comment '发表时间',
   primary key (user_id, article_id),
   FOREIGN KEY (user_id) REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (article_id)REFERENCES article(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: article_comment                                       */
/*==============================================================*/
create table article_comment
(
   id                   int not null AUTO_INCREMENT  comment '文章评论编号',
   user_id              int not null  comment '用户编号',
   article_id            int not null  comment '答案编号',
   content              varchar(100) not null  comment '评论内容',
   datetime             TIMESTAMP not null DEFAULT current_timestamp  comment '发表时间',
   up                   int default 0  comment '被赞数',
   down                 int default 0  comment '被踩数',
   reply_comment_id     int  comment '回复编号',
   status               int DEFAULT 0 COMMENT '评论状态，默认为0，-1为删除',
   primary key (id),
   FOREIGN KEY (user_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (article_id)REFERENCES article(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (reply_comment_id)REFERENCES article_comment(id) on DELETE CASCADE
);

/*==============================================================*/
/* Table: article_label                                         */
/*==============================================================*/
create table article_label
(
   article_id           int not null  comment '文章编号',
   label_id             int not null  comment '标签编号',
   primary key (label_id, article_id),
   FOREIGN KEY (article_id)REFERENCES article(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (label_id)REFERENCES label(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: article_report                                        */
/*==============================================================*/
create table article_report
(
   user_id              int not null  comment '用户编号',
   article_id           int not null  comment '文章编号',
   report_reason_id     int not null  comment '举报原因编号',
   remarks              varchar(100)  comment '补充内容',
   status               bool default false  comment '状态',
   datetime             TIMESTAMP not null DEFAULT current_timestamp   comment '发表时间',
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
   user_id              int not null  comment '用户编号',
   article_id           int not null  comment '文章编号',
   datetime             TIMESTAMP not null DEFAULT current_timestamp   comment '发表时间',
   is_up                int default 0 comment '是否被赞或被踩-1为踩，0为默认，1为赞',
   primary key (user_id, article_id),
   FOREIGN KEY (user_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (article_id)REFERENCES article(id)ON DELETE CASCADE ON UPDATE CASCADE
);


/*==============================================================*/
/* Table: notice                                                */
/*==============================================================*/
create table notice
(
   id                   int not null AUTO_INCREMENT comment '通知编号',
   user_id              int not null  comment '用户编号',
   content              varchar(100) not null  comment '内容',
   datetime             TIMESTAMP not null DEFAULT current_timestamp  comment '发表时间',
   primary key (id),
   FOREIGN KEY (user_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: question_follow                                       */
/*==============================================================*/
create table question_follow
(
   user_id              int not null  comment '用户编号',
   question_id          int not null  comment '问题编号',
   datetime             TIMESTAMP not null DEFAULT current_timestamp   comment '发表时间',
   primary key (user_id, question_id),
   FOREIGN KEY (user_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (question_id)REFERENCES question(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: question_label                                        */
/*==============================================================*/
create table question_label
(
   question_id           int not null  comment '问题编号',
   label_id             int not null  comment '标签编号',
   primary key (question_id, label_id),
   FOREIGN KEY (question_id)REFERENCES  question(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (label_id)REFERENCES label(id)ON DELETE CASCADE ON UPDATE CASCADE
);

/*==============================================================*/
/* Table: question_report                                       */
/*==============================================================*/
create table question_report
(
   user_id              int not null  comment '用户编号',
   question_id           int not null  comment '问题编号',
   report_reason_id     int not null  comment '举报原因编号',
   remarks              varchar(100)  comment '',
   status               bool default false  comment '状态',
   datetime             TIMESTAMP not null DEFAULT current_timestamp  comment '发表时间',
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
   user_id              int not null  comment '用户编号',
   question_id          int not null  comment '问题编号',
   datetime             TIMESTAMP not null DEFAULT current_timestamp   comment '发表时间',
   is_up                int default 0 comment '是否被赞或被踩-1为踩，0为默认，1为赞',
   primary key (user_id, question_id),
   FOREIGN KEY (user_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (question_id)REFERENCES question(id)ON DELETE CASCADE ON UPDATE CASCADE
);


/*==============================================================*/
/* Table: user_follow                                           */
/*==============================================================*/
create table user_follow
(
   user_id              int not null  comment '用户编号',
   follow_id            int not null  comment '被关注用户编号',
   datetime              TIMESTAMP not null DEFAULT current_timestamp  comment '发表时间',
   primary key (user_id, follow_id),
   FOREIGN KEY (user_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (follow_id)REFERENCES user(id)ON DELETE CASCADE ON UPDATE CASCADE
);
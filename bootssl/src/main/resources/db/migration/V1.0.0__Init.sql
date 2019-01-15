create table SYS_USER
(
	ID VARCHAR2(36 char) default NULL not null
		primary key,
	NAME VARCHAR2(100 char) default NULL,
	USERNAME VARCHAR2(100 char) default NULL not null,
	PASSWORD VARCHAR2(100 char) default NULL not null,
	AVATAR VARCHAR2(2000 char) default NULL,
	MOBILE VARCHAR2(11 char) default NULL,
	EMAIL VARCHAR2(100 char) default NULL,
	GENDER VARCHAR2(32 char) default NULL,
	STATUS VARCHAR2(32 char) default NULL not null,
	ORG_ID VARCHAR2(180 char) default NULL,
	DUTY_ID VARCHAR2(32 char) default NULL,
	SALT VARCHAR2(100 char),
	SIGN VARCHAR2(36 char)
)


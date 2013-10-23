-- Script creating security schema
-- author: Marcin Gołębski
-- date: 11.09.2008
-- version $Id: create-schema-postgresql.sql,v 1.4 2011-11-24 20:43:07 mgolebsk Exp $


-----------------------------------------------------------------------------
-- SECURITY_PERMISSION
-----------------------------------------------------------------------------
-- SET search_path TO ledge_hello_world;
drop TABLE IF EXISTS SECURITY_PERMISSION cascade;

CREATE TABLE SECURITY_PERMISSION
(
    PERMISSION_ID integer NOT NULL,
    PERMISSION_NAME varchar (99) NOT NULL,
    PERMISSION_DESC varchar (1024) NULL,
    CONSTRAINT SECURITY_PERMISSION_PK PRIMARY KEY(PERMISSION_ID),
    CONSTRAINT SECURITY_PERMISSION_UN UNIQUE (PERMISSION_NAME)
);

                                                
-----------------------------------------------------------------------------
-- SECURITY_ROLE
-----------------------------------------------------------------------------
drop table if exists SECURITY_ROLE cascade;

CREATE TABLE SECURITY_ROLE
(
    ROLE_ID integer NOT NULL,
    ROLE_NAME varchar (99) NOT NULL,
    ROLE_DESC varchar (1024) NULL,
    CONSTRAINT SECURITY_ROLE_PK PRIMARY KEY(ROLE_ID),
    CONSTRAINT SECURITY_ROLE_UN UNIQUE (ROLE_NAME)
);

                                                
-----------------------------------------------------------------------------
-- SECURITY_RESOURCE_GROUP
-----------------------------------------------------------------------------
drop table if exists SECURITY_RESOURCE_GROUP cascade;

CREATE TABLE SECURITY_RESOURCE_GROUP
(
    RESOURCE_GROUP_ID integer NOT NULL,
    RESOURCE_GROUP_NAME varchar (99) NOT NULL,
	RESOURCE_GROUP_DESC varchar (1024) NULL,
    CONSTRAINT SECURITY_RESOURCE_GROUP_PK PRIMARY KEY(RESOURCE_GROUP_ID),
    CONSTRAINT SECURITY_RESOURCE_GROUP_UN UNIQUE (RESOURCE_GROUP_NAME)
);

                                                
-----------------------------------------------------------------------------
-- SECURITY_ROLE_PERMISSION       
-----------------------------------------------------------------------------
drop table if exists SECURITY_ROLE_PERMISSION cascade;

CREATE TABLE SECURITY_ROLE_PERMISSION
(
    ROLE_ID integer NOT NULL,
    PERMISSION_ID integer NOT NULL,
    CONSTRAINT SECURITY_ROLE_PERMISSION_PK PRIMARY KEY(ROLE_ID,PERMISSION_ID),
    CONSTRAINT SECURITY_GROUP_ROLE_FK FOREIGN KEY (ROLE_ID) REFERENCES SECURITY_ROLE (ROLE_ID),
    CONSTRAINT SECURITY_GROUP_PERM_FK FOREIGN KEY (PERMISSION_ID) REFERENCES SECURITY_PERMISSION (PERMISSION_ID)
);

-----------------------------------------------------------------------------
-- SECURITY_USER
-----------------------------------------------------------------------------
drop table if exists SECURITY_USER cascade;

CREATE TABLE SECURITY_USER
(
    USER_ID integer NOT NULL,
    LOGIN_NAME varchar (32) NOT NULL,
    PASSWORD_VALUE varchar (32) NOT NULL,
    FIRST_NAME varchar (99) NOT NULL,
    LAST_NAME varchar (99) NOT NULL,
    EMAIL varchar (99),
    CONFIRM_VALUE varchar (99),
    MODIFIED timestamp,
    CREATED timestamp,
    LAST_LOGIN timestamp,
	USER_DESC varchar (1024) NULL,
	PERM BYTEA NULL,
	LOCKED BOOLEAN NOT NULL,
	CONFIRMED BOOLEAN NOT NULL,
	MAX_INACTIVE_INTERVAL  NUMERIC(5) NOT NULL DEFAULT 900,
	ACCESS_COUNTER NUMERIC(10) NOT NULL,
	RESOURCE_GROUP_ID integer,
    CONSTRAINT SECURITY_USER_PK PRIMARY KEY(USER_ID),
    CONSTRAINT SECURITY_USER_UN UNIQUE (LOGIN_NAME),
    CONSTRAINT SECURITY_USER_GROUP FOREIGN KEY (RESOURCE_GROUP_ID) REFERENCES  SECURITY_RESOURCE_GROUP(RESOURCE_GROUP_ID)
);

                                                
-----------------------------------------------------------------------------
-- SECURITY_USER_RESOURCE_GROUP_ROLE                  
-----------------------------------------------------------------------------
drop table if exists SECURITY_USER_RESOURCE_GROUP_ROLE cascade;

CREATE TABLE SECURITY_USER_RESOURCE_GROUP_ROLE
(
    USER_ID integer NOT NULL,
    RESOURCE_GROUP_ID integer NOT NULL,
    ROLE_ID integer NOT NULL,
    CONSTRAINT SECURITY_USER_GROUP_ROLE_PK PRIMARY KEY(USER_ID,RESOURCE_GROUP_ID,ROLE_ID),
    CONSTRAINT SECURITY_USER_GROUP_ROLE_USER_FK FOREIGN KEY (USER_ID) REFERENCES SECURITY_USER (USER_ID),
    CONSTRAINT SECURITY_USER_GROUP_ROLE_GROU_FK FOREIGN KEY (RESOURCE_GROUP_ID) REFERENCES SECURITY_RESOURCE_GROUP (RESOURCE_GROUP_ID),
    CONSTRAINT SECURITY_USER_GROUP_ROLE_ROLE_FK FOREIGN KEY (ROLE_ID) REFERENCES SECURITY_ROLE (ROLE_ID)
);

drop sequence if exists security_user_sequence cascade;
create sequence security_user_sequence;

drop sequence if exists security_permission_sequence cascade;
create sequence security_permission_sequence;

drop sequence if exists security_role_sequence cascade;
create sequence security_role_sequence;

drop sequence if exists security_resource_group_sequence cascade;
create sequence security_resource_group_sequence;

--drop sequence  cascade;
--create sequence ;

--drop sequence  cascade;
--create sequence ;
                        

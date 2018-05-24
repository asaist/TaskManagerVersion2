 CREATE SEQUENCE OBJECT_SEQUENCE
 START WITH 1
 INCREMENT BY 1
 NOMAXVALUE;

CREATE TABLE "SYSTEM"."OBJECT" 
   (	"OBJECT_ID" NUMBER NOT NULL ENABLE, 
	"OBJECT_TYPE_ID" NUMBER NOT NULL ENABLE,
	"NAME" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"DESCRIPTION" VARCHAR2(20 BYTE), 
	 PRIMARY KEY ("OBJECT_ID")
     )

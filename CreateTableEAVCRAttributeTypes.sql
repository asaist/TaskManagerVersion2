DROP SEQUENCE ATTRIBUTE_TYPES_SEQUENCE;

DROP TABLE SYSTEM.ATTRIBUTE_TYPES;


 CREATE SEQUENCE ATTRIBUTE_TYPES_SEQUENCE
 START WITH 9
 INCREMENT BY 1
 NOMAXVALUE;

CREATE TABLE "SYSTEM"."ATTRIBUTE_TYPES" 
   (	"ID_ATTRIBUTE_TYPE" NUMBER(*,0) NOT NULL ENABLE, 
	"NAME" VARCHAR2(20 BYTE) NOT NULL ENABLE,  
	 PRIMARY KEY ("ID_ATTRIBUTE_TYPE")
     )
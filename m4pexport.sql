--------------------------------------------------------
--  File created - Thursday-September-06-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for View GETMECHANICS
--------------------------------------------------------

  CREATE OR REPLACE FORCE EDITIONABLE VIEW "C##MFORP"."GETMECHANICS" ("ID", "NAME", "DESCRIPTION") AS 
  Select mech.id, mech.Name, mech.Description From Mechanics mech Join Game_Mechanics gm on mech.id = gm.Mechanic_ID;
--------------------------------------------------------
--  DDL for View TOPRATED
--------------------------------------------------------

  CREATE OR REPLACE FORCE EDITIONABLE VIEW "C##MFORP"."TOPRATED" ("ID", "NAME", "DESCRIPTION", "YEAR_PUBLISHED", "COST_OF_GAME", "AVERAGE_RATING", "R") AS 
  Select "ID","NAME","DESCRIPTION","YEAR_PUBLISHED","COST_OF_GAME","AVERAGE_RATING","R" From (Select id, Name, Description, Year_Published, Cost_of_Game, Average_Rating, ROW_NUMBER() OVER (order by Average_Rating DESC) R From Games);
--------------------------------------------------------
--  DDL for Table CONDITIONS
--------------------------------------------------------

  CREATE TABLE "C##MFORP"."CONDITIONS" 
   (	"CONDITION_ID" NUMBER(*,0), 
	"NAME" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table CUSTOMERS
--------------------------------------------------------

  CREATE TABLE "C##MFORP"."CUSTOMERS" 
   (	"ID" NUMBER(*,0), 
	"LAST_NAME" VARCHAR2(25 BYTE), 
	"FIRST_NAME" VARCHAR2(20 BYTE), 
	"USERNAME" VARCHAR2(20 BYTE), 
	"E_MAIL" VARCHAR2(20 BYTE), 
	"PASSWORD" VARCHAR2(64 BYTE), 
	"ADDRESS_LINE_1" VARCHAR2(20 BYTE), 
	"ADDRESS_LINE_2" VARCHAR2(20 BYTE), 
	"ZIPCODE" NUMBER(6,0), 
	"PHONE" NUMBER(10,0), 
	"MEMBER_STATUS" VARCHAR2(15 BYTE), 
	"JOIN_DATE" DATE, 
	"BALANCE" NUMBER, 
	"SALT" VARCHAR2(32 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table DESIGNERS
--------------------------------------------------------

  CREATE TABLE "C##MFORP"."DESIGNERS" 
   (	"ID" NUMBER(*,0), 
	"FIRST_NAME" VARCHAR2(20 BYTE), 
	"LAST_NAME" VARCHAR2(25 BYTE), 
	"WEBSITE" VARCHAR2(40 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table GAMES
--------------------------------------------------------

  CREATE TABLE "C##MFORP"."GAMES" 
   (	"ID" NUMBER(*,0), 
	"NAME" VARCHAR2(50 BYTE), 
	"DESCRIPTION" VARCHAR2(500 BYTE), 
	"YEAR_PUBLISHED" NUMBER(4,0), 
	"COST_OF_GAME" NUMBER, 
	"AVERAGE_RATING" NUMBER(5,2)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table GAME_COMMENTS
--------------------------------------------------------

  CREATE TABLE "C##MFORP"."GAME_COMMENTS" 
   (	"GAME_ID" NUMBER(*,0), 
	"CUSTOMER_ID" NUMBER(*,0), 
	"COMMENT_TEXT" VARCHAR2(280 BYTE), 
	"RATING" NUMBER(5,2), 
	"COMMENT_DATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table GAME_DESIGNERS
--------------------------------------------------------

  CREATE TABLE "C##MFORP"."GAME_DESIGNERS" 
   (	"GAME_ID" NUMBER(*,0), 
	"DESIGNER_ID" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table GAME_MECHANICS
--------------------------------------------------------

  CREATE TABLE "C##MFORP"."GAME_MECHANICS" 
   (	"GAME_ID" NUMBER(*,0), 
	"MECHANIC_ID" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table GAME_PICTURES
--------------------------------------------------------

  CREATE TABLE "C##MFORP"."GAME_PICTURES" 
   (	"ID" NUMBER(*,0), 
	"GAME_ID" NUMBER(*,0), 
	"PICTURE_SIZE" NUMBER(*,0), 
	"URI" VARCHAR2(40 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table GAME_PUBLISHERS
--------------------------------------------------------

  CREATE TABLE "C##MFORP"."GAME_PUBLISHERS" 
   (	"GAME_ID" NUMBER(*,0), 
	"PUBLISHER_ID" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table MECHANICS
--------------------------------------------------------

  CREATE TABLE "C##MFORP"."MECHANICS" 
   (	"ID" NUMBER(*,0), 
	"NAME" VARCHAR2(40 BYTE), 
	"DESCRIPTION" VARCHAR2(280 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table ORDERS
--------------------------------------------------------

  CREATE TABLE "C##MFORP"."ORDERS" 
   (	"ORDER_ID" NUMBER(*,0), 
	"ITEM_ID" NUMBER(*,0), 
	"CUSTOMER_ID" NUMBER(*,0), 
	"DATE_SHIPPED" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table PUBLISHERS
--------------------------------------------------------

  CREATE TABLE "C##MFORP"."PUBLISHERS" 
   (	"ID" NUMBER(*,0), 
	"NAME" VARCHAR2(45 BYTE), 
	"WEBSITE" VARCHAR2(40 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table RENTALS
--------------------------------------------------------

  CREATE TABLE "C##MFORP"."RENTALS" 
   (	"ID" NUMBER(*,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE , 
	"CUSTOMER_ID" NUMBER(*,0), 
	"ITEM_ID" NUMBER(*,0), 
	"DATE_RENTED" DATE, 
	"DUE_DATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table SHOPPING_CART
--------------------------------------------------------

  CREATE TABLE "C##MFORP"."SHOPPING_CART" 
   (	"ID" NUMBER(*,0), 
	"CUSTOMER_ID" NUMBER(*,0), 
	"ITEM_ID" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table STATUSES
--------------------------------------------------------

  CREATE TABLE "C##MFORP"."STATUSES" 
   (	"STATUS_ID" NUMBER(*,0), 
	"NAME" VARCHAR2(15 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table STOCK
--------------------------------------------------------

  CREATE TABLE "C##MFORP"."STOCK" 
   (	"ITEM_ID" NUMBER(*,0), 
	"GAME_ID" NUMBER(*,0), 
	"STATUS_ID" NUMBER(*,0), 
	"SERIAL_NUMBER" CHAR(10 BYTE), 
	"ACQUISITION_DATE" DATE, 
	"CONDITION_ID" NUMBER(*,0), 
	"LAST_EXAMINED" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table ZIPCODES
--------------------------------------------------------

  CREATE TABLE "C##MFORP"."ZIPCODES" 
   (	"ZIPCODE" NUMBER(6,0), 
	"CITY" VARCHAR2(20 BYTE), 
	"STATE" VARCHAR2(25 BYTE), 
	"COUNTRY" VARCHAR2(25 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Sequence CUST_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "C##MFORP"."CUST_SEQUENCE"  MINVALUE 1 MAXVALUE 10000 INCREMENT BY 1 START WITH 121 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence GAME_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "C##MFORP"."GAME_SEQ"  MINVALUE 1 MAXVALUE 10000 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
-- Unable to render SEQUENCE DDL for object C##MFORP.ISEQ$$_90294 with DBMS_METADATA attempting internal generator.
CREATE SEQUENCE ISEQ$$_90294 INCREMENT BY 1 MAXVALUE 9999999999999999999999999999 MINVALUE 1 CACHE 20
-- Unable to render SEQUENCE DDL for object C##MFORP.ISEQ$$_90312 with DBMS_METADATA attempting internal generator.
CREATE SEQUENCE ISEQ$$_90312 INCREMENT BY 1 MAXVALUE 9999999999999999999999999999 MINVALUE 1 CACHE 20
REM INSERTING into C##MFORP.CONDITIONS
SET DEFINE OFF;
Insert into C##MFORP.CONDITIONS (CONDITION_ID,NAME) values (1,'Excellent');
Insert into C##MFORP.CONDITIONS (CONDITION_ID,NAME) values (2,'Good');
Insert into C##MFORP.CONDITIONS (CONDITION_ID,NAME) values (3,'Fair');
Insert into C##MFORP.CONDITIONS (CONDITION_ID,NAME) values (4,'Poor');
Insert into C##MFORP.CONDITIONS (CONDITION_ID,NAME) values (5,'Needs Replacement');
REM INSERTING into C##MFORP.CUSTOMERS
SET DEFINE OFF;
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (22,null,null,'test01','test01@gmail.com','BCAEFF15E63B62916D6D83FA975832449C36A298F10B9BA26A3CD0FFB5EDE322',null,null,null,null,'Active',to_date('09-AUG-18','DD-MON-RR'),0,'00FD726A86CCB7746FA5CB57A991DA18');
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (23,null,null,'test03','test03@gmail.com','6FD163B9E41FF67407CAD17BEC69CAE32A975BF6F14757D7A5DBABFBDDA19E7C',null,null,null,null,'Active',to_date('09-AUG-18','DD-MON-RR'),0,'E95C0DAC99D13F18A6AD0B484DD1E734');
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (41,null,null,'ploss','ploss@gmail.com','95C43EB7E688C8AFF68D4E5190590DDE71FE984EB4F1F74EF40FA84082F520B3',null,null,null,null,'Active',to_date('12-AUG-18','DD-MON-RR'),0,'DDF8823EF2C95DEC27FFF5C2C7A1134C');
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (44,null,null,'nfields','nfields@gmail.com','215ABC3CF7C7535DDF380B894A8AFC8AC1314031D844C9F40AE5D2DA41F7C0E9',null,null,null,null,'Active',to_date('12-AUG-18','DD-MON-RR'),0,'1CFE1DC137341D63364F071654E97094');
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (1,'Chris','Acker','cacker','cacker@gmail.com','cacker91','12 pinehearst ave.',null,7006,8625173384,'Active',to_date('05-JAN-10','DD-MON-RR'),0,null);
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (2,'Paul','Borer','pborer','pborer@gmail.com','pborer92','10 lime ave.',null,7952,8654467552,'Active',to_date('02-FEB-09','DD-MON-RR'),0,null);
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (3,'Aaron','Cleese','acleese','acleese@gmail.com','acleese93','8 central blvd.','apt 12b',12517,4590322246,'Active',to_date('03-FEB-08','DD-MON-RR'),0,null);
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (4,'Erica','Doberman','edober','edober@hotmail.com','edober94','22 photon st.',null,9001,8175533054,'Suspended',to_date('05-MAY-12','DD-MON-RR'),5,null);
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (5,'Angela','Elgin','aelgin','aelgin@gmail.com','aelgin95','1 infinity loop st.',null,7006,5173365534,'Active',to_date('12-JUN-16','DD-MON-RR'),5,null);
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (6,'Brenda','Farmer','bfarmer212','bfarmer@gmail.com','bfarmer95','2 mobius drive','apt 2a',7005,2243389419,'Active',to_date('23-SEP-00','DD-MON-RR'),10,null);
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (7,'Derek','Gale','dgale','dgale@gmail.com','dgale97','3 kleinbottle road',null,7952,1421516322,'Active',to_date('21-OCT-01','DD-MON-RR'),0,null);
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (8,'Francis','Hopper','fhopper','fhopper@gmail.com','fhopper84','4 torus st','apt 3b',12517,556785990,'Active',to_date('19-NOV-02','DD-MON-RR'),0,null);
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (9,'Gordon','Incel','gincel','gincel@gmail.com','gincel92','5 dodecahedron dr',null,9001,6768789231,'Active',to_date('07-AUG-03','DD-MON-RR'),0,null);
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (10,'Helen','Jenkins','hjenkins','hjenkins@gmail.com','hjenkins89','6 cube blvd',null,7005,6763432215,'Active',to_date('15-FEB-01','DD-MON-RR'),1,null);
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (42,'Richard',null,'rhamburg','rhamburg@gmail.com','8BA83891324F4D36D44380F18C577BE06AB0896AAFA9DFAB7F1A2B07E783D68B',null,null,7005,0,'Active',to_date('12-AUG-18','DD-MON-RR'),0,'9493D930E5588983BDADCC0481562C16');
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (43,null,null,'afields','afields@gmail.com','9810D85C309B84C7FB928B72B963F03145AB71B6FA4996FEB2FA1F477010E593',null,null,null,null,'Active',to_date('12-AUG-18','DD-MON-RR'),0,'FB374975E651689713D1E11383D83C7E');
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (46,'Derek','Fields','derek','derek@fields.name','357687838B26F915B9C54234F4329139711429F6061A9015CA55E0693BAF3280',null,null,7952,0,'Active',to_date('12-AUG-18','DD-MON-RR'),0,'A30210DA7B62E653F39E827204551757');
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (61,'Ida','Law','ida','ida@gmail.com','0D876A529BF515A86B381591B926D150380EEC27EDD30D08727807CCDD07A38B',null,null,7005,0,'Active',to_date('13-AUG-18','DD-MON-RR'),0,'E98A9FBEB3436D9D95769E5AC208BF58');
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (21,'Noah','Fields','nqeron','nqeron@gmail.com','BDF473C4564521791E1546CB8B570418ACC9D7BCA1871190D6ADBC3074F281B5','5 riverside dr',null,7006,8622236855,'Active',to_date('09-AUG-18','DD-MON-RR'),0,'AB252FBD466874ED1C777CEBFC20DEE0');
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (45,null,null,'rglass','rglass@gmail.com','7C0C68D04DC1B2FA24419B40FE23C7E7CF31CCF31B606B93063C276F2038D391',null,null,null,null,'Active',to_date('12-AUG-18','DD-MON-RR'),0,'304E0192A2F3B67ABBB56B1D457B03F5');
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (62,null,null,'lowcal','lowcal@gmail.com','007CF2BD422EC831B54927BFC2560401616E61B5553633777A30EB6BB2DE1E72',null,null,null,null,'Active',to_date('13-AUG-18','DD-MON-RR'),0,'D646269D9119A41B8BE719C4B5E28D49');
Insert into C##MFORP.CUSTOMERS (ID,LAST_NAME,FIRST_NAME,USERNAME,E_MAIL,PASSWORD,ADDRESS_LINE_1,ADDRESS_LINE_2,ZIPCODE,PHONE,MEMBER_STATUS,JOIN_DATE,BALANCE,SALT) values (81,null,null,'qerosfn','qerosfn@gmail.com','CF7D3E12981E023C8A146B6994C434AD3E8E2ADD97264CDF92F87E9167BA4C61',null,null,0,0,'Active',to_date('29-AUG-18','DD-MON-RR'),0,'31F4158CC0BE7D9A35DE978F99EA7CD0');
REM INSERTING into C##MFORP.DESIGNERS
SET DEFINE OFF;
Insert into C##MFORP.DESIGNERS (ID,FIRST_NAME,LAST_NAME,WEBSITE) values (1,'Uwe','Rosenberg',null);
Insert into C##MFORP.DESIGNERS (ID,FIRST_NAME,LAST_NAME,WEBSITE) values (2,'Bruno','Cathala','http://www.brunocathala.com/');
Insert into C##MFORP.DESIGNERS (ID,FIRST_NAME,LAST_NAME,WEBSITE) values (3,'Matt','Leacock','http://www.leacock.com/');
Insert into C##MFORP.DESIGNERS (ID,FIRST_NAME,LAST_NAME,WEBSITE) values (4,'Jamey','Stegmaier','http://jameystegmaier.com/');
Insert into C##MFORP.DESIGNERS (ID,FIRST_NAME,LAST_NAME,WEBSITE) values (5,'Alan','Stone',null);
Insert into C##MFORP.DESIGNERS (ID,FIRST_NAME,LAST_NAME,WEBSITE) values (6,'Morten','Pedersen',null);
Insert into C##MFORP.DESIGNERS (ID,FIRST_NAME,LAST_NAME,WEBSITE) values (7,'Simone','Luciani',null);
Insert into C##MFORP.DESIGNERS (ID,FIRST_NAME,LAST_NAME,WEBSITE) values (8,'Michael','Kiesling',null);
Insert into C##MFORP.DESIGNERS (ID,FIRST_NAME,LAST_NAME,WEBSITE) values (9,'Wolfgang','Warsch',null);
REM INSERTING into C##MFORP.GAMES
SET DEFINE OFF;
Insert into C##MFORP.GAMES (ID,NAME,DESCRIPTION,YEAR_PUBLISHED,COST_OF_GAME,AVERAGE_RATING) values (1,'Le Havre','The gameplay takes place in the harbour of Le Havre, where players take goods such as fish and wood from the wharves. These goods are used either to feed the players'' community, to construct buildings and ships, or are processed into finished goods.',2008,70,2.12);
Insert into C##MFORP.GAMES (ID,NAME,DESCRIPTION,YEAR_PUBLISHED,COST_OF_GAME,AVERAGE_RATING) values (2,'Kingdomino','In Kingdomino, you are a Lord seeking new lands in which to expand your kingdom. You must explore all the lands, wheat fields, lakes, and mountains in order to spot the best plots. But be careful as some other Lords also covet these lands...',2016,20,2.08);
Insert into C##MFORP.GAMES (ID,NAME,DESCRIPTION,YEAR_PUBLISHED,COST_OF_GAME,AVERAGE_RATING) values (3,'Five Tribes','Crossing into the Land of 1001 Nights, your caravan arrives at the fabled Sultanate of Naqala. The old sultan just died and control of Naqala is up for grabs! The oracles foretold of strangers who would maneuver the Five Tribes to gain influence over the legendary city-state. Will you fulfill the prophecy? Invoke the old Djinns and move the Tribes into position at the right time, and the Sultanate may become yours!',2014,41,2.24);
Insert into C##MFORP.GAMES (ID,NAME,DESCRIPTION,YEAR_PUBLISHED,COST_OF_GAME,AVERAGE_RATING) values (4,'Pandemic','In Pandemic, several virulent diseases have broken out simultaneously all over the world! The players are disease-fighting specialists whose mission is to treat disease hotspots while researching cures for each of four plagues before they get out of hand.',2008,36,2.44);
Insert into C##MFORP.GAMES (ID,NAME,DESCRIPTION,YEAR_PUBLISHED,COST_OF_GAME,AVERAGE_RATING) values (5,'Viticulture Essentials Edition','In Viticulture, the players find themselves in the roles of people in rustic, pre-modern Tuscany who have inherited meager vineyards. They have a few plots of land, an old crushpad, a tiny cellar, and three workers. They each have a dream of being the first to call their winery a true success.',2015,60,2.3);
Insert into C##MFORP.GAMES (ID,NAME,DESCRIPTION,YEAR_PUBLISHED,COST_OF_GAME,AVERAGE_RATING) values (6,'Tzolk''in: The Mayan Calendar','Tzolkin: The Mayan Calendar presents a new game mechanism: dynamic worker placement. Players representing different Mayan tribes place their workers on giant connected gears, and as the gears rotate they take the workers to different action spots.',2012,34,2.5);
Insert into C##MFORP.GAMES (ID,NAME,DESCRIPTION,YEAR_PUBLISHED,COST_OF_GAME,AVERAGE_RATING) values (7,'Azul','In the game Azul, players take turns drafting colored tiles from suppliers to their player board. Later in the round, players score points based on how they''ve placed their tiles to decorate the palace. Extra points are scored for specific patterns and completing sets; wasted supplies harm the player''s score. The player with the most points at the end of the game wins.',2017,30,2.57);
Insert into C##MFORP.GAMES (ID,NAME,DESCRIPTION,YEAR_PUBLISHED,COST_OF_GAME,AVERAGE_RATING) values (8,'The Mind','The Mind is more than just a game. It''s an experiment, a journey, a team experience in which you can''t exchange information, yet will become one to defeat all the levels of the game.',2018,25,2.57);
Insert into C##MFORP.GAMES (ID,NAME,DESCRIPTION,YEAR_PUBLISHED,COST_OF_GAME,AVERAGE_RATING) values (9,'Caverna: The Cave Farmers','In the game, you are the bearded leader of a small dwarf family that lives in a little cave in the mountains. You begin the game with a farmer and his spouse, and each member of the farming family represents an action that the player can take each turn. Together, you cultivate the forest in front of your cave and dig deeper into the mountain. You furnish the caves as dwellings for your offspring as well as working spaces for small enterprises.',2013,97,2.56);
Insert into C##MFORP.GAMES (ID,NAME,DESCRIPTION,YEAR_PUBLISHED,COST_OF_GAME,AVERAGE_RATING) values (10,'Scythe','In Scythe, each player represents a character from one of five factions of Eastern Europe who are attempting to earn their fortune and claim their faction''s stake in the land around the mysterious Factory. Players conquer territory, enlist new recruits, reap resources, gain villagers, build structures, and activate monstrous mechs.',2016,80,3.27);
REM INSERTING into C##MFORP.GAME_COMMENTS
SET DEFINE OFF;
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (5,1,'Viticulture is such an amazing game!',5,to_date('01-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (2,1,'Kingdomino is an ok game',2,to_date('02-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (5,5,'Vitculture is a good medium weight Euro',4,to_date('03-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (1,6,'Le Havre is a very deep game',1.01,to_date('04-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (1,7,'Le Havre is far and away the best trading game',1.95,to_date('05-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (2,6,'Kingdomino is addictive',3.57,to_date('06-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (2,10,'My kids love this game!',3.42,to_date('07-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (3,10,'Such a good point salad game',3.34,to_date('08-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (3,1,'Bruno Cathala has done it again!',3.54,to_date('09-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (4,2,'A good coop',1.5,to_date('10-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (4,3,'Nice to have a game where players work together',4.78,to_date('11-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (4,5,'It''s fun to fight diseases',1.78,to_date('12-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (6,2,'Spinny Disk!',3,to_date('13-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (6,6,'I like this take on the classic worker placement',2.66,to_date('14-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (6,8,'Will recommend to all my friends',1.05,to_date('15-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (7,8,'Good pattern building',1.47,to_date('16-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (7,5,'Pretty windows',3.39,to_date('17-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (8,2,'We have become one',3.83,to_date('18-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (8,3,'What an interesting experience',2.93,to_date('19-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (9,7,'Long, complex, but good',1.78,to_date('20-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (9,9,'Deep and strategic',3.73,to_date('21-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (10,7,'Love the complexity',3.23,to_date('22-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (10,10,'Stegmeir has made another amazing game!',1.06,to_date('23-JAN-16','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (10,21,'Very strategic - what a wonderful game!',4,to_date('28-AUG-18','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (3,21,'A great game. The only negative is the amount of pieces involved.',3.5,to_date('28-AUG-18','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (9,21,'Caverna is a wonderful game.',3.5,to_date('28-AUG-18','DD-MON-RR'));
Insert into C##MFORP.GAME_COMMENTS (GAME_ID,CUSTOMER_ID,COMMENT_TEXT,RATING,COMMENT_DATE) values (8,21,'An interesting experience, but lacks good strategy. ',3,to_date('28-AUG-18','DD-MON-RR'));
REM INSERTING into C##MFORP.GAME_DESIGNERS
SET DEFINE OFF;
Insert into C##MFORP.GAME_DESIGNERS (GAME_ID,DESIGNER_ID) values (1,1);
Insert into C##MFORP.GAME_DESIGNERS (GAME_ID,DESIGNER_ID) values (2,2);
Insert into C##MFORP.GAME_DESIGNERS (GAME_ID,DESIGNER_ID) values (3,2);
Insert into C##MFORP.GAME_DESIGNERS (GAME_ID,DESIGNER_ID) values (4,3);
Insert into C##MFORP.GAME_DESIGNERS (GAME_ID,DESIGNER_ID) values (5,4);
Insert into C##MFORP.GAME_DESIGNERS (GAME_ID,DESIGNER_ID) values (5,5);
Insert into C##MFORP.GAME_DESIGNERS (GAME_ID,DESIGNER_ID) values (5,6);
Insert into C##MFORP.GAME_DESIGNERS (GAME_ID,DESIGNER_ID) values (6,7);
Insert into C##MFORP.GAME_DESIGNERS (GAME_ID,DESIGNER_ID) values (7,8);
Insert into C##MFORP.GAME_DESIGNERS (GAME_ID,DESIGNER_ID) values (8,9);
Insert into C##MFORP.GAME_DESIGNERS (GAME_ID,DESIGNER_ID) values (9,1);
Insert into C##MFORP.GAME_DESIGNERS (GAME_ID,DESIGNER_ID) values (10,4);
REM INSERTING into C##MFORP.GAME_MECHANICS
SET DEFINE OFF;
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (1,1);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (2,2);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (2,3);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (2,4);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (3,2);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (3,5);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (3,6);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (3,7);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (3,8);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (4,8);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (4,9);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (4,10);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (4,11);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (4,12);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (4,13);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (4,14);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (5,1);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (5,11);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (5,15);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (6,1);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (6,8);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (7,2);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (7,3);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (7,4);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (7,8);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (8,10);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (8,11);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (9,1);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (9,4);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (10,5);
Insert into C##MFORP.GAME_MECHANICS (GAME_ID,MECHANIC_ID) values (10,16);
REM INSERTING into C##MFORP.GAME_PICTURES
SET DEFINE OFF;
Insert into C##MFORP.GAME_PICTURES (ID,GAME_ID,PICTURE_SIZE,URI) values (1,5,2,'viticulture-medium.jpg');
Insert into C##MFORP.GAME_PICTURES (ID,GAME_ID,PICTURE_SIZE,URI) values (2,3,2,'fivetribes-medium.jpg');
Insert into C##MFORP.GAME_PICTURES (ID,GAME_ID,PICTURE_SIZE,URI) values (3,8,2,'themind-medium.jpg');
Insert into C##MFORP.GAME_PICTURES (ID,GAME_ID,PICTURE_SIZE,URI) values (4,2,2,'kingdomino-medium.jpg');
Insert into C##MFORP.GAME_PICTURES (ID,GAME_ID,PICTURE_SIZE,URI) values (5,9,2,'caverna-medium.jpg');
Insert into C##MFORP.GAME_PICTURES (ID,GAME_ID,PICTURE_SIZE,URI) values (6,4,2,'pandemic-medium.jpg');
Insert into C##MFORP.GAME_PICTURES (ID,GAME_ID,PICTURE_SIZE,URI) values (7,7,2,'azul-medium.jpg');
Insert into C##MFORP.GAME_PICTURES (ID,GAME_ID,PICTURE_SIZE,URI) values (8,6,2,'tzolkin-medium.jpg');
Insert into C##MFORP.GAME_PICTURES (ID,GAME_ID,PICTURE_SIZE,URI) values (9,10,2,'scythe-medium.jpg');
Insert into C##MFORP.GAME_PICTURES (ID,GAME_ID,PICTURE_SIZE,URI) values (10,1,2,'lehavre-medium.jpg');
REM INSERTING into C##MFORP.GAME_PUBLISHERS
SET DEFINE OFF;
Insert into C##MFORP.GAME_PUBLISHERS (GAME_ID,PUBLISHER_ID) values (1,1);
Insert into C##MFORP.GAME_PUBLISHERS (GAME_ID,PUBLISHER_ID) values (2,2);
Insert into C##MFORP.GAME_PUBLISHERS (GAME_ID,PUBLISHER_ID) values (2,3);
Insert into C##MFORP.GAME_PUBLISHERS (GAME_ID,PUBLISHER_ID) values (2,4);
Insert into C##MFORP.GAME_PUBLISHERS (GAME_ID,PUBLISHER_ID) values (3,5);
Insert into C##MFORP.GAME_PUBLISHERS (GAME_ID,PUBLISHER_ID) values (3,6);
Insert into C##MFORP.GAME_PUBLISHERS (GAME_ID,PUBLISHER_ID) values (4,7);
Insert into C##MFORP.GAME_PUBLISHERS (GAME_ID,PUBLISHER_ID) values (5,8);
Insert into C##MFORP.GAME_PUBLISHERS (GAME_ID,PUBLISHER_ID) values (6,9);
Insert into C##MFORP.GAME_PUBLISHERS (GAME_ID,PUBLISHER_ID) values (7,10);
Insert into C##MFORP.GAME_PUBLISHERS (GAME_ID,PUBLISHER_ID) values (8,11);
Insert into C##MFORP.GAME_PUBLISHERS (GAME_ID,PUBLISHER_ID) values (9,1);
Insert into C##MFORP.GAME_PUBLISHERS (GAME_ID,PUBLISHER_ID) values (10,8);
REM INSERTING into C##MFORP.MECHANICS
SET DEFINE OFF;
Insert into C##MFORP.MECHANICS (ID,NAME,DESCRIPTION) values (1,'Worker Placement','players select individual actions from a set of actions available to all players.');
Insert into C##MFORP.MECHANICS (ID,NAME,DESCRIPTION) values (2,'Card Drafting','Card drafting games are games in which players pick cards from a limited subset');
Insert into C##MFORP.MECHANICS (ID,NAME,DESCRIPTION) values (3,'Pattern Building','Pattern Building is a system where players place game components in specific patterns in order to gain specific or variable game results.');
Insert into C##MFORP.MECHANICS (ID,NAME,DESCRIPTION) values (4,'Tile Placement','Tile Placement games feature placing a piece to score VPs, with the amount often based on adjacent pieces');
Insert into C##MFORP.MECHANICS (ID,NAME,DESCRIPTION) values (5,'Area Control','typically awards control of an area to the player that has the majority of units or influence in that area.');
Insert into C##MFORP.MECHANICS (ID,NAME,DESCRIPTION) values (6,'Auction','This mechanic requires you to place a bid, usually monetary, on items in an auction of goods in order to enhance your position in the game.');
Insert into C##MFORP.MECHANICS (ID,NAME,DESCRIPTION) values (7,'Modular Board','Play occurs upon a modular board that is composed of multiple pieces, often tiles or cards.');
Insert into C##MFORP.MECHANICS (ID,NAME,DESCRIPTION) values (8,'Set Collection','The primary goal of a set collection mechanic is to encourage a player to collect a set of items.');
Insert into C##MFORP.MECHANICS (ID,NAME,DESCRIPTION) values (9,'Action Point Allowance','each player is allotted a certain amount of points per round. These points can be spent on available actions');
Insert into C##MFORP.MECHANICS (ID,NAME,DESCRIPTION) values (10,'Co-operative Play','Co-operative play encourages or requires players to work together to beat the game.');
Insert into C##MFORP.MECHANICS (ID,NAME,DESCRIPTION) values (11,'Hand Management','Hand management games are games with cards in them that reward players for playing the cards in certain sequences or groups.');
Insert into C##MFORP.MECHANICS (ID,NAME,DESCRIPTION) values (12,'Point to Point Movement','On a board of a game with point-to-point movement, there are certain spots that can be occupied by markers or figurines, e. g. cities on a map. These points are connected by lines, and movement can only happen along these lines.');
Insert into C##MFORP.MECHANICS (ID,NAME,DESCRIPTION) values (13,'Trading','In games with a trading mechanic, the players can exchange game items between each other.');
Insert into C##MFORP.MECHANICS (ID,NAME,DESCRIPTION) values (14,'Variable Player Powers','Variable Player Powers is a mechanic that grants different abilities and/or paths to victory to the players');
Insert into C##MFORP.MECHANICS (ID,NAME,DESCRIPTION) values (15,'Variable Phase Order','Variable Phase Order implies that turns may not be played the same way as before and / or after');
Insert into C##MFORP.MECHANICS (ID,NAME,DESCRIPTION) values (16,'Grid Movement','The Grid Movement occurs when pawns move on the grid in many directions.');
REM INSERTING into C##MFORP.ORDERS
SET DEFINE OFF;
Insert into C##MFORP.ORDERS (ORDER_ID,ITEM_ID,CUSTOMER_ID,DATE_SHIPPED) values (0,15,21,null);
Insert into C##MFORP.ORDERS (ORDER_ID,ITEM_ID,CUSTOMER_ID,DATE_SHIPPED) values (1,14,21,null);
Insert into C##MFORP.ORDERS (ORDER_ID,ITEM_ID,CUSTOMER_ID,DATE_SHIPPED) values (3,8,21,null);
Insert into C##MFORP.ORDERS (ORDER_ID,ITEM_ID,CUSTOMER_ID,DATE_SHIPPED) values (12,28,21,null);
Insert into C##MFORP.ORDERS (ORDER_ID,ITEM_ID,CUSTOMER_ID,DATE_SHIPPED) values (4,22,21,null);
Insert into C##MFORP.ORDERS (ORDER_ID,ITEM_ID,CUSTOMER_ID,DATE_SHIPPED) values (4,25,21,null);
Insert into C##MFORP.ORDERS (ORDER_ID,ITEM_ID,CUSTOMER_ID,DATE_SHIPPED) values (5,28,21,null);
Insert into C##MFORP.ORDERS (ORDER_ID,ITEM_ID,CUSTOMER_ID,DATE_SHIPPED) values (9,28,21,null);
Insert into C##MFORP.ORDERS (ORDER_ID,ITEM_ID,CUSTOMER_ID,DATE_SHIPPED) values (10,29,21,null);
Insert into C##MFORP.ORDERS (ORDER_ID,ITEM_ID,CUSTOMER_ID,DATE_SHIPPED) values (11,30,21,null);
Insert into C##MFORP.ORDERS (ORDER_ID,ITEM_ID,CUSTOMER_ID,DATE_SHIPPED) values (2,7,21,null);
Insert into C##MFORP.ORDERS (ORDER_ID,ITEM_ID,CUSTOMER_ID,DATE_SHIPPED) values (7,9,21,null);
Insert into C##MFORP.ORDERS (ORDER_ID,ITEM_ID,CUSTOMER_ID,DATE_SHIPPED) values (7,26,21,null);
Insert into C##MFORP.ORDERS (ORDER_ID,ITEM_ID,CUSTOMER_ID,DATE_SHIPPED) values (6,29,21,null);
Insert into C##MFORP.ORDERS (ORDER_ID,ITEM_ID,CUSTOMER_ID,DATE_SHIPPED) values (8,27,21,null);
Insert into C##MFORP.ORDERS (ORDER_ID,ITEM_ID,CUSTOMER_ID,DATE_SHIPPED) values (8,30,21,null);
Insert into C##MFORP.ORDERS (ORDER_ID,ITEM_ID,CUSTOMER_ID,DATE_SHIPPED) values (13,22,21,null);
REM INSERTING into C##MFORP.PUBLISHERS
SET DEFINE OFF;
Insert into C##MFORP.PUBLISHERS (ID,NAME,WEBSITE) values (1,'Lookout Games','http://www.lookout-games.de/');
Insert into C##MFORP.PUBLISHERS (ID,NAME,WEBSITE) values (2,'Blue Orange Games','http://www.blueorangegames.com/');
Insert into C##MFORP.PUBLISHERS (ID,NAME,WEBSITE) values (3,'Fantasmagoria','http://www.fantasmagoria.bg/');
Insert into C##MFORP.PUBLISHERS (ID,NAME,WEBSITE) values (4,'Feelindigo',null);
Insert into C##MFORP.PUBLISHERS (ID,NAME,WEBSITE) values (5,'Days of Wonder','http://www.daysofwonder.com/');
Insert into C##MFORP.PUBLISHERS (ID,NAME,WEBSITE) values (6,'Asterion Press','http://www.asterionpress.com/');
Insert into C##MFORP.PUBLISHERS (ID,NAME,WEBSITE) values (7,'Z-Man Games','http://www.zmangames.com/');
Insert into C##MFORP.PUBLISHERS (ID,NAME,WEBSITE) values (8,'Stonemaier Games','http://www.stonemaiergames.com/');
Insert into C##MFORP.PUBLISHERS (ID,NAME,WEBSITE) values (9,'Czech Games Edition','http://www.czechgames.com/');
Insert into C##MFORP.PUBLISHERS (ID,NAME,WEBSITE) values (10,'Next Move Games','https://www.nextmovegames.com/');
Insert into C##MFORP.PUBLISHERS (ID,NAME,WEBSITE) values (11,'Nürnberger-Spielkarten-Verlag','http://www.nsv.de/');
REM INSERTING into C##MFORP.RENTALS
SET DEFINE OFF;
REM INSERTING into C##MFORP.SHOPPING_CART
SET DEFINE OFF;
Insert into C##MFORP.SHOPPING_CART (ID,CUSTOMER_ID,ITEM_ID) values (10,46,23);
Insert into C##MFORP.SHOPPING_CART (ID,CUSTOMER_ID,ITEM_ID) values (1,1,1);
Insert into C##MFORP.SHOPPING_CART (ID,CUSTOMER_ID,ITEM_ID) values (2,1,2);
Insert into C##MFORP.SHOPPING_CART (ID,CUSTOMER_ID,ITEM_ID) values (3,1,4);
Insert into C##MFORP.SHOPPING_CART (ID,CUSTOMER_ID,ITEM_ID) values (4,2,10);
Insert into C##MFORP.SHOPPING_CART (ID,CUSTOMER_ID,ITEM_ID) values (5,2,11);
Insert into C##MFORP.SHOPPING_CART (ID,CUSTOMER_ID,ITEM_ID) values (6,3,13);
Insert into C##MFORP.SHOPPING_CART (ID,CUSTOMER_ID,ITEM_ID) values (7,3,12);
Insert into C##MFORP.SHOPPING_CART (ID,CUSTOMER_ID,ITEM_ID) values (11,21,16);
REM INSERTING into C##MFORP.STATUSES
SET DEFINE OFF;
Insert into C##MFORP.STATUSES (STATUS_ID,NAME) values (1,'In Stock');
Insert into C##MFORP.STATUSES (STATUS_ID,NAME) values (2,'En Route');
Insert into C##MFORP.STATUSES (STATUS_ID,NAME) values (3,'Rented');
REM INSERTING into C##MFORP.STOCK
SET DEFINE OFF;
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (1,1,1,'SNJ0150   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (2,1,1,'SNJ0151   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (3,1,1,'SNJ0152   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (4,2,1,'SNJ0153   ',to_date('01-JAN-16','DD-MON-RR'),2,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (5,2,1,'SNJ0154   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (6,2,1,'SNJ0155   ',to_date('01-JAN-16','DD-MON-RR'),3,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (7,3,1,'SNJ0156   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (8,3,1,'SNJ0157   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (9,3,1,'SNJ0158   ',to_date('01-JAN-16','DD-MON-RR'),4,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (10,4,1,'SNJ0159   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (11,4,1,'SNJ0160   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (12,4,1,'SNJ0161   ',to_date('01-JAN-16','DD-MON-RR'),2,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (13,5,1,'SNJ0162   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (14,5,1,'SNJ0163   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (15,5,1,'SNJ0164   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (16,6,1,'SNJ0165   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (17,6,1,'SNJ0166   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (18,6,1,'SNJ0167   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (19,7,1,'SNJ0168   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (20,7,1,'SNJ0169   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (21,7,1,'SNJ0170   ',to_date('01-JAN-16','DD-MON-RR'),2,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (22,8,1,'SNJ0171   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (23,8,1,'SNJ0172   ',to_date('01-JAN-16','DD-MON-RR'),3,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (24,8,1,'SNJ0173   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (25,9,1,'SNJ0174   ',to_date('01-JAN-16','DD-MON-RR'),4,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (26,9,1,'SNJ0175   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (27,9,1,'SNJ0176   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (28,10,1,'SNJ0177   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (29,10,1,'SNJ0178   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
Insert into C##MFORP.STOCK (ITEM_ID,GAME_ID,STATUS_ID,SERIAL_NUMBER,ACQUISITION_DATE,CONDITION_ID,LAST_EXAMINED) values (30,10,1,'SNJ0179   ',to_date('01-JAN-16','DD-MON-RR'),1,to_date('06-JUN-18','DD-MON-RR'));
REM INSERTING into C##MFORP.ZIPCODES
SET DEFINE OFF;
Insert into C##MFORP.ZIPCODES (ZIPCODE,CITY,STATE,COUNTRY) values (7006,'Caldwell','NJ','USA');
Insert into C##MFORP.ZIPCODES (ZIPCODE,CITY,STATE,COUNTRY) values (7952,'Bernardsville','NJ','USA');
Insert into C##MFORP.ZIPCODES (ZIPCODE,CITY,STATE,COUNTRY) values (12517,'Copake Falls','NY','USA');
Insert into C##MFORP.ZIPCODES (ZIPCODE,CITY,STATE,COUNTRY) values (9001,'Nowhere','KY','USA');
Insert into C##MFORP.ZIPCODES (ZIPCODE,CITY,STATE,COUNTRY) values (7005,'Boonton','NJ','USA');
Insert into C##MFORP.ZIPCODES (ZIPCODE,CITY,STATE,COUNTRY) values (0,null,null,null);
--------------------------------------------------------
--  DDL for Index SYS_C0012314
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0012314" ON "C##MFORP"."GAMES" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0012315
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0012315" ON "C##MFORP"."MECHANICS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0012316
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0012316" ON "C##MFORP"."GAME_MECHANICS" ("GAME_ID", "MECHANIC_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0012319
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0012319" ON "C##MFORP"."PUBLISHERS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0012320
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0012320" ON "C##MFORP"."GAME_PUBLISHERS" ("GAME_ID", "PUBLISHER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0012323
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0012323" ON "C##MFORP"."ZIPCODES" ("ZIPCODE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0012327
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0012327" ON "C##MFORP"."CUSTOMERS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0012328
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0012328" ON "C##MFORP"."CUSTOMERS" ("USERNAME") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0012329
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0012329" ON "C##MFORP"."CUSTOMERS" ("E_MAIL") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0012331
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0012331" ON "C##MFORP"."GAME_COMMENTS" ("GAME_ID", "CUSTOMER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0012334
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0012334" ON "C##MFORP"."STATUSES" ("STATUS_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0012335
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0012335" ON "C##MFORP"."CONDITIONS" ("CONDITION_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0012337
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0012337" ON "C##MFORP"."STOCK" ("ITEM_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0012338
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0012338" ON "C##MFORP"."STOCK" ("SERIAL_NUMBER") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0012342
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0012342" ON "C##MFORP"."SHOPPING_CART" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0012343
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0012343" ON "C##MFORP"."SHOPPING_CART" ("ITEM_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0012354
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0012354" ON "C##MFORP"."DESIGNERS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0012355
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0012355" ON "C##MFORP"."GAME_DESIGNERS" ("GAME_ID", "DESIGNER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0012358
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0012358" ON "C##MFORP"."GAME_PICTURES" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0016100
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0016100" ON "C##MFORP"."ORDERS" ("ORDER_ID", "ITEM_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0016104
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0016104" ON "C##MFORP"."RENTALS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0016105
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##MFORP"."SYS_C0016105" ON "C##MFORP"."RENTALS" ("ITEM_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger CALC_AVERAGE_RATING
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "C##MFORP"."CALC_AVERAGE_RATING" 
after insert or update on Game_Comments
declare avg_rating number;
begin
  for x in (Select Game_ID, ROUND(AVG(Rating),2) as avgRating FROM Game_Comments GROUP BY Game_ID )
  loop
    Update Games set Average_Rating = x.avgRating WHERE id = x.Game_ID;
  end loop;
end;
/
ALTER TRIGGER "C##MFORP"."CALC_AVERAGE_RATING" ENABLE;
--------------------------------------------------------
--  DDL for Trigger CUSTOMER_GENERATOR
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "C##MFORP"."CUSTOMER_GENERATOR" 
before insert on Customers
for each row
begin
  Select cust_sequence.nextval INTO :new.id FROM dual;
end;
/
ALTER TRIGGER "C##MFORP"."CUSTOMER_GENERATOR" ENABLE;
--------------------------------------------------------
--  DDL for Trigger GAME_GEN
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "C##MFORP"."GAME_GEN" 
before insert on Games
for each row
begin
  Select game_seq.nextval INTO :new.id FROM dual;
end;

-- function to calculate # weeks since given date;
-- returns null if year difference is >= 1; 
-- Otherwise returns difference (may involve negative numbers)
/
ALTER TRIGGER "C##MFORP"."GAME_GEN" ENABLE;
--------------------------------------------------------
--  DDL for Procedure CHECKRENTALWEEKS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##MFORP"."CHECKRENTALWEEKS" is
priceOfGame number;
begin
  for x in (Select Customer_ID, Item_ID, weeksSince(Due_Date, Sysdate)
            as weeksFrom from Rentals) 
  loop
    if x.weeksFrom > 1 and x.weeksFrom < 10 then
      update Customers set Balance = x.weeksFrom where id = x.Customer_ID;
    elsif x.weeksfrom >= 10 then
      Select Game.Cost_of_Game into priceOfGame 
        from Games Game join Stock item on Game.id = item.Game_ID
        where stock.Item_ID = x.Item_ID;
      update Customers set Balance = 10 + priceOfGame where id = x.Customer_ID;
    end if;
  end loop;
end;

/
--------------------------------------------------------
--  DDL for Procedure SUSPENDCUSTOMERS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##MFORP"."SUSPENDCUSTOMERS" is
begin
for x in (Select Customer_ID, count(Item_ID) from Rentals 
          where Sysdate > Due_Date group by Customer_ID 
          Having count(Item_ID) > 5)
loop
  update Customers set Member_Status = 'Suspended' where id = x.Customer_ID;
end loop;
end suspendCustomers;

/
--------------------------------------------------------
--  DDL for Function WEEKSSINCE
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "C##MFORP"."WEEKSSINCE" (
  fromDate date,
  toDate date
) 
return int
as
begin
  if to_char(toDate,'YYYY') - to_char(fromDate,'YYYY') >= 1 then
    return null;
  else
    return to_char(toDate,'WW') - to_char(fromDate,'WW');
  end if;
end weeksSince;

/
BEGIN
  DBMS_SCHEDULER.CREATE_JOB (
   job_name           =>  'update_balance_owed',
   job_type           =>  'STORED_PROCEDURE',
   job_action         =>  'CHECKRENTALWEEKS',
   start_date         =>  '01-JAN-18 07.00.00 PM',
   repeat_interval    =>  'FREQ=WEEKLY;INTERVAL=1',
   auto_drop          =>   FALSE,
   enabled            =>   TRUE,
   comments           =>  'auto check to see who is late returning');
END;
/
BEGIN
  DBMS_SCHEDULER.CREATE_JOB (
   job_name          =>  'suspendCustomersFromDB',
   job_type          =>  'STORED_PROCEDURE',
   job_action        =>  'SUSPENDCUSTOMERS', 
   repeat_interval   =>  'FREQ=DAILY;BYHOUR=12',
   enabled           =>  TRUE,
   comments          =>  'Suspend customers for too many late games, daily at noon');
END;
/
--------------------------------------------------------
--  Constraints for Table CONDITIONS
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."CONDITIONS" ADD PRIMARY KEY ("CONDITION_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table CUSTOMERS
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."CUSTOMERS" MODIFY ("USERNAME" NOT NULL ENABLE);
  ALTER TABLE "C##MFORP"."CUSTOMERS" MODIFY ("E_MAIL" NOT NULL ENABLE);
  ALTER TABLE "C##MFORP"."CUSTOMERS" MODIFY ("PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "C##MFORP"."CUSTOMERS" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "C##MFORP"."CUSTOMERS" ADD UNIQUE ("USERNAME")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "C##MFORP"."CUSTOMERS" ADD UNIQUE ("E_MAIL")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table DESIGNERS
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."DESIGNERS" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table GAMES
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."GAMES" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table GAME_COMMENTS
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."GAME_COMMENTS" ADD PRIMARY KEY ("GAME_ID", "CUSTOMER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table GAME_DESIGNERS
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."GAME_DESIGNERS" ADD PRIMARY KEY ("GAME_ID", "DESIGNER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table GAME_MECHANICS
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."GAME_MECHANICS" ADD PRIMARY KEY ("GAME_ID", "MECHANIC_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table GAME_PICTURES
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."GAME_PICTURES" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table GAME_PUBLISHERS
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."GAME_PUBLISHERS" ADD PRIMARY KEY ("GAME_ID", "PUBLISHER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table MECHANICS
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."MECHANICS" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ORDERS
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."ORDERS" ADD PRIMARY KEY ("ORDER_ID", "ITEM_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table PUBLISHERS
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."PUBLISHERS" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table RENTALS
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."RENTALS" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "C##MFORP"."RENTALS" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "C##MFORP"."RENTALS" ADD UNIQUE ("ITEM_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table SHOPPING_CART
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."SHOPPING_CART" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "C##MFORP"."SHOPPING_CART" ADD UNIQUE ("ITEM_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table STATUSES
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."STATUSES" ADD PRIMARY KEY ("STATUS_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table STOCK
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."STOCK" MODIFY ("SERIAL_NUMBER" NOT NULL ENABLE);
  ALTER TABLE "C##MFORP"."STOCK" ADD PRIMARY KEY ("ITEM_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "C##MFORP"."STOCK" ADD UNIQUE ("SERIAL_NUMBER")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ZIPCODES
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."ZIPCODES" ADD PRIMARY KEY ("ZIPCODE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CUSTOMERS
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."CUSTOMERS" ADD FOREIGN KEY ("ZIPCODE")
	  REFERENCES "C##MFORP"."ZIPCODES" ("ZIPCODE") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table GAME_COMMENTS
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."GAME_COMMENTS" ADD FOREIGN KEY ("GAME_ID")
	  REFERENCES "C##MFORP"."GAMES" ("ID") ENABLE;
  ALTER TABLE "C##MFORP"."GAME_COMMENTS" ADD FOREIGN KEY ("CUSTOMER_ID")
	  REFERENCES "C##MFORP"."CUSTOMERS" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table GAME_DESIGNERS
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."GAME_DESIGNERS" ADD FOREIGN KEY ("GAME_ID")
	  REFERENCES "C##MFORP"."GAMES" ("ID") ENABLE;
  ALTER TABLE "C##MFORP"."GAME_DESIGNERS" ADD FOREIGN KEY ("DESIGNER_ID")
	  REFERENCES "C##MFORP"."DESIGNERS" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table GAME_MECHANICS
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."GAME_MECHANICS" ADD FOREIGN KEY ("GAME_ID")
	  REFERENCES "C##MFORP"."GAMES" ("ID") ENABLE;
  ALTER TABLE "C##MFORP"."GAME_MECHANICS" ADD FOREIGN KEY ("MECHANIC_ID")
	  REFERENCES "C##MFORP"."MECHANICS" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table GAME_PICTURES
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."GAME_PICTURES" ADD FOREIGN KEY ("GAME_ID")
	  REFERENCES "C##MFORP"."GAMES" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table GAME_PUBLISHERS
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."GAME_PUBLISHERS" ADD FOREIGN KEY ("GAME_ID")
	  REFERENCES "C##MFORP"."GAMES" ("ID") ENABLE;
  ALTER TABLE "C##MFORP"."GAME_PUBLISHERS" ADD FOREIGN KEY ("PUBLISHER_ID")
	  REFERENCES "C##MFORP"."PUBLISHERS" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ORDERS
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."ORDERS" ADD FOREIGN KEY ("ITEM_ID")
	  REFERENCES "C##MFORP"."STOCK" ("ITEM_ID") ENABLE;
  ALTER TABLE "C##MFORP"."ORDERS" ADD FOREIGN KEY ("CUSTOMER_ID")
	  REFERENCES "C##MFORP"."CUSTOMERS" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table RENTALS
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."RENTALS" ADD FOREIGN KEY ("CUSTOMER_ID")
	  REFERENCES "C##MFORP"."CUSTOMERS" ("ID") ENABLE;
  ALTER TABLE "C##MFORP"."RENTALS" ADD FOREIGN KEY ("ITEM_ID")
	  REFERENCES "C##MFORP"."STOCK" ("ITEM_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SHOPPING_CART
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."SHOPPING_CART" ADD FOREIGN KEY ("CUSTOMER_ID")
	  REFERENCES "C##MFORP"."CUSTOMERS" ("ID") ENABLE;
  ALTER TABLE "C##MFORP"."SHOPPING_CART" ADD FOREIGN KEY ("ITEM_ID")
	  REFERENCES "C##MFORP"."STOCK" ("ITEM_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table STOCK
--------------------------------------------------------

  ALTER TABLE "C##MFORP"."STOCK" ADD FOREIGN KEY ("GAME_ID")
	  REFERENCES "C##MFORP"."GAMES" ("ID") ENABLE;
  ALTER TABLE "C##MFORP"."STOCK" ADD FOREIGN KEY ("STATUS_ID")
	  REFERENCES "C##MFORP"."STATUSES" ("STATUS_ID") ENABLE;
  ALTER TABLE "C##MFORP"."STOCK" ADD FOREIGN KEY ("CONDITION_ID")
	  REFERENCES "C##MFORP"."CONDITIONS" ("CONDITION_ID") ENABLE;

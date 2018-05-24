INSERT INTO OBJECT(OBJECT_ID, OBJECT_TYPE_ID, NAME) VALUES ('1', '1', 'Name Last Name Post');
INSERT INTO OBJECT(OBJECT_ID, OBJECT_TYPE_ID, NAME) VALUES ('2', '1', 'Name1 Last Name Post');
INSERT INTO OBJECT(OBJECT_ID, OBJECT_TYPE_ID, NAME) VALUES ('3', '1', 'Name Last Name1 Post1');
INSERT INTO OBJECT(OBJECT_ID, OBJECT_TYPE_ID, NAME) VALUES ('4', '1', 'Name1 Last Name1 Post1');

INSERT INTO OBJECT_TYPES(OBJECT_TYPE_ID, NAME) VALUES ('1', 'ASSIGNEE');
INSERT INTO OBJECT_TYPES(OBJECT_TYPE_ID, NAME) VALUES ('2', 'TASK');

INSERT INTO ATTRIBUTE_TYPES(ID_ATTRIBUTE_TYPE, NAME) VALUES ('1', 'NAME');
INSERT INTO ATTRIBUTE_TYPES(ID_ATTRIBUTE_TYPE, NAME) VALUES ('2', 'LAST NAME');
INSERT INTO ATTRIBUTE_TYPES(ID_ATTRIBUTE_TYPE, NAME) VALUES ('3', 'POST');
INSERT INTO ATTRIBUTE_TYPES(ID_ATTRIBUTE_TYPE, NAME) VALUES ('4', 'TASK NAME');
INSERT INTO ATTRIBUTE_TYPES(ID_ATTRIBUTE_TYPE, NAME) VALUES ('5', 'DESCRIPTION');
INSERT INTO ATTRIBUTE_TYPES(ID_ATTRIBUTE_TYPE, NAME) VALUES ('6', 'DEADLINE');
INSERT INTO ATTRIBUTE_TYPES(ID_ATTRIBUTE_TYPE, NAME) VALUES ('7', 'PRIORITY');
INSERT INTO ATTRIBUTE_TYPES(ID_ATTRIBUTE_TYPE, NAME) VALUES ('8', 'STATUS');

INSERT INTO ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_TYPE_ID, OBJECT_TYPE_ID, NAME) VALUES ('1', '1', '1', 'NAME');
INSERT INTO ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_TYPE_ID, OBJECT_TYPE_ID, NAME) VALUES ('2', '2', '1', 'LAST NAME');
INSERT INTO ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_TYPE_ID, OBJECT_TYPE_ID, NAME) VALUES ('3', '3', '1', 'POST');
INSERT INTO ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_TYPE_ID, OBJECT_TYPE_ID, NAME) VALUES ('4', '4', '2', 'TASK NAME');
INSERT INTO ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_TYPE_ID, OBJECT_TYPE_ID, NAME) VALUES ('5', '5', '2', 'DESCRIPTION');
INSERT INTO ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_TYPE_ID, OBJECT_TYPE_ID, NAME) VALUES ('6', '6', '2', 'DEADLINE');
INSERT INTO ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_TYPE_ID, OBJECT_TYPE_ID, NAME) VALUES ('7', '7', '2', 'PRIORITY');
INSERT INTO ATTRIBUTES(ATTRIBUTE_ID, ATTRIBUTE_TYPE_ID, OBJECT_TYPE_ID, NAME) VALUES ('8', '8', '2', 'STATUS');

INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('1', '1', 'NAME');
INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('1', '2', 'LAST NAME');
INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('1', '3', 'POST');
INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('2', '1', 'NAME1');
INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('2', '2', 'LAST NAME');
INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('2', '3', 'POST');
INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('3', '1', 'NAME');
INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('3', '2', 'LAST NAME1');
INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('3', '3', 'POST1');
INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('4', '1', 'NAME1');
INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('4', '2', 'LAST NAME1');
INSERT INTO PARAMS(OBJECT_ID, ATTRIBUTE_ID, VALUE) VALUES ('4', '3', 'POST1');


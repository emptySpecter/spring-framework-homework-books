CREATE TABLE authors(
	authorId SERIAL NOT NULL PRIMARY KEY,
	name varchar(50) NULL,
	surname varchar(70) NULL
);

CREATE TABLE books(
	bookId SERIAL NOT NULL PRIMARY KEY,
	name varchar(90) NULL,
	pagecount bigint NULL,
	points bigint NULL,
	authorId bigint NULL,
	typeId bigint NULL
);

CREATE TABLE types(
	typeId SERIAL NOT NULL PRIMARY KEY,
	name varchar(30) NULL
);
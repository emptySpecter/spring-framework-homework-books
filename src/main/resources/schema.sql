create TABLE authors(
	authorId BIGSERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	surname VARCHAR(70) NOT NULL
);

create TABLE types(
	typeId BIGSERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(30) NOT NULL
);

create TABLE books(
	bookId BIGSERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	pagecount BIGINT NOT NULL,
	points BIGINT NOT NULL,
	authorId BIGINT NOT NULL,
	typeId BIGINT NOT NULL,
	CONSTRAINT type_books FOREIGN KEY (authorId) references authors(authorId) ON delete RESTRICT ON update CASCADE,
	CONSTRAINT author_books FOREIGN KEY (typeId) references types(typeId) ON delete RESTRICT ON update CASCADE
);


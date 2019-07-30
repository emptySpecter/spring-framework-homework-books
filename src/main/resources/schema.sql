create TABLE authors(
	author_id BIGSERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	surname VARCHAR(70) NOT NULL
);

create TABLE types(
	type_id BIGSERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(30) NOT NULL
);

create TABLE books(
	book_id BIGSERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	page_count BIGINT NOT NULL,
	points BIGINT NOT NULL,
	author_id BIGINT NOT NULL,
	type_id BIGINT NOT NULL,
	CONSTRAINT type_books FOREIGN KEY (author_id) references authors(author_id) ON delete RESTRICT ON update CASCADE,
	CONSTRAINT author_books FOREIGN KEY (type_id) references types(type_id) ON delete RESTRICT ON update CASCADE
);


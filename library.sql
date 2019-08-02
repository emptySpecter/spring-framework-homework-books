drop database IF EXISTS library;
create DATABASE library;
USE library;

create TABLE authors(
	author_id SERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	surname VARCHAR(70) NOT NULL
);

create TABLE types(
	type_id SERIAL NOT NULL PRIMARY KEY ,
	name VARCHAR(30) NOT NULL
);

create TABLE books(
	book_id SERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	page_count BIGINT NOT NULL,
	points BIGINT NOT NULL,
	author_id BIGINT UNSIGNED NOT NULL,
	type_id BIGINT UNSIGNED NOT NULL,
	CONSTRAINT type_books FOREIGN KEY (author_id) references authors(author_id) ON delete RESTRICT ON update CASCADE,
	CONSTRAINT author_books FOREIGN KEY (type_id) references types(type_id) ON delete RESTRICT ON update CASCADE
);

create TABLE comments(
	comment_id SERIAL NOT NULL PRIMARY KEY ,
    book_id BIGINT UNSIGNED NOT NULL,
	text VARCHAR(256) NOT NULL,
	CONSTRAINT book_commentss FOREIGN KEY (book_id) references books(book_id) ON delete RESTRICT ON update CASCADE
);

insert into authors (author_id, name, surname) values (1, 'William Dean', 'Howells');
insert into authors (author_id, name, surname) values (2, 'Frederic', 'Brown');
insert into authors (author_id, name, surname) values (3, 'Jack', 'London');
insert into authors (author_id, name, surname) values (4, 'Albert', 'Blaisdell');
insert into authors (author_id, name, surname) values (5, 'Ellis', 'Butler');
insert into authors (author_id, name, surname) values (6, 'Arthur', 'Machen');
insert into authors (author_id, name, surname) values (7, 'Titus', 'Lucretius');
insert into authors (author_id, name, surname) values (8, 'Rabindranath', 'Tagore');
insert into authors (author_id, name, surname) values (9, 'Isaac', 'Asimov');
insert into authors (author_id, name, surname) values (10, 'Charles', 'Dickens');
insert into authors (author_id, name, surname) values (11, 'Ralph Waldo', 'Emerso');
insert into authors (author_id, name, surname) values (12, 'Dorothy', 'Canfield');
insert into authors (author_id, name, surname) values (13, 'Givoanni', 'Boccaccio');
insert into authors (author_id, name, surname) values (14, 'George', 'Orwell');
insert into authors (author_id, name, surname) values (15, 'Publius', 'Ovid');
insert into authors (author_id, name, surname) values (16, 'Robert Louis', 'Stevenson');
insert into authors (author_id, name, surname) values (17, 'Virginia', 'Woolf');
insert into authors (author_id, name, surname) values (18, 'George', 'Eliot');
insert into authors (author_id, name, surname) values (19, 'Amelia B.', 'Edwards');
insert into authors (author_id, name, surname) values (20, 'Fyodor', 'Dostoevsky');
insert into authors (author_id, name, surname) values (21, 'Emily', 'Dickinson');
insert into authors (author_id, name, surname) values (22, 'Edna', 'Ferber');
insert into authors (author_id, name, surname) values (23, 'Joseph Sheridan', 'LeFanu');
insert into authors (author_id, name, surname) values (24, 'John', 'DosPassos');
insert into authors (author_id, name, surname) values (25, 'Ruth', 'Stuart');
insert into authors (author_id, name, surname) values (26, 'Vladimir', 'Nabokov');
insert into authors (author_id, name, surname) values (27, 'Johanna', 'Spyri');
insert into authors (author_id, name, surname) values (28, 'Ernest', 'Dowson');
insert into authors (author_id, name, surname) values (29, 'Mary Hallock', 'Foote');
insert into authors (author_id, name, surname) values (30, 'Zane', 'Grey');
insert into authors (author_id, name, surname) values (31, 'H. P.', 'Lovecraft');
insert into authors (author_id, name, surname) values (32, 'Samuel', 'Pepys');
insert into authors (author_id, name, surname) values (33, 'Kate Dickinso', 'Sweetser');
insert into authors (author_id, name, surname) values (34, 'William', 'Lampto');
insert into authors (author_id, name, surname) values (35, 'Mother', 'Goose');
insert into authors (author_id, name, surname) values (36, 'Eleanor Hallowell', 'Abbott');


insert into types (type_id, name) values (1, 'Science fiction');
insert into types (type_id, name) values (2, 'Satire');
insert into types (type_id, name) values (3, 'Drama');
insert into types (type_id, name) values (4, 'Action and Adventure');
insert into types (type_id, name) values (5, 'Romance');
insert into types (type_id, name) values (6, 'Mystery');
insert into types (type_id, name) values (7, 'Horror');
insert into types (type_id, name) values (8, 'Health');
insert into types (type_id, name) values (9, 'Guide');
insert into types (type_id, name) values (10, 'Diaries');
insert into types (type_id, name) values (11, 'Comics');
insert into types (type_id, name) values (12, 'Diaries');
insert into types (type_id, name) values (13, 'Journals');
insert into types (type_id, name) values (14, 'Biographies');
insert into types (type_id, name) values (15, 'Fantasy');
insert into types (type_id, name) values (16, 'History');
insert into types (type_id, name) values (17, 'Science');
insert into types (type_id, name) values (18, 'Art');


insert into books (book_id, name, page_count, points, author_id, type_id) values (1, 'A Daughter of the Snows', 199, 84, 3, 9);
insert into books (book_id, name, page_count, points, author_id, type_id) values (2, 'The Near East: 10,000 Years of History', 298, 52, 9, 13);
insert into books (book_id, name, page_count, points, author_id, type_id) values (3, 'The Cocoon: A Rest-Cure Comedy', 90, 26, 25, 12);
insert into books (book_id, name, page_count, points, author_id, type_id) values (4, 'The Freakshow Murders', 321, 41, 2, 3);
insert into books (book_id, name, page_count, points, author_id, type_id) values (5, 'Pharaohs, Fellahs and Explorers', 367, 56, 19, 9);
insert into books (book_id, name, page_count, points, author_id, type_id) values (6, 'Hard Times', 293, 25, 10, 1);
insert into books (book_id, name, page_count, points, author_id, type_id) values (7, 'A Modern Instance', 222, 73, 1, 12);
insert into books (book_id, name, page_count, points, author_id, type_id) values (8, 'The Real Mother Goose', 88, 99, 35, 1);
insert into books (book_id, name, page_count, points, author_id, type_id) values (9, 'A Thousand Miles Up the Nile', 110, 90, 19, 18);
insert into books (book_id, name, page_count, points, author_id, type_id) values (10, 'Children of Blood and Bone', 137, 83, 7, 13);
insert into books (book_id, name, page_count, points, author_id, type_id) values (11, 'A pushcart at the curb', 95, 55, 24, 1);
insert into books (book_id, name, page_count, points, author_id, type_id) values (12, 'The Desert and the Sown', 126, 35, 29, 10);
insert into books (book_id, name, page_count, points, author_id, type_id) values (13, 'Three Soldiers', 223, 92, 24, 16);
insert into books (book_id, name, page_count, points, author_id, type_id) values (14, 'The End of Eternity', 168, 42, 9, 1);
insert into books (book_id, name, page_count, points, author_id, type_id) values (15, 'Annie Kilbur', 291, 51, 1, 12);
insert into books (book_id, name, page_count, points, author_id, type_id) values (16, 'A Touch of Sun and Other Stories', 141, 69, 29, 9);
insert into books (book_id, name, page_count, points, author_id, type_id) values (17, 'Show Boat', 151, 26, 22, 18);
insert into books (book_id, name, page_count, points, author_id, type_id) values (18, 'The Call of the Wild', 362, 49, 3, 18);
insert into books (book_id, name, page_count, points, author_id, type_id) values (19, 'My Mark Twain', 339, 64, 1, 9);
insert into books (book_id, name, page_count, points, author_id, type_id) values (20, 'Broken Ties', 134, 56, 8, 5);
insert into books (book_id, name, page_count, points, author_id, type_id) values (21, 'Short Stories From American History', 305, 89, 4, 8);
insert into books (book_id, name, page_count, points, author_id, type_id) values (22, 'Mrs Rosie and the Priest', 104, 30, 13, 14);
insert into books (book_id, name, page_count, points, author_id, type_id) values (23, 'So Big', 209, 57, 22, 3);
insert into books (book_id, name, page_count, points, author_id, type_id) values (24, 'Monsieur Maurice ', 92, 61, 19, 8);
insert into books (book_id, name, page_count, points, author_id, type_id) values (25, 'The Master of Ballantraen', 236, 66, 16, 13);
insert into books (book_id, name, page_count, points, author_id, type_id) values (26, 'The Unlived Life of Little Mary Ellen', 99, 45, 25, 6);
insert into books (book_id, name, page_count, points, author_id, type_id) values (27, 'Mouse - The Last Train', 184, 43, 2, 14);
insert into books (book_id, name, page_count, points, author_id, type_id) values (28, 'Edith Bonham', 122, 74, 29, 7);
insert into books (book_id, name, page_count, points, author_id, type_id) values (29, 'Maybe Mother Goose', 161, 98, 35, 14);
insert into books (book_id, name, page_count, points, author_id, type_id) values (30, 'The Noble Gases', 321, 33, 9, 5);
insert into books (book_id, name, page_count, points, author_id, type_id) values (31, 'Rainy Week', 98, 32, 36, 7);
insert into books (book_id, name, page_count, points, author_id, type_id) values (32, 'A Hazard of New Fortunes', 144, 92, 1, 4);
insert into books (book_id, name, page_count, points, author_id, type_id) values (33, 'A Plot for Murder', 108, 45, 2, 13);
insert into books (book_id, name, page_count, points, author_id, type_id) values (34, 'Nature', 357, 59, 11, 8);
insert into books (book_id, name, page_count, points, author_id, type_id) values (35, 'Hickory Dickory Dock', 326, 91, 35, 6);
insert into books (book_id, name, page_count, points, author_id, type_id) values (36, 'Big Fat He', 232, 28, 35, 12);
insert into books (book_id, name, page_count, points, author_id, type_id) values (37, 'The Lone Star Ranger', 328, 77, 30, 9);
insert into books (book_id, name, page_count, points, author_id, type_id) values (38, 'The Great Fire of London', 252, 69, 32, 9);
insert into books (book_id, name, page_count, points, author_id, type_id) values (39, 'White Nights', 265, 77, 20, 14);
insert into books (book_id, name, page_count, points, author_id, type_id) values (40, 'In a Glass Darkly', 173, 62, 23, 9);
insert into books (book_id, name, page_count, points, author_id, type_id) values (41, 'Fanny herself', 248, 81, 22, 10);
insert into books (book_id, name, page_count, points, author_id, type_id) values (42, 'Loaded', 273, 29, 2, 4);
insert into books (book_id, name, page_count, points, author_id, type_id) values (43, 'A Foregone Conclusion', 121, 35, 1, 15);
insert into books (book_id, name, page_count, points, author_id, type_id) values (44, 'The Hill of Dreams', 175, 82, 6, 6);
insert into books (book_id, name, page_count, points, author_id, type_id) values (45, 'Adventure', 217, 60, 3, 8);
insert into books (book_id, name, page_count, points, author_id, type_id) values (46, 'A Tagore Reader', 285, 40, 8, 4);
insert into books (book_id, name, page_count, points, author_id, type_id) values (47, 'A Tale of Two Cities', 331, 44, 10, 13);
insert into books (book_id, name, page_count, points, author_id, type_id) values (48, 'Dombey and Son', 344, 87, 10, 14);
insert into books (book_id, name, page_count, points, author_id, type_id) values (49, 'Famous Women', 273, 32, 13, 1);
insert into books (book_id, name, page_count, points, author_id, type_id) values (50, 'Rejection, The Ruling Spirit', 358, 86, 5, 2);
insert into books (book_id, name, page_count, points, author_id, type_id) values (51, 'Little Dorrit', 314, 43, 10, 13);
insert into books (book_id, name, page_count, points, author_id, type_id) values (52, 'The Four-Fifteen Express', 201, 85, 19, 2);
insert into books (book_id, name, page_count, points, author_id, type_id) values (53, 'Fairy Prince and Other Stories', 306, 90, 36, 15);
insert into books (book_id, name, page_count, points, author_id, type_id) values (54, 'Ten Tales from the Decameron', 183, 92, 13, 5);
insert into books (book_id, name, page_count, points, author_id, type_id) values (55, 'The Double', 372, 86, 20, 17);
insert into books (book_id, name, page_count, points, author_id, type_id) values (56, 'A Flight Of Swans', 115, 64, 8, 8);
insert into books (book_id, name, page_count, points, author_id, type_id) values (57, 'The Phantom Coach', 243, 28, 19, 17);
insert into books (book_id, name, page_count, points, author_id, type_id) values (58, 'The Game', 209, 52, 3, 10);
insert into books (book_id, name, page_count, points, author_id, type_id) values (59, 'Rico and Wiselin', 309, 53, 27, 8);
insert into books (book_id, name, page_count, points, author_id, type_id) values (60, 'The Shorter Pepys', 276, 64, 32, 5);
insert into books (book_id, name, page_count, points, author_id, type_id) values (61, 'My Life Had Stood a Loaded Gun ', 278, 82, 21, 12);
insert into books (book_id, name, page_count, points, author_id, type_id) values (62, 'A Tagore Testament', 268, 56, 8, 15);
insert into books (book_id, name, page_count, points, author_id, type_id) values (63, 'The Cruise of the Dazzler', 140, 25, 3, 10);
insert into books (book_id, name, page_count, points, author_id, type_id) values (64, 'To the Lighthouse', 90, 87, 17, 2);
insert into books (book_id, name, page_count, points, author_id, type_id) values (65, 'The Terror', 293, 101, 6, 18);
insert into books (book_id, name, page_count, points, author_id, type_id) values (66, 'The Sick-a-Bed Lady', 369, 79, 36, 9);
insert into books (book_id, name, page_count, points, author_id, type_id) values (67, 'Christmas Every Day', 98, 59, 1, 13);
insert into books (book_id, name, page_count, points, author_id, type_id) values (68, 'The home-maker', 316, 25, 12, 14);
insert into books (book_id, name, page_count, points, author_id, type_id) values (69, 'The Ground-Swell', 193, 55, 29, 10);
insert into books (book_id, name, page_count, points, author_id, type_id) values (70, 'A Sleep and a Forgetting', 360, 44, 1, 2);
insert into books (book_id, name, page_count, points, author_id, type_id) values (71, 'Nicholas Nickleby', 219, 97, 10, 1);
insert into books (book_id, name, page_count, points, author_id, type_id) values (72, 'The Whole Family: A Novel by Twelve Authors', 149, 65, 1, 6);
insert into books (book_id, name, page_count, points, author_id, type_id) values (73, 'Middlemarch', 288, 69, 18, 11);
insert into books (book_id, name, page_count, points, author_id, type_id) values (74, 'Life of Dante', 156, 44, 13, 14);
insert into books (book_id, name, page_count, points, author_id, type_id) values (75, 'Short Stories From English History', 189, 69, 4, 2);
insert into books (book_id, name, page_count, points, author_id, type_id) values (76, 'Little Eve Edgarton', 306, 41, 36, 5);
insert into books (book_id, name, page_count, points, author_id, type_id) values (77, 'The Kingdom of the Sun', 189, 68, 9, 9);
insert into books (book_id, name, page_count, points, author_id, type_id) values (78, 'Indian Summer', 107, 21, 1, 14);
insert into books (book_id, name, page_count, points, author_id, type_id) values (79, 'Mrs Dalloway', 321, 68, 17, 5);
insert into books (book_id, name, page_count, points, author_id, type_id) values (80, 'Demons', 232, 88, 20, 15);
insert into books (book_id, name, page_count, points, author_id, type_id) values (81, 'The Last Trail', 116, 64, 30, 14);
insert into books (book_id, name, page_count, points, author_id, type_id) values (82, 'The Fredric Brown Megapack', 324, 89, 2, 16);
insert into books (book_id, name, page_count, points, author_id, type_id) values (83, 'The Decameron: Selected Tales', 337, 34, 13, 17);
insert into books (book_id, name, page_count, points, author_id, type_id) values (84, 'Th bent twig', 367, 26, 12, 3);
insert into books (book_id, name, page_count, points, author_id, type_id) values (85, 'Things near and far', 199, 67, 6, 6);
insert into books (book_id, name, page_count, points, author_id, type_id) values (86, 'The Story of Salomen', 316, 44, 19, 11);
insert into books (book_id, name, page_count, points, author_id, type_id) values (87, 'The Destruction of Our Children', 104, 73, 5, 9);
insert into books (book_id, name, page_count, points, author_id, type_id) values (88, 'Collected Stories', 295, 100, 8, 4);
insert into books (book_id, name, page_count, points, author_id, type_id) values (89, 'The House of the Dead', 209, 53, 20, 2);
insert into books (book_id, name, page_count, points, author_id, type_id) values (90, 'Sonny: A Christmas Guest', 186, 30, 25, 6);
insert into books (book_id, name, page_count, points, author_id, type_id) values (91, 'Amores', 297, 93, 15, 10);
insert into books (book_id, name, page_count, points, author_id, type_id) values (92, 'Chaturanga', 218, 34, 8, 10);
insert into books (book_id, name, page_count, points, author_id, type_id) values (93, 'Little Eve Edgarton', 247, 32, 36, 14);
insert into books (book_id, name, page_count, points, author_id, type_id) values (94, 'Chitra', 122, 52, 8, 9);
insert into books (book_id, name, page_count, points, author_id, type_id) values (95, 'The Iron Heel', 81, 29, 3, 15);
insert into books (book_id, name, page_count, points, author_id, type_id) values (96, 'Self Reliance', 137, 64, 11, 18);
insert into books (book_id, name, page_count, points, author_id, type_id) values (97, 'The Great God Pan And The Hill Of Dreams', 158, 87, 6, 5);
insert into books (book_id, name, page_count, points, author_id, type_id) values (98, 'The White People and Other Weird Stories', 100, 93, 6, 15);
insert into books (book_id, name, page_count, points, author_id, type_id) values (99, 'The Diary of Samuel Pepys: A Selection', 271, 55, 32, 3);
insert into books (book_id, name, page_count, points, author_id, type_id) values (100, 'The Rise of Silas Lapham', 314, 36, 1, 2);
insert into books (book_id, name, page_count, points, author_id, type_id) values (101, 'The Mystery of Edwin Drood', 340, 59, 10, 12);
insert into books (book_id, name, page_count, points, author_id, type_id) values (102, 'In Exile and Other Stories', 140, 97, 29, 11);
insert into books (book_id, name, page_count, points, author_id, type_id) values (103, 'Bleak House', 130, 84, 10, 18);
insert into books (book_id, name, page_count, points, author_id, type_id) values (104, 'U.S.A Trilogy', 118, 57, 24, 1);
insert into books (book_id, name, page_count, points, author_id, type_id) values (105, 'A Comedy of Masks', 237, 87, 28, 13);
insert into books (book_id, name, page_count, points, author_id, type_id) values (106, 'Barnaby Rudge', 380, 31, 10, 12);
insert into books (book_id, name, page_count, points, author_id, type_id) values (107, 'Heroic Deeds of American Sailors', 243, 21, 4, 1);
insert into books (book_id, name, page_count, points, author_id, type_id) values (108, 'A Bouquet', 309, 89, 28, 18);
insert into books (book_id, name, page_count, points, author_id, type_id) values (109, 'Editha', 155, 80, 1, 18);
insert into books (book_id, name, page_count, points, author_id, type_id) values (110, 'Manhattan Transfer', 124, 21, 24, 3);
insert into books (book_id, name, page_count, points, author_id, type_id) values (111, 'Fasti', 325, 68, 15, 7);
insert into books (book_id, name, page_count, points, author_id, type_id) values (112, 'Hearts of Three', 272, 28, 3, 2);
insert into books (book_id, name, page_count, points, author_id, type_id) values (113, 'Speak, Memory', 154, 69, 26, 15);
insert into books (book_id, name, page_count, points, author_id, type_id) values (114, 'Kidnapped', 334, 60, 16, 3);
insert into books (book_id, name, page_count, points, author_id, type_id) values (115, 'The Brothers Karamazov', 161, 74, 20, 6);
insert into books (book_id, name, page_count, points, author_id, type_id) values (116, 'Envelope Poems', 342, 75, 21, 15);
insert into books (book_id, name, page_count, points, author_id, type_id) values (117, 'Selected Poems', 203, 72, 21, 9);
insert into books (book_id, name, page_count, points, author_id, type_id) values (118, 'Gobolinks or Shadow Pictures for Young and Old', 332, 61, 25, 5);
insert into books (book_id, name, page_count, points, author_id, type_id) values (119, 'The Decamero', 318, 94, 13, 12);
insert into books (book_id, name, page_count, points, author_id, type_id) values (120, 'The Cock and Anchor', 133, 61, 23, 1);
insert into books (book_id, name, page_count, points, author_id, type_id) values (121, 'Adrian Rome', 259, 89, 28, 13);
insert into books (book_id, name, page_count, points, author_id, type_id) values (122, 'Words in Genesis', 217, 101, 9, 17);
insert into books (book_id, name, page_count, points, author_id, type_id) values (123, 'Stories of Ohio', 191, 78, 1, 5);
insert into books (book_id, name, page_count, points, author_id, type_id) values (124, 'Treasure Island', 308, 75, 16, 4);
insert into books (book_id, name, page_count, points, author_id, type_id) values (125, 'Uncle Silas', 118, 57, 23, 1);
insert into books (book_id, name, page_count, points, author_id, type_id) values (126, 'The Idiot', 248, 97, 20, 7);
insert into books (book_id, name, page_count, points, author_id, type_id) values (127, 'Inside The Atom', 176, 52, 9, 4);
insert into books (book_id, name, page_count, points, author_id, type_id) values (128, 'The Story of Babette: A Little Creole Girl', 313, 76, 25, 5);
insert into books (book_id, name, page_count, points, author_id, type_id) values (129, 'The Eternal Husband', 225, 79, 20, 15);
insert into books (book_id, name, page_count, points, author_id, type_id) values (130, 'Heidi', 146, 67, 27, 17);
insert into books (book_id, name, page_count, points, author_id, type_id) values (131, 'The Concise Pepys', 303, 88, 32, 13);
insert into books (book_id, name, page_count, points, author_id, type_id) values (132, 'Tales From Shakespeare', 121, 62, 4, 12);
insert into books (book_id, name, page_count, points, author_id, type_id) values (133, 'Molly Make-Believe', 286, 53, 36, 10);
insert into books (book_id, name, page_count, points, author_id, type_id) values (134, 'Peace on Earth, Good Will to Dogs', 321, 84, 36, 9);
insert into books (book_id, name, page_count, points, author_id, type_id) values (135, 'Miss Darkness', 360, 63, 2, 8);
insert into books (book_id, name, page_count, points, author_id, type_id) values (136, 'Poor Folk', 202, 101, 20, 7);
insert into books (book_id, name, page_count, points, author_id, type_id) values (137, 'Their Wedding Journey', 132, 78, 1, 18);
insert into books (book_id, name, page_count, points, author_id, type_id) values (138, 'The Elegy of Lady Fiammetta', 157, 79, 13, 4);
insert into books (book_id, name, page_count, points, author_id, type_id) values (139, 'Art Of Tagore', 119, 72, 8, 16);
insert into books (book_id, name, page_count, points, author_id, type_id) values (140, 'Chemistry and Human Health', 244, 55, 9, 13);
insert into books (book_id, name, page_count, points, author_id, type_id) values (141, 'Realm of Numbers', 279, 57, 9, 11);
insert into books (book_id, name, page_count, points, author_id, type_id) values (142, 'The Kempton-Wace Letters', 240, 60, 3, 3);
insert into books (book_id, name, page_count, points, author_id, type_id) values (143, 'Crime and Punishment', 144, 30, 20, 17);
insert into books (book_id, name, page_count, points, author_id, type_id) values (144, 'Fairy Prince and Other Wonderful Stories', 219, 26, 36, 1);
insert into books (book_id, name, page_count, points, author_id, type_id) values (145, 'Adam Bede', 303, 63, 18, 4);
insert into books (book_id, name, page_count, points, author_id, type_id) values (146, 'Madam Crowl''s ghost', 344, 34, 23, 13);
insert into books (book_id, name, page_count, points, author_id, type_id) values (147, 'Is Anyone There?', 178, 42, 9, 14);
insert into books (book_id, name, page_count, points, author_id, type_id) values (148, 'An Imperative Duty', 100, 41, 1, 6);
insert into books (book_id, name, page_count, points, author_id, type_id) values (149, 'Adding a Dimension', 334, 37, 9, 11);
insert into books (book_id, name, page_count, points, author_id, type_id) values (150, 'The Caves of Steel', 98, 21, 9, 18);
insert into books (book_id, name, page_count, points, author_id, type_id) values (151, 'Carmilla', 91, 23, 23, 18);
insert into books (book_id, name, page_count, points, author_id, type_id) values (152, '1919', 105, 60, 24, 17);
insert into books (book_id, name, page_count, points, author_id, type_id) values (153, 'Pale Fire', 250, 80, 26, 13);
insert into books (book_id, name, page_count, points, author_id, type_id) values (154, 'Martin Chuzzlewit', 315, 52, 10, 3);
insert into books (book_id, name, page_count, points, author_id, type_id) values (155, 'Understood Betsy', 130, 86, 12, 7);
insert into books (book_id, name, page_count, points, author_id, type_id) values (156, 'The White Linen Nurse', 369, 35, 36, 13);
insert into books (book_id, name, page_count, points, author_id, type_id) values (157, 'A Writer''s Diary', 164, 87, 20, 1);
insert into books (book_id, name, page_count, points, author_id, type_id) values (158, 'Pni', 233, 68, 26, 14);
insert into books (book_id, name, page_count, points, author_id, type_id) values (159, 'Honeymoon in Hell', 202, 69, 2, 16);
insert into books (book_id, name, page_count, points, author_id, type_id) values (160, 'The Black Arrow', 366, 87, 16, 12);
insert into books (book_id, name, page_count, points, author_id, type_id) values (161, 'David Copperfield', 240, 29, 10, 16);
insert into books (book_id, name, page_count, points, author_id, type_id) values (162, 'Famous Ghost Stories', 152, 69, 19, 3);
insert into books (book_id, name, page_count, points, author_id, type_id) values (163, 'Book Of Indian Braves', 117, 53, 33, 8);
insert into books (book_id, name, page_count, points, author_id, type_id) values (164, 'Binodini', 306, 89, 8, 8);
insert into books (book_id, name, page_count, points, author_id, type_id) values (165, 'Holly And Pizen, And Other Stories', 220, 77, 25, 17);
insert into books (book_id, name, page_count, points, author_id, type_id) values (166, 'Lolita', 153, 89, 26, 2);
insert into books (book_id, name, page_count, points, author_id, type_id) values (167, 'The Best Short Stories', 115, 27, 20, 2);
insert into books (book_id, name, page_count, points, author_id, type_id) values (168, 'Cimarro', 101, 33, 22, 17);

insert into comments (comment_id, book_id, text) values (1, 1, 'predictable');
insert into comments (comment_id, book_id, text) values (2, 14, 'Das ist fantastisch');
insert into comments (comment_id, book_id, text) values (3, 1, 'Das ist fantastisch');


alter table `authors` ADD COLUMN _class VARCHAR(40) DEFAULT 'ru.otus.spring.domain.Author';
alter table `books` ADD COLUMN _class VARCHAR(40) DEFAULT 'ru.otus.spring.domain.Book';
alter table `types` ADD COLUMN _class VARCHAR(40) DEFAULT 'ru.otus.spring.domain.Genre';
alter table `Comments` ADD COLUMN _class VARCHAR(40) DEFAULT 'ru.otus.spring.domain.Comment';

select JSON_OBJECT('_class', `_class`, 'id', `author_id`, 'name', `name`, 'surname', `surname`)
	INTO OUTFILE 'c:/tmp/authors.json' FROM `authors`;
select JSON_OBJECT('_class', `_class`, 'id', `book_id`, 'name', `name`, 'pagecount',
	`page_count`, 'points', `points`, 'author_id', `author_id`, 'genreid', `type_id`) 
	INTO OUTFILE 'c:/tmp/books.json' FROM `books`;
select JSON_OBJECT('_class', `_class`, 'id', `type_id`, 'name', `name`)
	INTO OUTFILE 'c:/tmp/genres.json' FROM `types`;
select JSON_OBJECT('_class', `_class`, 'id', `comment_id`, 'bookId', `book_id`, 'text', `text`)
	INTO OUTFILE 'c:/tmp/comments.json' FROM `comments`;

select JSON_OBJECT('_class', `books`.`_class`, 'id', `book_id`, 'name', `books`.`name`, 'pagecount', `page_count`, 'points', `points`,
	'author', JSON_OBJECT('id', `authors`.`author_id`, 'name', `authors`.`name`, 'surname', `authors`.`surname`), 
    'genre', JSON_OBJECT('id', `types`.`type_id`, 'name', `types`.`name`)) 
	INTO OUTFILE 'c:/tmp/books_with_author_and_genre.json' 
FROM `books` 
INNER JOIN `authors` ON `authors`.`author_id` = `books`.`author_id`
INNER JOIN `types` ON `types`.`type_id` = `books`.`type_id`
ORDER BY `books`.`book_id`;

select JSON_OBJECT('_class', `books`.`_class`, 'id', `book_id`, 'name', `books`.`name`, 'pagecount', `page_count`, 'points', `points`,
	'author', JSON_OBJECT('id', `authors`.`author_id`, 'name', `authors`.`name`, 'surname', `authors`.`surname`), 
    'genre', JSON_OBJECT('id', `types`.`type_id`, 'name', `types`.`name`),
    'comments', (select cast(
						concat('[', GROUP_CONCAT(JSON_OBJECT('id', `comments`.`comment_id`, 'text', `comments`.`text`)), ']')
                    AS JSON) 
				FROM `comments` where `comments`.`book_id` = `books`.`book_id`))
	INTO OUTFILE 'c:/tmp/books_with_author_and_genre_and_comments.json' 
FROM `books` 
INNER JOIN `authors` ON `authors`.`author_id` = `books`.`author_id`
INNER JOIN `types` ON `types`.`type_id` = `books`.`type_id`
ORDER BY `books`.`book_id`;

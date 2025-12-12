INSERT INTO library_info (library_name, library_location, max_capacity) VALUES ('Tiny Town Public Library', 'Tiny Town', 200);
INSERT INTO library_info (library_name, library_location, max_capacity) VALUES ('Village Community College Library', 'Small Village', 50);
INSERT INTO library_info (library_name, library_location, max_capacity) VALUES ('The Neighborhood Bookshelf', 'Sesame Street', 25);
INSERT INTO library_info (library_name, library_location, max_capacity) VALUES ('Wan Shi Tong''s Spirit Library', 'Si Wong Desert', 9999999);

INSERT INTO author (first_name, middle_name, last_name) VALUES ('Theodor','Seuss','Geisel');
INSERT INTO author (first_name, middle_name, last_name) VALUES ('John','Ronald Reuel','Tolkien');
INSERT INTO author (first_name, middle_name, last_name) VALUES ('Eric','Arthur','Blair');
INSERT INTO author (first_name, last_name) VALUES ('James','Madison');



INSERT INTO title (title, genre, year_published) VALUES ('The Lord of the Rings','Fantasy Fiction','1954');
INSERT INTO title (title, genre, year_published) VALUES ('The Hobbit','Fantasy Fiction','1937');
INSERT INTO title (title, genre, year_published) VALUES ('The Cat in the Hat','Children''s Literature','1957');
INSERT INTO title (title, genre, year_published) VALUES ('1984','Distopian Fiction','1949');
INSERT INTO title (title, genre, year_published) VALUES ('The U.S. Constitution','Legal Document','1787');

INSERT INTO title_author (title_id, author_id) VALUES (1,2);
INSERT INTO title_author (title_id, author_id) VALUES (2,2);
INSERT INTO title_author (title_id, author_id) VALUES (3,1);
INSERT INTO title_author (title_id, author_id) VALUES (4,3);
INSERT INTO title_author (title_id, author_id) VALUES (5,4);

INSERT INTO book (book_id, title_id, library_id, book_status) VALUES ('Fan-Tol-1954-1',1,1,'available');
INSERT INTO book (book_id, title_id, library_id, book_status) VALUES ('Fan-Tol-1954-2',1,1,'available');
INSERT INTO book (book_id, title_id, library_id, book_status) VALUES ('Fan-Tol-1954-3',1,1,'checkedOut');
INSERT INTO book (book_id, title_id, library_id, book_status) VALUES ('Fan-Tol-1954-4',1,1,'lost');
INSERT INTO book (book_id, title_id, library_id, book_status) VALUES ('Fan-Tol-1954-5',1,2,'overDue');
INSERT INTO book (book_id, title_id, library_id, book_status) VALUES ('Fan-Tol-1954-6',1,2,'available');
INSERT INTO book (book_id, title_id, library_id, book_status) VALUES ('Fan-Tol-1954-7',1,3,'available');
INSERT INTO book (book_id, title_id, library_id, book_status) VALUES ('Fan-Tol-1937-1',2,1,'available');
INSERT INTO book (book_id, title_id, library_id, book_status) VALUES ('Fan-Tol-1937-2',2,1,'available');
INSERT INTO book (book_id, title_id, library_id, book_status) VALUES ('Fan-Tol-1937-3',2,2,'checkedOut');
INSERT INTO book (book_id, title_id, library_id, book_status) VALUES ('Fan-Tol-1937-4',2,3,'available');
INSERT INTO book (book_id, title_id, library_id, book_status) VALUES ('Chi-Gei-1957-1',3,1,'available');
INSERT INTO book (book_id, title_id, library_id, book_status) VALUES ('Chi-Gei-1957-2',3,2,'checkedOut');
INSERT INTO book (book_id, title_id, library_id, book_status) VALUES ('Chi-Gei-1957-3',3,4,'lost');







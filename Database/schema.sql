--Lookup table
CREATE TABLE biblioteca(
	library_id SERIAL PRIMARY KEY,
	library_name VARCHAR(255) NOT NULL,
	library_location VARCHAR(255) NOT NULL,
	max_capacity INT NOT NULL,
	current_capacity INT NOT NULL
);

--Lookup table
CREATE TABLE book(
	book_id SERIAL PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	author VARCHAR(255) NOT NULL,
	genre VARCHAR(255) NOT NULL
);

--Join table
CREATE TABLE library_book(
	library_id INT NOT NULL REFERENCES biblioteca(library_id)
	book_id INT NOT NULL REFERENCES book(book_id)
	quantity INT NOT NULL
	PRIMARY KEY(library_id, book_id)
);

--global serial number, unique identifier for every single physical book
--status = available, checked out, lost, overdue
CREATE TABLE serialNum(
	serialNum INT SERIAL PRIMARY KEY,
	book_id INT NOT NULL REFERENCES book(book_id),
	library_id INT NOT NULL REFERENCES biblioteca(library_id),
	status VARCHAR(255) NOT NULL	
);






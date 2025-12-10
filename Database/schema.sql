-- Lookup table --
CREATE TABLE biblioteca(
	library_id SERIAL PRIMARY KEY,
	library_name VARCHAR(255) NOT NULL,
	library_location VARCHAR(255) NOT NULL,
	max_capacity INT NOT NULL
	
	
	-- This is calculated based on other tables:
	-- current_capacity INT NOT NULL
);

-- Lookup table --
CREATE TABLE book(
	book_id SERIAL PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	author VARCHAR(255) NOT NULL,
	genre VARCHAR(255) NOT NULL
);

-- Join table --
CREATE TABLE serial_number(
	-- global serial number, unique identifier for every single physical book
	serial_number INT SERIAL PRIMARY KEY,
	book_id INT NOT NULL REFERENCES book(book_id),
	library_id INT NOT NULL REFERENCES biblioteca(library_id),
	-- book_status can be: {available, checked out, lost, overdue}
	book_status VARCHAR(255) NOT NULL	
);






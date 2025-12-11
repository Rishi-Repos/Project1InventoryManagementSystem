DROP TABLE IF EXISTS book, title_author, author, title, library_info;

-- Lookup Table --
CREATE TABLE library_info(
	-- library is a keyword in SQL

	library_id SERIAL PRIMARY KEY,
	library_name VARCHAR(255) NOT NULL,
	library_location VARCHAR(255) NOT NULL,
	max_capacity INT NOT NULL
	
	
	-- This is calculated based on other tables:
	-- current_capacity INT NOT NULL
);

-- Lookup Table --
CREATE TABLE title(

	title_id SERIAL PRIMARY KEY,
	title VARCHAR(255) NOT NULL,

	genre VARCHAR(255) NOT NULL,
	-- subject is a keyword in SQL

	year_published INT NOT NULL
);

-- Lookup Table --
CREATE TABLE author(
	author_id SERIAL PRIMARY KEY,
	first_name VARCHAR(255),
	middle_name VARCHAR(255),
	last_name VARCHAR(255) NOT NULL
);

-- Join Table --
CREATE TABLE title_author(
	title_id INT NOT NULL REFERENCES title(title_id),
	author_id INT NOT NULL REFERENCES author(author_id),
	PRIMARY KEY (title_id, author_id)
);

-- Join Table --
CREATE TABLE book(

	book_id VARCHAR(255) PRIMARY KEY,
	-- genre + author_last + year_published + serial# = unique identifier for every single physical book

	title_id INT NOT NULL REFERENCES title(title_id),
	library_id INT NOT NULL REFERENCES library_info(library_id),

	book_status VARCHAR(255) NOT NULL
	-- book_status can be: {available, checkedOut, lost, overDue}
	-- status is a keyword in SQL
);






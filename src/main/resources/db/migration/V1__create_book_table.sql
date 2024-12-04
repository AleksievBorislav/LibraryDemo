CREATE SEQUENCE book_id_sequence INCREMENT 2 START 1111;

CREATE TABLE IF NOT EXISTS Book
(
    book_id bigint NOT NULL DEFAULT nextval('book_id_sequence'),
    unique_book_number bigint NOT NULL UNIQUE,
    title character varying(60) NOT NULL,
    page_count integer NOT NULL,
    readers_count integer DEFAULT 0,
    author character varying(60) NOT NULL,
    price numeric NOT NULL,
    CONSTRAINT book_pkey PRIMARY KEY (book_id)
)
CREATE SEQUENCE reader_id_sequence INCREMENT 2 START 1111;

CREATE TABLE IF NOT EXISTS Reader
(
    reader_id bigint NOT NULL DEFAULT nextval('reader_id_sequence'),
    name character varying(60) NOT NULL,
    age smallint NOT NULL,
    book_count integer DEFAULT 0,
    egn bigint NOT NULL UNIQUE,
    CONSTRAINT reader_pkey PRIMARY KEY (reader_id)
);


CREATE TABLE IF NOT EXISTS Borrowings
(
    egn bigint NOT NULL,
    unique_book_number bigint NOT NULL,
    is_returned bool NOT NULL DEFAULT false,
    borrowed_time timestamp without time zone NOT NULL,
    returned_time timestamp without time zone DEFAULT NOW(),
    CONSTRAINT borrowings_pkey PRIMARY KEY (egn, unique_book_number, borrowed_time),
    CONSTRAINT borrowings_unique_book_number_fkey FOREIGN KEY (unique_book_number)
        REFERENCES Book (unique_book_number),
    CONSTRAINT borrowings_EGN_fkey FOREIGN KEY (egn)
        REFERENCES Reader (egn)
);

CREATE INDEX idx_borrowings_EGN ON borrowings(egn);
CREATE INDEX idx_borrowings_unique_book_number ON borrowings(unique_book_number);
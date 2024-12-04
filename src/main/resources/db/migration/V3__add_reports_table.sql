CREATE SEQUENCE report_id_sequence INCREMENT 2 START 1111;

CREATE TABLE IF NOT EXISTS Report
(
    report_id bigint NOT NULL DEFAULT nextval('report_id_sequence'),
    overdues_count int NOT NULL,
    report_time timestamp UNIQUE default NOW(),
    CONSTRAINT report_pkey PRIMARY KEY (report_id)
);

CREATE SEQUENCE overdue_borrowing_id INCREMENT 2 START 1111;

CREATE TABLE IF NOT EXISTS Overdue_borrowing
(
    overdue_borrowing_id bigint NOT NULL DEFAULT nextval('overdue_borrowing_id'),
    report_id bigint NOT NULL,
    egn bigint NOT NULL,
    unique_book_number bigint NOT NULL,
    borrowed_time timestamp without time zone NOT NULL,
    CONSTRAINT overdue_borrowing_pkey PRIMARY KEY (overdue_borrowing_id),
    CONSTRAINT report_id_fkey FOREIGN KEY (report_id)
        REFERENCES Report (report_id)
)
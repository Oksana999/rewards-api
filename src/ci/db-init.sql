CREATE SEQUENCE IF NOT EXISTS global_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE global_seq OWNER TO oksana;

CREATE TABLE IF NOT EXISTS users
(
    user_id bigint NOT NULL,
    name varchar(255),
    email varchar(255) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
);
ALTER TABLE users OWNER to oksana;

CREATE TABLE payments
(
    payment_id bigint NOT NULL,
    amount numeric(19,2),
    created_at bigint,
    user_id bigint,
    CONSTRAINT payments_pkey PRIMARY KEY (payment_id),
    CONSTRAINT user_ref FOREIGN KEY (user_id)
        REFERENCES users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE payments OWNER to oksana;

-- Add users:

INSERT INTO users (user_id, name, email) VALUES
(nextval('global_seq'), 'Pablo Perez', 'p_perez@mail.com'),
(nextval('global_seq'), 'Alex Miller', 'a_miller@mail.com'),
(nextval('global_seq'), 'Yan Xi', 'yan_xi@mail.com'),
(nextval('global_seq'), 'Jessica Robertson', 'j_robertson@mail.com'),
(nextval('global_seq'), 'Yana Lubova', 'y_lubova@mail.com'),
(nextval('global_seq'), 'Ban Nguyen', 'b_nguyen@mail.com'),
(nextval('global_seq'), 'Sara Esposito', 's_esposito@mail.com');

-- Add 100 random payments:

CREATE OR REPLACE TEMP VIEW user_ids_arr AS
SELECT array_agg(user_id) ids FROM users;

CREATE OR REPLACE TEMP VIEW user_payments AS
SELECT (random()*200000)::int/100.0 AS amount,
((extract(epoch from current_timestamp) - random() * 60 * 60 * 24 * 365)*1000)::bigint AS created_at
FROM generate_series (1,100);

INSERT INTO payments (payment_id, amount, created_at, user_id)
SELECT  nextval('global_seq') AS payment_id,
        user_payments.amount AS amount,
        user_payments.created_at AS created_at,
        ids[1 + floor((random() * array_length(ids, 1)))::int] AS user_id
FROM user_ids_arr JOIN user_payments ON true;
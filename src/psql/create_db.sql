CREATE TABLE public.login
(
    id bigserial NOT NULL,
    email text NOT NULL,
    password character(128) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)

);

CREATE TABLE public.account
(
    id bigserial NOT NULL,
    firstname text,
    lastname text,
    description character(256),
    bank_account character(26),
    creation_date date NOT NULL,
    phone character(9),
    specialization text,
    grade real,
    cook_status boolean NOT NULL,
    id_login bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_login) REFERENCES public.login (id)
);

CREATE TABLE public.account_grades
(
    id bigserial NOT NULL,
    id_account bigint NOT NULL,
    grade integer,
    comment character(256),
    id_owner bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_account) REFERENCES public.account (id),
    FOREIGN KEY (id_owner) REFERENCES public.account (id)
);

CREATE TABLE public.dish
(
    id bigserial NOT NULL,
    id_account bigint NOT NULL,
	name text NOT NULL,
	category text NOT NULL,
	description character(256),
    grade real NOT NULL,
    PRIMARY KEY (id),
	FOREIGN KEY (id_account) REFERENCES public.account (id)
);

CREATE TABLE public.offers
(
    id bigserial NOT NULL,
    id_account bigint NOT NULL,
    id_dish bigint NOT NULL,
	hours text NOT NULL,
	day text NOT NULL,
	localisation text NOT NULL,
    status boolean NOT NULL,
    periodic boolean NOT NULL,
    PRIMARY KEY (id),
	FOREIGN KEY (id_account) REFERENCES public.account (id),
    FOREIGN KEY (id_dish) REFERENCES public.dish (id)

);

CREATE TABLE public.dish_grades
(
    id bigserial NOT NULL,
    id_dish bigint NOT NULL,
    grade integer,
    comment character(256),
    id_owner bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_dish) REFERENCES public.dish (id),
    FOREIGN KEY (id_owner) REFERENCES public.account (id)
);
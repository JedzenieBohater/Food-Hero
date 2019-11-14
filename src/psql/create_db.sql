CREATE TABLE public.login
(
    id bigserial NOT NULL,
    email text NOT NULL,
    password character(128) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)

);

CREATE TABLE public.konto
(
    id bigserial NOT NULL,
    "imię" text,
    nazwisko text,
    opis character(256),
    nr_konta_bankowego character(26),
    data_utworzenia date NOT NULL,
    telefon character(9),
    specjalizacja text,
    ocena integer,
    status_kucharza boolean NOT NULL,
    id_login bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_login) REFERENCES public.login (id)
);

CREATE TABLE public.oceny_kont
(
    id bigserial NOT NULL,
    id_konta bigint NOT NULL,
    ocena integer,
    komentarz character(256),
    "id_wystawiającego" bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_konta) REFERENCES public.konto (id),
    FOREIGN KEY ("id_wystawiającego") REFERENCES public.konto (id)
);

CREATE TABLE public.dania_stale
(
    id bigserial NOT NULL,
    id_konta bigint NOT NULL,
	nazwa text NOT NULL,
	kategoria text NOT NULL,
	typ text NOT NULL,
	godziny text NOT NULL,
	dni text NOT NULL,
	lokalizacja text NOT NULL,
	opis character(256),
	status boolean NOT NULL,
    ocena integer NOT NULL,
    PRIMARY KEY (id),
	FOREIGN KEY (id_konta) REFERENCES public.konto (id)
);

CREATE TABLE public.dania_jednorazowe
(
    id bigserial NOT NULL,
    id_konta bigint NOT NULL,
	nazwa text NOT NULL,
	kategoria text,
	typ text,
	godzina text NOT NULL,
	dzien text NOT NULL,
	lokalizacja text NOT NULL,
	opis character(256),
	zamowienie0ogloszenie1 boolean,
    PRIMARY KEY (id),
	FOREIGN KEY (id_konta) REFERENCES public.konto (id)
);

CREATE TABLE public.oceny_dan_stalych
(
    id bigserial NOT NULL,
    id_dania bigint NOT NULL,
    ocena integer,
    komentarz character(256),
    "id_wystawiającego" bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_dania) REFERENCES public.dania_stale (id),
    FOREIGN KEY ("id_wystawiającego") REFERENCES public.konto (id)
);
CREATE TABLE public.login
(
    id bigserial NOT NULL,
    email text NOT NULL,
    password varchar(128) NOT NULL,
    is_admin boolean NOT NULL,
    is_active boolean NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE public.account
(
    id bigserial NOT NULL,
    firstname text,
    lastname text,
    description varchar(256),
    bank_account varchar(26),
    creation_date date NOT NULL,
    phone varchar(9),
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
    comment varchar(256),
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
    description varchar(256),
    grade real NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_account) REFERENCES public.account (id)
);

CREATE TABLE public.offer
(
    id bigserial NOT NULL,
    id_account bigint NOT NULL,
    id_dish bigint NOT NULL,
    hours text NOT NULL,
    day text NOT NULL,
    price real NOT NULL,
    localisation text NOT NULL,
    status boolean NOT NULL,
    periodic boolean NOT NULL,
    grade real NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_account) REFERENCES public.account (id),
    FOREIGN KEY (id_dish) REFERENCES public.dish (id)

);

CREATE TABLE public.dish_grades
(
    id bigserial NOT NULL,
    id_dish bigint NOT NULL,
    grade integer,
    comment varchar(256),
    id_owner bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_dish) REFERENCES public.dish (id),
    FOREIGN KEY (id_owner) REFERENCES public.account (id)
);

/*hasla dla kolejnych userów to:
  admin
  123
  qwer
  tyuiop
  qaz
  edc
  321
  baza
  ronnie
  baby yoda
 */


INSERT INTO login (email, password, is_admin, is_active) VALUES
    ('a@pw.edu.pl', '$2y$10$rqvXzFGQL4dM7YWc5QTILucKd/t4IeSt4hZJCsqgbn3rqdVQGql5i', true, true),
    ('b@pw.edu.pl', '$2y$10$rV/k6bnMF/GyuuFyrTGb8OqCOujK8K8KOMc19tKVfdmbKjWKsaNRi', true, true),
    ('c@pw.edu.pl', '$2y$10$ZdQ/cZsdjFAqjMeYOrRBk.7Vw7ETslHqgJ73OnotY7MlCn/cnSQOq', true, true),
    ('d@pw.edu.pl', '$2y$10$2dHaLUJYbdDS8LLf60CDNevd4m90gDCQrYSYAngXkFtYY22Ebs4d.', false, true),
    ('e@pw.edu.pl', '$2y$10$OF/qWEw5dy1B01eeXRUsbOLojMscUSmqjc2nsSwaiDOdxK3ZRHJeW', false, true),
    ('f@pw.edu.pl', '$2y$10$Gl0lRcHjKiOr9HU2EMYxeujq5sA96FJrywhVf8h6vQYMmhI.zL2A2', false, true),
    ('g@pw.edu.pl', '$2y$10$QuuPReeWSDKUmmJn8ws1ze4Ywox56D7LXZ4OGBiKwUJgwQ9eCg042', false, true),
    ('h@pw.edu.pl', '$2y$10$wWmRizgqTvUJhSMDsKJOj.u6WF5E7qf0fPoGBphBTvgE4XDdBS0c2', false, true),
    ('i@pw.edu.pl', '$2y$10$Obn8C5.0T4lYaPf.d3kA5e63lYMWWCa/uUEPJIESh0Vb78Jv8b39G', false, true),
    ('j@pw.edu.pl', '$2y$10$7Df5RhaqNxlEOUv54lD0l.RJcA4TiQlW3aGy6MlPxJ1ZedmpdKfFS', false, true);

INSERT INTO account (firstname, lastname, description, bank_account, creation_date, phone, specialization, grade, cook_status, id_login) VALUES
    ('Andrzej', 'Nowak', 'Dobry kucharz, lubię włoską kuchnię', '12341234123412341234', '2019-11-25', '123456789', 'kuchnia włoska', '5', 'true', 1),
    ('Marcin', 'Kowalski', 'Lubię placki', '12341234123412341233', '2018-10-24', '123456788', 'kuchnia grecka', '4', 'true', 2),
    ('Robert', 'Ciupa', 'Bona pettit', '12341234123412341232', '2019-02-21', '123456787', 'kuchnia francuska', '3', 'true', 3),
    ('Tomasz', 'Enter', 'React is my best friend', '12341234213712341234', '2017-01-15', '121376789', 'kuchnia amerykańska', '4', 'true', 4);

INSERT INTO account (creation_date, cook_status, id_login) VALUES
    ('2017-11-17', 'false', 5),
    ('2019-09-17', 'false', 6),
    ('2016-11-27', 'false', 7),
    ('2015-05-02', 'false', 8);

INSERT INTO account (firstname, lastname, creation_date, cook_status, id_login) VALUES
    ('Andrzej', 'Ciupa', '2017-01-07', 'false', 9),
    ('Mateusz', 'Magiera', '2015-09-16', 'true', 10);

INSERT INTO account_grades (id_account, grade, comment, id_owner) VALUES
    (1, 5, 'najlepszy kucharz ever', 5),
    (2, 4, 'moja mama lepsza', 5),
    (3, 3, 'jadłem gorzej', 5),
    (4, 1, 'nigdy więcej', 6),
    (5, 5, 'Spoko klient', 1),
    (6, 5, 'Nie mam uwag', 2),
    (7, 3, 'Wszystkiego się czepia', 3);

INSERT INTO account_grades (id_account, grade, id_owner) VALUES
    (1, 5, 7),
    (2, 4, 7),
    (1, 5, 8);

INSERT INTO dish (id_account, name, category, description, grade) VALUES
    (1, 'spaghetti napoli', 'kuchnia włoska', 'prosto z włoch, jeszcze ciepłe :)', 5),
    (1, 'pasta farfaria carbonaria', 'kuchnia włoska', 'też nie wiem jak to się pisze', 4),
    (2, 'placek grecki', 'kuchnia grecka', 'cant speak polish', 4),
    (3, 'ślimak', 'kuchnia francuska', 'specjalność kucharza', 3),
    (4, 'pizza', 'kuchnia amerykańska', 'bardzo doba', 5);

INSERT INTO dish (id_account, name, category, grade) VALUES
    (1, 'jakiś inny makaron', 'kuchnia włoska', 5),
    (2, 'souvlaki', 'kuchnia grecka', 4),
    (2, 'tzatziki', 'kuchnia grecka', 5),
    (3, 'żabie udka', 'kuchnia francuska', 4),
    (4, 'burger', 'kuchnia amerykańska', 4);

INSERT INTO dish_grades (id_dish, grade, comment, id_owner) VALUES
    (1, 5, 'Najlepsze spaghetti na świecie', 5),
    (2, 4, 'Taki typowy makaron', 6),
    (3, 4, 'Nawet dobry placek', 7),
    (4, 3, 'Kto by chciał jeść ślimaki', 6),
    (5, 5, 'Pizza pepperoni każdy ją zje', 8);

INSERT INTO dish_grades (id_dish, grade, id_owner) VALUES
    (6, 5, 5),
    (7, 4, 6),
    (8, 5, 7),
    (9, 4, 6),
    (10, 4, 8);

INSERT INTO offer (id_account, id_dish, hours, day, price, localisation, status, periodic, grade) VALUES
    (1, 1, '12-14', 'pon', 300, 'Ciechanów ul. Wyzwolenia 4', 'true', 'false', 0),
    (1, 2, '10-16', 'pon - czw', 500, 'Warszawa ul. Pierwsza 147', 'true', 'true', 0),
    (2, 3, '18-20', 'śr', 300, 'Ciechanów ul. Widna 4', 'true', 'true', 0),
    (3, 4, '08-20', 'pon-nie', 300, 'Warszawa ul. Pierwsza 147', 'true', 'true', 0),
    (4, 5, '16-18', 'sob', 300, 'Warszawa ul. Malczyńskiego 420', 'false', 'false', 0),
    (1, 6, '14-20', 'wt', 300, 'Ciechanów ul. Wyzwolenia 4', 'true', 'false', 0),
    (2, 7, '16-18', 'sob', 300, 'Ciechanów ul. Widna 4', 'true', 'false', 0),
    (2, 8, '16-20', 'pt', 300, 'Ciechanów ul. Widna 4', 'true', 'false', 0),
    (3, 9, '16-18', 'sob-nie', 300, 'Warszawa ul. Pierwsza 147', 'false', 'false', 0),
    (4, 10, '12-20', 'sob', 300, 'Warszawa ul. Malczyńskiego 420', 'true', 'true', 0);
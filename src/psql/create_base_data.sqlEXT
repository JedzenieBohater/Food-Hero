INSERT INTO login (email, password) VALUES
    ('a@pw.edu.pl', 'admin'),
    ('b@pw.edu.pl', '123'),
    ('c@pw.edu.pl', 'qwer'),
    ('d@pw.edu.pl', 'tyuiop'),
    ('e@pw.edu.pl', 'qaz'),
    ('f@pw.edu.pl', 'edc'),
    ('g@pw.edu.pl', '321'),
    ('h@pw.edu.pl', 'baza'),
    ('i@pw.edu.pl', 'ronnie'),
    ('j@pw.edu.pl', 'baby yoda');

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

INSERT INTO offers (id_account, id_dish, hours, day, localisation, status, periodic) VALUES
    (1, 1, '12-14', 'pon', 'Ciechanów ul. Wyzwolenia 4', 'true', 'false'),
    (1, 2, '10-16', 'pon - czw', 'Warszawa ul. Pierwsza 147', 'true', 'true'),
    (2, 3, '18-20', 'śr', 'Ciechanów ul. Widna 4', 'true', 'true'),
    (3, 4, '08-20', 'pon-nie', 'Warszawa ul. Pierwsza 147', 'true', 'true'),
    (4, 5, '16-18', 'sob', 'Warszawa ul. Malczyńskiego 420', 'false', 'false'),
    (1, 6, '14-20', 'wt', 'Ciechanów ul. Wyzwolenia 4', 'true', 'false'),
    (2, 7, '16-18', 'sob', 'Ciechanów ul. Widna 4', 'true', 'false'),
    (2, 8, '16-20', 'pt', 'Ciechanów ul. Widna 4', 'true', 'false'),
    (3, 9, '16-18', 'sob-nie', 'Warszawa ul. Pierwsza 147', 'false', 'false'),
    (4, 10, '12-20', 'sob', 'Warszawa ul. Malczyńskiego 420', 'true', 'true');
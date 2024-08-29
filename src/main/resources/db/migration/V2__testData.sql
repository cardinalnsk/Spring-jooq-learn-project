insert into countries(name, government_form, population)
values ('Russia', 'FEDERATE', 146745098),
       ('China', 'UNITARY', 1404328611),
       ('Switzerland', 'CONFEDERATE', 8558758);

insert into cities(country_id, name)
values (1, 'Moscow'),
       (1, 'Saint-Petersburg'),
       (1, 'Sevastopol'),
       (2, 'Beijing'),
       (2, 'Shanghai'),
       (2, 'Hong Kong'),
       (3, 'Zurich'),
       (3, 'Geneva'),
       (3, 'Bern');
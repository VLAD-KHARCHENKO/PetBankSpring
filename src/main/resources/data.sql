
insert into `user` (`first_name`, `last_name`,`login`, `password`, `condition`, `role`) values
('Сергій', 'Адміненко','admin@g.com', '$2a$12$BzcrGYG77xPP4EtfdV1WtOCb67RtnTAvUmSPyOuB74ADsUeRsIlGW', true, 'ADMIN'),
('Папа', 'Карло', 'user1@g.com', '$2a$12$BzcrGYG77xPP4EtfdV1WtOCb67RtnTAvUmSPyOuB74ADsUeRsIlGW', true, 'CUSTOMER'),
('Буратіно','Карло', 'user2@g.com', '$2a$12$BzcrGYG77xPP4EtfdV1WtOCb67RtnTAvUmSPyOuB74ADsUeRsIlGW', true, 'CUSTOMER'),
('Федір', 'Дядя', 'user3@g.com', '$2a$12$BzcrGYG77xPP4EtfdV1WtOCb67RtnTAvUmSPyOuB74ADsUeRsIlGW', false, 'CUSTOMER'),
('Дженіфер', 'Лопес', 'user4@g.com', '$2a$12$BzcrGYG77xPP4EtfdV1WtOCb67RtnTAvUmSPyOuB74ADsUeRsIlGW', true, 'CUSTOMER');

insert into `card`  (`card_name`,`number`,`condition`, `account_id`)  values
('UNIVERSAL', '2600124501',  true, 1),
('CREDIT', '2600124502', true, 2),
('UNIVERSAL', '2600124503', true, 3),
('CREDIT', '2600124504', true, 4),
('UNIVERSAL', '2600124505', true, 5),
('INTERNET', '2600124506', true, 6),
('UNIVERSAL', '2600124507', true, 7),
('ANOTHER', '2600124508', false, 8),
('UNIVERSAL', '2600124509', true, 9),
('CREDIT', '26001245010', false, 10);
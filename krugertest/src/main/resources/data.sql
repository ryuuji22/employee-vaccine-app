INSERT INTO public.roles values ('8c37cde1-1146-4c6b-8e2b-b24a813dff33','ROLE_ADMIN');
INSERT INTO public.roles values ('60b40c14-84f5-4432-b614-8c4ad0f012f6','ROLE_USER');

INSERT INTO public.users values (
    'f85b529d-e3c9-4849-87d0-760c4edb3060',
    '2022-08-07',
    TRUE,
    '1715849731',
    '$2a$12$hMZpDsEUpPlNRBjEhZ6tCejkab/9K6985omuzrdeWa049AJLuKR.C'
    );

INSERT INTO public.users_roles values (
    'f85b529d-e3c9-4849-87d0-760c4edb3060',
    '8c37cde1-1146-4c6b-8e2b-b24a813dff33'
    );

INSERT INTO public.employees values (
    '76ee1596-3cf5-435e-916c-c5a181edfe68',
    'Av Galo Plaza Lasso',
    '1988-05-20',
    '2022-08-07',
    'diego_and21@hotmail.com',
    TRUE,
    'Diego',
    '1715849731',
    'Hinojosa',
    '0992686594',
    FALSE,
    'f85b529d-e3c9-4849-87d0-760c4edb3060'
);

INSERT INTO public.vaccine(
	id, name)
	VALUES ('38caabe5-97e4-4854-a0f8-e3ae594c451c', 'AstraZeneca');

INSERT INTO public.vaccine(
	id, name)
	VALUES ('7b7e07b5-0823-4648-8836-8ec42d964e93', 'Pfizer');

INSERT INTO public.vaccine(
	id, name)
	VALUES ('cd2296e0-1fd3-4e1e-90a3-c75cd4c0ba32', 'Jhonson&Jhonson');

INSERT INTO public.vaccine(
	id, name)
	VALUES ('f23f4a20-792e-4577-b3ac-eca76ba92c66', 'Sputnik');
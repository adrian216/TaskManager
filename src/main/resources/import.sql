create table persistent_logins (
                                 username varchar(64) not null,
                                 series varchar(64) primary key,
                                 token varchar(64) not null,
                                 last_used timestamp not null
);
/* USERS */
INSERT INTO user(EMAIL, FIRSTNAME, LASTNAME, PASSWORD, active) VALUES ('admin@gmail.com','admin', 'admin', '$2a$10$c0T1KiwqBlHOIBAOKRnXe.deJcH.29wfnvkqIwFxYC/si0LmAiaoe', 1);
INSERT INTO user(EMAIL, FIRSTNAME, LASTNAME, PASSWORD, active) VALUES ('user@gmail.com','user', 'user', '$2a$10$c0T1KiwqBlHOIBAOKRnXe.deJcH.29wfnvkqIwFxYC/si0LmAiaoe', 1);

/* ROLES */
INSERT INTO role(ROLE_ID, ROLE) VALUES (1, 'ADMIN');
INSERT INTO role(ROLE_ID, ROLE) VALUES (2, 'USER');

INSERT INTO user_role(user_id, role_id) VALUES (1, 1);
INSERT INTO user_role(user_id, role_id) VALUES (1, 2);
INSERT INTO user_role(user_id, role_id) VALUES (2, 2);

/* TASKS */
INSERT INTO task(title, description, deadline, status, user_id) VALUES ('Shopping', 'Shoes and pants from Zara', '2019-03-06 10:30:00', 0, 1 );
INSERT INTO task(title, description, deadline, status, user_id) VALUES ('Laundry', 'Pick up clothes from laundry place', '2019-03-17 09:45:00', 1, 2 );
INSERT INTO task(title, description, deadline, status, user_id) VALUES ('Appointment', 'Eye appointment, pick up contacts', '2019-04-19 11:20:00', 0, 2 );
INSERT INTO task(title, description, deadline, status, user_id) VALUES ('Flight', 'Flight to Amsterdam', '2019-04-29 13:45:00', 0, 1 );
INSERT INTO task(title, description, deadline, status, user_id) VALUES ('Dinner', 'Family dinner at whitespot', '2019-05-26 21:00:00', 1, 2 );
INSERT INTO task(title, description, deadline, status, user_id) VALUES ('Project deadline', 'Hand in project', '2019-05-24 08:00:00', 0, 1 );
INSERT INTO task(title, description, deadline, status, user_id) VALUES ('Call', 'Call back to XYZ company', '2019-04-23 10:30:00', 0, 2 );
INSERT INTO task(title, description, deadline, status, user_id) VALUES ('Barber', 'st.Street 15/3 hairdresser', '2019-05-16 19:30:00', 1, 1 );
INSERT INTO task(title, description, deadline, status, user_id) VALUES ('Meeting', 'Business meeting with XYZ', '2019-04-01 14:00:00', 0, 1 );



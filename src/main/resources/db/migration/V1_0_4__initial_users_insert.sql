INSERT INTO public."user" (email, password, fname, sname, lname, description, active) VALUES ('denislavveizov@gmail.com', '$2a$10$AlhYSuK/1o0fWnBiG6h8JuMOgFptLNO5eSb25h227frmh2jG0ie3e', 'Denislav', 'S', 'Veizov', 'Administrator', true);
INSERT INTO public.user_role (user_id, role_id) VALUES (1, 2);

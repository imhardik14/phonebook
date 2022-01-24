INSERT INTO user (user_id,user_name, password, email,type) VALUES 
  (random_uuid(),'admin', 'admin', 'admin@test.com',1),
  (random_uuid(),'user', 'password', 'user@test.com',0);
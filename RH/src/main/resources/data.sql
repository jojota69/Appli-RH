INSERT INTO person (first_name, last_name, email, date_of_birth)
SELECT 'John', 'Doe', 'john.doe@example.com', '1990-01-01'
WHERE NOT EXISTS (
	SELECT 1 FROM person WHERE email = 'john.doe@example.com'
);

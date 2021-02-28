insert into users (
    id,
    created_date,
    email,
    password,
    status,
	role)

values (
    1,
    clock_timestamp(),
    'user1@gmail.com',
    '$2y$12$gAQpVMBLCsteGxSjKYUYuuEQxfr4w.PhpYOxjzwXcpzvgQHDTadSW',
    'ACTIVE',
	'USER'),
    (
    2,
    clock_timestamp(),
    'user2@gmail.com',
    '$2y$12$CNKkGKVgO3v1dE8RjSUvAOXMoxpQppcWUNIrXiDj2BkgdHkCULfe.',
	'ACTIVE',
	'USER'),
    (
    3,
    clock_timestamp(),
    'admin1@gmail.com',
    '$2y$12$JyJtFt8dMWRxKi/VQpOhge0GIb1LzXujdfXvk4BgPA./ZSdHRhYre',
	'ACTIVE',
	'ADMIN');
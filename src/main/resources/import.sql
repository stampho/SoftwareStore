/* USER TABLE */
insert into user (id, email, name, password, real_name, registration_date, role) values (0, 'admin@softwarestore.prf.sed.hu', 'admin', 'admin', 'Administrator', {ts '2013-04-21 20:42:28.69'}, 1);

insert into user (id, email, name, password, real_name, registration_date, role) values (1, 'wwilliams@mail.com', 'walter', 'walter', 'Walter Williams', {ts '2015-04-10 22:14:12.08'}, 0);
insert into user (id, email, name, password, real_name, registration_date, role) values (2, 'gary@gmail.com', 'gary', 'gary', 'Gary Elmore', {ts '2014-12-03 08:00:59.72'}, 0);
insert into user (id, email, name, password, real_name, registration_date, role) values (3, 'simmons@mail.com', 'carlos', 'carlos', 'Carlos Simmons', {ts '2014-08-08 12:03:02.00'}, 0);


/* PRODUCT_CATEGORY TABLE */
insert into product_category (id, name, description) values (0, 'OS', 'Software that manages computer hardware and software resources and provides common services for computer programs.');
insert into product_category (id, name, description) values (1, 'AntiVirus', 'Detect and remove harmful or malicious softwares like malwares, trojans, worms, adwares, viruses, etc.');
insert into product_category (id, name, description) values (2, 'Productivity Software', 'Dedicated to producing information, such as documents, presentations, worksheets, databases, charts, graphs, digital paintings, electronic music and digital video.');
insert into product_category (id, name, description) values (3, 'Firewall', 'Network security system that controls the incoming and outgoing network traffic based on an applied rule set.');
insert into product_category (id, name, description) values (4, 'File Archiver & Compressor', 'Combines a number of files together into one archive file, or a series of archive files, for easier transportation or storage. File archivers may employ lossless data compression in their archive formats to reduce the size of the archive.' );
insert into product_category (id, name, description) values (5, 'IDE & Developer Tool', 'An integrated development environment (IDE) or interactive development environment is a software application that provides comprehensive facilities to computer programmers for software development.');
insert into product_category (id, name, description) values (6, 'File Manager', 'Provides a user interface to manage files and folders.');
insert into product_category (id, name, description) values (7, 'Game', '');
insert into product_category (id, name, description) values (8, 'Photo & Video', 'Image viewers, video players, editors, etc.');


/* PRODUCT TABLE */
insert into product (id, name, version, release_date, company, category_id, price, description) values (0, 'Windows', '7', {ts '2009-07-22'}, 'Microsoft', 0, 18990, 'Personal computer operating system developed by Microsoft as part of the Windows NT family of operating systems.');
insert into product (id, name, version, release_date, company, category_id, price, description) values (1, 'Windows OEM (32-bit)', '8', {ts '2012-08-01'}, 'Microsoft', 0, 36990, 'Personal computer operating system developed by Microsoft as part of the Windows NT family of operating systems.');
insert into product (id, name, version, release_date, company, category_id, price, description) values (2, 'Windows OEM (64-bit)', '8', {ts '2012-08-01'}, 'Microsoft', 0, 36990, 'Personal computer operating system developed by Microsoft as part of the Windows NT family of operating systems.');
insert into product (id, name, version, release_date, company, category_id, price, description) values (3, 'Windows OEM (32-bit)', '8 Pro', {ts '2012-08-01'}, 'Microsoft', 0, 41990, 'Personal computer operating system developed by Microsoft as part of the Windows NT family of operating systems.');
insert into product (id, name, version, release_date, company, category_id, price, description) values (4, 'Windows OEM (64-bit)', '8 Pro', {ts '2012-08-01'}, 'Microsoft', 0, 41990, 'Personal computer operating system developed by Microsoft as part of the Windows NT family of operating systems.');
insert into product (id, name, version, release_date, company, category_id, price, description) values (5, 'Mac OS X', '10.6 Snow Leopard', {ts '2011-06-25'}, 'Apple', 0, 22490, 'Seventh major release of Mac OS X, Apples desktop and server operating system for Macintosh computers.');

insert into product (id, name, version, release_date, company, category_id, price, description) values (6, 'avast! Pro Antivirus', '2014', {ts '2014-02-13'}, 'avast', 1, 10890, 'Avast security software products are developed for Microsoft Windows, Mac OS X, Android and Linux users by AVAST Software s.r.o., a Czech private limited company.');


/* SALE TABLE */
insert into sale (id, user_id, payment, sale_date, comment) values (0, 2, 28000, {ts '2014-12-03 08:45:12'}, 'OS + Antivirus discount');
insert into sale_product (sale_id, product_id) values (0, 0);
insert into sale_product (sale_id, product_id) values (0, 6);
insert into sale (id, user_id, payment, sale_date, comment) values (1, 1, 41990, {ts '2015-04-11 14:25:38'}, '');
insert into sale_product (sale_id, product_id) values (1, 4);
insert into sale (id, user_id, payment, sale_date, comment) values (2, 1, 10890, {ts '2015-04-12 07:12:59'}, '');
insert into sale_product (sale_id, product_id) values (2, 6);
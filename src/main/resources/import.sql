/* USER TABLE */
insert into user (id, email, name, password, real_name, registration_date, role) values (0, 'admin@softwarestore.prf.sed.hu', 'admin', 'admin', 'Administrator', {ts '2013-04-21 20:42:28.69'}, 1)

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
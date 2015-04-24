/* USER TABLE */
insert into user (id, email, name, password, real_name, registration_date, role) values (-1, 'admin@softwarestore.prf.sed.hu', 'admin', 'admin', 'Administrator', {ts '2013-04-21 20:42:28.69'}, 1);

insert into user (id, email, name, password, real_name, registration_date, role) values (-2, 'wwilliams@mail.com', 'walter', 'walter', 'Walter Williams', {ts '2015-04-10 22:14:12.08'}, 0);
insert into user (id, email, name, password, real_name, registration_date, role) values (-3, 'gary@gmail.com', 'gary', 'Ch4r4cters', 'Gary Elmore', {ts '2014-12-03 08:00:59.72'}, 0);
insert into user (id, email, name, password, real_name, registration_date, role) values (-4, 'simmons@mail.com', 'carlos', 'C4rlos', 'Carlos Simmons', {ts '2014-08-08 12:03:02.00'}, 0);


/* PRODUCT_CATEGORY TABLE */
insert into product_category (id, name, description) values (-1, 'OS', 'Software that manages computer hardware and software resources and provides common services for computer programs.');
insert into product_category (id, name, description) values (-2, 'AntiVirus', 'Detect and remove harmful or malicious softwares like malwares, trojans, worms, adwares, viruses, etc.');
insert into product_category (id, name, description) values (-3, 'Productivity Software', 'Dedicated to producing information, such as documents, presentations, worksheets, databases, charts, graphs, digital paintings, electronic music and digital video.');
insert into product_category (id, name, description) values (-4, 'Firewall', 'Network security system that controls the incoming and outgoing network traffic based on an applied rule set.');
insert into product_category (id, name, description) values (-5, 'File Archiver & Compressor', 'Combines a number of files together into one archive file, or a series of archive files, for easier transportation or storage. File archivers may employ lossless data compression in their archive formats to reduce the size of the archive.' );
insert into product_category (id, name, description) values (-6, 'IDE & Developer Tool', 'An integrated development environment (IDE) or interactive development environment is a software application that provides comprehensive facilities to computer programmers for software development.');
insert into product_category (id, name, description) values (-7, 'File Manager', 'Provides a user interface to manage files and folders.');
insert into product_category (id, name, description) values (-8, 'Game', '');
insert into product_category (id, name, description) values (-9, 'Photo & Video', 'Image viewers, video players, editors, etc.');


/* PRODUCT TABLE */
insert into product (id, name, version, release_date, company, category_id, price, description) values (-1, 'Windows', '7', {ts '2009-07-22'}, 'Microsoft', -1, 18990, 'Personal computer operating system developed by Microsoft as part of the Windows NT family of operating systems.');
insert into product (id, name, version, release_date, company, category_id, price, description) values (-2, 'Windows OEM (32-bit)', '8', {ts '2012-08-01'}, 'Microsoft', -1, 36990, 'Personal computer operating system developed by Microsoft as part of the Windows NT family of operating systems.');
insert into product (id, name, version, release_date, company, category_id, price, description) values (-3, 'Windows OEM (64-bit)', '8', {ts '2012-08-01'}, 'Microsoft', -1, 36990, 'Personal computer operating system developed by Microsoft as part of the Windows NT family of operating systems.');
insert into product (id, name, version, release_date, company, category_id, price, description) values (-4, 'Windows OEM (32-bit)', '8 Pro', {ts '2012-08-01'}, 'Microsoft', -1, 41990, 'Personal computer operating system developed by Microsoft as part of the Windows NT family of operating systems.');
insert into product (id, name, version, release_date, company, category_id, price, description) values (-5, 'Windows OEM (64-bit)', '8 Pro', {ts '2012-08-01'}, 'Microsoft', -1, 41990, 'Personal computer operating system developed by Microsoft as part of the Windows NT family of operating systems.');
insert into product (id, name, version, release_date, company, category_id, price, description) values (-6, 'Mac OS X', '10.6 Snow Leopard', {ts '2011-06-25'}, 'Apple', -1, 22490, 'Seventh major release of Mac OS X, Apples desktop and server operating system for Macintosh computers.');

insert into product (id, name, version, release_date, company, category_id, price, description) values (-11, 'avast! Pro Antivirus', '2014', {ts '2014-02-13'}, 'avast', -2, 10890, 'Avast security software products are developed for Microsoft Windows, Mac OS X, Android and Linux users by AVAST Software s.r.o., a Czech private limited company.');

insert into product (id, name, version, release_date, company, category_id, price, description) values (-21, 'Office', '365 Personal', {ts '2011-06-28'}, 'Microsoft', -3, 19900, 'For consumers, the service allows the use of Microsoft Office apps on Windows and OS X, provides storage space on Microsofts cloud storage service OneDrive, and grants 60 Skype minutes per month.');
insert into product (id, name, version, release_date, company, category_id, price, description) values (-22, 'Adobe Acrobat Professional', '11', {ts '2015-04-06'}, 'Adobe Systems', -3, 174900, 'Adobe Acrobat is a family of application software and Web services developed by Adobe Systems to view, create, manipulate, print and manage files in Portable Document Format (PDF).');

insert into product (id, name, version, release_date, company, category_id, price, description) values (-31, 'Outpost Firewall Pro', '9', {ts '2014-02-24'}, 'Agnitum', -4, 10390, 'Software-based personal firewall package developed by Agnitum (founded in 1999 in St. Petersburg, Russia). Has  5 language packs - English, German, French, Spanish and Russian.');

insert into product (id, name, version, release_date, company, category_id, price, description) values (-41, 'WinRAR', '5.00', {ts '2015-02-16'}, 'RarLab', -5, 11990, 'File archiver and compressor utility for Windows. It can create archives in RAR or ZIP file formats,[2] and unpack numerous archive file formats.');
insert into product (id, name, version, release_date, company, category_id, price, description) values (-42, 'WinZip', '18 Standard', {ts '2013-10-31'}, 'WinZip', -5, 18990, 'File archiver and compressor for Windows, OS X, iOS and Android developed by WinZip Computing (formerly Nico Mak Computing). It can create archives in Zip file format, and unpack some other archive file formats.');

insert into product (id, name, version, release_date, company, category_id, price, description) values (-51, 'Visual Studio', '2010 Professional', {ts '2010-04-01'}, 'Microsoft', -6, 457190, 'An integrated development environment (IDE) from Microsoft. It is used to develop computer programs for Microsoft Windows, as well as web sites, web applications and web services.');

insert into product (id, name, version, release_date, company, category_id, price, description) values (-61, 'Total Commander', '8.50', {ts '2014-04-30'}, 'Ghisler', -7, 10990, 'Orthodox File Manager (OFM) for Windows, Windows CE, Windows Mobile, and Android. Some features include a built-in FTP client, tabbed interface, file compare, archive file navigation, and a versatile multi-rename tool with regular expression support.');

insert into product (id, name, version, release_date, company, category_id, price, description) values (-71, 'S.T.A.L.K.E.R.: Shadow of Chernobyl', '1.0', {ts '2007-03-23'}, 'GSC Game World', -8, 2490, 'First-person shooter survival horror video game developed by GSC Game World and published by THQ.');

insert into product (id, name, version, release_date, company, category_id, price, description) values (-81, 'Adobe Photoshop Elements', '12', {ts '2013-09-01'}, 'Adobe Systems', -9, 29900, 'Raster graphics editor for hobbyists and consumers. It contains most of the features of the professional version but with fewer and simpler options. The program allows users to create, edit, organize and share images.');

/* SALE TABLE */
insert into sale (id, user_id, payment, sale_date, comment) values (-1, -3, 28000, {ts '2014-12-03 08:45:12'}, 'OS + Antivirus discount');
insert into sale_product (sale_id, product_id) values (-1, -1);
insert into sale_product (sale_id, product_id) values (-1, -11);
insert into sale (id, user_id, payment, sale_date, comment) values (-2, -2, 41990, {ts '2015-04-11 14:25:38'}, '');
insert into sale_product (sale_id, product_id) values (-2, -5);
insert into sale (id, user_id, payment, sale_date, comment) values (-3, -2, 10890, {ts '2015-04-12 07:12:59'}, '');
insert into sale_product (sale_id, product_id) values (-3, -11);
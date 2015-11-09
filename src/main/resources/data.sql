insert into cars values(1,'Ford','Mustang','Sports Car');
insert into cars values(2,'Ford','F150','Truck');
insert into cars values(3,'Mercedes','S600','Sedan');

insert into categories(id,name,description) values(1,'Computers','Laptops, Desktops, etc...');
insert into categories(id,name,description) values(2,'Hand Held Devices','Tablets, MP3 Players, Arm Bands & Watches, etc...');
insert into categories(id,name,description) values(3,'Phones','Android, Iphone, Windows, and Blackberry');

insert into inventory(id,category,item,description,price,on_hand) values(1,1,'Thinkpad 540','Lenovo Laptop, 16GB Ram, i7 5th Generation, Mobile workstation ready for serious computing needs...',999,5);
insert into inventory(id,category,item,description,price,on_hand) values(2,1,'Macbook Pro','Apple Laptop, 16GB Ram, i7 5th Generation',1999,10);
insert into inventory(id,category,item,description,price,on_hand) values(3,2,'Google Nexus 9','9" Tablet, WiFi & GSM',399,10);
insert into inventory(id,category,item,description,price,on_hand) values(4,2,'Ipad Air II','10" Tablet, WiFi Only',499,8);
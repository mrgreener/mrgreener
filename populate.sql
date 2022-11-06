INSERT INTO `Client` (`authId`, `username`, `name`, `isAdmin`, `registeredOn`, `bio`, `avatarUrl`)
VALUES ("XdWjHaOwII23", "Alex42", "Alexander", FALSE, "2022-10-01 01:00:00", "Student @ BIT",
        "https://de.wikipedia.org/wiki/Alex_Pettyfer#/media/Datei:Alex_Pettyfer_2006_(252479969)_cropped.jpg"),
       ("kSAudf67qCsf", "M4rk", "Mark Ipatov", TRUE, "2022-09-25 12:30:00", "2nd year Student @ JUB",
        "https://s1.zerochan.net/Penny.Polendina.600.1628923.jpg"),
       ("kGFks11elsif", "petrtsv", "Tsvetkov Petr", FALSE, "2022-10-03 11:49:12", "Go Green!!",
        "https://sun9-east.userapi.com/sun9-24/s/v1/ig2/9NAmhLElVsO_w49wZDmo7TBmqF7Hl_JGuwSUt4E6BCK8s9QpzFd7AzK00UklT0UapZkROIRjuqqYKIJc3itT3RYe.jpg?size=1440x2160&quality=95"),
       ("fewuh3r3124x", "kirdmv", "Kirill Ivanov", TRUE, "2022-10-04 11:49:12", "Hi, I am Kirill", NULL);

INSERT INTO `Organizations` (`authId`, `username`, `name`, `apiKey`, `verified`, `description`, `avatarUrl`, `location`,
                             `siteUrl`, `contactEmail`, `registeredOn`)
VALUES ("9w&x0Xq31wo3", "avocadoshop", "Avocado Shop", "wi3f89s74zue72", TRUE,
        "Best Avocado Shop in the town",
        "https://imgmedia.lbb.in/media/2019/11/5dcae73a53703c2f98a1da63_1573578554772.jpg",
        "Campus Ring 1, Bremen, Germany", "https://www.avocadostore.de", "inbox@avocadostore.de", "2022-11-06T00:49"),
       ("fuwer2yh8e87", "Shein", "Shein Shop", "1143oo12peoxkf", TRUE,
        "The great Shein Shop",
        "https://www.textilwirtschaft.de/news/media/23/Klarna--Shein-Pop-up-Shop-in-London-225385-detailp.png",
        "Schadowstraße 23", "https://roe.shein.com/", "inbox@sheinstore.de", "2022-11-01T13:29"),
       ("OE7387hfdO*&", "avocadoshop", "Avocado Shop", "wi3f89s74zue72", TRUE, --dup!
        "Best Avocado Shop in the town",
        "https://imgmedia.lbb.in/media/2019/11/5dcae73a53703c2f98a1da63_1573578554772.jpg",
        "Campus Ring 1, Bremen, Germany", "https://www.avocadostore.de", "inbox@avocadostore.de", "2022-11-06T00:49"),
       ("L#Q@O*E(UPQ)", "avocadoshop", "Avocado Shop", "wi3f89s74zue72", TRUE, --dup!
        "Best Avocado Shop in the town",
        "https://imgmedia.lbb.in/media/2019/11/5dcae73a53703c2f98a1da63_1573578554772.jpg",
        "Campus Ring 1, Bremen, Germany", "https://www.avocadostore.de", "inbox@avocadostore.de", "2022-11-06T00:49");

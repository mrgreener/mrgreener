INSERT INTO client (authId, username, name, isAdmin, registered_on, bio, avatarUrl)
VALUES ('XdWjHaOwII23', 'Alex42', 'Alexander', FALSE, '2022-10-01 01:00:00', 'Student @ BIT',
        'https://de.wikipedia.org/wiki/Alex_Pettyfer#/media/Datei:Alex_Pettyfer_2006_(252479969)_cropped.jpg'),
       ('kSAudf67qCsf', 'M4rk', 'Mark Ipatov', TRUE, '2022-09-25 12:30:00', '2nd year Student @ JUB',
        'https://s1.zerochan.net/Penny.Polendina.600.1628923.jpg'),
       ('kGFks11elsif', 'petrtsv', 'Tsvetkov Petr', FALSE, '2022-10-03 11:49:12', 'Go Green!!',
        'https://sun9-east.userapi.com/sun9-24/s/v1/ig2/9NAmhLElVsO_w49wZDmo7TBmqF7Hl_JGuwSUt4E6BCK8s9QpzFd7AzK00UklT0UapZkROIRjuqqYKIJc3itT3RYe.jpg?size=1440x2160&quality=95'),
       ('fewuh3r3124x', 'kirdmv', 'Kirill Ivanov', TRUE, '2022-10-04 11:49:12', 'Hi, I am Kirill', NULL);

INSERT INTO organizations (authId, username, name, apiKey, verified, description, avatar_url, location,
                             site_url, contact_email, registered_on)
VALUES ('9w&x0Xq31wo3', 'avocadoshop', 'Avocado Shop', 'wi3f89s74zue72', TRUE,
        'Best Avocado Shop in the town',
        'https://imgmedia.lbb.in/media/2019/11/5dcae73a53703c2f98a1da63_1573578554772.jpg',
        'Campus Ring 1, Bremen, Germany', 'https://www.avocadostore.de', 'inbox@avocadostore.de', '2022-11-06T00:49'),
       ('fuwer2yh8e87', 'Shein', 'Shein Shop', '1143oo12peoxkf', TRUE,
        'The great Shein Shop',
        'https://www.textilwirtschaft.de/news/media/23/Klarna--Shein-Pop-up-Shop-in-London-225385-detailp.png',
        'Schadowstraße 23', 'https://roe.shein.com/', 'inbox@sheinstore.de', '2022-11-01T13:29'),
       ('OE7388hfdO*&', 'green-your-life', 'Green Your Life', 'wi3f89s84zue72', TRUE,
        'Das Leben ist schön. Und damit das für dich, deine Familie und den ganzen Planeten so bleibt verkaufen wir nur schöne und zugleich nachhaltig produzierte Dinge. Sachen, die grün, recycelt oder öko sind – und gleichzeitig großartig designt, mit einer coolen Idee und guter Qualität.',
        'https://cdn02.plentymarkets.com/bro4m9maz244/frontend/img/logos/GYL_Logo_Wort_Bild_positiv_OG_Image.jpg',
        'Erlenmeyerstrasse 1, Innenstadt, Germany', 'https://www.green-your-life.de', 'support@green-your-life.de', '2022-11-06T05:54'),
       ('L#Q@O*E(UPQ)', 'amazon', 'Amazon', 'wi3d89s74zue72', TRUE,
        'Largest online shop.',
        'https://m.media-amazon.com/images/G/03/social_share/amazon_logo._CB633266471_.png',
        '\Germany', 'https://www.amazon.de', 'support@amazon.de', '2022-11-06T06:02');

INSERT INTO promotions (name, description, short_description, verified, organization_id, reward_points,
                          isactive, pictureurl, created_on)
VALUES ('Cashback', 'Get 100 points for purchance above 20 euros', 'Get 100 points for purchance above 20 euros',
        TRUE, 1, 100, TRUE, 'https://imgmedia.lbb.in/media/2019/11/5dcae73a53703c2f98a1da63_1573578554772.jpg',
        '2022-11-06T07:22'),
       ('Coffe cup', 'Get 5 points for buying coffee', 'Get 5 points for buying coffee',
        TRUE, 1, 5, TRUE, 'https://imgmedia.lbb.in/media/2019/11/5dcae73a53703c2f98a1da63_1573578554772.jpg',
        '2022-11-06T07:31'),
       ('Cashback', 'Get 50 points for purchance above 10 euros', 'Get 50 points for purchance above 10 euros',
        TRUE, 3, 50, TRUE, 'https://www.textilwirtschaft.de/news/media/23/Klarna--Shein-Pop-up-Shop-in-London-225385-detailp.png',
        '2022-11-06T07:34');

INSERT INTO promotionvouchers (code, rewardpoints, issuedon, promotion_id)
VALUES ('sgjsghjm1', 100, '2022-11-06T08:22', 1),
       ('sgjsghjm2', 100, '2022-11-06T08:23', 1),
       ('sgjsghjm3', 100, '2022-11-06T08:24', 1),
       ('sgjsghjm4', 100, '2022-11-06T18:25', 1),
       ('sgjsghjm5', 100, '2022-11-06T09:26', 1),
       ('sgjfghjsm1', 5, '2022-11-06T08:22', 2),
       ('sgjfghsjm2', 5, '2022-11-06T08:23', 2),
       ('sgjfghjsm3', 5, '2022-11-06T08:24', 2),
       ('sgasdasdghjm4', 50, '2022-11-06T18:25', 3),
       ('sgdasdadasdashjm5', 50, '2022-11-06T09:26', 3);

INSERT INTO promotionvoucheractivations (activatedon, client_id, promotion_voucher_id)
VALUES ('2022-11-06T08:23', 1, 1),
       ('2022-11-06T20:23', 1, 2),
       ('2022-11-06T20:25', 1, 7),
       ('2022-11-06T20:33', 2, 3),
       ('2022-11-06T20:27', 2, 8),
       ('2022-11-06T20:29', 3, 9);

INSERT INTO  rewards (content, createdon, description, is_active, name, pictureurl, short_description, verified, organization_id, price)
VALUES ('HikdU65785HD', '2022-10-05T20:23', 'Gift card 100.00 EUR', TRUE, 'Gift card', 'https://dundle.com/resources/images/products/product-google-search/amazon-16x9.png', 'Gift card 100.00 EUR', TRUE, 4, 100);

INSERT INTO rewardvouchers (content, issuedon, reward_id)
VALUES ('HikdU65785HD', '2022-11-06T22:29', 1),
       ('HikdU65785HD', '2022-11-06T22:30', 1);

INSERT INTO rewardvoucheractivations (activatedon, client_id, reward_voucher_id)
VALUES ('2022-11-06T22:29', 1, 1);
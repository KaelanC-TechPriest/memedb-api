--ENTITIES

INSERT INTO [USER] (User_id, Username, Email, Password, Join_date) 
VALUES 
    (1, 'AliceInWonder', 'alice@example.com', 'd0ntKn0wTh!s', '2024-11-28'),
    (2, 'BobBuilder123', 'bob@builder.net', 'B0bBu!lds', '2024-11-27'),
    (3, 'CharlieChill', 'charlie@chill.org', 'Ch@rlieR3l@x', '2024-11-26'),
    (4, 'DoraExplorer', 'dora@explorer.com', 'D0r@Adv3ntur3', '2024-11-25'),
    (5, 'EllieElephant', 'ellie@elephant.io', '3l3phantsn0tF0rgotten', '2024-11-24');

INSERT INTO MEME (Meme_id, [Size], Times_reported, Upload_date, Is_private, Views, Likes, Dislikes, Creator) 
VALUES 
    (1, 500000, 0, '2024-11-28', 0, 150, 45, 3, 1),
    (2, 1048576, 2, '2024-11-27', 1, 10, 2, 1, 2),
    (3, 200000, 0, '2024-11-26', 0, 500, 120, 10, 2),
    (4, 1500000, 1, '2024-11-25', 0, 1000, 250, 20, 4),
    (5, 750000, 0, '2024-11-24', 1, 20, 5, 0, 5);

INSERT INTO PICTURE_MEME (Picture_meme_id, Base_image, Text_image, Format)
VALUES 
    (1, 'https://memes.co.in/Uploads/Media/Aug19/Sun25/248/fe932fbf.jpg', 'https://example.com/text.png', 'JPEG'),
    (2, 'https://www.boredpanda.com/blog/wp-content/uploads/2023/08/funny-adorable-cat-memes-happycat318-cover_800.jpg', NULL, 'PNG'),
    (3, 'https://www.boredpanda.com/blog/wp-content/uploads/2022/09/it-humor-and-memes-211-63285cf3a401d__700.jpg', NULL, 'BMP');

INSERT INTO GIF_MEME (Gif_meme_id, Gif, Duration) 
VALUES (4, 'http://images.memes.com/meme/247869', '00:00:05');

INSERT INTO VIDEO_MEME (Video_meme_id, Video, Format, Duration) 
VALUES (5, 'https://youtu.be/dQw4w9WgXcQ?si=-dHbU0ArqcORrm8y', 'MP4', '00:03:33');

INSERT INTO COMMENT (User_id, Meme_id, Comment_id, [Text], Date_posted) 
VALUES 
    (1, 1, 1, 'Haha, this is epic!', '2024-11-28 14:30:00'),
    (2, 2, 1, 'I laughed way too hard at this!', '2024-11-28 12:15:23'),
    (2, 3, 1, 'This meme speaks to my soul.', '2024-11-28 09:45:00'),
    (3, 4, 1, 'Can someone explain this to me?', '2024-11-27 18:00:00'),
    (4, 5, 1, 'Loving the creativity here!', '2024-11-27 22:30:45');

INSERT INTO TAG (Name, Times_used)
VALUES 
    ('Technology', 1),
    ('Adventure', 0),
    ('Cuisine', 2),
    ('Science', 1),
    ('Politics', 1);

INSERT INTO ADVERTISER ([Name], Num_ads, Date_of_payment) 
VALUES 
    ('WalksWagon. Das feet', 150, '2024-11-28'),
    ('Edison', 200, '2024-11-27'),
    ('Drug dealers & co', 50, '2024-11-26'),
    ('Shamone', 75, '2024-11-25'),
    ('Eugenics inc.', 100, '2024-11-24');

INSERT INTO LT_ADVERTISER ([Name], Billing_info, [Address], Date_joined, Billing_period) 
VALUES 
    ('WalksWagon. Das feet', 'Credit Card: 1234-1234-1234-1234, Exp 12/39/00', 'Austria', '2024-11-28', 'Quarterly'),
    ('Drug dealers & co', 'Invoice via Email: agent47@fbi.gov', 'FBI', '2024-11-27', 'Biannual'),
    ('Shamone', '4312-4321-4321-4321', '407 Smooth Criminal rd', '2024-11-26', 'Annual');

INSERT INTO AD ([Number], Publisher, Url, Date_published) 
VALUES 
    (1, 'WalksWagon. Das feet', 'https://www.ww.com/pics', '2024-11-28 10:30:00'),
    (2, 'Edison', 'https://www.edison.com/', '2024-11-27 15:45:22'),
    (3, 'Shamone', 'https://www.youvebeenhitby.com/new-release', '2024-11-26 09:00:00'),
    (4, 'Drug dealers & co', 'https://www.not-the-fbi.gov/clickme', '2024-11-25 12:11:30'),
    (5, 'Eugenics inc.', 'https://www.eugenics.com/', '2024-11-24 18:20:00');

INSERT INTO PICTURE_AD (Ad_number, Picture, Format) 
VALUES 
    (1, 'https://www.ww.com/pics/437281.jpg', 'JPEG'),
    (2, 'https://www.edison.com/s3xy.png', 'PNG'),
    (3, 'https://www.youvebeenhitby.com/new-release/shamone.bmp', 'BMP');

INSERT INTO GIF_AD (Ad_number, Gif) 
VALUES 
    (4, 'https://www.not-the-fbi.gov/fig.gif');

INSERT INTO VIDEO_AD (Ad_number, Video, Format, Duration) 
VALUES 
    (5, 'https://www.eugenics.com/propaganda.mp4', 'MP4', '00:03:00');

SELECT * FROM [USER];
SELECT * FROM MEME;
SELECT * FROM COMMENT;
SELECT * FROM TAG;
SELECT * FROM ADVERTISER;
SELECT * FROM AD;

-- RELATIONSHIPS

INSERT INTO LIKES_DISLIKES_MEMES (User_id, Meme_id, Is_like) 
VALUES 
    (1, 1, 1),  -- User 1 likes Meme 1
    (2, 2, 0),  -- User 2 dislikes Meme 2
    (2, 1, 1),  -- User 2 likes Meme 1
    (3, 1, 1),  -- User 3 likes Meme 1
    (4, 3, 1),  -- User 4 likes Meme 3
    (5, 4, 0);  -- User 5 dislikes Meme 4

INSERT INTO REPORTS (User_id, Meme_id) 
VALUES 
    (1, 2),
    (2, 2),
    (3, 2);

INSERT INTO DESCRIBES (Tag_name, Meme_id) 
VALUES 
    ('Cuisine', 1),
    ('Cuisine', 2),
    ('Science', 3),
    ('Technology', 4),
    ('Politics', 5);

INSERT INTO DESCRIBES (Tag_name, Meme_id) 
VALUES
    ('Science', 4),
    ('Politics', 3),
    ('Science', 2);

SELECT * FROM DESCRIBES;

INSERT INTO LIKES_DISLIKES_TAGS (User_id, Tag_name, Is_like) 
VALUES 
    (1, 'Cuisine', 1),
    (2, 'Cuisine', 0),
    (3, 'Cuisine', 1),
    (4, 'Science', 1),
    (5, 'Technology', 0);

INSERT INTO RELATES_TO (Ad_id, Tag_id) 
VALUES 
    (1, 'Technology'),
    (2, 'Politics'),
    (3, 'Science');
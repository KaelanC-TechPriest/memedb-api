-- ENTITY TYPES

CREATE TABLE [USER]
(
    User_id INT NOT NULL CONSTRAINT PK_USER_ID PRIMARY KEY,
    Username VARCHAR(30) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    [Password] VARCHAR(150) NOT NULL CONSTRAINT CK_USER_PASSWORD CHECK (LEN(Password) >= 8),
    Join_date DATE NOT NULL,
    Num_posts INT NOT NULL DEFAULT 0,
)


CREATE TABLE MEME
(
    Meme_id INT NOT NULL CONSTRAINT PK_MEME_MEME_ID PRIMARY KEY,
    [Size] INT NOT NULL CONSTRAINT CK_MEME_SIZE CHECK ([Size] > 0),
    Times_reported INT NOT NULL CONSTRAINT DF_MEME_REPTIMES DEFAULT 0,
    Upload_date DATE NOT NULL CONSTRAINT DF_MEME_UPLOADDATE DEFAULT GETDATE(),
    Is_private BIT NOT NULL CONSTRAINT DF_MEME_ISPRIVATE DEFAULT 0,
    Views INT NOT NULL CONSTRAINT DF_MEME_VIEWS DEFAULT 0,
    Likes INT NOT NULL CONSTRAINT DF_MEME_LIKES DEFAULT 0,
    Dislikes INT NOT NULL CONSTRAINT DF_MEME_DISLIKES DEFAULT 0,
    Creator INT NOT NULL CONSTRAINT FK_MEME_CREATOR FOREIGN KEY REFERENCES [USER](User_id) ON DELETE CASCADE
)

CREATE TABLE PICTURE_MEME
(
    Picture_meme_id INT NOT NULL CONSTRAINT PK_PICTURE_MEME_ID PRIMARY KEY CONSTRAINT FK_PICTURE_MEME_ID FOREIGN KEY REFERENCES MEME(Meme_id) ON DELETE CASCADE,
    Base_image VARCHAR(1000) NOT NULL, -- Assuming this is a link to the image
    Text_image VARCHAR(1000) NULL,
    Format VARCHAR(10) NOT NULL CONSTRAINT CK_PICTURE_MEME_FORMAT CHECK (Format IN ('JPEG', 'PNG', 'BMP'))
)

CREATE TABLE GIF_MEME
(
    Gif_meme_id INT NOT NULL CONSTRAINT PK_GIF_MEME_ID PRIMARY KEY CONSTRAINT FK_GIF_MEME_ID FOREIGN KEY REFERENCES MEME(Meme_id) ON DELETE CASCADE,
    Gif VARCHAR(1000) NOT NULL,
    Duration TIME NOT NULL,
)

CREATE TABLE VIDEO_MEME
(
    Video_meme_id INT NOT NULL CONSTRAINT PK_VIDEO_MEME_ID PRIMARY KEY CONSTRAINT FK_VIDEO_MEME_ID FOREIGN KEY REFERENCES MEME(Meme_id) ON DELETE CASCADE,
    Video VARCHAR(1000) NOT NULL,
    Format VARCHAR(10) NOT NULL CONSTRAINT CK_VIDEO_MEME_FORMAT CHECK (Format IN ('MP4', 'WEBM', 'AVI')),
    Duration TIME NOT NULL,
)

CREATE TABLE COMMENT
(
    User_id INT NOT NULL CONSTRAINT FK_COMMENT_USER FOREIGN KEY REFERENCES [USER](User_id) ON DELETE CASCADE,
    Meme_id INT NOT NULL CONSTRAINT FK_COMMENT_MEME FOREIGN KEY REFERENCES MEME(Meme_id),
    Comment_id INT NOT NULL,
    [Text] VARCHAR(MAX) NOT NULL,
    Date_posted DATETIME NOT NULL CONSTRAINT DF_COMMENT_DATEPOSTED DEFAULT GETDATE(),
    CONSTRAINT PK_COMMENT_USER_MEME PRIMARY KEY(User_id, Meme_id, Comment_id)
)

CREATE TABLE TAG
(
    [Name] VARCHAR(30) NOT NULL CONSTRAINT PK_TAG_NAME PRIMARY KEY,
    Times_used INT NOT NULL CONSTRAINT DF_TAG_TIMES_USED DEFAULT 0
)

CREATE TABLE ADVERTISER
(
    [Name] VARCHAR(50) NOT NULL CONSTRAINT PK_ADVERTISER_NAME PRIMARY KEY,
    Num_ads INT NOT NULL CONSTRAINT DF_ADVERTISER_NUM_ADS DEFAULT 0,
    Date_of_payment DATE NOT NULL CONSTRAINT DF_ADVERTISER_DOP DEFAULT GETDATE()
)

CREATE TABLE LT_ADVERTISER
(
    [Name] VARCHAR(50) NOT NULL CONSTRAINT PK_LT_ADVERTISER_NAME PRIMARY KEY CONSTRAINT FK_LT_ADVERTISER_NAME FOREIGN KEY REFERENCES ADVERTISER([Name]) ON DELETE CASCADE,
    Billing_info VARCHAR(MAX) NOT NULL,
    [Address] VARCHAR(255) NOT NULL,
    Date_joined DATE NOT NULL CONSTRAINT DF_DATE_JOINED DEFAULT GETDATE(),
    Billing_period VARCHAR(20) NOT NULL CONSTRAINT DF_BILLING_PERIOD DEFAULT 'Monthly',
)

CREATE TABLE AD
(
    [Number] INT NOT NULL CONSTRAINT PK_AD_NUMBER PRIMARY KEY,
    Publisher VARCHAR(50) NOT NULL CONSTRAINT FK_AD_PUBLISHER FOREIGN KEY REFERENCES ADVERTISER([Name]) ON DELETE CASCADE,
    [Url] VARCHAR(1000) NOT NULL,
    Date_published DATETIME NOT NULL CONSTRAINT DF_AD_DATE_PUBLISHED DEFAULT GETDATE()
)

CREATE TABLE PICTURE_AD
(
    Ad_number INT NOT NULL CONSTRAINT PK_PICTURE_AD_NUMBER PRIMARY KEY CONSTRAINT FK_PICTURE_AD_NUMBER FOREIGN KEY REFERENCES AD([Number]) ON DELETE CASCADE,
    Picture VARCHAR(1000) NOT NULL,
    Format VARCHAR(10) NOT NULL CONSTRAINT CK_PICTURE_AD_FORMAT CHECK (Format IN ('JPEG', 'PNG', 'BMP')),
)

CREATE TABLE GIF_AD
(
    Ad_number INT NOT NULL CONSTRAINT PK_GIF_AD_NUMBER PRIMARY KEY (Ad_number) CONSTRAINT FK_GIF_AD_NUMBER FOREIGN KEY REFERENCES AD([Number]) ON DELETE CASCADE,
    Gif VARCHAR(1000) NOT NULL,
    Format VARCHAR(10) NOT NULL CONSTRAINT CK_GIF_AD_FORMAT CHECK (Format = 'GIF') CONSTRAINT DF_GIF_AD_FORMAT DEFAULT 'GIF',
)

CREATE TABLE VIDEO_AD
(
    Ad_number INT NOT NULL CONSTRAINT PK_VIDEO_AD_NUMBER PRIMARY KEY (Ad_number) CONSTRAINT FK_VIDEO_AD_NUMBER FOREIGN KEY REFERENCES AD([Number]) ON DELETE CASCADE,
    Video VARCHAR(1000) NOT NULL,
    Format VARCHAR(10) NOT NULL CONSTRAINT CK_VIDEO_AD_FORMAT CHECK (Format IN ('MP4', 'WEBM', 'AVI')),
    Duration TIME NOT NULL,
)

-- RELATIONSHIPS

CREATE TABLE LIKES_DISLIKES_MEMES
(
    User_id INT NOT NULL CONSTRAINT FK_LIKE_DISLIKE_USER FOREIGN KEY REFERENCES [USER](User_id),
    Meme_id INT NOT NULL CONSTRAINT FK_LIKE_DISLIKE_MEME FOREIGN KEY REFERENCES MEME(Meme_id) ON DELETE CASCADE,
    Is_like BIT NOT NULL,
    CONSTRAINT PK_LIKES_DISLIKES_MEMES PRIMARY KEY (User_id, Meme_id)
)

CREATE TABLE REPORTS
(
    User_id INT NOT NULL CONSTRAINT FK_REPORTS_USER FOREIGN KEY REFERENCES [USER](User_id),
    Meme_id INT NOT NULL CONSTRAINT FK_REPORTS_MEME FOREIGN KEY REFERENCES MEME(Meme_id) ON DELETE CASCADE,
    CONSTRAINT PK_REPORTS PRIMARY KEY (User_id, Meme_id)
)

CREATE TABLE DESCRIBES
(
    Tag_name VARCHAR(30) NOT NULL CONSTRAINT FK_DESCRIBES_TAG FOREIGN KEY REFERENCES TAG(Name) ON DELETE CASCADE,
    Meme_id INT NOT NULL CONSTRAINT FK_DESCRIBES_MEME FOREIGN KEY REFERENCES MEME(Meme_id),
    CONSTRAINT PK_DESCRIBES PRIMARY KEY (Tag_name, Meme_id)
)

CREATE TABLE LIKES_DISLIKES_TAGS
(
    User_id INT NOT NULL CONSTRAINT FK_LIKE_DISLIKE_TAG_USER FOREIGN KEY REFERENCES [USER](User_id) ON DELETE CASCADE,
    Tag_name VARCHAR(30) NOT NULL CONSTRAINT FK_LIKE_DISLIKE_TAG FOREIGN KEY REFERENCES TAG(Name) ON DELETE CASCADE,
    Is_like BIT NOT NULL,
    CONSTRAINT PK_LIKES_DISLIKES_TAGS PRIMARY KEY (User_id, Tag_name)
)

CREATE TABLE RELATES_TO
(
    Ad_id INT NOT NULL CONSTRAINT FK_RELATES_TO_AD FOREIGN KEY REFERENCES AD(Number) ON DELETE CASCADE,
    Tag_id VARCHAR(30) NOT NULL CONSTRAINT FK_RELATES_TO_TAG FOREIGN KEY REFERENCES TAG(Name) ON DELETE CASCADE,
    CONSTRAINT PK_RELATES_TO PRIMARY KEY (Ad_id, Tag_id)
)
-- Users テーブル
CREATE TABLE Users (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    login_id VARCHAR(255) NOT NULL UNIQUE,
    nickname VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    self_introduction TEXT,
    icon INTEGER,
    address VARCHAR(255),
    birth_year INTEGER,
    gender VARCHAR(255),   -- enums/UserGender
    security_key VARCHAR(255),
    status VARCHAR(255) DEFAULT 'ACTIVE',    -- enums/UserStatus
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted BOOLEAN DEFAULT FALSE
);

-- Admin_users テーブル
CREATE TABLE Admin_users (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    login_id VARCHAR(255) NOT NULL UNIQUE,
    nickname VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Projects テーブル
CREATE TABLE Projects (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    url_key VARCHAR(255) NOT NULL UNIQUE,
    introduction TEXT,
    main_img_url VARCHAR(500),
    qr_img_url VARCHAR(500),
    theme_color VARCHAR(255),   -- enums/ThemeColor
    status VARCHAR(255),    -- enums/ProjectStatus
    enable_visible_book_title BOOLEAN DEFAULT FALSE,
    enable_visible_review_title BOOLEAN DEFAULT FALSE,
    enable_visible_user_info BOOLEAN DEFAULT FALSE,
    published BOOLEAN DEFAULT FALSE,
    project_start_at TIMESTAMP,
    project_end_at TIMESTAMP,
    submission_start_at TIMESTAMP,
    submission_end_at TIMESTAMP,
    voting_start_at TIMESTAMP,
    voting_end_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted BOOLEAN DEFAULT FALSE
);

-- Genres テーブル
CREATE TABLE Genres (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted BOOLEAN DEFAULT FALSE
);

-- Reviews テーブル
CREATE TABLE Reviews (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    project_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    user_nickname VARCHAR(255),
    user_address VARCHAR(255),
    user_age_group INTEGER,
    user_gender VARCHAR(255),   -- enums/UserGender
    user_self_introduction TEXT,
    book_isbn BIGINT,
    book_title VARCHAR(500) NOT NULL,
    book_publisher VARCHAR(255),
    book_author VARCHAR(255),
    review_title VARCHAR(50) NOT NULL,
    review_content TEXT NOT NULL,
    review_content_edited TEXT,
    vote_count INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (project_id) REFERENCES Projects(id),
    FOREIGN KEY (user_id) REFERENCES Users(id)
);

-- Nominated_reviews テーブル
CREATE TABLE Nominated_reviews (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    project_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    user_nickname VARCHAR(255),
    user_address VARCHAR(255),
    user_age_group INTEGER,
    user_gender INTEGER,
    user_self_introduction TEXT,
    review_id INTEGER NOT NULL,
    review_title VARCHAR(50),
    review_content TEXT NOT NULL,
    review_content_edited TEXT,
    review_vote_count INTEGER,
    review_awarded BOOLEAN DEFAULT FALSE,
    book_isbn BIGINT,
    book_title VARCHAR(500),
    book_publisher VARCHAR(255),
    book_author VARCHAR(255),
    admin_comments TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (project_id) REFERENCES Projects(id),
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (review_id) REFERENCES Reviews(id)
);

-- PDF_urls テーブル
CREATE TABLE PDF_urls (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    project_id INTEGER NOT NULL,
    url VARCHAR(500) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (project_id) REFERENCES Projects(id)
);

-- Notifications テーブル
CREATE TABLE Notifications (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    admin_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    review_id INTEGER,
    title VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    seen BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (admin_id) REFERENCES Admin_users(id),
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (review_id) REFERENCES Reviews(id)
);

-- Notification_templates テーブル
CREATE TABLE Notification_templates (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    title VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted BOOLEAN DEFAULT FALSE
);

-- Posts テーブル
CREATE TABLE Posts (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    admin_id INTEGER NOT NULL,
    category VARCHAR(255) NOT NULL, -- enums/PostCategory
    title VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    posted_at TIMESTAMP,
    status VARCHAR(255) NOT NULL,   -- enums/PostStatus
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (admin_id) REFERENCES Admin_users(id)
);

-- Review_genres テーブル (中間テーブル)
CREATE TABLE Review_genres (
    review_id INTEGER NOT NULL,
    genre_id INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (review_id, genre_id),
    FOREIGN KEY (review_id) REFERENCES Reviews(id),
    FOREIGN KEY (genre_id) REFERENCES Genres(id)
);

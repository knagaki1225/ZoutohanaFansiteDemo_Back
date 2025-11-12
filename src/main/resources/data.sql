INSERT INTO genres (name) VALUES ('ミステリー');

INSERT INTO Projects
    (name, url_key, introduction, logo_img_url, theme_color, status, is_visible_book_title, is_visible_review_title, is_visible_user_info, is_public, start_at, end_at, submission_start_at, submission_end_at, voting_start_at, voting_end_at)
    VALUES (
             '2025年春の読書感想文コンテスト',
             'spring-reading-2025',
             '春をテーマにした読書感想文を募集します。あなたのお気に入りの本について自由に語ってください。',
             null,
             1,
             1,
             TRUE,
             TRUE,
             TRUE,
             TRUE,
             '2025-02-01 00:00:00',
             '2025-06-30 23:59:59',
             '2025-03-01 00:00:00',
             '2025-04-30 23:59:59',
             '2025-05-01 00:00:00',
             '2025-05-31 23:59:59'
            ),
            (
            '夏休み特別企画',
            'summer-special-2025',
            '夏休みに読んだ本の感想を共有しましょう。詳細は近日公開予定です。',
            null,
            2,
            1,
            FALSE,
            FALSE,
            FALSE,
            TRUE,
            '2025-07-01 00:00:00',
            '2025-09-30 23:59:59',
            '2025-07-15 00:00:00',
            '2025-08-31 23:59:59',
            '2025-09-01 00:00:00',
            '2025-09-15 23:59:59'
            ),
            (
            '2024年冬の読書マラソン',
            'winter-reading-2024',
            '冬に読んだ本の中から印象的だった作品をご紹介いただきました。たくさんのご参加ありがとうございました！',
            'https://example.com/logos/winter-marathon.png',
            3, -- #6699FF (薄い青色)
            3,
            TRUE,
            TRUE,
            TRUE,
            TRUE,
            '2025-11-01 00:00:00',
            '2026-01-31 23:59:59',
            '2025-02-01 00:00:00',
            '2025-02-28 23:59:59',
            '2025-02-01 00:00:00',
            '2025-02-28 23:59:59'
            );
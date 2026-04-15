-- テストユーザー
INSERT INTO users (id, email, password_hash, provider, name)
VALUES (
           '11111111-1111-1111-1111-111111111111',
           'test@example.com',
           'dummy_hash',
           'LOCAL',
           'テストユーザー'
       ) ON CONFLICT (id) DO NOTHING;

-- テスト用支出データ
INSERT INTO expenses (id, user_id, amount, expense_date, category_id, description, payment_method, memo)
SELECT
    gen_random_uuid(),
    '11111111-1111-1111-1111-111111111111',
    amount,
    expense_date,
    category_id,
    description,
    payment_method,
    memo
FROM (
         VALUES
             (1500.00, '2026-04-01'::date, (SELECT id FROM categories WHERE name = '食費' AND is_default = true LIMIT 1), 'ランチ', 'CASH', '同僚と'),
     (3200.00, '2026-04-02'::date, (SELECT id FROM categories WHERE name = '交通費' AND is_default = true LIMIT 1), '電車定期', 'CREDIT_CARD', '4月分'),
     (850.00, '2026-04-03'::date, (SELECT id FROM categories WHERE name = '食費' AND is_default = true LIMIT 1), 'コンビニ', 'CASH', NULL),
     (12000.00, '2026-04-05'::date, (SELECT id FROM categories WHERE name = '光熱費' AND is_default = true LIMIT 1), '電気代', 'CREDIT_CARD', '3月分'),
     (5500.00, '2026-04-07'::date, (SELECT id FROM categories WHERE name = '娯楽費' AND is_default = true LIMIT 1), '映画', 'CASH', '友人と'),
     (2300.00, '2026-04-10'::date, (SELECT id FROM categories WHERE name = '日用品' AND is_default = true LIMIT 1), 'ドラッグストア', 'CREDIT_CARD', 'シャンプー等'),
     (980.00, '2026-04-12'::date, (SELECT id FROM categories WHERE name = '食費' AND is_default = true LIMIT 1), 'カフェ', 'CASH', '作業用'),
     (45000.00, '2026-04-01'::date, (SELECT id FROM categories WHERE name = '住居費' AND is_default = true LIMIT 1), '家賃', 'CREDIT_CARD', '4月分')
    ) AS t(amount, expense_date, category_id, description, payment_method, memo)
WHERE category_id IS NOT NULL;

-- テスト用収入データ
INSERT INTO incomes (id, user_id, amount, income_date, category_id, description, memo)
SELECT
    gen_random_uuid(),
    '11111111-1111-1111-1111-111111111111',
    amount,
    income_date,
    category_id,
    description,
    memo
FROM (
         VALUES
             (280000.00, '2026-04-25'::date, (SELECT id FROM categories WHERE name = '給与' AND is_default = true LIMIT 1), '4月給与', '手取り'),
     (15000.00, '2026-04-15'::date, (SELECT id FROM categories WHERE name = '副業' AND is_default = true LIMIT 1), 'フリーランス案件', 'Webサイト制作')
    ) AS t(amount, income_date, category_id, description, memo)
WHERE category_id IS NOT NULL;
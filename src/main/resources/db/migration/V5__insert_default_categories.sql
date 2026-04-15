-- デフォルトカテゴリを挿入
-- user_id が NULL のカテゴリは全ユーザー共通のデフォルトカテゴリ

-- 支出カテゴリ
INSERT INTO categories (id, user_id, name, type, color, sort_order, is_default) VALUES
                                                                                    (gen_random_uuid(), NULL, '食費', 'EXPENSE', '#FF6B6B', 1, TRUE),
                                                                                    (gen_random_uuid(), NULL, '交通費', 'EXPENSE', '#4ECDC4', 2, TRUE),
                                                                                    (gen_random_uuid(), NULL, '住居費', 'EXPENSE', '#45B7D1', 3, TRUE),
                                                                                    (gen_random_uuid(), NULL, '光熱費', 'EXPENSE', '#96CEB4', 4, TRUE),
                                                                                    (gen_random_uuid(), NULL, '通信費', 'EXPENSE', '#FFEAA7', 5, TRUE),
                                                                                    (gen_random_uuid(), NULL, '医療費', 'EXPENSE', '#DDA0DD', 6, TRUE),
                                                                                    (gen_random_uuid(), NULL, '娯楽費', 'EXPENSE', '#98D8C8', 7, TRUE),
                                                                                    (gen_random_uuid(), NULL, '衣服費', 'EXPENSE', '#F7DC6F', 8, TRUE),
                                                                                    (gen_random_uuid(), NULL, '日用品', 'EXPENSE', '#BB8FCE', 9, TRUE),
                                                                                    (gen_random_uuid(), NULL, 'その他', 'EXPENSE', '#AEB6BF', 10, TRUE);

-- 収入カテゴリ
INSERT INTO categories (id, user_id, name, type, color, sort_order, is_default) VALUES
                                                                                    (gen_random_uuid(), NULL, '給与', 'INCOME', '#2ECC71', 1, TRUE),
                                                                                    (gen_random_uuid(), NULL, '賞与', 'INCOME', '#27AE60', 2, TRUE),
                                                                                    (gen_random_uuid(), NULL, '副業', 'INCOME', '#1ABC9C', 3, TRUE),
                                                                                    (gen_random_uuid(), NULL, '投資', 'INCOME', '#3498DB', 4, TRUE),
                                                                                    (gen_random_uuid(), NULL, 'その他', 'INCOME', '#95A5A6', 5, TRUE);
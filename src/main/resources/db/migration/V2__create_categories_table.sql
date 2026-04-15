-- カテゴリテーブル
CREATE TABLE categories (
                            id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                            user_id UUID REFERENCES users(id) ON DELETE CASCADE,
                            name VARCHAR(100) NOT NULL,
                            type VARCHAR(20) NOT NULL,  -- 'EXPENSE' or 'INCOME'
                            color VARCHAR(7),  -- HEXカラーコード（例: #FF5733）
                            sort_order INTEGER NOT NULL DEFAULT 0,
                            is_default BOOLEAN NOT NULL DEFAULT FALSE,
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- ユーザーごとに同じtype内で名前の重複を防ぐ
                            CONSTRAINT uk_categories_user_name_type UNIQUE (user_id, name, type),
    -- typeの値を制限
                            CONSTRAINT chk_categories_type CHECK (type IN ('EXPENSE', 'INCOME'))
);

CREATE INDEX idx_categories_user_id ON categories(user_id);

COMMENT ON TABLE categories IS 'カテゴリ情報';
COMMENT ON COLUMN categories.type IS 'カテゴリ種別（EXPENSE: 支出 / INCOME: 収入）';
COMMENT ON COLUMN categories.is_default IS 'デフォルトカテゴリフラグ';
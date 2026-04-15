-- ユーザーテーブル
CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password_hash VARCHAR(255),  -- Google認証の場合はNULL
                       provider VARCHAR(50) NOT NULL DEFAULT 'LOCAL',  -- 'LOCAL' or 'GOOGLE'
                       provider_id VARCHAR(255),  -- Google認証時のID
                       name VARCHAR(100),
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- インデックス
CREATE INDEX idx_users_email ON users(email);

-- コメント
COMMENT ON TABLE users IS 'ユーザー情報';
COMMENT ON COLUMN users.provider IS '認証プロバイダー（LOCAL/GOOGLE）';
COMMENT ON COLUMN users.provider_id IS '外部認証プロバイダーのユーザーID';
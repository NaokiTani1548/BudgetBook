-- 支出テーブル
CREATE TABLE expenses (
                          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                          user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                          category_id UUID REFERENCES categories(id) ON DELETE SET NULL,
                          amount DECIMAL(12, 2) NOT NULL,
                          description VARCHAR(500),
                          expense_date DATE NOT NULL,
                          payment_method VARCHAR(50),  -- 'CASH', 'CREDIT_CARD'
                          memo TEXT,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- 金額は0より大きい
                          CONSTRAINT chk_expenses_amount CHECK (amount > 0),
    -- 支払い方法の値を制限
                          CONSTRAINT chk_expenses_payment_method CHECK (
                              payment_method IS NULL OR payment_method IN ('CASH', 'CREDIT_CARD')
                              )
);

-- 検索用インデックス
CREATE INDEX idx_expenses_user_date ON expenses(user_id, expense_date);

COMMENT ON TABLE expenses IS '支出情報';
COMMENT ON COLUMN expenses.payment_method IS '支払い方法（CASH: 現金 / CREDIT_CARD: クレジットカード）';
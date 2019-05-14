-- Scripts
INSERT INTO finance_tracker_hibernate.categories (name, type, user_id)
VALUES ('Transfer','TRANSFER', null);
INSERT INTO finance_tracker_hibernate.categories (name, type, user_id)
VALUES ('Deposit','DEPOSIT', null);

INSERT INTO finance_tracker_hibernate.currencies (currency_id, language, region)
VALUES ('BGN', 'bg', 'BG'), ('CAD', 'en', 'CA'), ('EUR', 'de', 'DE'), ('GBP', 'en', 'GB'), ('JPY', 'ja', 'JP'), ('USD', 'en', 'US');
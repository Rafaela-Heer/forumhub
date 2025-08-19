CREATE TABLE topics (
  id           BIGINT PRIMARY KEY AUTO_INCREMENT,
  title        VARCHAR(160) NOT NULL,
  message      TEXT NOT NULL,
  status       VARCHAR(20) NOT NULL DEFAULT 'ABERTO',
  course       VARCHAR(120) NOT NULL,
  author_id    BIGINT NOT NULL,
  created_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at   TIMESTAMP NULL,

  message_hash CHAR(64) AS (SHA2(message, 256)) STORED,

  UNIQUE KEY uq_topic_title_message (title, message_hash)
);

CREATE TABLE `schedules`
(
    `id`         BIGINT      NOT NULL AUTO_INCREMENT COMMENT '일정 고유 ID (PK)',
    `user_id`    BIGINT      NOT NULL COMMENT '작성 유저 ID (FK)',
    `title`      VARCHAR(20) NOT NULL COMMENT '할 일 제목',
    `contents`   TEXT        NULL COMMENT '할 일 내용',
    `created_at` TIMESTAMP   NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일',
    `updated_at` TIMESTAMP   NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES users (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
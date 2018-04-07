CREATE TABLE `Resource` (
  `resource_id` VARCHAR(36) NOT NULL,
  `user_id` VARCHAR(36) NOT NULL,
  `name` VARCHAR NOT NULL,
  `http_method` VARCHAR(7) NOT NULL,
  `url` VARCHAR NOT NULL,
  `headers` VARCHAR NOT NULL,
  `body` VARCHAR NOT NULL,
  PRIMARY KEY (`resource_id`),
  INDEX(user_id),
  FOREIGN KEY(user_id) REFERENCES User (user_id)
)

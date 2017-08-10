-- -----------------------------------------------------
-- Table `mydb`.`t_user_capital`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`t_user_capital` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '资金ID',
  `user_id` VARCHAR(50) NULL COMMENT '用户ID',
  `amount` DECIMAL NULL COMMENT '金额',
  `status` VARCHAR(10) NULL COMMENT '账户状态',
  `create_time` MEDIUMTEXT NULL,
  `update_time` MEDIUMTEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`t_user_capital_billing`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`t_user_capital_billing` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(50) NULL COMMENT '用户ID',
  `amount` DECIMAL NULL COMMENT '金额',
  `balance` DECIMAL NULL COMMENT '余额',
  `operate` VARCHAR(5) NULL COMMENT '加/减',
  `purpose` VARCHAR(5) NULL COMMENT '目的',
  `correlation` VARCHAR(50) NULL COMMENT '关联',
  `description` VARCHAR(100) NULL COMMENT '描述',
  `del_tag` CHAR(1) NULL DEFAULT '0',
  `createTime` MEDIUMTEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
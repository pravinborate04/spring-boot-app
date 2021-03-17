CREATE TABLE product (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    amount BIGINT,
    quantity BIGINT,
    brand VARCHAR(255),
    is_active TINYINT(1) NULL DEFAULT 0,
    created_by BIGINT,
    created_date DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    deleted TINYINT(1) NULL DEFAULT 0,
    modified_by BIGINT,
    modified_date DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    version INT NULL DEFAULT 1,
    PRIMARY KEY (id)
);


CREATE TABLE document (
    id BIGINT NOT NULL AUTO_INCREMENT,
    document_url VARCHAR(500),
    product_id BIGINT,
    PRIMARY KEY (id)
);


CREATE TABLE `user` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `firstname` VARCHAR(255) NULL,
    `lastname` VARCHAR(255) NULL,
    `email` VARCHAR(255) NULL,
    `password` VARCHAR(255) NULL,
    `active` TINYINT NULL,
    `role` VARCHAR(255) NULL,
     PRIMARY KEY (`id`)
);

INSERT INTO `user` (`id`, `firstname`, `email`, `password`, `active`, `role`) VALUES ('1', 'User 1 ', 'user1@pim.in', 'password', '1', 'USER');
INSERT INTO `user` (`id`, `firstname`, `email`, `password`, `active`, `role`) VALUES ('2', 'User 2', 'user2@pim.in', 'password', '1', 'USER');
INSERT INTO `user` (`id`, `firstname`, `email`, `password`, `active`, `role`) VALUES ('3', 'Admin', 'admin@pim.in', 'password', '1', 'ADMIN');

INSERT INTO `product` (`id`, `name`, `amount`, `quantity`, `brand`, `is_active`, `created_by`, `created_date`, `deleted`, `modified_by`, `modified_date`, `version`) VALUES ('1', 'Apple MacBook Pro', '122720', '12', 'Apple', '1', '1', now(), '0', '1', now(), '1');
INSERT INTO `product` (`id`, `name`, `amount`, `quantity`, `brand`, `is_active`, `created_by`, `created_date`, `deleted`, `modified_by`, `modified_date`, `version`) VALUES ('2', 'Samsung Smart Watch', '15000', '3', 'Samsung', '1', '1', now(), '0', '1', now(), '1');
INSERT INTO `product` (`id`, `name`, `amount`, `quantity`, `brand`, `is_active`, `created_by`, `created_date`, `deleted`, `modified_by`, `modified_date`, `version`) VALUES ('3', 'One Plus 6', '35000', '32', 'One Plus', '1', '1', now(), '0', '1', now(), '1');
INSERT INTO `product` (`id`, `name`, `amount`, `quantity`, `brand`, `is_active`, `created_by`, `created_date`, `deleted`, `modified_by`, `modified_date`, `version`) VALUES ('4', 'One Plus 6T', '42000', '12', 'One Plus', '1', '1', now(), '0', '1', now(), '1');
INSERT INTO `product` (`id`, `name`, `amount`, `quantity`, `brand`, `is_active`, `created_by`, `created_date`, `deleted`, `modified_by`, `modified_date`, `version`) VALUES ('5', 'Samsung Bravia Smart TV', '46000', '12', 'Samsung', '1', '1', now(), '0', '1', now(), '1');
INSERT INTO `product` (`id`, `name`, `amount`, `quantity`, `brand`, `is_active`, `created_by`, `created_date`, `deleted`, `modified_by`, `modified_date`, `version`) VALUES ('6', 'Sony WalkMan', '15000', '38', 'Sony', '1', '1', now(), '0', '1', now(), '1');

INSERT INTO `pim`.`document` (`id`,`document_url`, `product_id`) VALUES ('1','https://picsum.photos/id/0/5616/3744', '1');
INSERT INTO `pim`.`document` (`id`,`document_url`, `product_id`) VALUES ('2','https://picsum.photos/id/1/5616/3744', '1');
INSERT INTO `pim`.`document` (`id`,`document_url`, `product_id`) VALUES ('3','https://picsum.photos/id/10/2500/1667', '2');
INSERT INTO `pim`.`document` (`id`,`document_url`, `product_id`) VALUES ('4','https://picsum.photos/id/100/2500/1656', '2');
INSERT INTO `pim`.`document` (`id`,`document_url`, `product_id`) VALUES ('5','https://picsum.photos/id/1000/5626/3635', '3');
INSERT INTO `pim`.`document` (`id`,`document_url`, `product_id`) VALUES ('6','https://picsum.photos/id/1001/5616/3744', '4');
INSERT INTO `pim`.`document` (`id`,`document_url`, `product_id`) VALUES ('7','https://picsum.photos/id/1002/4312/2868', '5');
INSERT INTO `pim`.`document` (`id`,`document_url`, `product_id`) VALUES ('8','https://picsum.photos/id/1003/1181/1772', '5');
INSERT INTO `pim`.`document` (`id`,`document_url`, `product_id`) VALUES ('9','https://picsum.photos/id/1005/5760/3840', '6');
INSERT INTO `pim`.`document` (`id`,`document_url`, `product_id`) VALUES ('10','https://picsum.photos/id/1006/3000/2000', '7');
INSERT INTO `pim`.`document` (`id`,`document_url`, `product_id`) VALUES ('11','https://picsum.photos/id/1008/5616/3744', '7');
INSERT INTO `pim`.`document` (`id`,`document_url`, `product_id`) VALUES ('12','https://picsum.photos/id/1009/5000/7502', '7');
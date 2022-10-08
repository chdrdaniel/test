ALTER TABLE li_receipt ADD company_name varchar ( 100 ) DEFAULT NULL COMMENT '公司名称 如果是个人则是个人名称';
ALTER TABLE li_receipt ADD receipt_type varchar ( 100 ) DEFAULT NULL COMMENT '发票类型';
ALTER TABLE li_receipt ADD register_address varchar ( 255 ) DEFAULT NULL COMMENT '注册地址';
ALTER TABLE li_receipt ADD register_mobile varchar ( 255 ) DEFAULT NULL COMMENT '注册电话';
ALTER TABLE li_receipt ADD bank_name varchar ( 255 ) DEFAULT NULL COMMENT '开户银行';
ALTER TABLE li_receipt ADD bank_account varchar ( 255 ) DEFAULT NULL COMMENT '银行账户';
ALTER TABLE li_receipt ADD receiver_receipt_name varchar ( 100 ) DEFAULT NULL COMMENT '收票人姓名';
ALTER TABLE li_receipt ADD receiver_receipt_email varchar ( 50 ) DEFAULT NULL COMMENT '收票人邮箱';
ALTER TABLE li_receipt ADD receiver_receipt_mobile varchar ( 50 ) DEFAULT NULL COMMENT '收票人电话';
ALTER TABLE li_receipt ADD receiver_receipt_address_path varchar ( 255 ) DEFAULT NULL COMMENT '地址名称,分割';
ALTER TABLE li_receipt ADD receiver_receipt_address_id_path varchar ( 255 ) DEFAULT NULL COMMENT '地址id,分割';
ALTER TABLE li_receipt ADD receiver_receipt_address varchar ( 255 ) DEFAULT NULL COMMENT '收票人详细地址';



ALTER TABLE li_receipt ADD receipt_file text DEFAULT NULL COMMENT '发票附件';
ALTER TABLE li_receipt ADD logistics_no varchar ( 255 ) DEFAULT NULL COMMENT '发货单号';
ALTER TABLE li_receipt ADD logistics_id varchar ( 255 ) DEFAULT NULL COMMENT '物流公司ID';
ALTER TABLE li_receipt ADD logistics_name varchar ( 255 ) DEFAULT NULL COMMENT '物流公司名称';






ALTER TABLE li_store_detail ADD electronic_status varchar ( 255 ) DEFAULT 'CLOSE' COMMENT '电子发票状态';
ALTER TABLE li_store_detail ADD vat_special_status varchar ( 255 ) DEFAULT 'CLOSE' COMMENT '增值税专用发票状态';
ALTER TABLE li_store_detail ADD vat_status varchar ( 255 ) DEFAULT 'CLOSE' COMMENT '增值税普通发票状态';



INSERT INTO `li_setting` VALUES ('RECEIPT_SETTING', 'admin', '2022-08-14 15:12:23.132000', b'0', NULL, NULL, '{\"electronicStatus\":\"CLOSE\",\"vatSpecialStatus\":\"CLOSE\",\"vatStatus\":\"CLOSE\"}');





ALTER TABLE li_member_receipt ADD company_name varchar ( 100 ) DEFAULT NULL COMMENT '公司名称 如果是个人则是个人名称';
ALTER TABLE li_member_receipt ADD register_address varchar ( 255 ) DEFAULT NULL COMMENT '注册地址';
ALTER TABLE li_member_receipt ADD register_mobile varchar ( 255 ) DEFAULT NULL COMMENT '注册电话';
ALTER TABLE li_member_receipt ADD bank_name varchar ( 255 ) DEFAULT NULL COMMENT '开户银行';
ALTER TABLE li_member_receipt ADD bank_account varchar ( 255 ) DEFAULT NULL COMMENT '银行账户';
ALTER TABLE li_member_receipt ADD receiver_receipt_name varchar ( 100 ) DEFAULT NULL COMMENT '收票人姓名';
ALTER TABLE li_member_receipt ADD receiver_receipt_email varchar ( 50 ) DEFAULT NULL COMMENT '收票人邮箱';
ALTER TABLE li_member_receipt ADD receiver_receipt_mobile varchar ( 50 ) DEFAULT NULL COMMENT '收票人电话';
ALTER TABLE li_member_receipt ADD receiver_receipt_address_path varchar ( 255 ) DEFAULT NULL COMMENT '地址名称,分割';
ALTER TABLE li_member_receipt ADD receiver_receipt_address_id_path varchar ( 255 ) DEFAULT NULL COMMENT '地址id,分割';
ALTER TABLE li_member_receipt ADD receiver_receipt_address varchar ( 255 ) DEFAULT NULL COMMENT '收票人详细地址';


ALTER TABLE li_receipt ADD receipt_source varchar ( 255 ) DEFAULT NULL COMMENT '发票开具方';


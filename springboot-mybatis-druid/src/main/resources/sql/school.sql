
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 创建student表
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `parent_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家长电话号码逗号分隔',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '居住城市json格式存储{\"province\":\"广东省\",\"city\":\"深圳市\",\"district\":\"南山区\"}',
  `net_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网名逗号分隔',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- 插入学生信息
-- ----------------------------
INSERT INTO `student` VALUES (1, '小英', '13222222222,13333333333,15777777777', '{\"province\":\"广东省\",\"city\":\"深圳市\",\"district\":\"南山区\"}', '会飞的鱼,小小雪,茉莉香果果');
INSERT INTO `student` VALUES (2, '小明', '13222222222,13333333333,15777777777', '{\"province\":\"广东省\",\"city\":\"深圳市\",\"district\":\"南山区\"}', '会飞的鱼2,小小雪2,茉莉香果果2');

SET FOREIGN_KEY_CHECKS = 1;

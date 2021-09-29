CREATE TABLE IF NOT EXISTS `sys_face` (
  `faceId` varchar(50) NOT NULL COMMENT '人脸id',
  `faceName` varchar(50) DEFAULT NULL COMMENT '人脸名称',
  `facePath` varchar(50) DEFAULT NULL COMMENT '人脸路径',
  `gender` INT  DEFAULT NULL COMMENT '性别',
  `gmtCreate` TIMESTAMP   NULL COMMENT '创建时间',
  PRIMARY KEY (`faceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
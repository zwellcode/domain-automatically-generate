package cn.zwellcode.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author HM
 */
@Getter
@Setter
@ToString
public class DomainVO {
    private String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String user = "root";
    private String password = "zhouziwei";
    private String packageName;
    private String path;
    private String tableName;
}

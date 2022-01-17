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
    private String url;
    private String driver;
    private String user;
    private String password;
    private String packageName;
    private String path;
    private String tableName;
}

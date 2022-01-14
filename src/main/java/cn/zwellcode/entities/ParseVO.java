package cn.zwellcode.entities;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 参数
 */
@Setter
@Getter
public class ParseVO {
    public static final int TYPE_YES = 1;
    public static final int TYPE_NO = 2;
    private String[] domains; //实体类
    private String[] comments; //注释



    private String domain; //实体类
    private String basePackage;//基本包
    private String comment; //注释
    private String outputPath; //输出地址
    private int type = TYPE_YES;  //是否分包 1：分包， 2：不分
}

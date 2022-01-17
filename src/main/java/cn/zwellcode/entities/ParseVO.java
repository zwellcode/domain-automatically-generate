package cn.zwellcode.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 参数
 * @author HM
 */
@Setter
@Getter
@ToString
public class ParseVO {
    public static final int TYPE_YES = 1;
    public static final int TYPE_NO = 2;

    /**
     * 实体类
     */
    private String[] domains;

    /**
     * 注释
     */
    private String[] comments;

    /**
     * 实体类
     */
    private String domain;

    /**
     * 基本包
     */
    private String basePackage;

    /**
     * 注释
     */
    private String comment;

    /**
     * 输出地址
     */
    private String outputPath;

    /**
     * 是否分包 1：分包， 2：不分
     */
    private Integer type;
}

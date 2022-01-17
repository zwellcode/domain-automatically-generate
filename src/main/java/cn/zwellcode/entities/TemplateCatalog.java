package cn.zwellcode.entities;

import lombok.Getter;

/**
 * @author HM
 */

@Getter
public enum TemplateCatalog {
    //qo
    QUERY_OBJECT("BaseQuery.ftl"),
    //mapper
    REPOSITORY("BaseMapper.ftl"),
    //service接口
    SERVICE("BaseIService.ftl"),
    //service接口实现类
    SERVICE_IMPL("BaseServiceImpl.ftl"),
    //controller
    CONTROLLER("BaseController.ftl"),
    //controller
    DOMAIN("BaseDomain.ftl");

    private static final String SP = "/";
    private String template;
    private String name;

    private TemplateCatalog(String template) {
        this.template = template;
    }

    public String outputPath(ParseVO vo) {
        StringBuilder sb = new StringBuilder();
        StringBuilder nameSb = new StringBuilder();
        switch (this) {
            case QUERY_OBJECT:
                this.name = nameSb.append(vo.getDomain()).append("Query.java").toString();
                sb.append("query").append(SP).append(this.name);
                break;
            case REPOSITORY:
                this.name = nameSb.append(vo.getDomain()).append("Mapper.java").toString();
                sb.append("mapper").append(SP).append(this.name);
                break;
            case SERVICE:
                this.name = nameSb.append("I").append(vo.getDomain()).append("Service.java").toString();
                sb.append("service").append(SP).append(this.name);
                break;
            case SERVICE_IMPL:
                this.name = nameSb.append(vo.getDomain()).append("ServiceImpl.java").toString();
                sb.append("service").append(SP).append("impl").append(SP).append(this.name);
                break;
            //case CONTROLLER:
            default:
                this.name = nameSb.append(vo.getDomain()).append("Controller.java").toString();
                sb.append("controller").append(SP).append(this.name);
                break;
        }
        //拼接模板分类名： 比如 UserInfoQuery.java
        if (vo.getType() == ParseVO.TYPE_NO) {
            sb.insert(0, vo.getOutputPath());
        } else {
            String path = vo.getBasePackage().replaceAll("\\.", SP) + SP;
            sb.insert(0, path).insert(0, vo.getOutputPath());
        }
        return sb.toString();
    }

}

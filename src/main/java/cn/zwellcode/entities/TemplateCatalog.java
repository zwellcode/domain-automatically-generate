package cn.zwellcode.entities;

import lombok.Getter;

@Getter
public enum TemplateCatalog {

    QUERYOBJECT("BaseQuery.ftl"),  //qo
    REPOSITORY("BaseMapper.ftl"), //mapper
    ISERVICE("BaseIService.ftl"),  //service接口
    SERVICEIMPL("BaseServiceImpl.ftl"), //service接口实现类
    CONTROLLER("BaseController.ftl"), //controller
    DOMAIN("BaseDomain.ftl"); //controller

    private static final String sp = "/";
    private String template;
    private String name;

    private TemplateCatalog(String template){
        this.template = template;
    }

    public String outputPath(ParseVO vo){
        StringBuilder sb = new StringBuilder();
        StringBuilder nameSB = new StringBuilder();
        switch (this){
            case QUERYOBJECT:
                this.name =nameSB.append(vo.getDomain()).append("Query.java").toString();
                sb.append("query").append(sp).append(this.name);
                break;
            case REPOSITORY:
                this.name =nameSB.append(vo.getDomain()).append("Mapper.java").toString();
                sb.append("mapper").append(sp).append(this.name);
                break;
            case ISERVICE:
                this.name =nameSB.append("I").append(vo.getDomain()).append("Service.java").toString();
                sb.append("service").append(sp).append(this.name);
                break;
            case SERVICEIMPL:
                this.name =nameSB.append(vo.getDomain()).append("ServiceImpl.java").toString();
                sb.append("service").append(sp).append("impl").append(sp).append(this.name);
                break;
            case CONTROLLER:
                this.name =nameSB.append(vo.getDomain()).append("Controller.java").toString();
                sb.append("controller").append(sp).append(this.name);
                break;
        }
        //拼接模板分类名： 比如 UserInfoQuery.java
        if(vo.getType() == ParseVO.TYPE_NO){
            sb.insert(0, vo.getOutputPath());
        }else{
            String path = vo.getBasePackage().replaceAll("\\.", sp)+sp;
            sb.insert(0, path).insert(0,vo.getOutputPath());
        }
        return sb.toString();
    }

}

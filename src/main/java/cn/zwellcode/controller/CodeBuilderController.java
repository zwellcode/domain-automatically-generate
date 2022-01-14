package cn.zwellcode.controller;

import cn.zwellcode.DomainGenerator;
import cn.zwellcode.entities.DomainVO;
import cn.zwellcode.entities.ParseVO;
import cn.zwellcode.entities.Result;
import cn.zwellcode.util.AssertUtil;
import cn.zwellcode.util.CodeBuilder;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
@RequestMapping("/code")
public class CodeBuilderController {
    @RequestMapping("/codebuilder")
    @ResponseBody
    public Object codebuilder(ParseVO vo){
        File file = new File(vo.getOutputPath());
        if(!file.exists()){
            return Result.error("模板输出路径："+vo.getOutputPath()+"不存在~");
        }
        if(file.isFile()){
            return Result.error("模板输出路径："+vo.getOutputPath()+"必须是一个目录");
        }
        String[] comments = vo.getComments();
        String[] domains = vo.getDomains();
        ArrayList list = new ArrayList<>();
        AssertUtil.hasLength(vo);
        for (int i = 0; i < comments.length; i++) {
            vo.setComment(comments[i]);
            vo.setDomain(domains[i]);
            list.add(CodeBuilder.builder(vo));
        }
        return Result.success(list);
    }

    @RequestMapping("/domainbuilder")
    @ResponseBody
    public Object codebuilder(DomainVO vo){
        String msg = null;
        try {
            try {
                msg = DomainGenerator.domainBuilder(vo);
            } catch (SQLException e) {
                e.printStackTrace();
                return Result.error("你的数据库配置有问题，请核实");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return Result.error("你的数据库配置有问题，请核实");
            } catch (TemplateException e) {
                e.printStackTrace();
                return Result.error("你的数据库配置有问题，请核实");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("你的存储路径有问题或模板找不到，请不要带中文，请核实");
        }
        return Result.success(msg+"------已贴mp的注解~");
    }

}

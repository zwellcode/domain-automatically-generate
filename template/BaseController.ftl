package ${basePackage}.controller;

import ${basePackage}.domain.${domain};
import ${basePackage}.query.${domain}Query;
import ${basePackage}.service.I${domain}Service;
import ${basePackage}.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
* ${comment}控制层
*/
@Controller
@RequestMapping("${domain?uncap_first}")
public class ${domain}Controller {

    @Autowired
    private I${domain}Service ${domain?uncap_first}Service;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") ${domain}Query qo){
        IPage<${domain}> page = ${domain?uncap_first}Service.queryPage(qo);
        model.addAttribute("page", page);
        return "${domain?uncap_first}/list";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object get(Long id){
        return JsonResult.success(${domain?uncap_first}Service.getById(id));
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(${domain} ${domain?uncap_first}){
        ${domain?uncap_first}Service.saveOrUpdate(${domain?uncap_first});
        return JsonResult.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id){
        ${domain?uncap_first}Service.removeById(id);
        return JsonResult.success();
    }
}

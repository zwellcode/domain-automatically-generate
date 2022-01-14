package ${basePackage}.service;

import ${basePackage}.domain.${domain};
import ${basePackage}.query.${domain}Query;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * ${comment}服务接口
 */
public interface I${domain}Service extends IService<${domain}>{
    /**
    * 分页
    * @param qo
    * @return
    */
    IPage<${domain}> queryPage(${domain}Query qo);
}

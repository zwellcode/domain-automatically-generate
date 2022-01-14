package ${basePackage}.service.impl;

import ${basePackage}.domain.${domain};
import ${basePackage}.mapper.${domain}Mapper;
import ${basePackage}.query.${domain}Query;
import ${basePackage}.service.I${domain}Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* ${comment}服务接口实现
*/
@Service
@Transactional
public class ${domain}ServiceImpl extends ServiceImpl<${domain}Mapper,${domain}> implements I${domain}Service  {

    @Override
    public IPage<${domain}> queryPage(${domain}Query qo) {
        IPage<${domain}> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<${domain}> wrapper = Wrappers.<${domain}>query();
        return super.page(page, wrapper);
    }
}

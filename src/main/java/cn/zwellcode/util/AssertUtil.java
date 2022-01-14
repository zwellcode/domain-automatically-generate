package cn.zwellcode.util;

import cn.zwellcode.entities.ParseVO;
import cn.zwellcode.exception.LogicException;
import org.springframework.util.StringUtils;

public class AssertUtil {
    private AssertUtil(){}
    public static void hasLength(ParseVO vo){
        String[] domains = vo.getDomains();
        String[] comments = vo.getComments();
        if (domains.length==0){
            throw new LogicException("类名不能为空");
        }
        if (comments.length==0){
            throw new LogicException("注释不能为空");
        }
        for (int i = 0; i < domains.length; i++) {
            if (!StringUtils.hasLength(domains[i])){
                throw new LogicException("类名不能为空");
            }
            if (!MatchUtil.isUpper(domains[i])){
                throw new LogicException("类名首字母必须大写或类名不允许有中文");
            }
            if (!StringUtils.hasLength(comments[i])){
                throw new LogicException("注释不能为空");
            }
        }
    }
}

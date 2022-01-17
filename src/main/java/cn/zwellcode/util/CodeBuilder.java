package cn.zwellcode.util;

import cn.zwellcode.entities.ParseVO;
import cn.zwellcode.entities.TemplateCatalog;
import cn.zwellcode.exception.LogicException;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HM
 */
public class CodeBuilder {
    public static List builder(ParseVO vo) {

        List<Map<String, Object>> list = new ArrayList<>();

        //1.创建一个 Configuration 对象， 参数是freemarker 的版本号
        Configuration configuration = new Configuration(Configuration.getVersion());
        //2.设置模板文件所在的路径
        try {
            configuration.setDirectoryForTemplateLoading(new File("template"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new LogicException("获取模板路径失败");
        }
        //3. 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");
        //qo
        list.add(templateParse(configuration, vo, TemplateCatalog.QUERY_OBJECT));

        //repository
        list.add(templateParse(configuration, vo, TemplateCatalog.REPOSITORY));

        //service interface
        list.add(templateParse(configuration, vo, TemplateCatalog.SERVICE));

        //service impl
        list.add(templateParse(configuration, vo, TemplateCatalog.SERVICE_IMPL));

        //controller - mgrsite
        list.add(templateParse(configuration, vo, TemplateCatalog.CONTROLLER));

        return list;
    }

    private static Map<String, Object> templateParse(Configuration configuration, ParseVO vo, TemplateCatalog catalog) {

        Map<String, Object> map = new HashMap<>(16);

        BufferedWriter fileWriter = null;
        String outputPath = catalog.outputPath(vo);
        try {
            //BaseQuery.ftl
            Template template = configuration.getTemplate(catalog.getTemplate());

            File file = new File(outputPath);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();  //创建目录
            }
            if (!file.exists()) {
                file.createNewFile(); //创建文件
            }
            fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
            template.process(vo, fileWriter);

            map.put("name", catalog.getName());

        } catch (Exception e) {
            e.printStackTrace();
            map.put("name", catalog.getName() + "失败");
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException ioE) {
                    ioE.printStackTrace();
                }
            }
        }
        map.put("outputPath", outputPath);
        return map;
    }
}

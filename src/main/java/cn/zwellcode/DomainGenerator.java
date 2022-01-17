package cn.zwellcode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


import cn.zwellcode.entities.DomainVO;
import cn.zwellcode.entities.Table;
import cn.zwellcode.util.ConvertUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * @author HM
 */
public class DomainGenerator {
    static Properties pro = new Properties();
//    static{
//        try {
//            pro.load(DomainGenerator.class.getClassLoader().getResourceAsStream("jdbc.properties"));
//        } catch (IOException e) {
//        System.out.println("加载配置文件失败");
//        }
//    }

    public static void main(String[] args) throws Exception {
        domainBuilder(new DomainVO());
    }

    public static String domainBuilder(DomainVO vo) throws IOException, SQLException, ClassNotFoundException, TemplateException {
        String driver = vo.getDriver();
        String url = vo.getUrl();
        String user = vo.getUser();
        String password = vo.getPassword();
        String packageName = vo.getPackageName();
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, user, password);
        Table table = ConvertUtil.getTables(conn, vo.getTableName());
        return gen(table, packageName, vo.getPath());
    }

    public static String gen(Table table, String packageName, String path) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(new File("template"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Template temp = cfg.getTemplate("BaseDomain.ftl");

        System.out.println(table);
        Map<String, Object> root = new HashMap<String, Object>(16);
        root.put("packageName", packageName);
        root.put("author", "admin");
        root.put("className", table.getTableName());
        root.put("attrs", table.getColumns());
        root.put("trueTableName", table.getTrueTableName());

        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //java文件的生成目录
        OutputStream fos = new FileOutputStream(new File(dir, table.getTableName() + ".java"));
        Writer out = new OutputStreamWriter(fos);
        temp.process(root, out);

        fos.flush();
        fos.close();

        System.out.println("代码生成成功");
        return "代码生成成功,生成路径为:" + dir + "\\" + table.getTableName() + ".java";
    }
}
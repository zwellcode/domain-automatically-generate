package cn.zwellcode.util;

import cn.zwellcode.entities.Attribute;
import cn.zwellcode.entities.Table;
import cn.zwellcode.exception.LogicException;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HM
 */
public class ConvertUtil {
    public static final Map<String,String> columnToJavaType=new HashMap<String,String>();
    static{//mysql类型到java类型的转换
        columnToJavaType.put("VARCHAR", "String");
        columnToJavaType.put("DOUBLE", "Double");
        columnToJavaType.put("INT", "Integer");
        columnToJavaType.put("BIGINT", "Long");
        columnToJavaType.put("TIME", "Date");
        columnToJavaType.put("BIT", "Boolean");
        columnToJavaType.put("DATETIME", "Date");
        columnToJavaType.put("DATE", "Date");
        columnToJavaType.put("TEXT", "String");
        columnToJavaType.put("DECIMAL", "BigDecimal");
    }
    public static Table getTables(Connection con,String tableName111) throws SQLException {
        DatabaseMetaData metaData = con.getMetaData();// 获取数据库信息
        //获取数据库信息
        ResultSet rs = metaData.getTables(null, null, null, new String[] { "TABLE", "VIEW" });
        Table tab=null;
        List<Attribute> columnList=null;

        while (rs.next()) {//遍历表和列
            if (rs.getString(4) != null
                    && (rs.getString(4).equalsIgnoreCase("TABLE") || rs.getString(4).equalsIgnoreCase("VIEW"))) {

                String tableName = rs.getString(3).toLowerCase();
                if (tableName111.equals(tableName)){
                    tab=new Table();
                    tab.setTrueTableName(rs.getString(3));
                    tab.setTableName(toClassCase(tableName));
                    columnList=new ArrayList<Attribute>();
                    ResultSet colRet = metaData.getColumns(null, "%", tableName, "%");//获取列
                    Attribute col=null;
                    int i = 0;
                    while (colRet.next()) {//遍历列
                        col=new Attribute();
                        String columnName = colRet.getString("COLUMN_NAME");
                        if ("id".equals(columnName)){
                            i += 1;
                            if (i>1){break;}
                        }
                        String columnType = colRet.getString("TYPE_NAME");
                        col.setName(toCamelCase(columnName));
                        col.setType(columnToJavaType.get(columnType));
                        columnList.add(col);
                    }
                    tab.setColumns(columnList);
                    break;
                }}
        }
        if (!StringUtils.hasLength(tab.getTableName())){
            throw new LogicException("数据库中没有这个表");
        }
        return tab;
    }

    /**将字符串转换为驼峰命名
     * @param str
     * @return
     */
    public static String toCamelCase(String str){
        if(str==null||!str.contains("_"))return str;
        StringBuffer buf=new StringBuffer();
        char[] charArray = str.toCharArray();
        for(int i=0,len=charArray.length;i<len-1;i++){
            if(charArray[i]=='_'&&(charArray[i+1]>='a'&&charArray[i+1]<'z')){
                charArray[i+1]=(char) (charArray[i+1]-32);
            }
            if(charArray[i]!='_')
                buf.append(charArray[i]);
        }
        buf.append(charArray[charArray.length-1]);
        return buf.toString();
    }
    /**转换类名
     * @param str
     * @return
     */
    public static String toClassCase(String str){
        String camelCase = toCamelCase(str);
        String substring = camelCase.substring(0,1);
        return camelCase.replaceFirst(substring, substring.toUpperCase());
    }

}
package cn.zwellcode.entities;

import lombok.ToString;

import java.util.List;

/**
 *表实体
 */
@ToString
public class Table {
    private String tableName;
    private String trueTableName;

    public String getTrueTableName() {
        return trueTableName;
    }

    public void setTrueTableName(String trueTableName) {
        this.trueTableName = trueTableName;
    }

    private List<Attribute> columns;
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public List<Attribute> getColumns() {
        return columns;
    }
    public void setColumns(List<Attribute> columns) {
        this.columns = columns;
    }



}
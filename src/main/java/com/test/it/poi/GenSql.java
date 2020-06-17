package com.test.it.poi;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 解析excel表格数据，生成对应sql:
 * 表头为key，sql中出现${key}则替换为key对应值
 *
 * 例如：excel:
 * aa bb cc
 * a1 b1 c1
 * a2 b2 c2
 * 对应SQL：update table_a set aa=${aa}, bb=${bb} where cc=${cc};
 * 替换后生成2条sql:
 * update table_a set aa=a1, bb=b1 where cc=c1;
 * update table_a set aa=a2, bb=b2 where cc=c2;
 *
 * @Author: zhenghong.cai
 * @Date: Create in 2020/5/28 10:41
 * @Description:
 */
public class GenSql {

    private static String sql = "update table_a set aa='${aa中文}', bb='${bb}' where cc='${中文}';";

    public static void main(String[] args) throws Exception {
//        if (args == null || args.length < 1 || StringUtils.isBlank(args[0])) {
//            System.out.println("请输入excel文件位置");
//            return;
//        }
//        String exelFile = args[0];
        String exelFile = "/Users/caizh/tmp/0527/table_a.xlsx";

        try (FileInputStream fileInputStream = new FileInputStream(new File(exelFile))) {
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            List<String> head = resolveHeadRow(iterator.next());

            // 表格数据: List<Map<列名，值>>
            List<Map<String, String>> data = Lists.newArrayList();
            while (iterator.hasNext()) {
                data.add(rowDataMap(iterator.next(), head));
            }

            genSqlToFile(data, exelFile.substring(0, exelFile.lastIndexOf(File.separator)));
        }
    }

    private static Map<String, String> rowDataMap(Row row, List<String> head) throws Exception {
        int headLen = head.size();
        Map<String, String> rowMap = Maps.newHashMapWithExpectedSize(headLen);
        for (int j = 0; j < headLen; j++) {
            rowMap.put(head.get(j), getCellValue(row.getCell(j)));
        }
        return rowMap;
    }

    /**
     * 获取表头
     * @param headRow
     * @return
     * @throws Exception
     */
    private static List<String> resolveHeadRow(Row headRow) throws Exception {
        // 列头
        List<String> head = Lists.newArrayList();
        String value = null;
        int i = 0;
        while (StringUtils.isNotBlank(value = getCellValue(headRow.getCell(i)))) {
            head.add(value);
            i++;
        }
        return head;
    }

    private static void genSqlToFile(List<Map<String, String>> data, String dir) throws IOException {
        List<String> lines = Lists.newArrayListWithCapacity(data.size());
        for (Map<String, String> rowMap : data) {
            StrSubstitutor strSubstitutor = new StrSubstitutor(rowMap);
            String genSql = strSubstitutor.replace(sql);
            System.out.println(genSql);
            lines.add(genSql);
        }


        File target = new File(dir + File.separator + "target.sql");
        FileUtils.writeLines(target, lines);
        System.out.println("target file: " + target.getAbsolutePath());
    }


    private static String getCellValue(Cell cell) throws Exception {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue().trim();
            case Cell.CELL_TYPE_NUMERIC:
                return Double.valueOf(cell.getNumericCellValue()).longValue() + "";
            case Cell.CELL_TYPE_ERROR:
            case Cell.CELL_TYPE_BOOLEAN:
            case Cell.CELL_TYPE_FORMULA:
            case Cell.CELL_TYPE_BLANK:
            default:
                throw new Exception("不支持的数据类型");
        }
    }

    private void genSql(Object bean, String baseSql) {
        BeanMap gpsInfoBeanMap = BeanMap.create(bean);
        Map<String, Object> map = Maps.newHashMap();
        for (Object key : gpsInfoBeanMap.keySet()) {
            if (gpsInfoBeanMap.get(key) != null) {
                map.put(toDbColName(key.toString()), valueToString(gpsInfoBeanMap.get(key)));
            }
        }

        StrSubstitutor strSubstitutor = new StrSubstitutor(map);
        System.out.print(strSubstitutor.replace(baseSql));
    }

    private String valueToString(Object value) {
        if (value instanceof Date) {
            return "'" + DateUtils.formatDate((Date) value) + "'";
        } else if (value instanceof String) {
            return "'" + ((String) value).trim() + "'";
        }
        return value.toString().trim();
    }

    /**
     * 大写转下划线加小写
     * @param key
     * @return
     */
    private String toDbColName(String key) {
        char[] keyChar = new char[key.length()];
        key.getChars(0, key.length(), keyChar, 0);
        char[] keyChar2 = new char[key.length() + 20];
        int index = 0;
        for (char c : keyChar) {
            if ('A' <= c && c <= 'Z') {
                keyChar2[index++] = '_';
                keyChar2[index++] = Character.toLowerCase(c);
            } else {
                keyChar2[index++] = c;
            }
        }

        return new String(keyChar2, 0, index);
    }
}

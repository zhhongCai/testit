package com.test.it.mybatis;

import org.apache.ibatis.jdbc.SQL;
import org.junit.Test;

/**
 * @Author: theonecai
 * @Date: Create in 2019-09-07 14:25
 * @Description:
 */
public class SqlTest {

    @Test
    public void testGenSql() {
        System.out.println(new SQL(){{
            SELECT("a.test, a.name");
            SELECT("b.title,b.status");
            FROM("table_a a");
            FROM("table_b b");
            WHERE("a.id = b.a_id");
            OR();
            WHERE("a.name = b.title");
            AND();
            WHERE("a.test = b.desc");
        }}.toString());
        System.out.println(new SQL().SELECT("a.test, a.name")
                .SELECT("b.title,b.status")
                .FROM("table_a a")
                .FROM("table_b b")
                .WHERE("a.id = b.a_id")
                .OR()
                .WHERE("a.name = b.title").toString());
    }
}

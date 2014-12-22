package com.test.it.crawl.test;

import cn.edu.hfut.dmic.webcollector.crawler.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.model.Page;

/**
 * Author: caizh
 * CreateTime: 2014/12/19 11:43
 * Version: 1.0
 */
public class WebCollectorTest extends BreadthCrawler {

    @Override
    public void visit(Page page) {
        System.out.println(page.getUrl());
    }

    public static void main(String[] args) throws Exception {
        WebCollectorTest crawler = new WebCollectorTest();
        crawler.addSeed("http://www.zhihu.com/question/21003086");
        crawler.addRegex("http://www.zhihu.com/.*");
        crawler.start(2);
    }
}

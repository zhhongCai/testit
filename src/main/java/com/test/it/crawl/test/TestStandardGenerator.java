/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test.it.crawl.test;

import cn.edu.hfut.dmic.webcollector.generator.FSInjector;
import cn.edu.hfut.dmic.webcollector.generator.Injector;
import cn.edu.hfut.dmic.webcollector.generator.FSGenerator;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author hu
 */

public class TestStandardGenerator {
    @Test
    public void testGenerator() throws Exception{
        String crawl_path="d:/tmp/webcollector_test";
        ArrayList<String> seeds=new ArrayList<String>();
        seeds.add("http://www.sina.com.cn/");
        seeds.add("http://www.xinhuanet.com/");
        
        Injector injector=new FSInjector(crawl_path);
        injector.inject(seeds);
       
        FSGenerator generator=new FSGenerator(crawl_path);
        CrawlDatum crawldatum=null;
        ArrayList<CrawlDatum> datums=new ArrayList<CrawlDatum>();
        while((crawldatum=generator.next())!=null){
            datums.add(crawldatum);
        }
        
        Assert.assertEquals(seeds.size(),datums.size());
        for(int i=0;i<seeds.size();i++){           
            Assert.assertEquals(-1, datums.get(i).getFetchTime());
            Assert.assertEquals(CrawlDatum.STATUS_DB_UNFETCHED, datums.get(i).getStatus());
            Assert.assertEquals(seeds.get(i), datums.get(i).getUrl());
        }
        
        System.out.println("123123");
    }
    
    
   
}

package org.goaler.springbootdemo.service;

import org.goaler.springbootdemo.dao.db.read.mapper.ReadDemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class Init implements CommandLineRunner {

    @Autowired
    ReadDemoDao readDemoDao;

    @Autowired
    ReadDemoDao writeDemoDao;

    @Override
    public void run(String... args) throws Exception {
        Integer count = readDemoDao.count();
        System.out.println(count);
        Integer count2 = writeDemoDao.count();
        System.out.println(count2);
    }
}

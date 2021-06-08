package com.vueblog;

import com.vueblog.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling   //用于设置定时启动
@SpringBootApplication
public class VueblogApplication implements CommandLineRunner {
    @Autowired
    private CategoryService categoryService;

    public static void main(String[] args) {
        SpringApplication.run(VueblogApplication.class, args);
    }

    /**
     * 程序启动时加载redis
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        categoryService.initWeek();
    }
}

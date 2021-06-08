package com.vueblog.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-01-17-10-22
 */
@SpringBootConfiguration
public class C3p0config {
    @Value("${spring.datasource.driver-class-name}")
    private String jdbcDriver;
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String jdbcUser;
    @Value("${spring.datasource.password}")
    private String jdbcPassword;

    @Bean
    public DataSource getDataSource() throws PropertyVetoException {
        ComboPooledDataSource c3p0 = new ComboPooledDataSource();
        c3p0.setDriverClass(jdbcDriver);
        c3p0.setUser(jdbcUser);
        c3p0.setPassword(jdbcPassword);
        c3p0.setJdbcUrl(jdbcUrl);
        return c3p0;
    }
}

package cn.strongme.serverauth.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;

/**
 * description:
 * email: <a href="strongwalter2014@gmail.com">阿水</a>
 *
 * @author 阿水
 * @date 2018-11-29 23:00.
 */

@Configuration
@EnableTransactionManagement
@MapperScan("cn.strongme.serverauth.dao")
public class MybatisPlusConfig {

    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


    /**
     * 性能分析拦截器，不建议生产使用
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    @Component
    public class MetaObjectHandlerConfig implements MetaObjectHandler {

        public void insertFill(MetaObject metaObject) {
            Date dateNow = new Date();
            setFieldValByName("createDate", dateNow, metaObject);
            setFieldValByName("updateDate", dateNow, metaObject);
        }

        public void updateFill(MetaObject metaObject) {
            Date dateNow = new Date();
            setFieldValByName("updateDate", dateNow, metaObject);
        }
    }

}

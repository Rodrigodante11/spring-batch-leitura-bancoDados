package com.rodrigo.springbatch.jdbccursorreader.reader;

import com.rodrigo.springbatch.jdbccursorreader.dominio.Cliente;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class JdbcPagingItemReaderConfig {
    @Bean
    public JdbcPagingItemReader<Cliente> jdbcPagingItemReader(
            @Qualifier("appDataSource") DataSource dataSource, // o banco appDataSource setadon na classe DataSourceConfig
            PagingQueryProvider queryProvider
    ) {
        return new JdbcPagingItemReaderBuilder<Cliente>()
                .name("jdbcPagingItemReader")
                .dataSource(dataSource)
                .queryProvider(queryProvider)
                .pageSize(1)
                .rowMapper(new BeanPropertyRowMapper<Cliente>(Cliente.class))
                .build();
    }

    @Bean
    public SqlPagingQueryProviderFactoryBean queryPovider(
            @Qualifier("appDataSource") DataSource dataSource) {
        SqlPagingQueryProviderFactoryBean  queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause("select * ");
        queryProvider.setFromClause("from cliente");
        queryProvider.setSortKey("email");

        return queryProvider;

    }
}

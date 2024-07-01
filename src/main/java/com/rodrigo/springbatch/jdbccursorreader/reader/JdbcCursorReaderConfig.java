package com.rodrigo.springbatch.jdbccursorreader.reader;


import com.rodrigo.springbatch.jdbccursorreader.dominio.Cliente;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcCursorReaderConfig {
    @Bean
    public JdbcCursorItemReader<Cliente> jdbcCursorReader() {
        return null;
    }
}

package com.rodrigo.springbatch.jdbccursorreader.step;

import com.rodrigo.springbatch.jdbccursorreader.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class JdbcCursorReaderStepConfig {
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step jdbcCursorReaderStep(ItemReader<Cliente> jdbcCursorReader, ItemWriter<Cliente> jdbcCursorWriter) {
        return stepBuilderFactory
                .get("jdbcCursorReaderStep")
                .<Cliente, Cliente>chunk(1)
                .reader(jdbcCursorReader)
                .writer(jdbcCursorWriter)
                .faultTolerant()
                .skip(Exception.class) // a excessao que sera permitida
                .skipLimit(2) // pode skipar ate 2 registros
                .build();
    }
}
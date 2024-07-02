package com.rodrigo.springbatch.jdbccursorreader.reader;


import com.rodrigo.springbatch.jdbccursorreader.dominio.Cliente;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class JdbcCursorReaderConfig {
    @Bean
    public JdbcCursorItemReader<Cliente> jdbcCursorReader(
            @Qualifier("appDataSource")DataSource dataSource // o banco appDataSource setadon na classe DataSourceConfig
            ) {
        return new JdbcCursorItemReaderBuilder<Cliente>()
                .name("jdbcCursorReader")
                .dataSource(dataSource)
                .sql("select * from cliente")
                .rowMapper(clienteRowMapper()) // forcando um erro na linha 11
                .build();
    }
    private RowMapper<Cliente> clienteRowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                if(rs.getRow() == 11)
                    throw new SQLException("encerrando a execucao - cliente invalido %s", rs.getString("email"));
                else return clienteRowMapper(rs);
            }

            public Cliente clienteRowMapper(ResultSet rs) throws SQLException {
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setIdade(rs.getString("idade"));
                cliente.setEmail(rs.getString("email"));

                return cliente;
            }
        };
    }
}

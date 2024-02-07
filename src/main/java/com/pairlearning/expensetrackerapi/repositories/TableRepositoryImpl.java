package com.pairlearning.expensetrackerapi.repositories;

import com.pairlearning.expensetrackerapi.domain.Table;
import com.pairlearning.expensetrackerapi.exceptions.EtResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class TableRepositoryImpl implements TableRepository {

    private static final String SQL_FIND_ALL_TABLES = "SELECT * FROM et_tables";
    private static final String SQL_FIND_BY_ID_TABLE = "SELECT * FROM et_tables WHERE table_id = ?";
    private static final String SQL_CREATE_TABLE = "INSERT INTO et_tables (table_type, number_of_tables) VALUES (?, ?)";
    private static final String SQL_UPDATE_TABLE = "UPDATE et_tables SET number_of_tables=? WHERE table_id=?";
    private static final String SQL_REMOVE_BY_ID_TABLE = "DELETE FROM et_tables WHERE table_id=?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Table> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL_TABLES, tableRowMapper);
    }

    @Override
    public Table findById(String tableType) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID_TABLE, new Object[]{tableType}, tableRowMapper);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Table not found");
        }
    }

    @Override
    public void create(String tableType, int numberOfTables) {
        try {
            jdbcTemplate.update(SQL_CREATE_TABLE, tableType, numberOfTables);
        } catch (Exception e) {
            throw new RuntimeException("Invalid request", e);
        }
    }

    @Override
    public void update(String tableType, Table table) throws EtResourceNotFoundException {
        try {
            jdbcTemplate.update(SQL_UPDATE_TABLE, new Object[]{table.getNumberOfTables(), tableType});
        } catch (Exception e) {
            throw new RuntimeException("Invalid request", e);
        }
    }

    @Override
    public void removeById(String tableType) {
        try {
            jdbcTemplate.update(SQL_REMOVE_BY_ID_TABLE, new Object[]{tableType});
        } catch (Exception e) {
            throw new RuntimeException("Invalid request", e);
        }
    }

    private RowMapper<Table> tableRowMapper = ((rs, rowNum) ->
            new Table(rs.getString("TABLE_TYPE"), rs.getInt("NUMBER_OF_TABLES")));
}

package com.pairlearning.expensetrackerapi.services;

import com.pairlearning.expensetrackerapi.domain.Table;
import com.pairlearning.expensetrackerapi.exceptions.EtBadRequestException;
import com.pairlearning.expensetrackerapi.exceptions.EtResourceNotFoundException;
import com.pairlearning.expensetrackerapi.repositories.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;

    @Override
    public List<Table> fetchAllTables() {
        return tableRepository.findAll();
    }

    @Override
    public Table fetchTableById(String tableType) throws EtResourceNotFoundException {
        return tableRepository.findById(tableType);
    }

    @Override
    public Table addTable(Table table) throws EtBadRequestException {
        tableRepository.create(table.getTableType(), table.getNumberOfTables());
        return tableRepository.findById(table.getTableType());
    }

    @Override
    public void updateTable(String tableType, Table table) throws EtBadRequestException {
        tableRepository.update(tableType, table);
    }

    @Override
    public void removeTableById(String tableType) throws EtResourceNotFoundException {
        tableRepository.removeById(tableType);
    }
}

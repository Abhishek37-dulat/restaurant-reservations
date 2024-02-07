package com.pairlearning.expensetrackerapi.services;

import com.pairlearning.expensetrackerapi.domain.Table;
import com.pairlearning.expensetrackerapi.exceptions.EtBadRequestException;
import com.pairlearning.expensetrackerapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface TableService {
    List<Table> fetchAllTables();
    Table fetchTableById(String tableType) throws EtResourceNotFoundException;
    Table addTable(Table table) throws EtBadRequestException;
    void updateTable(String tableType, Table table) throws EtBadRequestException;
    void removeTableById(String tableType) throws EtResourceNotFoundException;
}

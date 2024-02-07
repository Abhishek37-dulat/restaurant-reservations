package com.pairlearning.expensetrackerapi.repositories;

import com.pairlearning.expensetrackerapi.domain.Table;
import com.pairlearning.expensetrackerapi.exceptions.EtBadRequestException;
import com.pairlearning.expensetrackerapi.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface TableRepository {

    List<Table> findAll();

    Table findById(String tableType) throws EtResourceNotFoundException;

    void create(String tableType, int numberOfTables)throws EtBadRequestException;

    void update(String tableType, Table table) throws EtBadRequestException;

    void removeById(String tableType);
}

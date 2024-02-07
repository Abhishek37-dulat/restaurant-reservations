package com.pairlearning.expensetrackerapi.domain;


public class Table {
    private String tableType;
    private int numberOfTables;

    public Table(String tableType, int numberOfTables) {
        this.tableType = tableType;
        this.numberOfTables = numberOfTables;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTables = numberOfTables;
    }
}

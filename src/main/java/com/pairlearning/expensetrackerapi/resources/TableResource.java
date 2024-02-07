package com.pairlearning.expensetrackerapi.resources;

import com.pairlearning.expensetrackerapi.domain.Table;
import com.pairlearning.expensetrackerapi.services.TableService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tables")
public class TableResource {

    @Autowired
    private TableService tableService;

    @GetMapping("")
    public ResponseEntity<List<Table>> getAllTables(HttpServletRequest request) {
        List<Table> tables = tableService.fetchAllTables();
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    @GetMapping("/{tableType}")
    public ResponseEntity<Table> getTableById(@PathVariable("tableType") String tableType) {
        try {
            Table table = tableService.fetchTableById(tableType);
            return new ResponseEntity<>(table, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Table> addTable(@RequestBody Table table) {
        try {
            Table addedTable = tableService.addTable(table);
            return new ResponseEntity<>(addedTable, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{tableType}")
    public ResponseEntity<Map<String, Boolean>> updateTable(@PathVariable("tableType") String tableType,
                                                            @RequestBody Table table) {
        try {
            tableService.updateTable(tableType, table);
            Map<String, Boolean> map = new HashMap<>();
            map.put("success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{tableType}")
    public ResponseEntity<Map<String, Boolean>> deleteTable(@PathVariable("tableType") String tableType) {
        try {
            tableService.removeTableById(tableType);
            Map<String, Boolean> map = new HashMap<>();
            map.put("success", true);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

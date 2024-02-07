import com.pairlearning.expensetrackerapi.domain.Table;
import com.pairlearning.expensetrackerapi.exceptions.EtBadRequestException;
import com.pairlearning.expensetrackerapi.exceptions.EtResourceNotFoundException;
import com.pairlearning.expensetrackerapi.repositories.TableRepository;
import com.pairlearning.expensetrackerapi.repositories.TableRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableRepositoryTest {

    private TableRepository tableRepository;

    @BeforeEach
    void setUp() {
        this.tableRepository = new TableRepositoryImpl();
    }

    @Test
    void findAll() {
        List<Table> tables = tableRepository.findAll();
        assertNotNull(tables);
        // Add more assertions based on the expected behavior of findAll
    }

    @Test
    void findById() {
        String tableType = "TestTable";
        assertThrows(EtResourceNotFoundException.class, () -> tableRepository.findById(tableType));
        // Add more assertions based on the expected behavior of findById
    }

    @Test
    void create() {
        String tableType = "TestTable";
        int numberOfTables = 5;
        assertThrows(EtBadRequestException.class, () -> tableRepository.create(tableType, numberOfTables));
        // Add more assertions based on the expected behavior of create
    }


    @Test
    void removeById() {
        String tableType = "TestTable";
        assertDoesNotThrow(() -> tableRepository.removeById(tableType));
        // Add more assertions based on the expected behavior of removeById
    }
}

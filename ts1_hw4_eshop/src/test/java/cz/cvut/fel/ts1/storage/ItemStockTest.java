package cz.cvut.fel.ts1.storage;

import cz.cvut.fel.ts1.shop.StandardItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class ItemStockTest {

    private StandardItem item;
    private ItemStock stock;

    @BeforeEach
    void setUp() {
        item = new StandardItem(1, "IPhone", 100, "TECH", 10);
        stock = new ItemStock(item);
    }

    @Test
    void constructor_validParameters_fieldAreSetCorrectly() {
        assertEquals(item, stock.getItem());
        assertEquals(0, stock.getCount());
    }

    @ParameterizedTest
    @CsvSource({
            "0", "1", "3", "100"
    })
    void increaseItemCount_variousInputs_itemCountIncreased(int itemCount){
        stock.increaseItemCount(itemCount);

        assertEquals(itemCount, stock.getCount());
    }

    @ParameterizedTest
    @CsvSource({
            "0", "1", "3", "100"
    })
    void increaseItemCount_variousInputs_itemCountDecreased(int itemCount){
        stock.increaseItemCount(101);
        stock.decreaseItemCount(itemCount);

        assertEquals(101 - itemCount, stock.getCount());
    }




}

package cz.cvut.fel.ts1.shop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

//methodName_condition_expectedBehavior()
public class StandardItemTest {

    @Test
    void constructor_validParameters_fieldsAreSetCorrectly() {
        int id = 12;
        String name = "PC";
        float price = 50.12F;
        String category = "TECH";
        int loyaltyPoints = 5;

        StandardItem item = new StandardItem(id, name, price, category, loyaltyPoints);

        assertEquals(id, item.getID());
        assertEquals(name, item.getName());
        assertEquals(price, item.getPrice());
        assertEquals(category, item.getCategory());
        assertEquals(loyaltyPoints, item.getLoyaltyPoints());
    }

    @Test
    void copy_called_copyEqualsButNotSame() {
        int id = 12;
        String name = "PC";
        float price = 50.12F;
        String category = "TECH";
        int loyaltyPoints = 5;

        StandardItem item = new StandardItem(id, name, price, category, loyaltyPoints);
        StandardItem copy = item.copy();

        assertEquals(item, copy);
        assertNotSame(item, copy);
    }

    @ParameterizedTest
    @CsvSource({
            "121,Monitor,100.5,TECH,10,121,Monitor,100.5,TECH,10,true",
            "122,Monitor,100.5,TECH,10,123,Monitor,100.5,TECH,10,false",
            "122,Monitor,100.5,TECH,10,122,Keyboard,100.5,TECH,10,false",
            "122,Monitor,100.5,TECH,10,122,Monitor,99.9,TECH,10,false",
            "122,Monitor,100.5,TECH,10,122,Monitor,100.5,HOUSE,10,false",
            "122,Monitor,100.5,TECH,10,122,Monitor,100.5,TECH,5,false"
    })
    void equals_variousItems_compareResults(
            int id1, String name1, float price1, String category1, int lp1,
            int id2, String name2, float price2, String category2, int lp2,
            boolean expected
    ) {
        StandardItem item1 = new StandardItem(id1, name1, price1, category1, lp1);
        StandardItem item2 = new StandardItem(id2, name2, price2, category2, lp2);

        assertEquals(expected, item1.equals(item2));
    }



}

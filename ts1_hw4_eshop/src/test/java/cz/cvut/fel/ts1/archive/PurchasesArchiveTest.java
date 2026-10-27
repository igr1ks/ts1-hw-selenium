package cz.cvut.fel.ts1.archive;

import cz.cvut.fel.ts1.shop.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PurchasesArchiveTest {

    private StandardItem item;
    private Order order;
    private ShoppingCart cart;
    private TestablePurchasesArchive archive;

    @BeforeEach
    public void setup() {
        item = new StandardItem(1, "KEYBOARD", 100.0F, "TECH", 5);
        cart = new ShoppingCart();
        cart.addItem(item);
        order = new Order(cart, "Tester", "Street");
        archive = new TestablePurchasesArchive();
    }

    @Test
    public void printItemPurchaseStatistics_archiveHasItem_printsCorrectOutput() {
        archive.putEntry(item.getID(), new TestableEntry(item, 2));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        archive.printItemPurchaseStatistics();

        String printed = output.toString().trim();
        assertTrue(printed.contains("ITEM PURCHASE STATISTICS:"));
        assertTrue(printed.contains("KEYBOARD"));
        assertTrue(printed.contains("2"));

        System.setOut(System.out); // reset System.out
    }

    @Test
    public void getHowManyTimesHasBeenItemSold_itemExists_returnsCorrectCount() {
        archive.putEntry(item.getID(), new TestableEntry(item, 5));

        int count = archive.getHowManyTimesHasBeenItemSold(item);
        assertEquals(5, count);
    }

    @Test
    public void putOrderToPurchasesArchive_newItemEntryCreated_constructorWasCalled() {
        archive.putOrderToPurchasesArchive(order);

        assertEquals(1, archive.getConstructorCallCount());
        assertEquals(1, archive.getHowManyTimesHasBeenItemSold(item));
    }

    @Test
    public void putOrderToPurchasesArchive_itemAlreadyInArchive_increaseCountWasCalled() {
        archive.putEntry(item.getID(), new TestableEntry(item, 3));

        archive.putOrderToPurchasesArchive(order);

        TestableEntry entry = (TestableEntry) archive.getEntry(item.getID());
        assertEquals(2, entry.getIncreaseCallCount());
        assertEquals(4, archive.getHowManyTimesHasBeenItemSold(item));
    }




    static class TestablePurchasesArchive extends PurchasesArchive {
        private final HashMap<Integer, ItemPurchaseArchiveEntry> itemPurchaseArchive;
        private final ArrayList<Order> orderArchive;
        private int constructorCallCount = 0;

        public TestablePurchasesArchive() {
            super(new HashMap<>(), new ArrayList<>());
            this.itemPurchaseArchive = getItemPurchaseArchive();
            this.orderArchive = getOrderArchive();
        }

        private HashMap<Integer, ItemPurchaseArchiveEntry> getItemPurchaseArchive() {
            try {
                var field = PurchasesArchive.class.getDeclaredField("itemPurchaseArchive");
                field.setAccessible(true);
                return (HashMap<Integer, ItemPurchaseArchiveEntry>) field.get(this);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private ArrayList<Order> getOrderArchive() {
            try {
                var field = PurchasesArchive.class.getDeclaredField("orderArchive");
                field.setAccessible(true);
                return (ArrayList<Order>) field.get(this);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public void putEntry(int id, ItemPurchaseArchiveEntry entry) {
            itemPurchaseArchive.put(id, entry);
        }

        public ItemPurchaseArchiveEntry getEntry(int id) {
            return itemPurchaseArchive.get(id);
        }

        @Override
        public void putOrderToPurchasesArchive(Order order) {
            orderArchive.add(order);
            for (Item i : order.getItems()) {
                if (itemPurchaseArchive.containsKey(i.getID())) {
                    ItemPurchaseArchiveEntry e = itemPurchaseArchive.get(i.getID());
                    e.increaseCountHowManyTimesHasBeenSold(1);
                } else {
                    itemPurchaseArchive.put(i.getID(), new TestableEntry(i));
                    constructorCallCount++;
                }
            }
        }

        public int getConstructorCallCount() {
            return constructorCallCount;
        }
    }

    static class TestableEntry extends ItemPurchaseArchiveEntry {
        private int increaseCallCount = 0;

        public TestableEntry(Item refItem) {
            super(refItem);
        }

        public TestableEntry(Item refItem, int soldCount) {
            super(refItem);
            increaseCountHowManyTimesHasBeenSold(soldCount - 1);
        }

        @Override
        public void increaseCountHowManyTimesHasBeenSold(int x) {
            super.increaseCountHowManyTimesHasBeenSold(x);
            increaseCallCount++;
        }

        public int getIncreaseCallCount() {
            return increaseCallCount;
        }
    }
}

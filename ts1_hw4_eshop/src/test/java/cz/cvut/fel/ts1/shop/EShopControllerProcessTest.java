package cz.cvut.fel.ts1.shop;

import cz.cvut.fel.ts1.storage.NoItemInStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EShopControllerProcessTest {

    @BeforeEach
    void setup() {
        EShopController.startEShop();
    }

    @Test
    public void purchaseShoppingCart_FilledCart_StockDecreasedAndArchived() throws NoItemInStorage {
        StandardItem item = new StandardItem(100, "Mouse", 500f, "TECH", 2);
        EShopController.getStorage().insertItems(item, 1);

        ShoppingCart cart = EShopController.newCart();
        cart.addItem(item);

        EShopController.purchaseShoppingCart(cart, "Bob", "Mars");

        assertEquals(0, EShopController.getStorage().getItemCount(item));
        assertEquals(1, EShopController.getArchive().getHowManyTimesHasBeenItemSold(item));
    }

    @Test
    public void purchaseShoppingCart_NotEnoughInStock_ThrowsNoItemInStorage() {
        StandardItem item = new StandardItem(101, "Keyboard", 800f, "TECH", 3);

        ShoppingCart cart = EShopController.newCart();
        cart.addItem(item);

        assertThrows(NoItemInStorage.class, () -> {
            EShopController.purchaseShoppingCart(cart, "Eve", "Moon");
        });
    }
}

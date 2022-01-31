package shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;


class CartTest {

    private Cart cart;
    private RealItem car;
    private RealItem car2;
    private VirtualItem disk;
    private VirtualItem disk2;
    double totalCar = 0;
    double totalDisk = 0;

    @BeforeEach
    public void createObj() {
        cart = new Cart("andrew-cart");

        car = new RealItem();
        car2 = new RealItem();

        disk = new VirtualItem();
        disk2 = new VirtualItem();

        car.setPrice(300.12);
        car2.setPrice(30.12);

        disk.setPrice(28);
        disk2.setPrice(88);

        totalCar = car.getPrice() + (car.getPrice() * 0.2) + car2.getPrice() + (car2.getPrice() * 0.2);
        totalDisk = disk.getPrice() + (disk.getPrice() * 0.2) + disk2.getPrice() + (disk2.getPrice() * 0.2);
    }

    @Test
    void addRealItem() {
        cart.addRealItem(car);
        cart.addRealItem(car2);
        assertEquals("andrew-cart", cart.getCartName());
        assertEquals(totalCar, cart.getTotalPrice());
    }

    @Test
    void deleteRealItem() {
        cart.addRealItem(car);
        cart.addRealItem(car2);
        cart.deleteRealItem(car);
        assertEquals(totalCar, cart.getTotalPrice());
        cart.showItems();
    }

    @Test
    void addVirtualItem() {
        cart.addVirtualItem(disk);
        cart.addVirtualItem(disk2);
        assertEquals("andrew-cart", cart.getCartName());
        assertEquals(totalDisk, cart.getTotalPrice());
    }

    @Test
    void deleteVirtualItem() {
        cart.addVirtualItem(disk);
        cart.addVirtualItem(disk2);
        cart.deleteVirtualItem(disk);
        assertEquals(totalDisk, cart.getTotalPrice());
        cart.showItems();
    }


}
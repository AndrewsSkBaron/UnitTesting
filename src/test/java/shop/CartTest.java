package shop;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;


class CartTest {

    private Cart cart;
    private RealItem car;
    private RealItem car2;
    private VirtualItem disk;
    private VirtualItem disk2;
    double totalCar = 0;
    double totalDisk = 0;

    @BeforeMethod
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

    }

    @Test
    void addRealItem() {
        cart.addRealItem(car);
        cart.addRealItem(car2);
        totalCar = car.getPrice() + (car.getPrice() * 0.2) + car2.getPrice() + (car2.getPrice() * 0.2);
        assertEquals("andrew-cart", cart.getCartName());
        assertEquals(totalCar, cart.getTotalPrice());
    }

    @Test
    void deleteRealItem() {
        cart.addRealItem(car);
        cart.addRealItem(car2);
        cart.deleteRealItem(car);
        totalCar = car2.getPrice() + (car2.getPrice() * 0.2);
        assertEquals(totalCar, cart.getTotalPrice());
    }

    @Test
    void addVirtualItem() {
        cart.addVirtualItem(disk);
        cart.addVirtualItem(disk2);
        totalDisk = disk.getPrice() + (disk.getPrice() * 0.2) + disk2.getPrice() + (disk2.getPrice() * 0.2);
        assertEquals("andrew-cart", cart.getCartName());
        assertEquals(totalDisk, cart.getTotalPrice());
    }

    @Test
    void deleteVirtualItem() {
        cart.addVirtualItem(disk);
        cart.addVirtualItem(disk2);
        cart.deleteVirtualItem(disk);
        totalDisk = disk2.getPrice() + (disk2.getPrice() * 0.2);
        assertEquals(totalDisk, cart.getTotalPrice());
    }

}
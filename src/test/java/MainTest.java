import org.junit.*;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.File;


public class MainTest {
    Cart andrewCart = new Cart("andrew-cart");
    RealItem car = new RealItem();
    VirtualItem disk = new VirtualItem();
    Parser parser = new JsonParser();
    Cart eugenCart = parser.readFromFile(new File("src/main/resources/eugen-cart.json"));

    private Main main;

    @Before
    public void start(){

    }
}

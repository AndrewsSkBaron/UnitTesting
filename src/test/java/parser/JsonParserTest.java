package parser;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testng.annotations.Parameters;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {
    private Cart cart = new Cart("test-andrew-cart");
    private Gson gson = new Gson();
    private JsonParser jsonParser = new JsonParser();
    @BeforeEach
    public void createObj() {
        RealItem car = new RealItem();
        VirtualItem disk = new VirtualItem();

        car.setName("WV");
        car.setPrice(21582.9);
        car.setWeight(1560);

        disk.setName("Linux");
        disk.setPrice(28);
        disk.setSizeOnDisk(20000);

        cart.addRealItem(car);
        cart.addVirtualItem(disk);
    }

    @Test
    void readFromFile() throws IOException {
        FileWriter writer = new FileWriter("src/main/resources/" + cart.getCartName() + ".json");
        writer.write(gson.toJson(cart));
        writer.close();
        Cart cartObj = jsonParser.readFromFile(new File("src/main/resources/" + cart.getCartName() + ".json"));
        assertEquals(cartObj.getCartName(), cart.getCartName());
        assertEquals(cartObj.getTotalPrice(), cart.getTotalPrice());
        assertEquals(cartObj.getTotalPrice(), cart.getTotalPrice());
    }

    @Test
    void writeToFile() throws IOException {
        jsonParser.writeToFile(cart);
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + cart.getCartName() + ".json"));
        Cart cartObj =gson.fromJson(reader.readLine(), Cart.class);
        reader.close();
        assertEquals(cartObj.getCartName(), cart.getCartName());
        assertEquals(cartObj.getTotalPrice(), cart.getTotalPrice());
    }

    @ParameterizedTest
    @ValueSource(strings = { "car", "car-andrew", "andrews","andrew-car", "test-andrew-cart"})
    public void testThrowsException(String param) throws NoSuchFileException {
        File template = new File(param);
        Throwable thrown = assertThrows(NoSuchFileException.class, () -> {
            new JsonParser().readFromFile(template);
        });
        assertEquals(thrown.getMessage(), String.format("File %s.json not found!", template));
    }
}
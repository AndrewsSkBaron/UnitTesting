package parser;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import shop.Cart;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {
    private Cart cart = new Cart("test-andrew-cart");
    private Gson gson = new Gson();

    @Test
    void readFromFile() throws IOException {
        FileWriter writer = new FileWriter("src/main/resources/" + cart.getCartName() + ".json");
        writer.write(gson.toJson(cart));
        writer.close();

        JsonParser jsonParser = new JsonParser();
        Cart cartObj = jsonParser.readFromFile(new File("src/main/resources/" + cart.getCartName() + ".json"));
        assertEquals(cartObj.getCartName(), cart.getCartName());
        assertEquals(cartObj.getTotalPrice(), cart.getTotalPrice());
    }

    @Test
    void writeToFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + cart.getCartName() + ".json"));
        gson.fromJson(reader.readLine(), Cart.class);
        reader.close();
        JsonParser jsonParser = new JsonParser();
        Cart cartObj = jsonParser.readFromFile(new File("src/main/resources/" + cart.getCartName() + ".json"));
        assertEquals(cartObj.getCartName(), cart.getCartName());
        assertEquals(cartObj.getTotalPrice(), cart.getTotalPrice());
    }

    @Test
    public void testThrowsException() throws NoSuchFileException {
        JsonParser jsonParser = new JsonParser();
        Throwable thrown = assertThrows(NoSuchFileException.class, () -> {
            jsonParser.readFromFile(new File(cart.getCartName() + ".json"));
        });
        assertEquals(thrown.getLocalizedMessage(), "File test-andrew-cart.json.json not found!");
    }
}
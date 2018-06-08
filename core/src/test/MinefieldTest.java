package test;

import com.mygdx.game.Field;
import com.mygdx.game.Minefield;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinefieldTest {

    @Test
    void testBombGeneration() {
        int width = 16;
        int height = 16;
        int bombs = 40;
        Minefield minefield = new Minefield(width, height, bombs);
        int bombCount = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (minefield.getField(x, y).getContent() == Field.Content.BOMB) bombCount++;
            }
        }

        assertEquals(bombs, bombCount);
    }

    @Test
    void testEveryFieldForNotNull() {
        int width = 16;
        int height = 16;
        int bombs = 40;
        Minefield minefield = new Minefield(width, height, bombs);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                assertNotNull(minefield.getField(x, y));
            }
        }
    }
}
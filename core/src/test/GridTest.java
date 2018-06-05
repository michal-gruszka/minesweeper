package test;

import com.mygdx.game.Field;
import com.mygdx.game.Grid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void testInvalidCoordinatesInGetField() {
        Grid grid = new Grid(10, 10, 10);
        assertThrows(IllegalArgumentException.class, () -> {grid.getField(10,2);});
    }

    @Test
    void testBombGeneration() {
        int width = 16;
        int height = 16;
        int bombs = 40;
        Grid grid = new Grid(width, height, bombs);
        int bombCount = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (grid.getField(x, y).getContent() == Field.Content.BOMB) bombCount++;
            }
        }

        assertEquals(bombs, bombCount);
    }

    @Test
    void testEveryFieldForNotNull() {
        int width = 16;
        int height = 16;
        int bombs = 40;
        Grid grid = new Grid(width, height, bombs);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                assertNotNull(grid.getField(x, y));
            }
        }
    }
}
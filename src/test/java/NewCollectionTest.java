import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewCollectionTest {
    public NewCollection collOne = new NewCollection();


    @BeforeEach
    public void setUp() {
        collOne.add("One");
        collOne.add("Two");
        collOne.add("Three");
        collOne.add("Four");
        collOne.add("Five");
    }

 //   @AfterEach
 //   public void tearDown(){
  //      collOne = null;
 //   }

    @AfterEach
    public void tearDown() {
        collOne.clear();
    }

    @Test
    public void testAdd() {
        assertTrue("Could not add not null value", collOne.add("One"));
        assertFalse("Added null value", collOne.add(null));
    }

    @Test
    public void testAddAll() {
        String[] arrayToAdd = {"Six", "Seven", "Eight"};
        String[] nullArrayToAdd = null;
        NewCollection collNull = null;
        NewCollection collTwo = new NewCollection();
        collTwo.add("Nine");
        collTwo.add("Ten");
        collTwo.add("Eleven");

        assertTrue("Could not add String[] array", collOne.addAll(arrayToAdd));
        assertTrue("Could not add newCollection", collOne.addAll(collTwo));

        assertFalse("Added a null array", collOne.addAll(nullArrayToAdd));
        assertFalse("Added a null newCollection", collOne.addAll(collNull));
    }

    @Test
    public void testDelete() {
        assertTrue("Did not find existing value", collOne.delete("One"));
        assertFalse("Deleted wrong element", collOne.delete("Twenty"));
    }

    @Test
    public void testGet() {
        int source = 0;
        String expected = "One";
        String actual = collOne.get(source);
        assertEquals(expected, actual);
    }

    @Test
    public void testContains() {
        assertTrue("Did not find existing value", collOne.contains("One"));
        assertFalse("Found wrong element", collOne.contains("Twenty"));
    }

    @Test
    public void testClear() {
        assertTrue("Did not clear a NewCollection", collOne.clear());
    }

    @Test
    public void testSize() {
        int expected = 5;
        int actual = collOne.size();
        assertEquals(expected, actual);
    }

    @Test
    public void testTrim() {
        assertTrue(collOne.trim());

    }

    @Test
    public void testCompare() {
        NewCollection collDifferent = new NewCollection();
        collDifferent.addAll(collOne);
        collDifferent.delete("Three");
        NewCollection collEquals = new NewCollection();
        collEquals.addAll(collOne);

        assertTrue("Returned false to equal collections", collOne.compare(collEquals));
        assertFalse("Returned true to different collections", collOne.compare(collDifferent));
    }
}


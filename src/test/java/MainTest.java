public class Test {
    @Test
    public void testEmptyArray() {
        int[] array = new int[0];
        assertEquals("", Ser.ser(array));
    }

    @Test
    public void testOneElementArray() {
        int[] array = new int[]{1};
        assertEquals("1", Ser.ser(array));
    }

    @Test
    public void testTwoElementsArray() {
        int[] array = new int[]{1, 2};
        assertEquals("1.2", Ser.ser(array));
    }
}

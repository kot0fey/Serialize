import org.example.Main;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainTest {
    private final Random random = new Random();
    private static Integer numberOfTests = 0;
    private static Float sumContraction = 0F;

    private void test(int[] array) {
        String ser = Main.ser(array);

        float startBytes = (float) (array.length * 4);
        float endBytes = (float) (ser.length() / 2);
        float contraction = ((startBytes - endBytes) / startBytes) * 100;

        System.out.println("Исходная строка: " + arrayToString(array));
        System.out.println("Сжатая строка: " + ser);
        System.out.println("Коэффициент сжатия: " + contraction);
        System.out.println("-----------------------------------------------------------------------------");

        numberOfTests++;
        sumContraction+=contraction;
        assertEquals(arrayToString(array), arrayToString(Main.deser(ser)));
    }

    private String arrayToString(int[] array) {
        StringBuilder arrayString = new StringBuilder();
        for (int num : array) {
            arrayString
                    .append(num)
                    .append(",");
        }
        arrayString.deleteCharAt(arrayString.length() - 1);
        return arrayString.toString();
    }

    @AfterAll
    public static void cleanup(){
        System.out.println("Средний коэффициент сжатия: " + sumContraction/numberOfTests);
    }

    //Простейшие короткие
    @Test
    @Order(1)
    public void simple() {
        System.out.println("Простейшие короткие");
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        test(array);
    }

    //Случайные
    @Test
    @Order(2)
    public void random50() {
        System.out.println("Случайные 50");
        int[] array = new int[50];
        for (int i = 0; i < 50; i++) {
            array[i] = random.nextInt(1, 301);
        }
        test(array);
    }

    @Test
    @Order(3)
    public void random100() {
        System.out.println("Случайные 100");
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = random.nextInt(1, 301);
        }
        test(array);
    }

    @Test
    @Order(4)
    public void random500() {
        System.out.println("Случайные 500");
        int[] array = new int[500];
        for (int i = 0; i < 500; i++) {
            array[i] = random.nextInt(1, 301);
        }
        test(array);
    }

    @Test
    @Order(5)
    public void random1000() {
        System.out.println("Случайные 1000");
        int[] array = new int[1000];
        for (int i = 0; i < 1000; i++) {
            array[i] = random.nextInt(1, 301);
        }
        test(array);
    }

    //Граничные
    @Test
    @Order(6)
    public void edge() {
        System.out.println("Граничные");
        int[] array = new int[900];
        for (int i = 0; i < 300; i++) {
            array[i] = i + 1;
        }
        for (int i = 300; i < 600; i++) {
            array[i] = i + 1 - 300;
        }
        for (int i = 600; i < 900; i++) {
            array[i] = i + 1 - 600;
        }
        test(array);
    }

}

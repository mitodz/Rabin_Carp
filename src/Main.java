import java.util.Random;
import java.util.Scanner;

public class Main {

    /* реализация возведения в степень во избежание переполнения */

    public static long pow(int x, int i, int p) {
        long result = 1;
        for (int j = 1; j <= i; j++) {
            result = ((result * x) % p + p) % p;
        }
        return result;
    }

    /* вычисление хэша */

    public static int getHash(String word, int p, int x) {
        long result = 0;
        for (int i = 0; i < word.length(); i++) {
            result = (result + ((word.charAt(i) * pow(x, i, p)) % p + p) % p + p) % p;
        }
        return (int) (result);
    }

    /* реализация алгоритма Рабина-Карпа */

    public String RabinCarp (String pattern, String text) {
        int n = text.length();//размер текста
        int pL = pattern.length();
        int p = 1_000_000_007;//большое простое число
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        int buf = rnd.nextInt(p);
        int x = buf == 0 ? 1 : buf; //случайное число до p
        long big = pow(x, pL - 1, p); //самая большая степень x
        long pHash = getHash(pattern, p, x); //хэш паттэрна
        long tempHash = getHash(text.substring(n - pL), p , x);//последний хэш в тексте
        int m = n - pL + 1; //размер результирующего массива
        long[] h = new long[m];//результирующий массив
        h[m - 1] = tempHash;
        if (m >= 2) {
            for (int i = m - 2; i >= 0; i--) {
                h[i] = (((((tempHash - text.charAt(i + pL) * big) % p + p) % p) * x) % p + p) % p + text.charAt(i);
                tempHash = h[i];
            }
        }
        for (int i = n - pL; i >= 0  ; i--) {
            if (h[i] == pHash) {
                if (pattern.equals(text.substring(i, i + pL))) {
                    sb.append(" ").append(i);
                }
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pattern = sc.next();
        String text = sc.next();
        System.out.print(new Main().RabinCarp(pattern, text));
    }
}

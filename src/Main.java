import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {

    /* реализация возведения в степень во избежание переполнения */

    public static long pow(int x, int i, int p) {
        long result = 1;
        for (int j = 1; j <= i; j++) {
            result = ((result % p) * (x % p)) % p;
        }
        return result;
    }

    /* вычисление хэша */

    public static int getHash(String word, int p, int x) {
        long result = 0;
        long pow = 1;
        for (int i = 0; i < word.length(); i++) {
            result = (result % p + ((word.charAt(i) % p) * pow) % p + p) % p;
            pow = (((pow % p) * (x % p)) % p + p) % p;
        }
        return (int) (result);
    }

    /* реализация алгоритма Рабина-Карпа */

    public String RabinCarp(String pattern, String text) {
        char[] ptn = pattern.toCharArray();
        char[] txt = text.toCharArray();
        int n = text.length();//размер текста
        int pL = pattern.length();//размер паттерна
        int p = 2_147_483_647;//большое простое число
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        int buf = rnd.nextInt(p);
        int x = buf == 0 ? 1 : buf; //случайное число до p
        long big = pow(x, pL - 1, p); //самая большая степень x
        long pHash = getHash(pattern, p, x); //хэш паттэрна
        long tempHash = getHash(text.substring(n - pL), p, x);//последний хэш в тексте
        int m = n - pL + 1; //размер результирующего массива
        long[] h = new long[m];//результирующий массив хэшей
        h[m - 1] = tempHash;
        //заполнение массива хэшей
        if (m >= 2) {
            for (int i = m - 2; i >= 0; i--) {
                h[i] = (((((tempHash % p - (txt[i + pL] % p) * (big % p)) % p + p) % p) * x % p) % p + p) % p + txt[i] % p;
                tempHash = h[i];
            }
        }
        //алгоритм
        outer:
        for (int i = 0; i <= n - pL; i++) {
            if (h[i] == pHash) {
                for (int j = 0; j < pL; j++) { //при совпадении хэшей, проверяются крайние символы
                    if (ptn[j] != txt[i + j] || ptn[pL - j - 1] != txt[i + pL - j - 1]) {
                        continue outer;
                    }
                }
                sb.append(i).append(" ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pattern = br.readLine();
        String text = br.readLine();
        System.out.print(new Main().RabinCarp(pattern, text));
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;
import java.util.Scanner;
import java.util.Random;
import java.math.BigInteger;
 
public class MillerRabin {

    /** Function to check if prime or not **/
    public static long random (long b){
        long k = 0;
//             Random l = new Random();
//        for (long i = 1; i<9999; i++){
//          k = l.nextInt(99999);     
       k = 1 + (int)(Math.random() * (5));
        
        return k;
    }
    public boolean isPrime(long n, int iteration)
    {
        if (n == 0 || n == 1)
            return false;
        if (n == 2)
            return true;
        /** любое четное число составное**/
        if (n % 2 == 0)
            return false;
 
        long s = n - 1;// сколько раз делится на 2 записываем в s
        while (s % 2 == 0)
            s /= 2;
 
        Random rand = new Random();
        for (int i = 0; i < 100; i++)
        {
            int r = Math.abs(rand.nextInt());      //какое то рендомное число причем без условия, что должно быть меньше n     
            long a = r % (n - 1) + 1, temp = s; //  по формуле получаем a. деление по mod
            long mod = modPow(a, temp, n);
            while (temp != n - 1 && mod != 1 && mod != n - 1)// два из трех должно быть false
            {
                mod = mulMod(mod, mod, n);
                temp *= 2;
            }
            if (mod != n - 1 && temp % 2 == 0)
                return false;
        }
        return true;        
    }
    /** функция (a ^ b) % c    a ← r mod (n-1)+1 возведение в степерь по модулю число b - свидетель простоты**/ 
    public long modPow(long a, long b, long c)// a число с рандома, колво деления, само число
    {
        long res = 1;
        for (int i = 0; i < b; i++) //меньше б, где  б кол-во деления до s-1
        {
            res *= a;// сначала a*1, затем полученное делим на само число
            res %= c; 
        }
        return res % c; // res mod c
    }
    /** функция (mod * mod) % n ,a ← a2 mod n
      если x = 1, то вернуть составное
      если x = m − 1, то перейти на следующую итерацию цикла A**/
    public long mulMod(long a, long b, long n) // вместо n должно быть mod
    {
        return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).mod(BigInteger.valueOf(n)).longValue();
    }
    public static void main (String[] args) 
    {
        long b = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("тест Миллера Рабина\n");
        MillerRabin mr = new MillerRabin();
     
//        System.out.println("Введите число\n");
//        long num = scan.nextLong();
           long num = random(b);
        
          
        /** проверить простое **/
        boolean prime = mr.isPrime(num, 100);
        if (prime)
            System.out.println("\n"+ num +" простое");
        else
            System.out.println("\n"+ num +" составное");
 
    }
}

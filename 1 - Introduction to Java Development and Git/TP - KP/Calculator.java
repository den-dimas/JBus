import java.io.*;
import java.util.Scanner;

public class Calculator
{
    static int num1;
    static int num2;
    
    public static void main(String [] args) 
    {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Masukkan x: ");
        int x = input.nextInt();
        
        System.out.print("Masukkan y: ");
        int y = input.nextInt();
        
        Calculator calc = new Calculator(x, y);
        
        System.out.print("\n");
        
        calc.tambah();
        calc.kurang();
        calc.kali();
        calc.bagi();
    }
    
    Calculator(int x, int y)
    {
        this.num1 = x;
        this.num2 = y;
    }

    static void tambah()
    {
        System.out.println("Hasil pertambahan x dan y = " + (num1 + num2));
    }
    
    static void kurang()
    {
        System.out.println("Hasil perkurangan x dan y = " + (num1 - num2));
    }
    
    static void kali()
    {
        System.out.println("Hasil perkalian x dan y = " + (num1 * num2));
    }
    
    static void bagi()
    {
        if (num2 == 0)
        {
            System.out.println("Hasil pembagian x dan y = undefined\n");
            System.out.println("Tidak bisa membagi dengan 0!\n");
            return;
        }
        
        System.out.println("Hasil pembagian x dan y = " + (num1 / num2));
    }
}

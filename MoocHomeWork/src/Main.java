import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        Fraction a = new Fraction(in.nextInt(), in.nextInt());

        Fraction b = new Fraction(in.nextInt(),in.nextInt());

        a.print();

        b.print();

        a.plus(b).print();

        a.multiply(b).plus(new Fraction(5,6)).print();

        a.print();

        b.print();

        in.close();

    }

}
class Fraction {
    int a;
    int b;

    Fraction(int a, int b) {
        this.a = a;
        this.b = b;
    }

    double toDouble() {
        if(b == 0)
        {
            return 0;
        }
        return (double) a / b;
    }
    int divisor(int n1, int n2)
    {
        int n = n1 > n2 ? n2 : n1;
        int di = 0;
        for (int i = 1; i <= n; i++) {
            if (n1 % i == 0 && n2 % i == 0) {
                di = i;
            }
        }
        return di;
    }
    Fraction plus(Fraction r) {
        int na, nb;
        if (r.b == b) {
            na = r.a + a;
            nb = b;
        }else
        {
            nb = r.b * b;
            na = r.a * b + a * r.b;
        }
        int di = divisor(na, nb);
        Fraction tmp = new Fraction(na / di, nb / di);
        return tmp;
    }

    Fraction multiply(Fraction r) {
        int na, nb;
        na = r.a * a;
        nb = r.b * b;
        int di = divisor(na, nb);
        Fraction tmp = new Fraction(na / di, nb / di);
        return tmp;
    }

    void print()
    {
        if(a == b)
        {
            System.out.println(a/b);
        }else {
            int di = divisor(a, b);
            System.out.println(a / di + "/" + b / di);
        }
    }


}
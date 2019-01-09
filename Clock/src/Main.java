class Clock
{
    private Display h, m, s;
    public Clock(int hour, int minute, int second)
    {
        h = new Display(24, hour);
        m = new Display(60, minute);
        s = new Display(60, second);
    }

    public void tick()
    {
        s.increase();
        if(s.getValue() == 0)
        {
            m.increase();
            if(m.getValue() == 0)
            {
                h.increase();
            }
        }
    }

    public String toString()
    {
        String str = null;
        str = String.format("%02d:%02d:%02d", h.getValue(), m.getValue(), s.getValue());
        return str;
    }
}

class Display
{
    private int limit;
    private int value = 0;

    public Display(int limit, int value)
    {
        this.limit = limit;
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    public void increase()
    {
        value++;
        if(value == limit)
        {
            value = 0;
        }
    }
}

public class Main {
    public static void main(String[] args) {

        java.util.Scanner in = new java.util.Scanner(System.in);

        Clock clock = new Clock(in.nextInt(), in.nextInt(), in.nextInt());

        clock.tick();

        System.out.println(clock);

        in.close();

    }
}

import java.util.ArrayList;

public class Database {
//    private ArrayList<CD> listCD = new ArrayList<CD>();
    private ArrayList<Item> listItem = new ArrayList<Item>();

    public void add(Item item)
    {
        listItem.add(item);
    }

    public void list()
    {
        for(Item item : listItem)
        {
            item.print();
        }
    }
    public static void main(String[] args)
    {
        Database db = new Database();
        db.add(new CD("abd", "abc", 4, 60,",,,"));
        db.add(new DVD("aaa", "###", 40, "ppp"));
        db.list();
    }

}

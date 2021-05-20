package orderprojectexpress.prototype.Express.Class;

        import java.util.ArrayList;

public class GlobalVariables
{
    public ArrayList<Item> encounters;

    private GlobalVariables() {
        encounters = new ArrayList<Item>();
    }

    private static GlobalVariables instance;

    public static GlobalVariables getInstance() {
        if (instance == null) instance = new GlobalVariables();
        return instance;
    }
}
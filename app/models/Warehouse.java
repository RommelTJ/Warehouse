package models;

import java.util.List;
import java.util.ArrayList;

public class Warehouse {
    public String name;
    public List<StockItem> stock = new ArrayList<>();

    public String toString() {
        return name;
    }
}
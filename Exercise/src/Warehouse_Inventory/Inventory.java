package Warehouse_Inventory;

import java.util.List;

public class Inventory {
    private String name;
    private List<List<String>> palletItemIds;

    public Inventory(String name, List<List<String>> palletItemIds) {
        this.name = name;
        this.palletItemIds = palletItemIds;
    }

    public List<List<String>> getPalletItemIds() {
        return palletItemIds;
    }
}


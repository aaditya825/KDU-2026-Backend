package Warehouse_Inventory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InventoryProcessor {

    public static Set<String> getAllUniqueItemIds(Inventory inventory) {
        return inventory.getPalletItemIds()
                .stream()              // Stream<List<String>>
                .flatMap(pallet -> pallet.stream()) // Stream<String>
                .collect(Collectors.toSet());
    }
}


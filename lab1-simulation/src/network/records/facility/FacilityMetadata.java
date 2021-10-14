package network.records.facility;

import network.records.IconMetadata;

import java.util.ArrayList;

public record FacilityMetadata(String factoryType, ArrayList<IconMetadata> icons, ArrayList<FacilityEntryComponent> entryType, String exitType, int productionInterval) {
    public FacilityMetadata(String factoryType, ArrayList<IconMetadata> icons, String exitType, int productionInterval) {
        this(factoryType, icons, null, exitType, productionInterval);
    }
}
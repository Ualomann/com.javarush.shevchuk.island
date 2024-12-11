package Fabric;

import Entity.LivingOrganism;
import Fabric.AnimalFabric;
import Fabric.AnimalFabric.*;

import java.util.ArrayList;
import java.util.List;

public class StartingValue {
    public static List<LivingOrganism> populatingOneCell()
    {
        AnimalFabric.createCountLiveOrganism(LiveOrgNames.HORSE,5);
        AnimalFabric.createCountLiveOrganism(LiveOrgNames.DEER,5);
        AnimalFabric.createCountLiveOrganism(LiveOrgNames.RABBIT,10);
        AnimalFabric.createCountLiveOrganism(LiveOrgNames.MOUSE,10);
        AnimalFabric.createCountLiveOrganism(LiveOrgNames.GOAT,5);
        AnimalFabric.createCountLiveOrganism(LiveOrgNames.SHEEP,5);
        AnimalFabric.createCountLiveOrganism(LiveOrgNames.BOAR,5);
        AnimalFabric.createCountLiveOrganism(LiveOrgNames.BUFFALO,2);
        AnimalFabric.createCountLiveOrganism(LiveOrgNames.DUCK,10);
        AnimalFabric.createCountLiveOrganism(LiveOrgNames.CATERPILLAR,50);
        AnimalFabric.createCountLiveOrganism(LiveOrgNames.PLANT,20);
        AnimalFabric.createCountLiveOrganism(LiveOrgNames.WOLF,5);
        AnimalFabric.createCountLiveOrganism(LiveOrgNames.FOX,5);
        AnimalFabric.createCountLiveOrganism(LiveOrgNames.BEAR,2);
        AnimalFabric.createCountLiveOrganism(LiveOrgNames.BOA,2);
        AnimalFabric.createCountLiveOrganism(LiveOrgNames.EAGLE,2);

        List<LivingOrganism> startV = AnimalFabric.getFabricList();
        return startV;
    }

    public static List<LivingOrganism> creationPlantOnCell(){
        AnimalFabric.createCountLiveOrganism(AnimalFabric.LiveOrgNames.PLANT,20);
        return AnimalFabric.getFabricList();
    }
}



import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.cloudbus.cloudsim.vms.Vm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoundRobinVmAllocationTest {

    public static Config conf = ConfigFactory.load("values.conf");

    @Test
    public void checkConfig(){
        RoundRobinVmAllocation_1DC rrvmat = new RoundRobinVmAllocation_1DC();
        assertNotNull(rrvmat.conf);
    }

    @Test
    public void checkRAM(){
        RoundRobinVmAllocationTestUtil rrvmatu = new RoundRobinVmAllocationTestUtil();
        assertEquals(rrvmatu.ram,conf.getInt("Vm_properties.ram"));
    }

    @Test
    public void checkVmNullResource(){
        final Vm obj =Vm.NULL;
        assertAll(
                () -> assertEquals(0, obj.getBw().getAllocatedResource())
        );
    }

    @Test
    public void checkVmNullObjectListeners() {
        final Vm obj = Vm.NULL;
        obj.addOnHostAllocationListener(null);
        assertFalse(obj.removeOnHostAllocationListener(null));
    }

    @Test
    public void checkVmNull(){
        final Vm obj = Vm.NULL;
        assertAll(
                () -> assertTrue(obj.getStateHistory().isEmpty())
        );
    }



}
package komo.fraczek.servicemodule;


import komo.fraczek.servicemodule.domain.ServiceStatus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusTest {

    @ParameterizedTest
    @EnumSource(ServiceStatus.class)
    public void testParkingStatus(){
        assertEquals(ServiceStatus.valueOf("WORKING"), ServiceStatus.WORKING);
        assertEquals(ServiceStatus.valueOf("IN_SERVICE"), ServiceStatus.IN_SERVICE);
        assertEquals(ServiceStatus.valueOf("BROKEN"), ServiceStatus.BROKEN);
        assertEquals(ServiceStatus.valueOf("UNKNOWN"), ServiceStatus.UNKNOWN);

        assertEquals(ServiceStatus.WORKING.toString(),"WORKING");
        assertEquals(ServiceStatus.IN_SERVICE.toString(),"IN_SERVICE");
        assertEquals(ServiceStatus.UNKNOWN.toString(),"UNKNOWN");
    }
}

package taskmaster;

import ch.ilv.m295.demoapp.vehicle.Vehicle;
import ch.ilv.m295.demoapp.vehicle.VehicleRepository;
import ch.ilv.m295.demoapp.vehicle.VehicleType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class DBTests {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    void insertVehicle() {
        Vehicle objCar = this.vehicleRepository.save(new Vehicle("BL 123", "", VehicleType.CAR));
        Assertions.assertNotNull(objCar.getId());
        Vehicle objBike = this.vehicleRepository.save(new Vehicle("Bike", "", VehicleType.BICYCLE));
        Assertions.assertNotNull(objBike.getId());
    }
}

package taskmaster;

import ch.ilv.m295.demoapp.vehicle.Vehicle;
import ch.ilv.m295.demoapp.vehicle.VehicleRepository;
import ch.ilv.m295.demoapp.vehicle.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VehicleServiceTests {

    private VehicleService vehicleService;
    private final VehicleRepository vehicleRepositoryMock = mock(VehicleRepository.class);

    private final Vehicle vehicleMock = mock(Vehicle.class);

    @BeforeEach
    void setUp() {
        vehicleService = new VehicleService(vehicleRepositoryMock);
    }

    @Test
    void createVehicle() {
        when(vehicleRepositoryMock.save(vehicleMock)).thenReturn(vehicleMock);
        vehicleService.insertVehicle(vehicleMock);
        verify(vehicleRepositoryMock, times(1)).save(any());
    }

    @Test
    void findVehicle() {
        when(vehicleRepositoryMock.findById(any())).thenReturn(Optional.ofNullable(vehicleMock));
        Vehicle v = vehicleService.getVehicle(any());
        verify(vehicleRepositoryMock, times(1)).findById(any());
    }

    @Test
    void deleteVehicle() {
        vehicleService.deleteVehicle(any());
        verify(vehicleRepositoryMock, times(1)).deleteById(any());
    }
}
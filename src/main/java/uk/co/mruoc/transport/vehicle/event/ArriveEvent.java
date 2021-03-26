package uk.co.mruoc.transport.vehicle.event;

import lombok.Builder;
import lombok.Getter;
import uk.co.mruoc.transport.vehicle.Vehicle;

import java.time.Duration;

@Getter
public class ArriveEvent extends AbstractVehicleEvent {

    @Builder
    public ArriveEvent(Duration time, String location, Vehicle vehicle) {
        super(Type.ARRIVE, time, location, vehicle);
    }

}

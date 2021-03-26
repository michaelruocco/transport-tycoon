package uk.co.mruoc.transport.vehicle.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import uk.co.mruoc.transport.vehicle.Vehicle;

import java.time.Duration;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HandlingEvent extends AbstractVehicleEvent {

    private final Duration duration;

    public HandlingEvent(Type type, Duration time, String location, Vehicle vehicle) {
        super(type, time, location, vehicle);
        this.duration = vehicle.getLoadDuration();
    }

}

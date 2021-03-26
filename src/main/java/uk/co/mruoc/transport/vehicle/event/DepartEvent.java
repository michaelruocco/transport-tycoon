package uk.co.mruoc.transport.vehicle.event;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import uk.co.mruoc.transport.vehicle.Vehicle;

import java.time.Duration;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DepartEvent extends AbstractVehicleEvent {

    private final String destination;

    @Builder
    public DepartEvent(Duration time, String location, String destination, Vehicle vehicle) {
        super(Type.DEPART, time, location, vehicle);
        this.destination = destination;
    }

}

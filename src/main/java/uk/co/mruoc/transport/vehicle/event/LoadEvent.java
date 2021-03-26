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
public class LoadEvent extends HandlingEvent {

    private final Duration duration;

    @Builder
    public LoadEvent(Duration time, String location, Vehicle vehicle) {
        super(Type.LOAD, time, location, vehicle);
        this.duration = vehicle.getLoadDuration();
    }

}

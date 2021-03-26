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
public class UnloadEvent extends HandlingEvent {

    private final Duration duration;

    @Builder
    public UnloadEvent(Duration time, String location, Vehicle vehicle) {
        super(Type.UNLOAD, time, location, vehicle);
        this.duration = vehicle.getLoadDuration();
    }

}

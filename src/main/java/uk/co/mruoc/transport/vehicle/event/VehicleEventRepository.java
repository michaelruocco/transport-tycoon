package uk.co.mruoc.transport.vehicle.event;

public interface VehicleEventRepository {

    void clear();

    void store(VehicleEvent event);

    VehicleEvents getAll();

}

package uk.co.mruoc.transport.map;

import uk.co.mruoc.transport.location.terminal.Terminal;
import uk.co.mruoc.transport.location.terminal.TerminalNames;
import uk.co.mruoc.transport.location.terminal.Terminals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class DefaultTransportMap implements TransportMap {

    private final Terminal factory;
    private final Terminal port;
    private final Terminal warehouseA;
    private final Terminal warehouseB;

    private final Terminals terminals;
    private final Collection<Route> routes;

    public DefaultTransportMap(Duration portToWarehouseADuration) {
        this.factory = new Terminal(TerminalNames.FACTORY);
        this.port = new Terminal(TerminalNames.PORT);
        this.warehouseA = new Terminal(TerminalNames.WAREHOUSE_A);
        this.warehouseB = new Terminal(TerminalNames.WAREHOUSE_B);
        this.terminals = toTerminals(factory, port, warehouseA, warehouseB);
        this.routes = buildRoutes(portToWarehouseADuration);
    }

    @Override
    public Terminals getTerminals() {
        return terminals;
    }

    @Override
    public Terminal findTerminal(String name) {
        return terminals.findByName(name);
    }

    @Override
    public Route findRoute(String origin, String destination) {
        return routes.stream()
                .filter(route -> route.containsOrigin(origin))
                .filter(route -> route.containsDestination(destination))
                .findFirst()
                .orElseThrow(() -> new RouteNotFoundException(origin, destination));
    }

    private Collection<Route> buildRoutes(Duration portToWarehouseADuration) {
        Route factoryToWarehouseA = buildFactoryToWarehouseA(portToWarehouseADuration);
        Route factoryToWarehouseB = buildFactoryToWarehouseB();
        return toRoutes(factoryToWarehouseA, factoryToWarehouseB);
    }

    private Route buildFactoryToWarehouseA(Duration portToWarehouseADuration) {
        Leg factoryToPort = Leg.road()
                .origin(factory)
                .destination(port)
                .duration(Duration.ofHours(1))
                .build();
        Leg portToWarehouseA = Leg.shippingLane()
                .origin(port)
                .destination(warehouseA)
                .duration(portToWarehouseADuration)
                .build();
        return new Route(factoryToPort, portToWarehouseA);
    }

    private Route buildFactoryToWarehouseB() {
        return new Route(Leg.road()
                .origin(factory)
                .destination(warehouseB)
                .duration(Duration.ofHours(5))
                .build());
    }

    private static Terminals toTerminals(Terminal... terminals) {
        return new Terminals(Arrays.asList(terminals));
    }

    private static Collection<Route> toRoutes(Route... inputRoutes) {
        Collection<Route> routes = new ArrayList<>();
        Arrays.stream(inputRoutes).forEach(inputRoute -> {
            routes.add(inputRoute);
            routes.add(inputRoute.toReturnRoute());
        });
        return Collections.unmodifiableCollection(routes);
    }

}

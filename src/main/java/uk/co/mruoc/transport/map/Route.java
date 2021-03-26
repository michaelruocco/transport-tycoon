package uk.co.mruoc.transport.map;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Route {

    private final LinkedList<Leg> legs;

    public Route(Leg... legs) {
        this(Arrays.asList(legs));
    }

    public Route(Collection<Leg> legs) {
        this(new LinkedList<>(legs));
    }

    public boolean containsOrigin(String origin) {
        if (getOriginName().equals(origin)) {
            return true;
        }
        return legs.stream().anyMatch(leg -> leg.getOriginName().equals(origin));
    }

    public boolean containsDestination(String destination) {
        if (getDestinationName().equals(destination)) {
            return true;
        }
        return legs.stream().anyMatch(leg -> leg.getDestinationName().equals(destination));
    }

    public Leg findLegWithOrigin(String origin) {
        return legs.stream()
                .filter(leg -> leg.getOriginName().equals(origin))
                .findFirst()
                .orElseThrow(() -> new LegWithOriginNotFoundException(origin));
    }

    public String getOriginName() {
        return legs.getFirst().getOriginName();
    }

    public String getDestinationName() {
        return legs.getLast().getDestinationName();
    }

    public Route toReturnRoute() {
        List<Leg> returnLegs = toReturnLegs();
        Collections.reverse(returnLegs);
        return new Route(returnLegs);
    }

    private List<Leg> toReturnLegs() {
        return legs.stream()
                .map(Leg::toReturnLeg)
                .collect(Collectors.toList());
    }

}

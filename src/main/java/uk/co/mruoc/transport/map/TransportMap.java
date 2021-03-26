package uk.co.mruoc.transport.map;

import uk.co.mruoc.transport.location.terminal.Terminal;
import uk.co.mruoc.transport.location.terminal.Terminals;

public interface TransportMap {

    Terminals getTerminals();

    Terminal findTerminal(String name);

    Route findRoute(String origin, String destination);

}

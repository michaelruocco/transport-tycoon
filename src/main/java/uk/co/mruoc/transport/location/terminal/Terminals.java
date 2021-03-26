package uk.co.mruoc.transport.location.terminal;

import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
public class Terminals {

    private final Collection<Terminal> values;

    public void clearAllCargos() {
        values.forEach(Terminal::clearCargos);
    }

    public Terminal findByName(String name) {
        return values.stream()
                .filter(terminal -> terminal.hasName(name))
                .findFirst()
                .orElseThrow(() -> new TerminalNotFoundException(name));
    }

}

package uk.co.mruoc.transport.location.terminal;

public interface TerminalMother {

    static Terminal build() {
        return withName("terminal-name");
    }

    static Terminal withName(String name) {
        return new Terminal(name);
    }

}

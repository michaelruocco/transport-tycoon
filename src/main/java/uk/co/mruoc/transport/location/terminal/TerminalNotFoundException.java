package uk.co.mruoc.transport.location.terminal;

public class TerminalNotFoundException extends RuntimeException {

    public TerminalNotFoundException(String name) {
        super(name);
    }

}

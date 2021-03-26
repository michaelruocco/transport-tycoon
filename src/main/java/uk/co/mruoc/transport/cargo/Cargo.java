package uk.co.mruoc.transport.cargo;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class Cargo {

    private final long id;
    private final String origin;
    private final String destination;

}

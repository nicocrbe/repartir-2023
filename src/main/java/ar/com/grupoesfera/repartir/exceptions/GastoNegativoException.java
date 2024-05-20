package ar.com.grupoesfera.repartir.exceptions;

public class GastoNegativoException extends Throwable {
    public GastoNegativoException() {
        super("El gasto no puede ser negativo");
    };
}

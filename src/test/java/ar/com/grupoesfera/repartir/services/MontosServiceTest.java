package ar.com.grupoesfera.repartir.services;

import ar.com.grupoesfera.repartir.exceptions.DivisionInvalidaException;
import ar.com.grupoesfera.repartir.exceptions.GastoNegativoException;
import ar.com.grupoesfera.repartir.model.Gasto;
import ar.com.grupoesfera.repartir.model.Grupo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MontosServiceTest {

    MontosService montos;

    @BeforeEach
    void crear() {

        montos = new MontosService();
    }

    private BigDecimal $(int pesos, int centavos) {

        return BigDecimal.valueOf(pesos * 100 + centavos, 2);
    }

    @Test
    void inicializarTotalDelGrupo() {

        Grupo grupo = new Grupo();

        montos.inicializarTotal(grupo);

        assertThat(grupo.getTotal()).isEqualTo($(0,0));
    }

    @Test
    void acumularMontoDelGastoAlTotalDelGrupo() throws GastoNegativoException {

        Grupo grupo = new Grupo();
        grupo.setTotal($(300,51));

        Gasto gasto = new Gasto();
        gasto.setMonto($(23,90));

        montos.acumularAlTotal(grupo, gasto);

        assertThat(grupo.getTotal()).isEqualTo($(324,41));
    }

    @Test
    void dividirMonto() {

        BigDecimal montoDividido = montos.dividirMonto($(10, 50), 2);

        assertThat(montoDividido).isEqualTo($(5, 25));
    }

    @Test
    void divisionInexactaDeLosMontos() {

        BigDecimal montoDividido = montos.dividirMonto($(9,99), 2);

        assertThat(montoDividido).isEqualTo($(4,99));
    }

    @Test
    void dividirPorCero() {

        assertThrows(DivisionInvalidaException.class, ()-> montos.dividirMonto($(10,0), 0));
    }
}

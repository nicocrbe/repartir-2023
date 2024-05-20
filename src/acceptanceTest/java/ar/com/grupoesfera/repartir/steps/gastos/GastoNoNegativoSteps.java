package ar.com.grupoesfera.repartir.steps.gastos;

import ar.com.grupoesfera.repartir.steps.CucumberSteps;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class GastoNoNegativoSteps extends CucumberSteps {

    @Y("el usuario agrega un gasto de ${string} al grupo")
    public void elUsuarioAgregaUnGastoDe$AlGrupo(String gasto) {

        var agregarGasto = driver.findElement(By.id("agregarGastoGruposButton-1"));
        agregarGasto.click();
        var input = driver.findElement(By.id("montoGastoNuevoInput"));
        input.click();
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(Keys.BACK_SPACE);

        input.sendKeys(gasto);
        driver.findElement(By.id("guardarGastoNuevoButton")).click();
    }

    @Entonces("debería visualizar el grupo con total ${string}")
    public void deberiaVisualizarElGrupoConTotal(String total) {
        var grupoTD = driver.findElements(By.cssSelector("app-grupos p-card p-table tr"))
                .get(1).findElements(By.tagName("td")).get(2).getText();

        assertThat(grupoTD).contains(total);
    }

    @Entonces("se muestra un mensaje de error indicando que el gasto no puede ser negativo")
    public void seMuestraUnMensajeDeErrorIndicandoQueElGastoNoPuedeSerNegativo() {
        var wait = new WebDriverWait(driver, 2);
        var mensajesToast = wait.withMessage("Mostro Toast")
                .until(visibilityOfElementLocated(By.id("mensajesToast")));
        wait.withMessage("Título del Toast es 'Error'")
                .until(textToBePresentInElement(mensajesToast, "Error"));
        assertThat(mensajesToast.getText())
                .as("Descripción del Toast")
                .contains("No se puede guardar");
    }
}

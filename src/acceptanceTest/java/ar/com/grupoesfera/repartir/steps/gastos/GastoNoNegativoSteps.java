package ar.com.grupoesfera.repartir.steps.gastos;

import ar.com.grupoesfera.repartir.steps.CucumberSteps;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class GastoNoNegativoSteps extends CucumberSteps {

    @Y("el usuario agrega un gasto de ${string} al grupo")
    public void elUsuarioAgregaUnGastoDeAlGrupo(String gasto) {
        WebDriverWait wait = new WebDriverWait(driver, 10); // Incrementar tiempo de espera

        WebElement buttonAgregarGasto = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("agregarGastoGruposButton-1")));
        buttonAgregarGasto.click();

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("montoGastoNuevoInput")));

        input.click();
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(Keys.BACK_SPACE);

        input.sendKeys(gasto);
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("guardarGastoNuevoButton")));
        button.click();
    }

    @Entonces("se muestra un mensaje de error indicando que el gasto no puede ser negativo")
    public void seMuestraUnMensajeDeErrorIndicandoQueElGastoNoPuedeSerNegativo() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        WebElement mensajesToast = wait.withMessage("Mostro Toast")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("mensajesToast")));
        wait.withMessage("Título del Toast es 'Error'")
                .until(ExpectedConditions.textToBePresentInElement(mensajesToast, "Error"));
        assertThat(mensajesToast.getText())
                .as("Descripción del Toast")
                .contains("No se puede guardar");
    }

    @Entonces("se muestra un mensaje indicando que el gasto fue agregado correctamente")
    public void seMuestraUnMensajeIndicandoQueElGastoFueAgregadoCorrectamente() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement mensajesToast = wait.withMessage("Mostro Toast")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("mensajesToast")));
        wait.withMessage("Título del Toast es 'Éxito'")
                .until(ExpectedConditions.textToBePresentInElement(mensajesToast, "Gasto agregado"));
        assertThat(mensajesToast.getText())
                .as("Descripción del Toast")
                .contains("Gasto agregado");
    }
}

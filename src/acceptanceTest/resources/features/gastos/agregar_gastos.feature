# language: es

  Característica: Agregar gastos a un grupo

    Regla: Los grupos no pueden tener saldo negativo

      Escenario: Agregar un gasto positivo a un grupo
        Dado que el usuario inició Repartir
        Cuando el usuario crea un grupo
        Y el usuario agrega un gasto de $'1000' al grupo
        Entonces se muestra un mensaje indicando que el gasto fue agregado correctamente

      Escenario: Agregar un gasto negativo a un grupo
        Dado que el usuario inició Repartir
        Cuando el usuario crea un grupo
        Y el usuario agrega un gasto de $'-1000' al grupo
        Entonces se muestra un mensaje de error indicando que el gasto no puede ser negativo


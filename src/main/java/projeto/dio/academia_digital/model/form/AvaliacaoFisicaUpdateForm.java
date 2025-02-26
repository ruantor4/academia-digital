package projeto.dio.academia_digital.model.form;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoFisicaUpdateForm {

  @NotNull(message = "Preencha o campo corretamente.")
  @Positive(message = "${validatedValue}' precisa ser positivo.")
  private double peso;

  @NotNull(message = "Preencha o campo corretamente.")
  @Positive(message = "${validatedValue}' precisa ser positivo.")
  @DecimalMin(value = "150", message = "'${validatedValue}' precisa ser nomínimo {value}.")
  private double altura;
}

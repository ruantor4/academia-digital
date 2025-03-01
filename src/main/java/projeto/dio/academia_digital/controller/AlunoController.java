package projeto.dio.academia_digital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import projeto.dio.academia_digital.model.Aluno;
import projeto.dio.academia_digital.model.AvaliacaoFisica;
import projeto.dio.academia_digital.model.form.AlunoForm;
import projeto.dio.academia_digital.model.form.AlunoUpdateForm;
import projeto.dio.academia_digital.service.impl.AlunoServiceImpl;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

  @Autowired
  private AlunoServiceImpl alunoService;

  @GetMapping("/{id}")
  public ResponseEntity<Aluno> getAluno(@PathVariable Long id) {
    return ResponseEntity.ok(alunoService.get(id));
  }

  @GetMapping
  public ResponseEntity<List<Aluno>> getAll(
      @RequestParam(value = "dataDeNascimento", required = false) String dataDeNacimento) {
    return ResponseEntity.ok(alunoService.getAll(dataDeNacimento));
  }

  @PostMapping
  public ResponseEntity<Aluno> create(@Valid @RequestBody AlunoForm form) {
    Aluno alunoSave = alunoService.create(form);

    return ResponseEntity.status(HttpStatus.CREATED).body(alunoSave);
  }

  @GetMapping("/avaliacoes/{id}")
  public ResponseEntity<List<AvaliacaoFisica>> getAllAvaliacaoFisicaId(@PathVariable Long id) {
    return ResponseEntity.ok(alunoService.getAllAvaliacaoFisicaId(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Aluno> update(@PathVariable Long id, @RequestBody AlunoUpdateForm form) {
    try {
      Aluno alunoUp = alunoService.update(id, form);
      return ResponseEntity.ok(alunoUp);

    } catch (EntityNotFoundException e) {

      return ResponseEntity.notFound().build();
    }

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    try {
      alunoService.delete(id);
      return ResponseEntity.noContent().build(); // Retorna 204 No Content

    } catch (EntityNotFoundException e) {

      return ResponseEntity.notFound().build(); // Retorna 404 se o usuário não for encontrado
    }
  }

}

package projeto.dio.academia_digital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import projeto.dio.academia_digital.model.AvaliacaoFisica;
import projeto.dio.academia_digital.model.form.AvaliacaoFisicaForm;
import projeto.dio.academia_digital.service.impl.AvaliacaoFisicaServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

  @Autowired
  private AvaliacaoFisicaServiceImpl service;

  @PostMapping
  public AvaliacaoFisica create(@RequestBody AvaliacaoFisicaForm form) { 
    return service.create(form);
  }

  @GetMapping
  public List<AvaliacaoFisica> getAll() {
    return service.getAll();
  }

}

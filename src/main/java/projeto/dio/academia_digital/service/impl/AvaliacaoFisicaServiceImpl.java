package projeto.dio.academia_digital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projeto.dio.academia_digital.model.Aluno;
import projeto.dio.academia_digital.model.AvaliacaoFisica;
import projeto.dio.academia_digital.model.form.AvaliacaoFisicaForm;
import projeto.dio.academia_digital.model.form.AvaliacaoFisicaUpdateForm;
import projeto.dio.academia_digital.repository.AlunoRepository;
import projeto.dio.academia_digital.repository.AvaliacaoFisicaRepository;
import projeto.dio.academia_digital.service.IAvaliacaoFisicaService;

import java.util.List;

@Service
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {

  @Autowired
  private AvaliacaoFisicaRepository avaliacaoFisicaRepository;

  @Autowired
  private AlunoRepository alunoRepository;

  @Override
  public AvaliacaoFisica create(AvaliacaoFisicaForm form) {
    AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
    Aluno aluno = alunoRepository.findById(form.getAlunoId()).get();

    avaliacaoFisica.setAluno(aluno);
    avaliacaoFisica.setPeso(form.getPeso());
    avaliacaoFisica.setAltura(form.getAltura());

    return avaliacaoFisicaRepository.save(avaliacaoFisica);
  }

  @Override
  public AvaliacaoFisica get(Long id) {
    return null;
  }

  @Override
  public List<AvaliacaoFisica> getAll() {

    return avaliacaoFisicaRepository.findAll();
  }

  @Override
  public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm formUpdate) {
    return null;
  }

  @Override
  public void delete(Long id) {

  }
}

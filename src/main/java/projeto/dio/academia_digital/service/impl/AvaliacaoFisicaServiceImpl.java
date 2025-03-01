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

import javax.persistence.EntityNotFoundException;

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
    return avaliacaoFisicaRepository.findById(id)
          .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado com o ID: " + id));

  }

  
  @Override
  public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm formUpdate) {
    AvaliacaoFisica avalicaoFisica = avaliacaoFisicaRepository.getById(id);
    avalicaoFisica.setPeso(formUpdate.getPeso());
    avalicaoFisica.setAltura(formUpdate.getPeso());
    return avaliacaoFisicaRepository.save(avalicaoFisica);
  }

  @Override
  public void delete(Long id) { 
  if (!avaliacaoFisicaRepository.existsById(id)) {
    throw new EntityNotFoundException("Usuário não encontrado com o ID: " + id); 
  }
  avaliacaoFisicaRepository.deleteById(id);
  }
  
  @Override
  public List<AvaliacaoFisica> getAll() {

    return avaliacaoFisicaRepository.findAll();
  }

}

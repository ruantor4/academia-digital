package projeto.dio.academia_digital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projeto.dio.academia_digital.model.Aluno;
import projeto.dio.academia_digital.model.Matricula;
import projeto.dio.academia_digital.model.form.MatriculaForm;
import projeto.dio.academia_digital.repository.AlunoRepository;
import projeto.dio.academia_digital.repository.MatriculaRepository;
import projeto.dio.academia_digital.service.IMatriculaService;

import java.util.List;

@Service
public class MatriculaServiceImpl implements IMatriculaService {

  @Autowired
  private MatriculaRepository matriculaRepository;

  @Autowired
  private AlunoRepository alunoRepository;

  @Override
  public Matricula create(MatriculaForm form) {
    Matricula matricula = new Matricula();
    Aluno aluno = alunoRepository.findById(form.getAlunoId()).get();

    matricula.setAluno(aluno);

    return matriculaRepository.save(matricula);
  }

  @Override
  public Matricula get(Long id) {
    return matriculaRepository.findById(id).get();
  }

  @Override
  public List<Matricula> getAll(String bairro) {

    if (bairro == null) {
      return matriculaRepository.findAll();
    } else {
      return matriculaRepository.findAlunosMatriculadosBairro(bairro);
    }

  }

  @Override
  public void delete(Long id) {
  }

}

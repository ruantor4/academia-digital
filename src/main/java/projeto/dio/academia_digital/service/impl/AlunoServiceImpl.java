package projeto.dio.academia_digital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projeto.dio.academia_digital.infra.utils.JavaTimeUtils;
import projeto.dio.academia_digital.model.Aluno;
import projeto.dio.academia_digital.model.AvaliacaoFisica;
import projeto.dio.academia_digital.model.form.AlunoForm;
import projeto.dio.academia_digital.model.form.AlunoUpdateForm;
import projeto.dio.academia_digital.repository.AlunoRepository;
import projeto.dio.academia_digital.service.IAlunoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

@Service
public class AlunoServiceImpl implements IAlunoService {

  @Autowired
  private AlunoRepository alunoRepository;

  @Override
  public Aluno create(AlunoForm form) {
    Aluno aluno = new Aluno();
    aluno.setNome(form.getNome());
    aluno.setCpf(form.getCpf());
    aluno.setBairro(form.getBairro());
    aluno.setDataDeNascimento(form.getDataDeNascimento());

    return alunoRepository.save(aluno);
  }

  @Override
  public Aluno get(Long id) {
		return alunoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + id));
	}


  @Override
  public List<Aluno> getAll(String dataDeNascimento) {

    if (dataDeNascimento == null) {
      return alunoRepository.findAll();
    } else {
      LocalDate localDate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
      return alunoRepository.findByDataDeNascimento(localDate);
    }

  }

  @Override
  public Aluno update(Long id, AlunoUpdateForm form) {
      Optional<Aluno> alunoDb = alunoRepository.findById(id);
      if(alunoDb.isEmpty()){
        throw new EntityNotFoundException("Usuário não encontrado com o ID: " + id);
      }
      Aluno alunoExistente = alunoDb.get();

      if (form.getNome() != null) {
        alunoExistente.setNome(form.getNome());
        alunoExistente.setBairro(form.getBairro());
        alunoExistente.setDataDeNascimento(form.getDataDeNascimento());
      }
       
    return alunoRepository.save(alunoExistente);
  }

  @Override
  public void delete(Long id) {
    if (id == null) {
			throw new IllegalArgumentException("O ID não pode ser nulo");
		}
    boolean exists = alunoRepository.existsById(id);
		System.out.println("ID " + id + " existe? " + exists);;

    if(!exists){
      throw new EntityNotFoundException("Usuário não encontrado com o ID: " + id);
    }
    alunoRepository.deleteById(id);
	}

  @Override
  public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(Long id) {

    Aluno aluno = alunoRepository.findById(id).get();

    return aluno.getAvaliacoes();

  }

  

}

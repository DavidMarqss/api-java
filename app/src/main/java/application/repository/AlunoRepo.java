package application.repository;

import org.springframework.data.repository.CrudRepository;
import application.model.Aluno;

public interface AlunoRepo extends CrudRepository < Aluno, Long>{

}

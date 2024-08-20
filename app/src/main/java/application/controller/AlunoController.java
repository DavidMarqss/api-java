package application.controller;
 
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PathVariable;
 
import application.model.Aluno;
import application.repository.AlunoRepo;
 
@RestController
@RequestMapping("/alunos")
public class AlunoController {
   
    @Autowired
    private AlunoRepo alunoRepository;
 
    @GetMapping
    public Iterable<Aluno> list() {
        return alunoRepository.findAll();
    }
 
    @PostMapping
    public Aluno insert(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }
 
    @GetMapping("/{id}")
    public Aluno details(@PathVariable long id) {
        Optional<Aluno> resultado = alunoRepository.findById(id);
        if(resultado.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Sem aluno"
            );
        }
        return resultado.get();
    }
 
    @PutMapping("/{id}")
    public Aluno put(
        @PathVariable long id,
        @RequestBody Aluno novosDados) {
        Optional<Aluno> resultado = alunoRepository.findById(id);
        if(resultado.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Sem aluno"
            );
        }

        if(novosDados.getNome().isEmpty() ){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Nome inválido"
            );
        }
 
        resultado.get().setNome(novosDados.getNome());
        return alunoRepository.save(resultado.get());
    }
 
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if(!alunoRepository.existsById(id)){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Sem aluno"
            );
        }
        alunoRepository.deleteById(id);
    }
 
}
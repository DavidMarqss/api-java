package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.repository.AlunoRepo;
import application.model.Aluno;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepo alunoRepo;

    @GetMapping
    public Iterable<Aluno> list(){
        return alunoRepo.findAll();
    }

    @PostMapping
    public Aluno insert(@RequestBody Aluno aluno){
        return alunoRepo.save(aluno);
    }

}

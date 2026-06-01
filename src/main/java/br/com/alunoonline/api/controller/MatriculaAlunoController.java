package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.model.MatriculaAluno;
import br.com.alunoonline.api.service.MatriculaAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matriculas")
public class MatriculaAlunoController {

    @Autowired
    MatriculaAlunoService matriculaAlunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarMatriculaAluno(@RequestBody MatriculaAluno matriculaAluno) {
        matriculaAlunoService.criarMatriculaAluno(matriculaAluno);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MatriculaAluno> buscarTodasMatriculasAlunos() {
        return matriculaAlunoService.buscarTodasMatriculasAlunos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<MatriculaAluno> buscarMatriculaAlunoPorId(@PathVariable Long id) {
        return matriculaAlunoService.buscarMatriculaAlunoPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarMatriculaAlunoPorId(@PathVariable Long id) {
        matriculaAlunoService.deletarMatriculaAlunoPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarMatriculaAlunoPorId(@PathVariable Long id, @RequestBody MatriculaAluno matriculaAlunoAtualizada) {
        matriculaAlunoService.atualizarMatriculaAlunoPorId(id, matriculaAlunoAtualizada);
    }

}

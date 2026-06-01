package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.MatriculaAluno;
import br.com.alunoonline.api.repository.AlunoRepository;
import br.com.alunoonline.api.repository.DisciplinaRepository;
import br.com.alunoonline.api.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaAlunoService {

    @Autowired
    private MatriculaAlunoRepository matriculaAlunoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public void criarMatriculaAluno(MatriculaAluno matriculaAluno) {
        prepararRelacionamentos(matriculaAluno);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    public List<MatriculaAluno> buscarTodasMatriculasAlunos() {
        return matriculaAlunoRepository.findAll();
    }

    public Optional<MatriculaAluno> buscarMatriculaAlunoPorId(Long id) {
        return matriculaAlunoRepository.findById(id);
    }

    public void deletarMatriculaAlunoPorId(Long id) {
        matriculaAlunoRepository.deleteById(id);
    }

    public void atualizarMatriculaAlunoPorId(Long id, MatriculaAluno matriculaAlunoAtualizada) {
        Optional<MatriculaAluno> matriculaAlunoDoBancoDeDados = buscarMatriculaAlunoPorId(id);

        if (matriculaAlunoDoBancoDeDados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula nao encontrada no Banco de Dados");
        } else {
            MatriculaAluno matriculaAlunoParaEditar = matriculaAlunoDoBancoDeDados.get();

            if (matriculaAlunoAtualizada.getAluno() != null) {
                prepararAluno(matriculaAlunoAtualizada);
                matriculaAlunoParaEditar.setAluno(matriculaAlunoAtualizada.getAluno());
            }

            if (matriculaAlunoAtualizada.getDisciplina() != null) {
                prepararDisciplina(matriculaAlunoAtualizada);
                matriculaAlunoParaEditar.setDisciplina(matriculaAlunoAtualizada.getDisciplina());
            }

            matriculaAlunoParaEditar.setNota1(matriculaAlunoAtualizada.getNota1());
            matriculaAlunoParaEditar.setNota2(matriculaAlunoAtualizada.getNota2());
            matriculaAlunoParaEditar.setStatus(matriculaAlunoAtualizada.getStatus());

            matriculaAlunoRepository.save(matriculaAlunoParaEditar);
        }
    }

    private void prepararRelacionamentos(MatriculaAluno matriculaAluno) {
        prepararAluno(matriculaAluno);
        prepararDisciplina(matriculaAluno);
    }

    private void prepararAluno(MatriculaAluno matriculaAluno) {
        if (matriculaAluno.getAluno() != null && matriculaAluno.getAluno().getId() != null) {
            var aluno = alunoRepository.findById(matriculaAluno.getAluno().getId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Aluno nao encontrado no Banco de Dados"
                    ));
            matriculaAluno.setAluno(aluno);
        }
    }

    private void prepararDisciplina(MatriculaAluno matriculaAluno) {
        if (matriculaAluno.getDisciplina() != null && matriculaAluno.getDisciplina().getId() != null) {
            var disciplina = disciplinaRepository.findById(matriculaAluno.getDisciplina().getId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Disciplina nao encontrada no Banco de Dados"
                    ));
            matriculaAluno.setDisciplina(disciplina);
        }
    }

}

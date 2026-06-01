package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Disciplina;
import br.com.alunoonline.api.repository.DisciplinaRepository;
import br.com.alunoonline.api.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public void criarDisciplina(Disciplina disciplina) {
        prepararProfessor(disciplina);
        disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> buscarTodasDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> buscarDisciplinaPorId(Long id) {
        return disciplinaRepository.findById(id);
    }

    public void deletarDisciplinaPorId(Long id) {
        disciplinaRepository.deleteById(id);
    }

    public void atualizarDisciplinaPorId(Long id, Disciplina disciplinaAtualizada) {
        Optional<Disciplina> disciplinaDoBancoDeDados = buscarDisciplinaPorId(id);

        if (disciplinaDoBancoDeDados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina nao encontrada no Banco de Dados");
        } else {
            Disciplina disciplinaParaEditar = disciplinaDoBancoDeDados.get();

            disciplinaParaEditar.setNome(disciplinaAtualizada.getNome());
            disciplinaParaEditar.setDescricao(disciplinaAtualizada.getDescricao());
            disciplinaParaEditar.setCargaHoraria(disciplinaAtualizada.getCargaHoraria());

            if (disciplinaAtualizada.getProfessor() != null) {
                prepararProfessor(disciplinaAtualizada);
                disciplinaParaEditar.setProfessor(disciplinaAtualizada.getProfessor());
            }

            disciplinaRepository.save(disciplinaParaEditar);
        }
    }

    private void prepararProfessor(Disciplina disciplina) {
        if (disciplina.getProfessor() != null && disciplina.getProfessor().getId() != null) {
            var professor = professorRepository.findById(disciplina.getProfessor().getId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Professor nao encontrado no Banco de Dados"
                    ));
            disciplina.setProfessor(professor);
        }
    }

}

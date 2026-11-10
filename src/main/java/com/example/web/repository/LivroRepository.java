package com.example.web.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.web.model.Livro;

@Repository
public class LivroRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Livro> listarTodos() {
        String sql = "SELECT * FROM livro";
        List<Livro> resultado = new ArrayList<>();

        for (Map<String, Object> linha : jdbcTemplate.queryForList(sql)) {
            resultado.add(Livro.deMap(linha));
        }

        return resultado;
    }

    public int contarLivros() {
        String sql = "SELECT COUNT(*) FROM livro";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public List<Livro> listarAtePreco(double precoMaximo) {
        String sql = "SELECT * FROM livro WHERE preco <= ?";
        List<Livro> resultado = new ArrayList<>();

        for (Map<String, Object> linha : jdbcTemplate.queryForList(sql, precoMaximo)) {
            resultado.add(Livro.deMap(linha));
        }

        return resultado;
    }

    public void salvar(Livro livro) {
        String sql = "INSERT INTO livro (titulo, autor, ano, preco) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, livro.getTitulo(), livro.getAutor(), livro.getAno(), livro.getPreco());
    }

    public double somarValorAcervo() {
        String sql = "SELECT SUM(preco) FROM livro";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }

    public Livro buscarPorId(Long id) {
        String sql = "SELECT * FROM livro WHERE id = ?";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql, id);

        if (linhas.isEmpty()) {
            return null;
        }

        return Livro.deMap(linhas.get(0));
    }

    public List<Livro> listarOrdenadoPor(String coluna) {
        List<String> colunasPermitidas = List.of("titulo", "autor", "ano", "preco");

        if (coluna == null || !colunasPermitidas.contains(coluna)) {
            return listarTodos();
        }

        String sql = "SELECT * FROM livro ORDER BY " + coluna;
        List<Livro> resultado = new ArrayList<>();

        for (Map<String, Object> linha : jdbcTemplate.queryForList(sql)) {
            resultado.add(Livro.deMap(linha));
        }

        return resultado;
    }

    public List<Livro> listarDoMesmoAutor(String autor, Long idAtual) {
        String sql = "SELECT * FROM livro WHERE autor = ? AND id <> ?";
        List<Livro> resultado = new ArrayList<>();

        for (Map<String, Object> linha : jdbcTemplate.queryForList(sql, autor, idAtual)) {
            resultado.add(Livro.deMap(linha));
        }

        return resultado;
    }
}

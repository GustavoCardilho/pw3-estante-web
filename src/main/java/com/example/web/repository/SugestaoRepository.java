package com.example.web.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.web.model.Sugestao;

@Repository
public class SugestaoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void salvar(Sugestao sugestao) {
        String sql = "INSERT INTO sugestao (nome_cliente, livro_sugerido) VALUES (?, ?)";
        jdbcTemplate.update(sql, sugestao.getNomeCliente(), sugestao.getLivroSugerido());
    }

    public List<Sugestao> listarTodas() {
        String sql = "SELECT * FROM sugestao";
        List<Sugestao> resultado = new ArrayList<>();

        for (Map<String, Object> linha : jdbcTemplate.queryForList(sql)) {
            resultado.add(Sugestao.deMap(linha));
        }

        return resultado;
    }

    public int contarSugestoes() {
        String sql = "SELECT COUNT(*) FROM sugestao";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}

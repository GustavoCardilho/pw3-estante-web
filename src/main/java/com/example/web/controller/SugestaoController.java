package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.web.model.Sugestao;
import com.example.web.repository.SugestaoRepository;

@Controller
public class SugestaoController {

    @Autowired
    private SugestaoRepository sugestaoRepository;

    @GetMapping("/sugestoes/nova")
    public String formularioSugestao(Model model) {
        return "sugestao-nova";
    }

    @PostMapping("/sugestoes")
    public String receberSugestao(@RequestParam(name = "nomeCliente") String nomeCliente,
                                    @RequestParam(name = "livroSugerido") String livroSugerido, Model model) {

        if (nomeCliente.isBlank() || livroSugerido.isBlank()) {
            model.addAttribute("erro", "Preencha seu nome e o livro sugerido.");
            model.addAttribute("nomeCliente", nomeCliente);
            model.addAttribute("livroSugerido", livroSugerido);
            return "sugestao-nova";
        }

        Sugestao sugestao = new Sugestao();
        sugestao.setNomeCliente(nomeCliente);
        sugestao.setLivroSugerido(livroSugerido);
        sugestaoRepository.salvar(sugestao);

        return "redirect:/sugestoes?enviada=true";
    }

    @GetMapping("/sugestoes")
    public String listarSugestoes(@RequestParam(required = false) Boolean enviada, Model model) {
        model.addAttribute("sugestoes", sugestaoRepository.listarTodas());
        model.addAttribute("enviada", enviada);
        return "sugestoes";
    }
}

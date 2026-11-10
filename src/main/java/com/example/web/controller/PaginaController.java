package com.example.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaginaController {

    @GetMapping("/")
    @ResponseBody
    public String paginaInicial() {
        return "EstanteWeb: livros usados com história";
    }

    @GetMapping("/sobre")
    @ResponseBody
    public String sobre() {
        return "EstanteWeb é um site de venda de livros usados, onde você pode encontrar livros com história e contribuir para a sustentabilidade.";
    }

    @GetMapping("/contato")
    public String formularioContato(Model model) {
        return "contato";
    }

    @PostMapping("/contato")
    public String contato(@RequestParam(name = "nome") String nome, @RequestParam(name = "mensagem") String mensagem, Model model) {

        if (mensagem.isBlank()) {
            model.addAttribute("erro", "A mensagem não pode ficar em branco.");
            model.addAttribute("nome", nome);
            return "contato";
        }

        return "redirect:/contato/sucesso";
    }

    @GetMapping("/contato/sucesso")
    public String contatoSucesso() {
        return "contato-sucesso";
    }

}

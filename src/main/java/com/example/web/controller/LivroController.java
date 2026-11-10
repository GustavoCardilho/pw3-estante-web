package com.example.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.example.web.model.Livro;
import com.example.web.repository.LivroRepository;

@Controller
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/livros")
    @ResponseBody
    public String listar() {
        String resultado = "Acervo EstanteWeb: ";
        boolean primeiro = true;

        for (Livro livro : livroRepository.listarTodos()) {
            if (!primeiro) {
                resultado += " | ";
            }
            resultado += livro.getTitulo() + ", de " + livro.getAutor() + " (R$ " + livro.getPreco() + ")";
            primeiro = false;
        }

        return resultado;
    }

    @GetMapping("/livros/total")
    @ResponseBody
    public String total() {
        return "O acervo tem " + livroRepository.contarLivros() + " livros.";
    }

    @GetMapping("/livros/html")
    public String listarHtml(@RequestParam(required = false) String ordem, Model model) {
        model.addAttribute("livros", livroRepository.listarOrdenadoPor(ordem));
        return "livros";
    }

    @GetMapping("/livros/{id}")
    public String detalhar(@PathVariable Long id, Model model) {
        Livro livro = livroRepository.buscarPorId(id);

        if (livro == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        model.addAttribute("livro", livro);
        model.addAttribute("mesmoAutor", livroRepository.listarDoMesmoAutor(livro.getAutor(), id));
        return "livro";
    }

    @GetMapping("/livros/busca")
    public ResponseEntity<String> listarBusca(@RequestParam(name = "precoMaximo", defaultValue = "999") double precoMaximo) {
        List<Livro> listaLivros = livroRepository.listarAtePreco(precoMaximo);

        if (listaLivros.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Nenhum livro encontrado até R$ " + precoMaximo);
        }

        String resultado = "Acervo EstanteWeb: ";
        boolean primeiro = true;

        for (Livro livro : listaLivros) {
            if (!primeiro) {
                resultado += " | ";
            }
            resultado += livro.getTitulo() + ", de " + livro.getAutor() + " (R$ " + livro.getPreco() + ")";
            primeiro = false;
        }

        return ResponseEntity.ok()
            .header("X-Total-Encontrados", "" + listaLivros.size())
            .body(resultado);
    }

    @GetMapping("/livros/novo")
    public String formularioLivro(Model model) {
        return "livro-novo";
    }

    @PostMapping("/livros")
    public String cadastrar(@RequestParam(name = "titulo") String titulo,
                            @RequestParam(name = "autor") String autor,
                            @RequestParam(name = "ano") int ano,
                            @RequestParam(name = "preco") double preco, Model model) {

        if (titulo.isBlank() || autor.isBlank() || preco <= 0) {
            model.addAttribute("erro", "Informe título, autor e um preço maior que zero.");
            model.addAttribute("titulo", titulo);
            model.addAttribute("autor", autor);
            model.addAttribute("ano", ano);
            model.addAttribute("preco", preco);
            return "livro-novo";
        }

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAno(ano);
        livro.setPreco(preco);
        livroRepository.salvar(livro);

        return "redirect:/livros/html";
    }

    @GetMapping("/livros/valor-total")
    @ResponseBody
    public String valorTotal() {
        return "O acervo vale R$ " + livroRepository.somarValorAcervo();
    }

    @GetMapping(value = "/livros/quem-acessa")
    @ResponseBody
    public ResponseEntity<String> quemAcessa(@RequestHeader("User-Agent") String header) {
        return ResponseEntity.ok("Acesso vindo de: " + header);
    }
}

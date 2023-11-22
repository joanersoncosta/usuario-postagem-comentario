package dev.wakandaacademy.postagem.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/postagem")
public interface PostagemAPI {

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	PostagemIdResponse criarPostagem(@RequestBody @Valid PostagemRequest postagemRequest);

}

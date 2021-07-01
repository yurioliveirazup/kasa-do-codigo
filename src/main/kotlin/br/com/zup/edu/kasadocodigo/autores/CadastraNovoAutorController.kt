package br.com.zup.edu.kasadocodigo.autores

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api/autores")
class CadastraNovoAutorController(val autorRepository: AutorRepository) {

    @PostMapping
    fun cadastra(@RequestBody @Valid novoAutor: NovoAutorRequest, uriBuilder: UriComponentsBuilder) = novoAutor.paraAutor()
            .also(autorRepository::save)
            .let {
                    val uri = uriBuilder.path("/api/autores/{id}").buildAndExpand(it.id).toUri()
                    ResponseEntity.created(uri).build<Any>()
            }
}

data class NovoAutorRequest(@field:NotBlank val nome: String,
                            @field:NotBlank @field:Email val email: String,
                            @field:NotBlank @field:Size(max = 400) val descricao: String,
) {

    fun paraAutor() = Autor(nome = nome, descricao = descricao, email = email)
}
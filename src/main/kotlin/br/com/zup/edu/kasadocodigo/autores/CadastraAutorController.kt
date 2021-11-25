package br.com.zup.edu.kasadocodigo.autores

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api/autores")
class CadastraAutorController(val autorRepository: AutorRepository) {

    @PostMapping
    fun cadastra(@RequestBody @Valid request: NovoAutorRequest): ResponseEntity<Any> {

        println(request)

        val novoAutor = request.paraAutor()

        autorRepository.save(novoAutor)

        // converter a request para o modelo

        // salvar o modelo

        return ResponseEntity.ok().build()

    }

}


data class NovoAutorRequest(@field:NotBlank val nome: String,
                            @field:NotBlank @field:Email val email: String,
                            @field:NotBlank @field:Size(max = 400) val descricao: String,
) {

    fun paraAutor() = Autor(nome = nome,
                            descricao = descricao,
                            email = email)

}

package br.com.zup.edu.kasadocodigo.autores

import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api/autores")
class CadastraNovoAutorController(val autorRepository: AutorRepository) {

    @PostMapping
    fun cadastra(@Valid @RequestBody request: NovoAutorRequest) = request.paraAutor()
                    .also(autorRepository::save)
                    .let { ResponseEntity.ok().build<Any>() }


    @InitBinder("novoAutorRequest")
    fun binder(binder: WebDataBinder) {
        binder.addValidators(CampoUnicoValidator(autorRepository))
    }
}

class CampoUnicoValidator(val autorRepository: AutorRepository) : Validator {

    override fun supports(clazz: Class<*>): Boolean {
        return clazz.isAssignableFrom(NovoAutorRequest::class.java)
    }

    override fun validate(target: Any, errors: Errors) {
        val autor = target as NovoAutorRequest // (NovoAutorRequest) target

        if (autor.email.isNullOrBlank()) {
            errors.rejectValue("email", "email.naoInformado", "Email nao informado")
            return
        }

        if (autorRepository.existsByEmail(autor.email)) {
            errors.rejectValue("email", "email.jaCadastrado", "Email ja cadastrado")
        }

    }

}

data class NovoAutorRequest(@field:NotBlank val nome: String?,
                            @field:NotBlank @field:Email val email: String?,
                            @field:NotBlank @field:Size(max = 400) val descricao: String?,
) {

    fun paraAutor() = Autor(nome!!, email!!, descricao!!)
}

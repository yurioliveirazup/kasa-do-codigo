package br.com.zup.edu.kasadocodigo.autores

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
class Autor(@field:NotBlank val nome: String,
            @field:NotBlank @field:Email val email: String,
            @field:NotBlank @field:Size(max = 400) val descricao: String,
) {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null
}

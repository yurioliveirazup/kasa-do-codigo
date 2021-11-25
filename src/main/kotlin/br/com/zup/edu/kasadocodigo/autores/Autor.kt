package br.com.zup.edu.kasadocodigo.autores

import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
class Autor(@field:NotBlank @Column(nullable = false) val nome: String,
            @field:NotBlank @field:Email @Column(nullable = false) val email: String,
            @field:NotBlank @field:Size(max = 400) @Column(nullable = false) val descricao: String,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null


    val criadoEm = LocalDateTime.now()

}
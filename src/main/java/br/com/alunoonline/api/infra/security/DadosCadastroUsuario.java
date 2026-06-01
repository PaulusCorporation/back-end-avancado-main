package br.com.alunoonline.api.infra.security;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank(message = "Login e obrigatorio.")
        String login,

        @NotBlank(message = "Senha e obrigatoria.")
        String senha
) {
}

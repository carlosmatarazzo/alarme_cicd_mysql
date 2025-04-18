package br.com.fiap.alarme.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmbienteConfig {

    @Value("${app.env:prd}")
    private String ambiente;

    public String sufixo() {
        return "_" + ambiente;
    }

    public String tabela(String base) {
        return base + sufixo();
    }

    public String sequence(String base) {
        return "SEQ_" + base.toUpperCase() + sufixo().toUpperCase();
    }
}

package br.edu.utfpr.pb.pw.spark.model;

public class Pessoa {

    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;

    public Pessoa() {
    }

    public Pessoa(String nome, String cpf, String telefone, String rua, String numero, String complemento, String bairro, String cep, String cidade, String estado) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }


    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getCpf() {

        return cpf;
    }

    public void setCpf(String cpf) {

        this.cpf = cpf;
    }

    public String getTelefone() {

        return telefone;
    }

    public void setTelefone(String telefone) {

        this.telefone = telefone;
    }

    public String getRua() {

        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {

        return numero;
    }

    public void setNumero(String numero) {

        this.numero = numero;
    }

    public String getComplemento() {

        return complemento;
    }

    public void setComplemento(String complemento) {

        this.complemento = complemento;
    }

    public String getBairro() {

        return bairro;
    }

    public void setBairro(String bairro) {

        this.bairro = bairro;
    }

    public String getCep() {

        return cep;
    }

    public void setCep(String cep) {

        this.cep = cep;
    }

    public String getCidade() {

        return cidade;
    }

    public void setCidade(String cidade) {

        this.cidade = cidade;
    }

    public String getEstado() {

        return estado;
    }

    public void setEstado(String estado) {

        this.estado = estado;
    }
}

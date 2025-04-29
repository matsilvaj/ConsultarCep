import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Cep {
    private Scanner scanner = new Scanner(System.in);
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String regiao;

    public Cep (Endereco endereco) {
        this.rua = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cidade = endereco.localidade();
        this.estado = endereco.estado();
        this.regiao = endereco.regiao();
    }

    public Cep() {

    }

    @Override
    public String toString() {
        return "\n" + rua + " | Bairro: " + bairro + " | Cidade: "
                + cidade + " | Estado: " + estado + " | Região: " + regiao;
    }

    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public void Consultar() {
        System.out.print("Digite o número do cep: ");
        String numeroCep = scanner.nextLine();

        String urlCep = "https://viacep.com.br/ws/" + numeroCep + "/json/";

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlCep))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            Endereco consultaCep = gson.fromJson(json, Endereco.class);

            Cep meuCep = new Cep(consultaCep);
            System.out.println(meuCep);

            GeradorDeArquivo gerador = new GeradorDeArquivo();
            gerador.salvaJson(consultaCep);

        } catch (Exception e) {
            throw new RuntimeException("Cep inválido, digite novamente");
        }
    }
}

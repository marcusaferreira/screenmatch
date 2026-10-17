package br.com.alura.screenmatch.service;


import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import com.openai.models.responses.ResponseOutputText;

public class ConsultaChatGPT {

    public static String obterTraducao(String texto) {
        String prompt = "Traduza o seguinte texto para o português: " + texto;
        OpenAIClient client = OpenAIOkHttpClient.fromEnv();

        ResponseCreateParams params =
                ResponseCreateParams.builder()
                        .input(prompt)
                        .model("gpt-6-astra")
                        .maxOutputTokens(1000)
                        .build();

        Response response = client.responses().create(params);
        response.output().stream()
                .flatMap(item -> item.message().stream())
                .flatMap(message -> message.content().stream())
                .flatMap(content -> content.outputText().stream())
                .forEach(outputText -> IO.println(outputText.text()));

        return response.output().stream()
                .flatMap(item -> item.message().stream())
                .flatMap(message -> message.content().stream())
                .flatMap(content -> content.outputText().stream())
                .findFirst()
                .map(ResponseOutputText::text)
                .orElse("Tradução não encontrada.");

    }
}

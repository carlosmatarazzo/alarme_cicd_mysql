package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.DepositoModel;
import context.WorldContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CadastroDepositosService {

    private final DepositoModel depositoModel = new DepositoModel();
    public final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    public Response response;
    private final String baseUrl = "http://localhost:8080/api";
    private final String schemasPath = "src/test/resources/schemas/";
    private JSONObject jsonSchema;
    private final ObjectMapper mapper = new ObjectMapper();

    public void setFieldsDeposito(String field, String value) {
        switch (field) {
            case "recipienteId" -> depositoModel.setRecipienteId(value);
            case "peso" -> depositoModel.setPeso(Integer.parseInt(value));
            case "data" -> depositoModel.setData(value);
            default -> throw new IllegalStateException("Campo inesperado: " + field);
        }
    }

    public void createDeposito(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(depositoModel);

        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    public void retrieveDepositoId() {
        int id = gson.fromJson(response.jsonPath().prettify(), DepositoModel.class).getDepositoId();
        WorldContext.depositoId = String.valueOf(id);
    }

    public String getDepositoId() {
        return WorldContext.depositoId;
    }

    public void deleteDeposito(String endPoint) {
        String id = WorldContext.depositoId;
        String url = String.format("%s%s/%s", baseUrl, endPoint, id);

        System.out.println(">>> Enviando DELETE para: " + url);

        response = given()
                .accept(ContentType.JSON)
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }

    public void setContract(String contract) throws IOException {
        switch (contract) {
            case "Cadastro bem-sucedido de deposito" ->
                    jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-deposito.json");
            default -> throw new IllegalStateException("Contrato inesperado: " + contract);
        }
    }

    private JSONObject loadJsonFromFile(String filePath) throws IOException {
        String content = Files.readString(Paths.get(filePath));
        try {
            return new JSONObject(content);
        } catch (JSONException e) {
            System.err.println("Erro ao analisar o arquivo JSON: " + filePath);
            e.printStackTrace();
            throw new IOException("Erro ao carregar o arquivo JSON", e);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException {
        String jsonBody = response.getBody().asString(); // ✅ força string
        JSONObject jsonResponse = null;
        try {
            jsonResponse = new JSONObject(jsonBody); // ✅ sem ambiguidade de tipo
        } catch (JSONException e) {
            System.err.println("Erro ao analisar o corpo da resposta JSON:");
            System.err.println("Corpo da resposta: " + jsonBody);
            e.printStackTrace();
            throw new IOException("Erro ao analisar o corpo da resposta JSON", e);
        }

        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString()); // ✅ certo

        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        return schema.validate(jsonResponseNode);
    }


}
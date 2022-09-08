import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Юзаем Яндекс переводчик
public class Translator {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("Введите предложение на русском языке:");
        Scanner sc = new Scanner(System.in);
        String sentenceToTranslate = sc.nextLine();
        sc.close();

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://translate.api.cloud.yandex.net/translate/v2/translate";
        // Необходимо в запросе передавать 2 хедера
        // Для этого заведем HttpHeaders из пакета Спринга
        HttpHeaders headers = new HttpHeaders();
        // Первый указывает, что мы в теле запроса передаем JSON
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Второй - указывам наш токен авторизации
        headers.add("Authorization", "Bearer " + "some_token");

        Map<String, String > jsonData = new HashMap<>();
        // Свой id
        jsonData.put("folderId", "some_folder_id");
        // На какой язык переводим
        jsonData.put("targetLanguageCode", "en");
        // Передаем массив тексов на перевод
        jsonData.put("texts", "[" + sentenceToTranslate + "]");

        // Оборачиваем в запрос наш самодельный JSON и хедеры
        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonData, headers);

        // Парсим JSON в наш собственный объект
        YandexResponse response = restTemplate.postForObject(url, request, YandexResponse.class);
//
//        // Теперь надо распарсить JSON с помощью Jackson
//        ObjectMapper mapper = new ObjectMapper();
//        // readTree возвращает JsonNode
//        JsonNode object = mapper.readTree(response);



        System.out.println("Перевод: " + response.getTranslations().get(0).getText());
    }
}

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Consumer {
    public static void main(String[] args) {
        // С помощью этого класса мы делаем запросы к удаленным сервисам
        RestTemplate restTemplate = new RestTemplate();

        // Для отправки можем использовать мапу
        Map<String, String> jsonToSend = new HashMap<>();
        jsonToSend.put("name", "TOW");
        jsonToSend.put("job", "Leader");
        // Для отправки мы должны упаковать мапу в HttpEntity (это запрос/request)
        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonToSend);


        String url = "https://reqres.in/api/users";
        // Post запрос, дополнительно возвращает нам JSON
        String response = restTemplate.postForObject(url, request, String.class);

        System.out.println(response);
    }
}

import java.util.List;

// Класс для всего ответа/response от Яндекса
public class YandexResponse {

    private List<Translation> translations;

    public List<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
    }
}

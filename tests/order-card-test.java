import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderCardTest {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "http://localhost:9999";
        Configuration.headless = true;
    }

    @Test
    void shouldSubmitRequestSuccessfully() {
        open("/");
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79991112233");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='order-success']").shouldBe(visible);
    }

    @Test
    void shouldShowErrorForInvalidName() {
        open("/");
        $("[data-test-id='name'] input").setValue("John Doe");
        $("[data-test-id='phone'] input").setValue("+79991112233");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='name'] .input__sub").shouldHave(visible.text("Имя и Фамилия указаны неверно"));
    }

    @Test
    void shouldShowErrorForInvalidPhone() {
        open("/");
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("89991112233");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(visible.text("Телефон указан неверно"));
    }

    @Test
    void shouldShowErrorIfCheckboxNotSelected() {
        open("/");
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79991112233");
        $("button.button").click();
        $("[data-test-id='agreement'].input_invalid").shouldBe(visible);
    }
}

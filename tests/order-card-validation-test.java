import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OrderCardValidationTest {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "http://localhost:9999";
        Configuration.headless = true;
    }

    @Test
    void shouldShowErrorIfNameFieldIsEmpty() {
        open("/");
        $("[data-test-id='phone'] input").setValue("+79991112233");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='name'].input_invalid").shouldBe(visible);
    }

    @Test
    void shouldShowErrorIfPhoneFieldIsEmpty() {
        open("/");
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='phone'].input_invalid").shouldBe(visible);
    }

    @Test
    void shouldShowErrorIfAgreementNotChecked() {
        open("/");
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79991112233");
        $("button.button").click();
        $("[data-test-id='agreement'].input_invalid").shouldBe(visible);
    }

    @Test
    void shouldShowErrorForInvalidNameFormat() {
        open("/");
        $("[data-test-id='name'] input").setValue("John Doe");
        $("[data-test-id='phone'] input").setValue("+79991112233");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='name'].input_invalid").shouldBe(visible);
        $("[data-test-id='name'] .input__sub").shouldHave(text("Имя и Фамилия указаны неверно"));
    }

    @Test
    void shouldShowErrorForInvalidPhoneFormat() {
        open("/");
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("89991112233");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='phone'].input_invalid").shouldBe(visible);
        $("[data-test-id='phone'] .input__sub").shouldHave(text("Телефон указан неверно"));
    }
}

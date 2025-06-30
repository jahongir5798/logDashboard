package uz.jahonservice.dashboard.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Jahongir",
                        email = "jahongirjuraqulov5787@gmail.com",
                        url = "https://t.me/jahongirjuraqulov5787"
                ),
                description = "Simple dashboard ",
                title = "bu yerda title bulishi mumkin edi",
                version = "1.0 - demo",
                license = @License(
                        name = "litsenziyamiz yuq umman"
                ),
                termsOfService = "xizmat kursatish shartlari"
        )
)
public class OpenApiConfig {
}

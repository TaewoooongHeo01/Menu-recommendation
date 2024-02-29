package menurecommendation.menurecommendation.controller;

import lombok.RequiredArgsConstructor;
import menurecommendation.menurecommendation.service.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.net.MalformedURLException;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final IngredientService ingredientService;

    @GetMapping("/")
    public String main() throws IOException, org.json.simple.parser.ParseException {
        ingredientService.getIRDNT();
        return "main";
    }
}

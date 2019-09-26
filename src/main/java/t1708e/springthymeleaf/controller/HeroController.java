package t1708e.springthymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import t1708e.springthymeleaf.entity.Hero;
import t1708e.springthymeleaf.service.HeroService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/heroes")
public class HeroController {

    @Autowired
    HeroService heroService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<Hero> heroes = heroService.heroes();
        model.addAttribute("heroes", heroes);
        return "hero/index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String detail(@PathVariable int id, Model model) {
        Hero hero = heroService.getById(id);
        if (hero == null) {
            return "error/404";
        }
        model.addAttribute("hero", hero);
        return "hero/detail";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String create(Model model) {
        model.addAttribute("hero", new Hero());
        return "hero/form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String store(Model model, @Valid Hero hero, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("hero", hero);
            return "hero/form";
        }
        heroService.create(hero);
        return "redirect:/heroes";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Hero hero = heroService.getById(id);
        if (hero == null) {
            return "error/404";
        }
        model.addAttribute("hero", hero);
        return "hero/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit/{id}")
    public String update(@PathVariable int id, Model model, Hero updateHero) {
        Hero hero = heroService.getById(id);
        if (hero == null) {
            return "error/404";
        }
        hero.setName(updateHero.getName());
        hero.setDescription(updateHero.getDescription());
        hero.setHistory(updateHero.getHistory());
        heroService.update(hero);
        return "redirect:/heroes";
    }

    // viết ajax call.
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseBody
    public ResponseEntity<Object> update(@PathVariable int id) {
        HashMap<String, Object> mapResponse = new HashMap<>();
        Hero hero = heroService.getById(id);
        if (hero == null) {
            mapResponse.put("status", HttpStatus.NOT_FOUND.value());
            mapResponse.put("message", "Hero is not found!");
            return new ResponseEntity<>(mapResponse, HttpStatus.NOT_FOUND);
        }
        heroService.delete(hero);
        mapResponse.put("status", HttpStatus.OK.value());
        mapResponse.put("message", "Delete success");
        return new ResponseEntity<>(mapResponse, HttpStatus.OK);
    }
}

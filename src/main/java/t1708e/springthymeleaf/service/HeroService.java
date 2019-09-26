package t1708e.springthymeleaf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import t1708e.springthymeleaf.entity.Hero;
import t1708e.springthymeleaf.repository.HeroRepository;

import java.util.Calendar;
import java.util.List;

@Service
public class HeroService {

    @Autowired
    HeroRepository heroRepository;

    public List<Hero> heroes() {
        //heroRepository.findAll(PageRequest.of(1, 3));
        return heroRepository.findActiveHero(1);
    }

    public Page<Hero> heroes(Specification specification, int page, int limit) {
        return heroRepository.findAll(specification, PageRequest.of(page - 1, limit));
    }

    public Hero getById(int id) {
        return heroRepository.findById(id).orElse(null);
    }

    public Hero create(Hero hero) {
        hero.setStatus(1);
        hero.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        hero.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return heroRepository.save(hero);
    }

    public Hero update(Hero hero) {
        hero.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return heroRepository.save(hero);
    }

    public boolean delete(Hero hero) {
        hero.setDeletedAt(Calendar.getInstance().getTimeInMillis());
        hero.setStatus(-1);
        heroRepository.save(hero);
        return true;
    }
}

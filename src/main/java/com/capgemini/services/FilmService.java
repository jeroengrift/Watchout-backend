package com.capgemini.services;

import com.capgemini.data.FilmRepository;
import com.capgemini.domain.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService {
    private FilmRepository repository;

    @Autowired
    public void setRepository(FilmRepository repository) {
        this.repository = repository;
    }

    public Film create(Film film) {
        return repository.save(film);
    }

    public List<Film> all() {
        Iterable<Film> iterable = repository.findAll();
        List<Film> categories = new ArrayList<>();
        iterable.forEach(categories::add);
        return categories;
    }

    public Film read(final int id) {
        Optional<Film> film = repository.findById((id));
        if (film.isPresent()) {
            return film.get();
        }

        return null;
    }

    public Film update(Film film) {
        Optional<Film> oldItem = repository.findById(film.getId());
        if (oldItem.isPresent()) {
            if (film.getName() != null) {
                oldItem.get().setName(film.getName());
                oldItem.get().setDescription(film.getDescription());
                oldItem.get().setYoutubeId(film.getYoutubeId());
                oldItem.get().setRating(film.getRating());
                oldItem.get().setLon(film.getLon());
                oldItem.get().setLat(film.getLat());

            }
            return repository.save(oldItem.get());
        }

        return null;
    }

    public void delete(final int id) {
        repository.deleteById(id);
    }
}
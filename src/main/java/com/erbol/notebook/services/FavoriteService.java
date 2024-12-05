package com.erbol.notebook.services;


import com.erbol.notebook.entities.Favorite;
import com.erbol.notebook.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    public List<Favorite> getFavoritesByUserId(Long userId) {
        return favoriteRepository.findAll(); // Customize this method to filter by userId.
    }

    public Favorite addFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    public void removeFavorite(Long id) {
        favoriteRepository.deleteById(id);
    }

    public List<Favorite> getAllFav() {
        return favoriteRepository.findAll();
    }
}

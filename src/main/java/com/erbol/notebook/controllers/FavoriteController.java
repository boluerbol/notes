package com.erbol.notebook.controllers;


import com.erbol.notebook.entities.Favorite;
import com.erbol.notebook.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Favorite>> getAll() {
        return ResponseEntity.ok(favoriteService.getAllFav());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Favorite>> getFavoritesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriteService.getFavoritesByUserId(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<Favorite> addFavorite(@RequestBody Favorite favorite) {
        return ResponseEntity.ok(favoriteService.addFavorite(favorite));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> removeFavorite(@PathVariable Long id) {
        favoriteService.removeFavorite(id);
        return ResponseEntity.noContent().build();
    }
}

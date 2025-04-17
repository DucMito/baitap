package com.example.movieapp.api;

import com.example.movieapp.entity.Episode;
import com.example.movieapp.model.request.CreateEpisodeRequest;
import com.example.movieapp.service.EpisodeService;
import com.example.movieapp.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/episodes")
public class AdminEpisodesApi {
    private final MovieService movieService;
    private final EpisodeService episodeService;
    @GetMapping("/{movieId}")
    public ResponseEntity<?> getAllEpisodes(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @PathVariable("movieId") Integer movieId) {

        Page<Episode> moviePage = episodeService.findByStatusAndMovieId(true, page, pageSize, movieId);
        return ResponseEntity.ok(moviePage);
    }

    @PostMapping("")
    public ResponseEntity<?> addEpisodes(@RequestBody CreateEpisodeRequest createEpisodeRequest) {
        Episode episodes = episodeService.createEpisodes(createEpisodeRequest);
        return ResponseEntity.ok(episodes);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Integer id, @RequestBody UpdateEpisodeRequest updateEpisodeRequest) {
        Episode episodes = episodeService.updateEpisodes(updateEpisodeRequest,id);
        return ResponseEntity.ok(episodes);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        episodeService.deleteEpisodes(id);
        return ResponseEntity.ok().build();
    }
}
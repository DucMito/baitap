package com.example.movieapp.service;

import com.example.movieapp.entity.Episode;
import com.example.movieapp.entity.Movie;
import com.example.movieapp.entity.User;
import com.example.movieapp.exception.BadRequestException;
import com.example.movieapp.model.enums.UserRole;
import com.example.movieapp.model.request.CreateEpisodeRequest;
import com.example.movieapp.model.request.UpdateEpisodeRequest;
import com.example.movieapp.repository.EpisodeRepository;
import com.example.movieapp.repository.MovieRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.FileStore;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final EpisodeRepository episodeRepository;

    private HttpSession session;

    private final MovieRepository movieRepository;

    public List<Episode> findEpisodesByMovieId(Integer id) {
        return episodeRepository.findByMovie_IdAndStatusOrderByDisplayOrderAsc(id, true);
    }

    public Episode findEpisodeByDisplayOrder(Integer id, String tap) {
        Integer displayOrder = tap.equals("full") ? 1 : Integer.parseInt(tap);
        // select * from episodes where movie_id = id and display_order = displayOrder and status = true
        return episodeRepository.findByMovie_IdAndDisplayOrderAndStatus(id, displayOrder, true);
    }

    public Page<Episode> findByStatusAndMovieId(boolean b, Integer page, Integer pageSize, Integer movieId) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new BadRequestException("Cần login !!");
        }

        if (user.getRole() != UserRole.ADMIN) {
            throw new BadRequestException("Không quyền !!");
        }

        if (movieId == null) {
            throw new BadRequestException("Enter movieId !!");
        }

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new BadRequestException("Không tìm thấy phim ID: " + movieId));

        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Episode> episodesPage = episodeRepository.findByMovie_IdAndStatus(movieId, true, pageable);
        return episodesPage;
    }

    public Episode createEpisodes(CreateEpisodeRequest request) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new BadRequestException("Cần login");
        }

        if (user.getRole() != UserRole.ADMIN) {
            throw new BadRequestException("Không quyền !!");
        }
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new BadRequestException("Không tìm thấy phim với ID: " + request.getMovieId()));
        Episode episodes = Episode.builder()
                .name(request.getName())
                .displayOrder(request.getDisplayOrder())
                .status(request.getStatus())
                .movie(movie)
                .build();

        return   episodeRepository.save(episodes);
    }

    public Episode updateEpisodes(UpdateEpisodeRequest request, Integer id) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new BadRequestException("Cần login");
        }

        if (user.getRole() != UserRole.ADMIN) {
            throw new BadRequestException("Không quyền !!");
        }

        // Kiểm tra xem tập phim có tồn tại không
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Không tìm thấy tập phim  ID: " + id));

        // Cập nhật thông tin tập phim từ request
        episode.setName(request.getName());
        episode.setDisplayOrder(request.getDisplayOrder());
        episode.setStatus(request.getStatus());

        return episodeRepository.save(episode);
    }


    public void deleteEpisodes(Integer id) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new BadRequestException("Cần login");
        }

        if (user.getRole() != UserRole.ADMIN) {
            throw new BadRequestException("Không quyền !!");
        }
        Episode episodes = episodeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Không tìm thấy tập phim  ID: " + id));
        episodeRepository.delete(episodes);
    }


}

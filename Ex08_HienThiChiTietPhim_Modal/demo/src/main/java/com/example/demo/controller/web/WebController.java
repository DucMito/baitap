package com.example.demo.controller.web;

import com.example.demo.entity.Movie;
import com.example.demo.model.enums.MovieType;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.management.MonitorInfo;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final MovieService movieService;

    @GetMapping("/")
    public String getHomePage(Model model){
        List<Movie> hotMovies = movieService.findHotMovie(true, 4);
        List<Movie> phimLeList = movieService.findByType(MovieType.PHIM_LE, true, 1, 6).getContent();
        List<Movie> phimBoList = movieService.findByType(MovieType.PHIM_BO, true, 1, 6).getContent();
        List<Movie> phimChieuRapList = movieService.findByType(MovieType.PHIM_CHIEU_RAP, true, 1, 6).getContent();
        model.addAttribute("hotMovies", hotMovies);
        model.addAttribute("phimLeList", phimLeList);
        model.addAttribute("phimBoList", phimBoList);
        model.addAttribute("phimChieuRapList", phimChieuRapList);
        return "index";
    }

    @GetMapping("/phim_bo")
    public String getPhimBoPage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_BO, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", moviePage.getTotalPages());
        return "phim_bo";
    }

    @GetMapping("/phim/{id}/{slug}")
    public String getMovieDetailsPage(@PathVariable Integer id, @PathVariable String slug, Model model) {
        Movie movieList = movieService.findByIdSlugStatus(id, slug);
        List<Movie> moviePage = movieService.findBySlugAndStatus(movieList.getType(),true,6);
        model.addAttribute("movieList", movieList);
        model.addAttribute("movieType", movieList.getType());
        model.addAttribute("moviePage", moviePage);

        return "chi_tiet_phim";
    }

    @GetMapping("/phim_le")
    public String getPhimLePage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_LE, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", moviePage.getTotalPages());
        return "phim_le";
    }

    @GetMapping("/phim_chieu_rap")
    public String getPhimRapPage(@RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "18") Integer pageSize,
                                 Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_CHIEU_RAP, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", moviePage.getTotalPages());
        return "phim_chieu_rap";
    }


}

package org.superbiz.moviefun;

<<<<<<< HEAD
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
=======
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.superbiz.moviefun.albums.Album;
import org.superbiz.moviefun.albums.AlbumFixtures;
import org.superbiz.moviefun.albums.AlbumsBean;
import org.superbiz.moviefun.movies.Movie;
import org.superbiz.moviefun.movies.MovieFixtures;
import org.superbiz.moviefun.movies.MoviesBean;

import java.util.Map;
>>>>>>> HEAD@{1}

@Controller
public class HomeController {

<<<<<<< HEAD
    private MoviesBean moviesBean;

    public HomeController(MoviesBean bean) {
        this.moviesBean = bean;
    }

    @GetMapping("/")
    public String getHome() {
=======
    private final MoviesBean moviesBean;
    private final AlbumsBean albumsBean;
    private final MovieFixtures movieFixtures;
    private final AlbumFixtures albumFixtures;

    private final PlatformTransactionManager albumsTransactionManager;
    private final PlatformTransactionManager moviesTransactionManager;

    public HomeController(MoviesBean moviesBean,
                          AlbumsBean albumsBean,
                          MovieFixtures movieFixtures,
                          AlbumFixtures albumFixtures,
                          PlatformTransactionManager albumsTransactionManager,
                          PlatformTransactionManager moviesTransactionManager) {
        this.moviesBean = moviesBean;
        this.albumsBean = albumsBean;
        this.movieFixtures = movieFixtures;
        this.albumFixtures = albumFixtures;
        this.albumsTransactionManager = albumsTransactionManager;
        this.moviesTransactionManager = moviesTransactionManager;
    }

    @GetMapping("/")
    public String index() {
>>>>>>> HEAD@{1}
        return "index";
    }

    @GetMapping("/setup")
<<<<<<< HEAD
    public String getSetup(Model model){
        moviesBean.addMovie(new Movie("Wedding Crashers", "David Dobkin", "Comedy", 7, 2005));
        moviesBean.addMovie(new Movie("Starsky & Hutch", "Todd Phillips", "Action", 6, 2004));
        moviesBean.addMovie(new Movie("Shanghai Knights", "David Dobkin", "Action", 6, 2003));
        moviesBean.addMovie(new Movie("I-Spy", "Betty Thomas", "Adventure", 5, 2002));
        moviesBean.addMovie(new Movie("The Royal Tenenbaums", "Wes Anderson", "Comedy", 8, 2001));
        moviesBean.addMovie(new Movie("Zoolander", "Ben Stiller", "Comedy", 6, 2001));
        moviesBean.addMovie(new Movie("Shanghai Noon", "Tom Dey", "Comedy", 7, 2000));
        List<Movie> movies = moviesBean.getMovies();
        model.addAttribute("movies",movies);
        return "setup";
    }


=======
    public String setup(Map<String, Object> model) {
        TransactionTemplate moviesTransactionTemplate = new TransactionTemplate(moviesTransactionManager);

        for (Movie movie : movieFixtures.load()) {
            moviesTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                    moviesBean.addMovie(movie);
                }
            });
        }

        TransactionTemplate albumsTransactionTemplate = new TransactionTemplate(albumsTransactionManager);
        for (Album album : albumFixtures.load()) {
            albumsTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                    albumsBean.addAlbum(album);
                }
            });
        }

        model.put("movies", moviesBean.getMovies());
        model.put("albums", albumsBean.getAlbums());

        return "setup";
    }
>>>>>>> HEAD@{1}
}

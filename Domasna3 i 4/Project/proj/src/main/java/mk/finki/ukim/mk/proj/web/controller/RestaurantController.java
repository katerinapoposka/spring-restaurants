package mk.finki.ukim.mk.proj.web.controller;

import mk.finki.ukim.mk.proj.model.Restaurants;
import mk.finki.ukim.mk.proj.model.User;
import mk.finki.ukim.mk.proj.model.exceptions.RestaurantDoesNotExistException;
import mk.finki.ukim.mk.proj.service.RestaurantService;
import mk.finki.ukim.mk.proj.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final UserService userService;

    public RestaurantController(RestaurantService restaurantService, UserService userService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
    }
    @GetMapping()
    public String main(Model model){
        model.addAttribute("bodyContent","main");
        return "master-template";
    }
    @GetMapping("/index")
    public String index(){
        return "redirect:/restaurants/all/0";
    }
    @GetMapping(value = "/all/{pageId}")
    public String getAllRestaurants(@PathVariable(value = "pageId") int pageId, Model model){
        Pageable pageWithTenRestaurants = PageRequest.of(0,10*(pageId+1));
        Page<Restaurants> restaurants = restaurantService.getAllRestaurantsPageable(pageWithTenRestaurants);
        model.addAttribute("restaurants",restaurants);
        model.addAttribute("pageId",pageId+1);
        model.addAttribute("bodyContent","allRestaurants");
        return "master-template";
    }
    @GetMapping(value = "/{id}")
    public String getRestaurantById(@PathVariable(value = "id") Long id, HttpServletRequest req, Model model){
        Restaurants restaurant = restaurantService.getRestaurantById(id).orElseThrow(RestaurantDoesNotExistException::new);
        User user = req.getRemoteUser()!=null ? userService.findByUsername(req.getRemoteUser()) : null;

        model.addAttribute("restaurant",restaurant);
        model.addAttribute("from","A");
        model.addAttribute("user",user);
        model.addAttribute("bodyContent","getRestaurant");
        return "master-template";
    }
    @GetMapping(value = "/chooseFilter")
    public String chooseFilter(Model model){
        model.addAttribute("bodyContent","chooseFilter");
        return "master-template";
    }

    @GetMapping(value = "/search")
    public String searchRestaurants(Model model){
        model.addAttribute("cuisines",restaurantService.getCuisines());
        model.addAttribute("bodyContent","search");
        return "master-template";
    }
    @GetMapping(value = "/restaurantN")
    public String getRestaurantsByName(@RequestParam(value = "restaurantName", required = false) String restaurantName, Model model) {
        List<Restaurants> restaurants;
        if (restaurantName!=null){
            restaurants = restaurantService.getRestaurantByName(restaurantName);
        }
        else{
            restaurants = restaurantService.getAllRestaurants();
        }
        model.addAttribute("restaurants",restaurants);
        model.addAttribute("bodyContent","searchRestaurants");
        return "master-template";
    }
    @GetMapping(value = "/restaurantC")
    public String getRestaurantsByCuisine(@RequestParam(value = "cuisine", required = false) String cuisine, Model model) {
        List<Restaurants> restaurants;
        if (cuisine!=null && !cuisine.equals("-Cuisine-")){
            restaurants = restaurantService.getRestaurantByCuisine(cuisine.toUpperCase());
        }
        else{
            restaurants = restaurantService.getAllRestaurants();
        }
        model.addAttribute("restaurants",restaurants);
        model.addAttribute("bodyContent","searchRestaurants");
        return "master-template";
    }
    @GetMapping(value = "/addFav/{id}")
    public String addRestaurantToFavs(@PathVariable(value = "id") Long id, HttpServletRequest req, Model model){
        User user = userService.findByUsername(req.getRemoteUser());
        user=userService.addToFavourites(user.getId(),id);
        model.addAttribute("user",user);
        model.addAttribute("bodyContent","favorites");
        return "master-template";
    }
    @GetMapping(value = "/removeFav/{id}")
    public String removeRestaurantFromFavs(@PathVariable(value = "id") Long id, HttpServletRequest req, Model model){
        User user = userService.findByUsername(req.getRemoteUser());
        user=userService.removeFavourites(user.getId(),id);
        model.addAttribute("user",user);
        model.addAttribute("bodyContent","favorites");
        return "master-template";
    }
    @GetMapping(value = "/favourites")
    public String getAllFavourites(HttpServletRequest req, Model model){
        User user = userService.findByUsername(req.getRemoteUser());
        model.addAttribute("user",user);
        model.addAttribute("bodyContent","favorites");
        return "master-template";
    }
}

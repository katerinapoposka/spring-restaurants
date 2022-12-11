package mk.finki.ukim.mk.proj.web.controller;

import mk.finki.ukim.mk.proj.model.Restaurants;
import mk.finki.ukim.mk.proj.model.User;
import mk.finki.ukim.mk.proj.model.exceptions.RestaurantDoesNotExistException;
import mk.finki.ukim.mk.proj.service.RestaurantService;
import mk.finki.ukim.mk.proj.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/restaurants")
@SessionAttributes("user")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final UserService userService;

    public RestaurantController(RestaurantService restaurantService, UserService userService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
    }
    @GetMapping()
    public String main(HttpSession session, Model model){
        return "main";
    }
    @GetMapping("/index")
    public String index(HttpSession session, Model model){
        session.setAttribute("user",userService.findById(1L));
        return "redirect:/restaurants/all";
    }
    @GetMapping(value = "/all")
    public String getAllRestaurants(Model model){
        List<Restaurants> restaurants = restaurantService.getAllRestaurants();
        model.addAttribute("restaurants",restaurants);
        return "allRestaurants";
    }
    @GetMapping(value = "/{id}")
    public String getRestaurantById(@PathVariable(value = "id") Long id, Model model){
        Restaurants restaurant = restaurantService.getRestaurantById(id).orElseThrow(RestaurantDoesNotExistException::new);
        model.addAttribute("restaurant",restaurant);
        return "getRestaurant";
    }
    @GetMapping(value = "/restaurantN")
    public String getRestaurantsByName(@RequestParam(value = "restaurantName", required = false) String restaurantName, Model model) {
        List<Restaurants> restaurants = new ArrayList<>();
        if (restaurantName!=null){
            restaurants = restaurantService.getRestaurantByName(restaurantName);
        }
        else{
            restaurants = restaurantService.getAllRestaurants();
        }
        model.addAttribute("restaurants",restaurants);
        return "searchRestaurantsByName";
    }
    @GetMapping(value = "/restaurantC")
    public String getRestaurantsByCuisine(@RequestParam(value = "cuisine", required = false) String cuisine, Model model) {
        List<Restaurants> restaurants = new ArrayList<>();
        if (cuisine!=null){
            restaurants = restaurantService.getRestaurantByCuisine(cuisine.toUpperCase());
        }
        else{
            restaurants = restaurantService.getAllRestaurants();
        }
        model.addAttribute("restaurants",restaurants);
        return "searchRestaurantsByCuisine";
    }
    @GetMapping(value = "/addFav/{id}")
    public String addRestaurantToFavs(@PathVariable(value = "id") Long id, HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        user.getFavorites().add(restaurantService.getRestaurantById(id).orElseThrow(() -> new RestaurantDoesNotExistException()));
        model.addAttribute("user",user);
        return "favorites";
    }
// REST CONTROLLER VERSION
//    public ResponseEntity<List<Restaurants>> getAllRestaurants() {
//        List<Restaurants> restaurants = restaurantService.getAllRestaurants();
//        return new ResponseEntity<>(restaurants, HttpStatus.OK);
//    }

//    @GetMapping(value = "/{id}")
//    public ResponseEntity<Restaurants> getRestaurantById(@PathVariable(value = "id") Long id){
//        Restaurants restaurant = restaurantService.getRestaurantById(id).orElseThrow(RestaurantDoesNotExistException::new);
//        return new ResponseEntity<>(restaurant, HttpStatus.OK);
//    }

//    @GetMapping(value = "/restaurant")
//    public ResponseEntity<?> getRestaurantsByName(@RequestParam(value = "name") String restaurantName) {
//        List<Restaurants> restaurants = restaurantService.getRestaurantByName(restaurantName);
//        return new ResponseEntity<>(restaurants, HttpStatus.OK);
//    }

//    @GetMapping()
//    public ResponseEntity<?> getRestaurantsByCuisine(@RequestParam(value = "cuisine") String cuisine) {
//        List<Restaurants> restaurants = restaurantService.getRestaurantByCuisine(cuisine.toUpperCase());
//        return new ResponseEntity<>(restaurants, HttpStatus.OK);
//    }

//    @GetMapping(value = "/addFav/{id}")
//    public ResponseEntity<User> addRestaurantToFavs(@PathVariable(value = "id") Long id, HttpSession session){
//        User user = (User) session.getAttribute("user");
//        user.getFavorites().add(restaurantService.getRestaurantById(id).orElseThrow(() -> new RestaurantDoesNotExistException()));
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
}

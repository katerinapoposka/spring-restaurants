package mk.finki.ukim.mk.proj.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Restaurants {
    @Id
    private Long id;
    private String addrCity;
    @Enumerated(value = EnumType.STRING)
    private CuisineType cuisine;
    private String name;
    private String opening_hours;
    private String website;
    private String internet_access;
    private String phone;
    private String email;
    private String smoking;
    private float lon;
    private float lat;
}

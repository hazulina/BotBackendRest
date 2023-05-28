package com.botbackendrest.entity;

import com.botbackendrest.service.MappingVisitor;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cities")
@Data
public class City extends Structures{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int city_id;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "zipcode")
    private int zipcode;


    @Override
    public String toString() {
        return "City{" +
                "id=" + city_id +
                ", cityName='" + cityName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", zipcode=" + zipcode +
                '}';
    }


}

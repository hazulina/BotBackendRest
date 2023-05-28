package com.botbackendrest.entity;

import com.botbackendrest.service.MappingVisitor;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_city_links")
@Data
public class UserCityLInk extends Structures{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id")
    private int link_id;

    @Column(name = "user_id")
    private int linkedUser_id;

    @Column(name = "city_id")
    private int linkedCity_id;


}

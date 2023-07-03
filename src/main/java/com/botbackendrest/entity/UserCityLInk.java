package com.botbackendrest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_city_links")
@Data
public class UserCityLInk extends Structures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id")
    private int linkId;

    @Column(name = "user_id")
    private int linkedUserId;

    @Column(name = "city_id")
    private int linkedCityId;


}

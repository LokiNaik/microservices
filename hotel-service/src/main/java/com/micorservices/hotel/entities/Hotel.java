package com.micorservices.hotel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "HTL_TBL")
public class Hotel {
    @Id
    @Column(name = "HTL_ID")
    private String hotelId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "ABOUT")
    private String about;
}

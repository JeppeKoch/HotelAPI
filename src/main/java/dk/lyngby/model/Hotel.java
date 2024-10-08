package dk.lyngby.model;

import dk.lyngby.dto.HotelDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "hotel")
    private Set<Room> rooms;

    public Hotel(HotelDto hotelDto) {
        this.id = hotelDto.getId();
        this.name = hotelDto.getName();
        this.address = hotelDto.getAddress();
        this.rooms = new HashSet<>();
    }

}

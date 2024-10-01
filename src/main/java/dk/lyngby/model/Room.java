package dk.lyngby.model;

import dk.lyngby.dto.RoomDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int hotelId;
    private int number;
    private double price;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Room(RoomDto roomDto) {
        this.id = roomDto.getId();
        this.hotelId = roomDto.getHotelId();
        this.number = roomDto.getNumber();
        this.price = roomDto.getPrice();
        this.hotel = roomDto.getHotel();
    }
}

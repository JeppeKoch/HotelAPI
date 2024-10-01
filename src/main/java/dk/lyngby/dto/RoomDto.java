package dk.lyngby.dto;

import dk.lyngby.model.Hotel;
import dk.lyngby.model.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class RoomDto {

    private long id;
    private int hotelId;
    private int number;
    private double price;
    private Hotel hotel;


    public RoomDto(Room room) {
        this.id = room.getId();
        this.number = room.getNumber();
        this.price = room.getPrice();
        this.hotel = room.getHotel();
    }

    public static List<RoomDto> toRoomDTOList(List<Room> rooms) {
        return rooms.stream().map(RoomDto::new).toList();
    }
}

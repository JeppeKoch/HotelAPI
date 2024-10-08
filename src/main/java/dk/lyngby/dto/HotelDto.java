package dk.lyngby.dto;

import dk.lyngby.model.Hotel;
import dk.lyngby.model.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto {

    private Long id;
    private String name;
    private String address;

   private List<Room> rooms = new ArrayList<>();

   public HotelDto(Hotel hotel) {
       this.id = hotel.getId();
       this.name = hotel.getName();
       this.address = hotel.getAddress();
       for (Room room : hotel.getRooms()) {
           rooms.add(room);
       }
   }

   public HotelDto(long id, String name, String address) {
       this.id = id;
       this.name = name;
         this.address = address;
   }

       public static List<HotelDto> toHotelDTOList(List<Hotel> hotels) {
           return hotels.stream().map(HotelDto::new).toList();
       }
   }




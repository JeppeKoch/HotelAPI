package dk.lyngby.controller;

import dk.lyngby.dao.HotelDao;
import dk.lyngby.dto.HotelDto;
import dk.lyngby.exception.ApiException;
import dk.lyngby.model.Hotel;
import dk.lyngby.model.Message;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
public class HotelController implements IController {
    private final HotelDao hotelDao;
    private final Logger log = LoggerFactory.getLogger(HotelController.class);

    public HotelController(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    @Override
    public void getById(Context ctx) {
        try {
            long id = Long.parseLong(ctx.pathParam("id"));
            Hotel hotel = hotelDao.getById(id);

            HotelDto hotelDto = new HotelDto(hotel);
            ctx.res().setStatus(200);
            ctx.json(hotelDto, HotelDto.class);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void create(Context ctx) {
        try {

            HotelDto hotelDto = ctx.bodyAsClass(HotelDto.class);
            ctx.status(HttpStatus.CREATED);
            ctx.json(hotelDao.create(hotelDto));

            // == response ==
            ctx.res().setStatus(201);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void update(Context ctx) {
        try {
            long id = Long.parseLong(ctx.pathParam("id"));
            HotelDto hotelDto = ctx.bodyAsClass(HotelDto.class);

            // == querying ==
            Hotel hotel = hotelDao.getById(id);
            hotelDao.update(hotel, new Hotel(hotelDto));

            // == response ==
            ctx.res().setStatus(200);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void delete(Context ctx) {
        try {
            long id = Long.parseLong(ctx.pathParam("id"));

            // == querying ==
            Hotel hotel = hotelDao.getById(id);

            // == response ==
            hotelDao.delete(id);
            ctx.res().setStatus(204);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void getAll(Context ctx) {
        try {
            List<Hotel> hotels = hotelDao.getAll();


            List<HotelDto> hotelDtos = HotelDto.toHotelDTOList(hotels);
            ctx.res().setStatus(200);
            ctx.json(hotelDtos, HotelDto.class);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }
}
package dk.lyngby.controller;

import dk.lyngby.dao.RoomDao;
import dk.lyngby.dto.RoomDto;
import dk.lyngby.exception.ApiException;
import dk.lyngby.model.Message;
import dk.lyngby.model.Room;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
public class RoomController implements IController{
    private final RoomDao roomDao;
    private final Logger log = LoggerFactory.getLogger(RoomController.class);

    public RoomController(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public void getById(Context ctx) {
        try {
            long id = Long.parseLong(ctx.pathParam("id"));
            Room room = roomDao.getById(id);

            RoomDto roomDto = new RoomDto(room);
            ctx.res().setStatus(200);
            ctx.json(roomDto, RoomDto.class);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void create(Context ctx) {
        try {
            // == request ==
            RoomDto roomDto = ctx.bodyAsClass(RoomDto.class);

            if (roomDto == null){
                ctx.status(400);
                ctx.json(new Message(400, "Invalid request"));
                return;
            }

            // == querying ==
            Room newRoom = new Room(roomDto);
            roomDao.create(newRoom);

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
            RoomDto roomDto = ctx.bodyAsClass(RoomDto.class);

            // == querying ==
            Room room = roomDao.getById(id);
            roomDao.update(room, new Room(roomDto));

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
            Room room = roomDao.getById(id);

            // == response ==
            roomDao.delete(id);
            ctx.res().setStatus(204);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }

    @Override
    public void getAll(Context ctx) {
        try {
            List<Room> rooms = roomDao.getAll();


            List<RoomDto> roomDtos = RoomDto.toRoomDTOList(rooms);
            ctx.res().setStatus(200);
            ctx.json(roomDtos, RoomDto.class);
        } catch (Exception e) {
            log.error("400{}",e.getMessage());
            throw new ApiException(400, e.getMessage());
        }
    }
}

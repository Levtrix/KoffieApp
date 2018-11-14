package KoffieAppRESTServer.restservices;

import KoffieAppRESTServer.handlers.IDrinkHandler;
import KoffieAppRESTServer.response.Reply;
import com.google.gson.Gson;
import KoffieAppDatabase.models.Drink;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/KoffieApp/drink")
public class DrinkService {
    private static IDrinkHandler handler;
    private Gson gson = new Gson();

    public static void setHandler(IDrinkHandler handler) {
        DrinkService.handler = handler;
    }

    @GET
    @Path("/all")
    public Response getDrinks() {
        Reply reply = handler.getDrinks();

        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @GET
    @Path("/{id}")
    public Response getDrink(@PathParam("id") int drinkId){
        Reply reply = handler.getDrink(drinkId);

        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }

    @POST
    @Path("/save")
    @Consumes("application/json")
    public Response saveDrink(String data) {
        Drink drink = gson.fromJson(data, Drink.class);

        Reply reply = handler.saveDrink(drink);

        return Response.status(reply.getStatus().getCode()).entity(reply.getMessage()).build();
    }
}

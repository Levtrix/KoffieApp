package KoffieAppRESTServer.handlers;

import models.Drink;
import KoffieAppRESTServer.response.DrinkJson;
import KoffieAppRESTServer.response.ErrorJson;
import KoffieAppRESTServer.response.Reply;
import KoffieAppRESTServer.response.Status;
import com.google.gson.Gson;
import KoffieAppDal.repository.DrinkRepository;
import logging.Logger;

import java.util.ArrayList;
import java.util.List;

public class DrinkHandler implements IDrinkHandler{
    private DrinkRepository drinkRepository;
    private Gson gson;

    public DrinkHandler(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
        this.gson = new Gson();
    }

    @Override
    public Reply getDrinks() {
        try {
            //addDrinks();
            List<DrinkJson> drinkReponse = new ArrayList<>();

            for (Drink d : drinkRepository.findAll()) {
                drinkReponse.add(new DrinkJson(d.getId(), d.getName()));
            }

            String json = gson.toJson(drinkReponse);
            return new Reply(Status.OK, json);
        } catch (Exception e) {
            Logger.getInstance().log(e);
            ErrorJson errorJson = new ErrorJson("Something went wrong");

            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }

    @Override
    public Reply getDrink(int drinkId) {
        Drink drink = drinkRepository.findOne(drinkId);

        if (drink != null) {
            String json = gson.toJson(drink);

            return new Reply(Status.OK, json);
        }

        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }


    @Override
    public Reply saveDrink(Drink drink) {
        Drink saved = drinkRepository.save(drink);
        
        if (saved.getId() == drink.getId()) {
            return new Reply(Status.OK, gson.toJson(saved));
        }

        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }

    @Override
    public Reply deleteDrink(int drinkId) {
        try {
            drinkRepository.delete(drinkId);
            ErrorJson messageJson = new ErrorJson("Deleted");

            return new Reply(Status.OK, gson.toJson(messageJson));
        } catch (Exception e) {
            ErrorJson errorJson = new ErrorJson("Something went wrong");

            return new Reply(Status.ERROR, gson.toJson(errorJson));
        }
    }

    private void addDrinks() {
        Drink drink1 = new Drink("Koffie");
        Drink drink2 = new Drink("Thee");
        Drink drink3 = new Drink("Cappuccino");
        Drink drink4 = new Drink("Latte");

        drinkRepository.save(drink1);
        drinkRepository.save(drink2);
        drinkRepository.save(drink3);
        drinkRepository.save(drink4);
    }
}

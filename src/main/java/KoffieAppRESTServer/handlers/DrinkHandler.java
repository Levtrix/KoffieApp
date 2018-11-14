package KoffieAppRESTServer.handlers;

import KoffieAppRESTServer.response.DrinkJson;
import KoffieAppRESTServer.response.ErrorJson;
import KoffieAppRESTServer.response.Reply;
import KoffieAppRESTServer.response.Status;
import com.google.gson.Gson;
import KoffieAppDatabase.dal.repository.DrinkRepository;
import KoffieAppDatabase.logging.Logger;
import KoffieAppDatabase.models.Drink;
import KoffieAppDatabase.models.SQLDrink;

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
            List<Drink> drinks = new ArrayList<>();
            List<DrinkJson> drinkReponse = new ArrayList<>();

            for (SQLDrink d : drinkRepository.findAll()) {
                drinks.add(new Drink(d.getDrinkId(), d.getName()));
            }

            for (Drink d : drinks) {
                drinkReponse.add(new DrinkJson(d.getDrinkId(), d.getName()));
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
        SQLDrink tmp = drinkRepository.findOne(drinkId);
        Drink drink = new Drink(tmp.getDrinkId(), tmp.getName());

        if (drink != null) {
            String json = gson.toJson(drink);

            return new Reply(Status.OK, json);
        }

        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }


    @Override
    public Reply saveDrink(Drink drink) {
        SQLDrink saved = drinkRepository.save(new SQLDrink(drink.getDrinkId(), drink.getName()));
        
        if (saved.getDrinkId() == drink.getDrinkId()) {
            return new Reply(Status.OK, gson.toJson(new Drink(saved.getDrinkId(), saved.getName())));
        }

        ErrorJson errorJson = new ErrorJson("Something went wrong");
        return new Reply(Status.ERROR, gson.toJson(errorJson));
    }
}

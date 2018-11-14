package KoffieAppRESTServer.handlers;

import KoffieAppRESTServer.response.Reply;
import KoffieAppDatabase.models.Drink;

public interface IDrinkHandler {
    Reply getDrinks();
    Reply getDrink(int drinkId);
    Reply saveDrink(Drink drink);
}

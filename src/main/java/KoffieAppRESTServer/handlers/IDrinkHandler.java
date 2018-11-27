package KoffieAppRESTServer.handlers;

import KoffieAppRESTServer.response.Reply;
import KoffieAppRESTServer.models.Drink;

public interface IDrinkHandler {
    Reply getDrinks();
    Reply getDrink(int drinkId);
    Reply saveDrink(Drink drink);
    Reply deleteDrink(int drinkId);
}

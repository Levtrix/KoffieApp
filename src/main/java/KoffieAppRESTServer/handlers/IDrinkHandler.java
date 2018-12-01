package KoffieAppRESTServer.handlers;

import KoffieAppRESTServer.response.Reply;
import models.Drink;

public interface IDrinkHandler {
    Reply getDrinks();
    Reply getDrink(int drinkId);
    Reply saveDrink(Drink drink);
    Reply deleteDrink(int drinkId);
}

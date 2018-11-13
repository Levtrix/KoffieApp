package KoffieRESTServer.handlers;

import KoffieRESTServer.response.Reply;
import models.Drink;

public interface IDrinkHandler {
    Reply getDrinks();
    Reply getDrink(int drinkId);
    Reply saveDrink(Drink drink);
}

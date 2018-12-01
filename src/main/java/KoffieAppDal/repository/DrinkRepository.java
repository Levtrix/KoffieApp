package KoffieAppDal.repository;

import models.Drink;

public class DrinkRepository extends AbstractRepository<Drink, Integer> {
    @Override
    public Class<Drink> getDomainClass() {
        return Drink.class;
    }
}

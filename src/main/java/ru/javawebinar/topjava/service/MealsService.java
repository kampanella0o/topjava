package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealsService implements CRUD<Meal> {
    private static Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    private static AtomicInteger id = new AtomicInteger(-1);

    {
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        create(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public Meal get(int id) {
        return meals.get(id);
    }

    @Override
    public void create(Meal meal) {
        id.set(id.incrementAndGet());
        meal.setId(id.get());
        meals.put(id.get(), meal);
    }

    @Override
    public void update(Meal meal) {
        meals.put(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        meals.remove(id);
    }

    @Override
    public Map<Integer, Meal> getAll() {
        return meals;
    }
}
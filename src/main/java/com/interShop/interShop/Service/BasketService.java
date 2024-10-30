package com.interShop.interShop.Service;

import com.interShop.interShop.Entity.Basket;
import com.interShop.interShop.Repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BasketService {
    private final BasketRepository basketRepository;

    @Autowired
    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public List<Basket> findAllBaskets() {
        return basketRepository.findAll();
    }

    public Optional<Basket> findBasketById(Long id) {
        return basketRepository.findById(id);
    }

    public Basket saveBasket(Basket basket) {
        return basketRepository.save(basket);
    }

    public void deleteBasketById(Long id) {
        basketRepository.deleteById(id);
    }
}

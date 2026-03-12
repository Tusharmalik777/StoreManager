package uk.co.danielbryant.djshopping.stockmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uk.co.danielbryant.djshopping.stockmanager.model.Stock;
import uk.co.danielbryant.djshopping.stockmanager.repositories.StockRepository;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    // Get all stocks
    @GetMapping("/")
    public List<Stock> getAllStocks() {
        return (List<Stock>) stockRepository.findAll();
    }

    // Get stock by productId
    @GetMapping("/{productId}")
    public Stock getStock(@PathVariable String productId) {
        Optional<Stock> stockDetails = stockRepository.findById(productId);
        return stockDetails.orElse(null);
    }

    // Add new stock
    @PostMapping("/")
    public Stock addStock(@RequestBody Stock stock) {
        return stockRepository.save(stock);
    }

    // Update stock
    @PutMapping("/{productId}")
    public Stock updateStock(@PathVariable String productId, @RequestBody Stock stock) {
        Optional<Stock> existingStock = stockRepository.findById(productId);
        if (existingStock.isPresent()) {
            Stock s = existingStock.get();
            s.setSku(stock.getSku());
            s.setAmountAvailable(stock.getAmountAvailable());
            return stockRepository.save(s);
        } else {
            return null;
        }
    }

    // Delete stock
    @DeleteMapping("/{productId}")
    public void deleteStock(@PathVariable String productId) {
        stockRepository.deleteById(productId);
    }
}

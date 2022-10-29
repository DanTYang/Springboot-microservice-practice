package com.danyang.stock.dbservice.resource;

import com.danyang.stock.dbservice.model.Quote;
import com.danyang.stock.dbservice.model.Quotes;
import com.danyang.stock.dbservice.repository.QuotesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

    private QuotesRepository quotesRepository;

    public DbServiceResource(QuotesRepository quotesRepository) {
        this.quotesRepository = quotesRepository;
    }

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") final String username){
        return getQuotesByUserName(username);
    }

    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes) {

        quotes.getQuotes()
                .stream()
                .map(quote -> new Quote(quotes.getUsername(), quote))
                .forEach(quote->quotesRepository.save(quote));
        return getQuotesByUserName(quotes.getUsername());
    }

    @PostMapping("/delete/{username}")
    public List<String> delete(@PathVariable("username") final String username){

        List<Quote> quotes = quotesRepository.findByUserName(username);
        for(Quote q : quotes) {
            quotesRepository.delete(q);
        }
        return getQuotesByUserName(username);
    }

    private List<String> getQuotesByUserName(String username) {
        return quotesRepository.findByUserName(username)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }
}

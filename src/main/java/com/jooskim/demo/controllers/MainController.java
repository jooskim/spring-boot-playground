package com.jooskim.demo.controllers;

import java.util.List;

import com.jooskim.demo.models.EchoRequest;
import com.jooskim.demo.models.Order;
import com.jooskim.demo.models.OrderRegistry;
import com.jooskim.demo.models.Person;
import com.jooskim.demo.models.PersonRegistry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(path = "/api")
public class MainController {
    @Autowired
    private PersonRegistry peopleRegistry;

    @Autowired
    private OrderRegistry orderRegistry;

    @GetMapping(path = "/echo/{msg}")
    @ResponseBody
    public String echo(@PathVariable(name = "msg") String msg) {
        return msg;
    }

    @PostMapping(path = "/echo2",
                 consumes = { MediaType.APPLICATION_JSON_VALUE },
                 produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String echo2(@RequestBody EchoRequest echoReq) {
        return echoReq.getMsg();
    }

    @PostMapping(path = "/addAll")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public String addPeople() {
        for (int i = 0; i < 10; i++) {
            Person p = new Person();
            p.setFirstName("First" + i);
            p.setLastName("Last" + i);
            peopleRegistry.save(p);
        }
        return "created";
    }

    @PostMapping(path = "addOne", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    public Person addOne(@RequestBody Person person) {
        Person p = this.peopleRegistry.save(person);
        return p;
    }

    @GetMapping(path = "/orderBook/{userId},{bookName}")
    @ResponseBody
    public Order placeOrderFromUser(@PathVariable(name = "userId") Long id,
                                    @PathVariable(name = "bookName") String bookName) {
        Order no = new Order();
        Person person = peopleRegistry.findById(id).get();
        no.setName(bookName);;
        no.setOrderedBy(person);
        orderRegistry.save(no);
        return no;
    }

    @GetMapping(path = "/people")
    @ResponseBody
    public Page<Person> getPeopleByPage(Pageable pageable) {
        return peopleRegistry.findAll(pageable);
    }

    @GetMapping(path = "/books")
    @ResponseBody
    public Page<Order> getBooksByPage(Pageable pageable) {
        return orderRegistry.findAll(pageable);
    }

}
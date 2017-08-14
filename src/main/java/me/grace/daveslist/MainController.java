package me.grace.daveslist;


import me.grace.daveslist.models.Rent;
import me.grace.daveslist.repositories.RentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class MainController {
    @Autowired
    RentRepo rentRepo;

    @GetMapping("/")
    public String defaultRequest(){
        return "welcome";
    }


    @GetMapping("/addrent")
    public String addBook(Model model) {
        model.addAttribute("newRent", new Rent());
        return "addrent";
    }

    @PostMapping("/addrent")
    public String postBook(@Valid @ModelAttribute("newRent") Rent rent, BindingResult bindingResult)
    {

        if(bindingResult.hasErrors()){
            return "addrent";
        }
        rentRepo.save(rent);
        return "result";
    }

    @GetMapping("/load")
    public String load()
    {
        Iterable<Rent> loadlist;
        ArrayList<Rent> toadd = new ArrayList<>();
        Rent newRent1 = new Rent();
        newRent1.setRentinfo("New on Hazel street", "no cat", "none",
                true, true);

        Rent newRent2 = new Rent();
        newRent2.setRentinfo("New on Hazel street", "no cat", "none",
                true, true);

        Rent newRent3 = new Rent();
        newRent3.setRentinfo("New on Hazel street", "no cat", "none",
                true, true);

        Rent newRent4 = new Rent();
        newRent4.setRentinfo("New on Hazel street", "no cat", "none",
                true, true);

        toadd.add(newRent1);
        toadd.add(newRent2);
        toadd.add(newRent3);
        toadd.add(newRent4);

        loadlist=toadd;
        rentRepo.save(loadlist);

        return "loadconfirm";
    }

    @GetMapping("/rentornot")
    public String search(Model model)
    {
        Iterable<Rent> available = rentRepo.findAllByIsRented("false");
        model.addAttribute("avail", available);
        Iterable<Rent> notavailable = rentRepo.findAllByIsRented("true");
        model.addAttribute("notavail", notavailable);

        return "rentornot";
    }



}

package com.contactsaver.demo.controller;

import com.contactsaver.demo.entity.Address;
import com.contactsaver.demo.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/add")
    public void addAddress(@RequestBody Address address) {
        addressService.addAddress(address);
    }

    @GetMapping("/get")
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    @PutMapping("/update/{id}")
    public void updateAddress(@PathVariable int id, @RequestBody Address address) {
        addressService.updateAddress(id, address);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAddress(@PathVariable int id) {
        addressService.deleteAddress(id);
    }
}
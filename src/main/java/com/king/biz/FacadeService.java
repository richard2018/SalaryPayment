package com.king.biz;


import org.springframework.stereotype.Service;

@Service
public class FacadeService {
    public void addHourlyEmployee(int id, String name, String address) {
        System.out.println("addHourlyEmployee");
    }
}

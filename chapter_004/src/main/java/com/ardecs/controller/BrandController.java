package com.ardecs.controller;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 04.05.2019
 */

import com.ardecs.car_configurator.entities.Brand;
import com.ardecs.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/brand/")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping("list")
    public String showBrand(Model model) {
        List<Brand> brandList = brandService.getBrands();
        model.addAttribute("brandList", brandList);

        return "brand";
    }

    @GetMapping("new")
    public String addBrand(Model model) {
        model.addAttribute("brand", new Brand());
        return "newBrand";
    }

    @PostMapping("new")
    public String saveBrand(Brand brand) {
//        if (errors.hasErrors()) {
//            return null;//Вернуть страницу на, которой не правильно заполнили данные
//        }
        brandService.save(brand);
        return "redirect:list";
    }

    @GetMapping("{brandId}")
    public String updateBrand(@PathVariable("brandId") Long brandId, Model model) {
        Brand brand = brandService.getBrand(brandId);
        model.addAttribute("brand", brand);
        model.addAttribute("brandId", brand.getId());

        return "updateBrand";
    }

    @DeleteMapping("{brandId}")
    public String deleteBrand(@PathVariable("brandId") Long brandId) {
        brandService.deleteById(brandId);
        return "redirect:/brand/list";
    }
}
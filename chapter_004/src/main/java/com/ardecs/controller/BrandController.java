package com.ardecs.controller;

/**
 * @author Marat Yanbaev (yanbaevms@gmail.com)
 * @since 04.05.2019
 */

import com.ardecs.entities.mainEntities.Brand;
import com.ardecs.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String saveBrand(
            @Valid Brand brand,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("brand", brand);
            return "newBrand";
        } else {
            brandService.save(brand);
        }
        return "redirect:list";
    }

    @PutMapping("renewed")
    public String renewBrand(
            @Valid Brand brand,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("brand", brand);
            return "changeBrand";
        } else {
            brandService.save(brand);
        }
        return "redirect:list";
    }

    @GetMapping("{brandId}")
    public String changeBrand(@PathVariable("brandId") Long brandId, Model model) {
        Brand brand = brandService.findById(brandId).get();
        model.addAttribute("brand", brand);

        return "changeBrand";
    }

    @DeleteMapping("{brandId}")
    public String deleteBrand(@PathVariable("brandId") Long brandId) {
        brandService.deleteById(brandId);
        return "redirect:/brand/list";
    }
}
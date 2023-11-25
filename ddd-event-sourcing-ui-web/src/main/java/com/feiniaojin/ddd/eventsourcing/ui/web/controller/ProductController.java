package com.feiniaojin.ddd.eventsourcing.ui.web.controller;

import com.feiniaojin.ddd.eventsourcing.application.service.ProductApplicationService;
import com.feiniaojin.ddd.eventsourcing.application.service.command.processor.ProductPictureChangeCommandProcessor;
import com.feiniaojin.ddd.eventsourcing.domain.commands.ProductCountReduceCommand;
import com.feiniaojin.ddd.eventsourcing.domain.commands.ProductCreateCommand;
import com.feiniaojin.ddd.eventsourcing.domain.commands.ProductPictureChangeCommand;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductApplicationService productApplicationService;

    @RequestMapping("/create")
    public void create(@RequestBody ProductCreateCommand command) {
        productApplicationService.create(command);
    }

    @RequestMapping("/reduceCount")
    public void reduceCount(@RequestBody ProductCountReduceCommand command) {
        productApplicationService.modify(command);
    }

    @RequestMapping("/changePicture")
    public void changePicture(@RequestBody ProductPictureChangeCommand command) {
        productApplicationService.modify(command);
    }
}

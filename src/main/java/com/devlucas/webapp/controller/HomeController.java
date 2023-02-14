package com.devlucas.webapp.controller;

import com.devlucas.webapp.model.Pedido;
import com.devlucas.webapp.model.enums.StatusPedido;
import com.devlucas.webapp.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public String home(Model model) {
        List<Pedido> pedidos = pedidoService.findAll();
        model.addAttribute("pedidos", pedidos);

        return "home";
    }

    @GetMapping("/{status}")
    public String porStatus(@PathVariable("status") String status, Model model) {
        List<Pedido> pedidos = pedidoService.findByStatus(StatusPedido.valueOf(status.toUpperCase()));
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status);

        return "home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError() {
        return"redirect:/home";
    }
}

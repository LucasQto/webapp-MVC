package com.devlucas.webapp.service;

import com.devlucas.webapp.model.Pedido;
import com.devlucas.webapp.model.enums.StatusPedido;
import com.devlucas.webapp.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public void save(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    public List<Pedido> findByStatus(StatusPedido status) {
        List<Pedido> pedidos = new ArrayList<>();
        for(Pedido item : pedidoRepository.findAll()) {
            if(StringUtils.equals(item.getStatus(), status)) {
                pedidos.add(item);
            }
        }
        return pedidos;
    }
}

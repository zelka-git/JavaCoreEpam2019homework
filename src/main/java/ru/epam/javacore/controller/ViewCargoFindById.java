package ru.epam.javacore.controller;

import ru.epam.javacore.homework20200210.application.serviceholder.ServiceHolder;
import ru.epam.javacore.homework20200210.cargo.domain.Cargo;
import ru.epam.javacore.homework20200210.cargo.service.CargoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "FindById", urlPatterns = "/findbyid")
public class ViewCargoFindById extends BaseController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String cargoid = req.getParameter("cargoid");

            long id = Long.parseLong(cargoid);

            CargoService cargoService = ServiceHolder.getInstance().getCargoService();

            Optional<Cargo> optionalCargo = cargoService.getById(id);

            if( optionalCargo.isPresent()){
                List<Cargo> all = new ArrayList<>();
                all.add(optionalCargo.get());
                req.setAttribute("allCargos", all);
                goToPage(req, resp, "viewallcargos.jsp");
            }else{
                throw new Exception("Cargo don't find");
            }
        } catch (Exception e) {
            this.goToPage(req, resp, "error.jsp");
        }
    }
}

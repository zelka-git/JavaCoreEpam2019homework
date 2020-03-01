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

@WebServlet(name = "FindByName", urlPatterns = "/findbyname")
public class ViewCargoFindByName extends BaseController{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String cargoName = req.getParameter("cargoname");

            CargoService cargoService = ServiceHolder.getInstance().getCargoService();

            List<Cargo> all = cargoService.getByName(cargoName);

            if( !all.isEmpty()){
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

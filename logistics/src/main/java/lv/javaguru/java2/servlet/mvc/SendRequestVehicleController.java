package lv.javaguru.java2.servlet.mvc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.jdbc.CargoDAOImpl;
import lv.javaguru.java2.database.jdbc.VehicleDAOImpl;
import lv.javaguru.java2.domain.Cargo;
import lv.javaguru.java2.domain.Vehicle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendRequestVehicleController implements MVCController {

    @Override
    public MVCModel processRequest(HttpServletRequest request, 
                                   HttpServletResponse response) {
        Long vehicleId = Long.parseLong(request.getParameter("id"));
        Map<String, Object> modelHashMap = new HashMap<String, Object> ();
        VehicleDAOImpl vehicleDAO = new VehicleDAOImpl();
        CargoDAOImpl cargoDAO = new CargoDAOImpl();
        Vehicle vehicle = new Vehicle();
        List<Cargo> cargoList = new ArrayList<Cargo>();

        try {
            vehicle = vehicleDAO.getById(vehicleId);
        } catch (DBException e) {
            System.out.println("Exception while getting vehicleDAO.getById(vehicleId) SendRequestVehicleController");
            e.printStackTrace();
        }



        try {
//            cargoList = cargoDAO.getAll();
            cargoList = cargoDAO.getByParameters(vehicle.getType(),0.0,vehicle.getCapacity(),
                    cargoDAO.stringToDate2("1000-01-01",2), cargoDAO.stringToDate2("9999-12-31", 2),
                    cargoDAO.stringToDate2("1000-01-01",2), cargoDAO.stringToDate2("9999-12-31",2));
        } catch (DBException e) {
            System.out.println("Exception while getting cargoDAO.getByParameters() SendRequestVehicleController");
            e.printStackTrace();
        }

/*
               List<Cargo> cargos = cargoDAO.getByParameters("platform", 15.0, 32.0, cargoDAO.stringToDate("04/02/2015"),
                cargoDAO.stringToDate("25/02/2015"), cargoDAO.stringToDate("10/03/2015"), cargoDAO.stringToDate("26/03/2015"));

        PreparedStatement preparedStatement = connection.prepareStatement("select * from " + getTableName() +
                " where vehicle_type = ? and (weight between ? and ?) and " +
                "(load_date between ? and ?) and (unload_date between ? and ?);");
        preparedStatement.setString(1, vehicleType);
        preparedStatement.setDouble(2, weightFrom);
        preparedStatement.setDouble(3, weightTo);
        preparedStatement.setDate(4, utilDateToSQL(loadDateFrom));
        preparedStatement.setDate(5, utilDateToSQL(loadDateTo));
        preparedStatement.setDate(6, utilDateToSQL(unloadDateFrom));
        preparedStatement.setDate(7, utilDateToSQL(unloadDateTo));
        ResultSet resultSet = preparedStatement.executeQuery();


*/

        modelHashMap.put("vehicle", vehicle);
        modelHashMap.put("cargoList", cargoList);

        MVCModel model = new MVCModel("/jsp/sendRequestVehicle.jsp", modelHashMap);
        return model;
    }
}

package org.dmitrishkod.springbootapp.service.Impl;

import lombok.AllArgsConstructor;
import org.dmitrishkod.springbootapp.model.dto.CarDto;
import org.dmitrishkod.springbootapp.model.dto.Coordinate;
import org.dmitrishkod.springbootapp.model.entity.Car;
import org.dmitrishkod.springbootapp.repository.CarRepository;
import org.dmitrishkod.springbootapp.repository.OwnerRepository;
import org.dmitrishkod.springbootapp.service.CarService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final OwnerRepository ownerRepository;

    @Override
    public Car create(CarDto carDto) {
        Car car = new Car();
        car.setId(carDto.getId());
        car.setNumber(carDto.getNumber());
        car.setDate(carDto.getDate());

        if (carDto.getOwnerFio() != null)
            ownerRepository.findByFio(carDto.getOwnerFio());

        carRepository.save(car);

        return car;
    }

    @Override
    public Optional<Car> getById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public String parseLogFile(MultipartFile file) {
        List<String> parts = new ArrayList<>();
        try {
            byte[] bytes = file.getBytes();
            String content = new String(bytes, StandardCharsets.UTF_8);
            parts = Arrays.stream(content.split("$")).toList();

        } catch (IOException e) {
            e.printStackTrace();
        }


        LinkedList<Coordinate> list = new LinkedList<>();
        Coordinate coordinate = new Coordinate();

        for (String str: parts){
            if (str.startsWith("GPGGA")){
                String[] part = str.split(",");
                String latitude = part[2];
                double latitudeWithoutDegrees = Double.parseDouble(latitude.substring(2)); // Удаление градусов из широты
                String longitude = part[4];
                double longitudeWithoutDegrees = Double.parseDouble(longitude.substring(3)); // Удаление градусов из долготы
                coordinate.setLatitude(latitudeWithoutDegrees);
                coordinate.setLongitude(longitudeWithoutDegrees);
            } else if (str.startsWith("GNVTG")){
                String[] part = str.split(",");
                String strSpeed = part[7];
                double speed = Double.parseDouble(strSpeed);
                coordinate.setSpeed(speed);
            }

            if (coordinate.getLatitude() != null && coordinate.getLongitude() != null && coordinate.getSpeed() != null){
                int roundedNumber = (int) Math.round(coordinate.getSpeed());
                if (roundedNumber > 0){
                    list.add(coordinate);
                    coordinate.setSpeed(null);
                    coordinate.setLatitude(null);
                    coordinate.setLongitude(null);
                }
            }
        }
        Double distance = 0.0;
        Coordinate first = list.getFirst();
        Coordinate second = new Coordinate();
        for (Coordinate cd: list){
            if (cd.equals(first)){
                continue;
            }
            if (list.indexOf(cd) == 1){
                distance+= calculateDistance(first.getLatitude(),first.getLongitude(),cd.getLatitude(),cd.getLongitude());
                second = cd;
                continue;
            }

            distance+= calculateDistance(second.getLatitude(),second.getLongitude(),cd.getLatitude(),cd.getLongitude());
            second = cd;
        }

        return Math.round(distance) + " км";
    }

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        int R = 6371; // Радиус Земли в километрах
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = R * c;

        return distance;
    }
}

package org.dmitrishkod.springbootapp.controller;

import lombok.AllArgsConstructor;
import org.dmitrishkod.springbootapp.model.dto.DealerDto;
import org.dmitrishkod.springbootapp.model.dto.OwnerDto;
import org.dmitrishkod.springbootapp.model.entity.Dealer;
import org.dmitrishkod.springbootapp.model.entity.Owner;
import org.dmitrishkod.springbootapp.service.CarService;
import org.dmitrishkod.springbootapp.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/owner")
@AllArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;
    private final CarService carService;

    /**
     * Создание нового владельца
     * @param owner
     * @return возвращает владельца без списка машин
     */
    @PostMapping
    public ResponseEntity<OwnerDto> createOwner(@RequestBody OwnerDto owner){
        return ResponseEntity.status(HttpStatus.CREATED).body(Owner.toDto(ownerService.create(owner)));
    }

    /**
     * Удаляет машину по id у владельца, если есть.
     * @param carId
     * @param ownerDto
     * @return
     */
    @DeleteMapping("car/{id}")
    public ResponseEntity deleteCar(@PathVariable(name = "id") Long carId, @RequestBody OwnerDto ownerDto){
        if (carService.getById(carId).isEmpty()){
            Map<String, String> response = new HashMap<>();
            response.put("message", "Машина у владельца с ID " +carId+" не найдена.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        ownerService.deleteCarById(ownerDto, carId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Добавляет машину во владельцу
     * @param carId
     * @param ownerDto
     * @return
     */
    @PutMapping("car/{id}")
    public ResponseEntity<OwnerDto> updateCarList(@PathVariable(name = "id") Long carId,@RequestBody OwnerDto ownerDto){
        return ResponseEntity.ok(Owner.toDto(ownerService.updateCarList(ownerDto, carId)));
    }


    /**
     * Вызов списка всех владельцев ( у каждого также будет вызван список всех машин)
     * @return
     */
    @GetMapping
    public ResponseEntity<List<OwnerDto>> getAll(){
        return ResponseEntity.ok(ownerService.getAll().stream()
                .map(Owner::toDto)
                .toList());
    }

    /**
     * Вызов владельца по id со всеми его данными
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        if (ownerService.getById(id).isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Дилер с ID " + id + " не найден.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(Owner.toDto(ownerService.getById(id).get()));
    }
}

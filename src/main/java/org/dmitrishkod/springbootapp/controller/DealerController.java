package org.dmitrishkod.springbootapp.controller;

import lombok.AllArgsConstructor;
import org.dmitrishkod.springbootapp.model.dto.DealerDto;
import org.dmitrishkod.springbootapp.model.dto.OwnerDto;
import org.dmitrishkod.springbootapp.model.entity.Dealer;
import org.dmitrishkod.springbootapp.model.entity.Owner;
import org.dmitrishkod.springbootapp.service.DealerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dealer")
@AllArgsConstructor
public class DealerController {
    private DealerService dealerService;

    @PostMapping
    public ResponseEntity<DealerDto> createDealer(@RequestBody DealerDto dealer){
        return ResponseEntity.status(HttpStatus.CREATED).body(Dealer.toDto(dealerService.create(dealer)));
    }

    /**
     * Удаление владельца из списка у Диллера, если он есть в списке
     * @param ownerId
     * @param dealerDto
     * @return
     */
    @DeleteMapping("owner/{id}")
    public ResponseEntity deleteOwner(@PathVariable Long ownerId, @RequestBody DealerDto dealerDto){
        if (dealerService.getById(ownerId).isEmpty()){
            Map<String, String> response = new HashMap<>();
            response.put("message", "Владелец авто у диллера с ID " +ownerId+" не найден.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        dealerService.deleteOwnerById(dealerDto, ownerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Добавление в список нового владельца у диллера
     * @param ownerId
     * @param dealerDto
     * @return
     */
    @PutMapping("owner/{id}")
    public ResponseEntity<DealerDto> updateOwnerList(@PathVariable Long ownerId,@RequestBody DealerDto dealerDto){
        return ResponseEntity.ok(Dealer.toDto(dealerService.updateOwnerList(dealerDto, ownerId)));
    }

    /**
     * Вызов списка всех дилеров ( у каждого также будет вызван список всех владельцев, а у них список всех машин)
     * @return
     */
    @GetMapping
    public ResponseEntity<List<DealerDto>> getAll(){
        return ResponseEntity.ok(dealerService.getAll().stream()
                .map(Dealer::toDto)
                .toList());
    }

    /**
     * Вызов дилера по id со всеми его данными
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        if (dealerService.getById(id).isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Дилер с ID " + id + " не найден.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(Dealer.toDto(dealerService.getById(id).get()));
    }
}
